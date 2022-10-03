package com.volvocars.tax.congestion.calculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class TaxCongestionExceptionHandler {

  @ExceptionHandler(CongestionServiceException.class)
  public final ResponseEntity<Object> handleGlobalException(CongestionServiceException e) {
    HttpStatus httpStatus = e.getHttpStatus();
    if (null == e.getHttpStatus()) {
      httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    List<String> details = new ArrayList<>();
    details.add(e.getMessage());
    return new ResponseEntity<>(details, httpStatus);
  }
}
