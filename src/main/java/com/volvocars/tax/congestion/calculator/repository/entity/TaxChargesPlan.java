package com.volvocars.tax.congestion.calculator.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "taxplans")
public class TaxChargesPlan {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "cityUUID", nullable = false)
  private CityEntity cityEntity;

  @Column(name = "endTime", nullable = false)
  private LocalTime endTime;

  @Column(name = "startTime", nullable = false)
  private LocalTime startTime;

  @Column(name = "charges", nullable = false)
  private BigDecimal charges;
}
