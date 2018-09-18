package com.chukanwobi.vehiclemanagementsystem.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Entity
public class DailyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double costPerDayInDollars;

    public DailyRate(String description, Double costPerDayInDollars) {
        this.description = description;
        this.costPerDayInDollars = costPerDayInDollars;
    }

}
