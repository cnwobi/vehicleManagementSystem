package com.chukanwobi.vehiclemanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;

@Getter
@Setter
@Entity
public class Hire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
        private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Vehicle vehicle;
    @OneToOne
    private DailyRate dailyRate;
    private Calendar dateHired;
    private Calendar returnedDate;

    private Integer initialOdometerReading;

    public Hire(Customer customer, Vehicle vehicle) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.vehicle.getHires().add(this);
        this. customer.getHires().add(this);
        this.vehicle.setStatus(Status.HIRE);
        this.vehicle.setCurrentlyHiredBy(customer);
        this.customer.setCurrentlyHiring(this.vehicle);
        this.initialOdometerReading=vehicle.getOdometer();
        dateHired = Calendar.getInstance();
    }

    public Hire() {
    }

    public BigDecimal hireCharge(){
        if(returnedDate !=null) {

            long days = ChronoUnit.DAYS.between(dateHired.toInstant(), returnedDate.toInstant());

            return new BigDecimal(days * dailyRate.getCostPerDayInDollars());
        }

        throw new RuntimeException("This hire is not complete yet");
    }

    public Hire hireComplete(){
        this.customer.setCurrentlyHiring(null);
        this.vehicle.setCurrentlyHiredBy(null);
        this.vehicle.setStatus(Status.AVAILABLE);
        this.returnedDate = Calendar.getInstance();
        return this;
    }
}
