package com.volvocars.tax.congestion.calculator.controller;

import com.volvocars.tax.congestion.calculator.exception.CongestionServiceException;
import com.volvocars.tax.congestion.calculator.model.TaxRequest;
import com.volvocars.tax.congestion.calculator.model.TaxResponse;
import com.volvocars.tax.congestion.calculator.service.CongestionTaxService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CongestionTaxControllerTest {

    @InjectMocks
    private CongestionTaxController congestionTaxController;

    @Mock
    private CongestionTaxService congestionTaxService;

    @Before
    public void setUp(){

    }
    @Test
    public void testCongestionTax() throws CongestionServiceException {
        TaxRequest request = TaxRequest.builder().cityName("gothenburg").vehicleType("CAR").entryTimes(new ArrayList<>()).build();
        TaxResponse response = TaxResponse.builder().totalTax(BigDecimal.valueOf(0)).build();
        when(congestionTaxService.getTax(request.getCityName(),request.getVehicleType(),request.getEntryTimes())).thenReturn(response);
        congestionTaxController.calculateCongestionTax(request);
    }

}
