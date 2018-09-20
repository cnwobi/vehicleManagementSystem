package com.chukanwobi.vehiclemanagementsystem.model.vehicle;

import com.chukanwobi.vehiclemanagementsystem.model.Customer;
import com.chukanwobi.vehiclemanagementsystem.model.HireTransaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String regNumber;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private Integer odometer;
    private Double dailyBaseRate;
    @Enumerated(value = EnumType.STRING)
    private FuelType fuelType;
    @Enumerated(value = EnumType.STRING)
    private Transmission transmission;

    @OneToOne
    private Customer currentlyHiredBy;


    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<HireTransaction> hireTransactions = new HashSet<>();



    public Vehicle(String regNumber, String description, Status status, Integer odometer) {
        this.regNumber = regNumber;
        this.description = description;
        this.status = status;
        this.odometer = odometer;
    }
public boolean isAvailable(){
        if(status == Status.AVAILABLE|| status ==null){
            return true;
        }

        return false;
}








}

