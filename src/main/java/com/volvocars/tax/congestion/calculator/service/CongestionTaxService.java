package com.volvocars.tax.congestion.calculator.service;

import com.volvocars.tax.congestion.calculator.exception.CongestionServiceException;
import com.volvocars.tax.congestion.calculator.model.TaxResponse;
import com.volvocars.tax.congestion.calculator.repository.CityRepository;
import com.volvocars.tax.congestion.calculator.repository.entity.CityEntity;
import com.volvocars.tax.congestion.calculator.repository.entity.TaxChargesPlan;
import com.volvocars.tax.congestion.calculator.util.CalculationUtil;
import com.volvocars.tax.congestion.calculator.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CongestionTaxService implements ICongestionTax {

  @Autowired private CityRepository cityRepository;

  @Override
  public TaxResponse getTax(String cityName, String vehicleType, List<Date> entryTimes)
      throws CongestionServiceException {

    Optional<CityEntity> cityEntity = cityRepository.findByName(cityName);

    ValidationUtil.isValidCityAndVehicleType(cityEntity, vehicleType);

    if (ValidationUtil.isTollFreeVehicle(cityEntity.get(), vehicleType)) {
      return TaxResponse.builder().totalTax(BigDecimal.valueOf(0)).build();
    }
    removeTollFreeDates(cityEntity.get(), entryTimes);

    BigDecimal totalTax = calculateTotalTax(cityEntity.get(), entryTimes);

    return TaxResponse.builder().totalTax(totalTax).build();
  }

  private BigDecimal calculateTotalTax(CityEntity cityEntity, List<Date> entryTimes) {
    CalculationUtil.sortDate(entryTimes);
    BigDecimal totalFees = new BigDecimal(0);
    for (int init = 0; init < entryTimes.size(); init++) {
      BigDecimal startPrice =
          getTollChargesPerPlans(entryTimes.get(init), cityEntity.getTaxChargesPlans());

      for (int end = 1; end < entryTimes.size(); end++) {
        BigDecimal endPrice =
            getTollChargesPerPlans(entryTimes.get(end), cityEntity.getTaxChargesPlans());
        long diffInMilli = entryTimes.get(end).getTime() - entryTimes.get(init).getTime();
        long minutes = diffInMilli / 1000 / 60;
        // To check if the vehicle crossed multiple tolls in 1 hour gap(single charge rule)
        if (minutes <= 60) {
          if (endPrice.compareTo(startPrice) == 1) {
            totalFees = totalFees.add(endPrice);
          }
        } else {
          totalFees = totalFees.add(startPrice);
        }
      }
    }
    return totalFees;
  }

  private BigDecimal getTollChargesPerPlans(Date date, List<TaxChargesPlan> taxChargesPlans) {
    for (TaxChargesPlan taxChargesPlan : taxChargesPlans) {
      LocalTime startTime = taxChargesPlan.getStartTime();
      LocalTime endTime = taxChargesPlan.getEndTime();
      LocalTime calculateTaxTime =
          LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalTime();
      if (!calculateTaxTime.isBefore(startTime) && calculateTaxTime.isBefore(endTime)) {
        return taxChargesPlan.getCharges();
      }
    }
    return new BigDecimal(0);
  }

  private List<Date> removeTollFreeDates(CityEntity cityEntity, List<Date> entryTimes) {
    // Remove dates for weekends
    removeDatesForWeekendDates(cityEntity, entryTimes);
    // Remove dates from holiday months
    removeDatesFromNoTexMonth(cityEntity, entryTimes);
    // Remove date of public holidays
    removeDatesForPublicHolidays(cityEntity, entryTimes);
    return entryTimes;
  }

  private void removeDatesForPublicHolidays(CityEntity cityEntity, List<Date> entryTimes) {
    List<Date> datesToRemove = new ArrayList<>();
    cityEntity
        .getPublicHolidays()
        .forEach(
            date ->
                entryTimes.stream()
                    .filter(
                        date1 ->
                            CalculationUtil.checkPublicHolidays(
                                date, date, cityEntity.isDayBeforeConcession()))
                    .forEach(date1 -> datesToRemove.add(date)));
    entryTimes.removeAll(datesToRemove);
  }

  private List<Date> removeDatesForWeekendDates(CityEntity cityEntity, List<Date> entryTimes) {

    List<Date> datesToRemove = new ArrayList<>();
    cityEntity
        .getWeekendDays()
        .forEach(
            weekendDay -> {
              entryTimes.stream()
                  .filter(date -> CalculationUtil.checkWeekend(weekendDay, date))
                  .forEach(date -> datesToRemove.add(date));
            });
    entryTimes.removeAll(datesToRemove);
    return datesToRemove;
  }

  private void removeDatesFromNoTexMonth(CityEntity cityEntity, List<Date> entryTimes) {

    List<Date> datesToRemove = new ArrayList<>();
    List<String> noTaxMonths = cityEntity.getNoTaxMonths();
    noTaxMonths.forEach(
        noTaxMonth ->
            entryTimes.stream()
                .filter(date -> CalculationUtil.checkNoTaxMonth(noTaxMonth, date))
                .forEach(date -> datesToRemove.add(date)));
    entryTimes.removeAll(datesToRemove);
  }
}
