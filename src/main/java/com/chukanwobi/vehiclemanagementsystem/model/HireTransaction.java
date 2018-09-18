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
public class HireTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Vehicle vehicle;
    @OneToOne(cascade = CascadeType.ALL)
    private Rate rate;
    private Calendar dateHired;
    private Calendar returnedDate;

    private BigDecimal charge;

    private Integer initialOdometerReading;
    private Integer finalOdometerReading;

    public HireTransaction(Customer customer, Vehicle vehicle) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.vehicle.getHireTransactions().add(this);
        this.customer.getHireTransactions().add(this);
        this.vehicle.setStatus(Status.HIRE);
        this.vehicle.setCurrentlyHiredBy(customer);
        this.customer.setCurrentlyHiring(this.vehicle);
        this.initialOdometerReading = vehicle.getOdometer();
        dateHired = Calendar.getInstance();
    }

    public HireTransaction(Customer customer, Vehicle vehicle, Rate rate) {
        this(customer, vehicle);
        this.rate = rate;
    }

    public HireTransaction() {
    }




    public BigDecimal hireCharge() {
        if (returnedDate != null) {
            long days = ChronoUnit.DAYS.between(dateHired.toInstant(), returnedDate.toInstant());
            charge = new BigDecimal(days * rate.getCostPerDayInDollars());
            return charge;
        }
        throw new RuntimeException("This hire is not complete yet");
    }

    public HireTransaction hireComplete() {
        this.customer.setCurrentlyHiring(null);
        this.vehicle.setCurrentlyHiredBy(null);
        this.vehicle.setStatus(Status.AVAILABLE);
        this.returnedDate = Calendar.getInstance();
        return this;
    }
}
