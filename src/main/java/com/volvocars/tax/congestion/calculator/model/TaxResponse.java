package com.volvocars.tax.congestion.calculator.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Builder
public class TaxResponse {
    BigDecimal totalTax;

}
