package com.volvocars.tax.congestion.calculator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@Builder
public class TaxRequest {

  @NotBlank(message = "Vehicle type cannot be blank or null.")
  private String vehicleType;

  @NotNull
  @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
  private List<Date> entryTimes;

  @NotBlank(message = "City name cannot be blank or null.")
  private String cityName;

}
