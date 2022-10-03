package com.volvocars.tax.congestion.calculator.util;

import com.volvocars.tax.congestion.calculator.exception.CongestionServiceException;
import com.volvocars.tax.congestion.calculator.repository.entity.CityEntity;

import java.util.Optional;

public class ValidationUtil {

  public static void isValidCityAndVehicleType(Optional<CityEntity> cityEntity, String vehicleType)
      throws CongestionServiceException {
    if (!(cityEntity.isPresent() && cityEntity.get().getVehicleTypes().contains(vehicleType)))
      throw new CongestionServiceException("Incorrect city name and vehicle type.");
  }

  public static boolean isTollFreeVehicle(CityEntity cityEntity, String vehicleType) {
    if (cityEntity == null && cityEntity.getVehicleTypes() == null) return false;
    return vehicleType.equals(cityEntity.getVehicleTypes());
  }
}
