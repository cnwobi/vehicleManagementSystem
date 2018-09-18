package com.chukanwobi.vehiclemanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String regNumber;
    private String description;
    private Status status;
    private Integer odometer;


    @OneToOne
    private Customer currentlyHiredBy;


    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<HireTransaction> hireTransactions = new HashSet<>();

    public Vehicle() {
    }

    public Vehicle(String regNumber, String description, Status status, Integer odometer) {
        this.regNumber = regNumber;
        this.description = description;
        this.status = status;
        this.odometer = odometer;
    }









}

