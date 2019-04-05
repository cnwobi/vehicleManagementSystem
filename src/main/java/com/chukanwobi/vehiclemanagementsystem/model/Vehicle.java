package com.chukanwobi.vehiclemanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private String imageUrl;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private Integer odometer=0;
    private Double dailyBaseRate;
    @Enumerated(value = EnumType.STRING)
    private FuelType fuelType;
    @Enumerated(value = EnumType.STRING)
    private Brand brand;
    @Enumerated(value = EnumType.STRING)
    private Transmission transmission;
    private String model;
    private Year modelYear;
    @OneToOne
    private Customer currentlyHiredBy;
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<HireTransaction> hireTransactions = new HashSet<>();




public boolean isAvailable(){
        if(status == Status.AVAILABLE|| status ==null){
            return true;
        }

        return false;
}



/**
 * Enums used by the vehicle class*/
    public enum FuelType {DIESEL,PETROL,GAS}
    public enum Transmission {AUTOMATIC,MANUAL}
    public enum Status {HIRE,AVAILABLE,SERVICE}
    public  enum Brand{BENTLEY,MERCEDES,JAGUAR,PEUGOT,HONDA,TOYOTA,FORD}

    class Tire{
        private Integer size;
        private  String brand;

        public Tire(Integer size, String brand) {
            this.size = size;
            this.brand = brand;
        }

    }
}

