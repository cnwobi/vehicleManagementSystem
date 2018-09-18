package com.chukanwobi.vehiclemanagementsystem.service;

import com.chukanwobi.vehiclemanagementsystem.model.Vehicle;

public interface VehicleService {
    boolean checkHireAvailability(Vehicle vehicle) ;
    boolean service(Vehicle vehicle) throws Exception;
    boolean serviceComplete(Vehicle vehicle,Integer integer);

}
