package com.volvocars.tax.congestion.calculator.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "city")
public class CityEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cityUUID;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Column(name = "exemptTaxVehicle", nullable = false)
  @ElementCollection(targetClass=String.class)
  private List<String> exemptTaxVehicle;

  @Column(name = "weekendDays", nullable = false)
  @ElementCollection(targetClass=String.class)
  private List<String> weekendDays;

  @Column(name = "publicHolidays", nullable = false)
  @ElementCollection(targetClass=Date.class)
  private List<Date> publicHolidays;

  @Column(name = "dayBeforeConcession", nullable = false)
  private boolean dayBeforeConcession;

  @Column(name = "noTaxMonths", nullable = false)
  @ElementCollection(targetClass=String.class)
  private List<String> noTaxMonths;

  @Column(name = "vehicleTypes", nullable = false)
  @ElementCollection(targetClass=String.class)
  private List<String> vehicleTypes;

  @OneToMany(mappedBy = "cityEntity")
  private List<TaxChargesPlan> taxChargesPlans;
}
