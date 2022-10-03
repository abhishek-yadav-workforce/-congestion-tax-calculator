package com.volvocars.tax.congestion.calculator.service;

import com.volvocars.tax.congestion.calculator.exception.CongestionServiceException;
import com.volvocars.tax.congestion.calculator.repository.CityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CongestionServiceTest {
  @InjectMocks private CongestionTaxService congestionTaxService;

  @Mock private CityRepository cityRepository;

  @Before
  public void setUp() {}

  @Test
  public void getTax() throws CongestionServiceException {}
}
