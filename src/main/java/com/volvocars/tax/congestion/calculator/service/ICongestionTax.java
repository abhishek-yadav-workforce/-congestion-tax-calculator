package com.volvocars.tax.congestion.calculator.service;

import com.volvocars.tax.congestion.calculator.exception.CongestionServiceException;
import com.volvocars.tax.congestion.calculator.model.TaxResponse;
import com.volvocars.tax.congestion.calculator.model.Vehicle;

import java.util.Date;
import java.util.List;

public interface ICongestionTax{
    public TaxResponse getTax(String cityName,String vehicleType, List<Date> entryTimes) throws CongestionServiceException;
}
