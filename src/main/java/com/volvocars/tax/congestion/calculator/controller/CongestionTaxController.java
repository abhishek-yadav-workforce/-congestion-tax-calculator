package com.volvocars.tax.congestion.calculator.controller;

import com.volvocars.tax.congestion.calculator.exception.CongestionServiceException;
import com.volvocars.tax.congestion.calculator.model.TaxRequest;
import com.volvocars.tax.congestion.calculator.model.TaxResponse;
import com.volvocars.tax.congestion.calculator.service.CongestionTaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/calculate-tax")
public class CongestionTaxController {

  @Autowired
  private CongestionTaxService congestionTaxService;

  @PostMapping
  public ResponseEntity<TaxResponse> calculateCongestionTax(@Valid @RequestBody TaxRequest request) throws CongestionServiceException {
    TaxResponse tax = congestionTaxService.getTax(request.getCityName(), request.getVehicleType(), request.getEntryTimes());
    return new ResponseEntity<>(tax, HttpStatus.OK);
  }
}
