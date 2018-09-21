package com.chukanwobi.vehiclemanagementsystem.service;

import com.chukanwobi.vehiclemanagementsystem.exception.RecordNotFoundException;
import com.chukanwobi.vehiclemanagementsystem.model.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle findById(Long vehicleId) throws RecordNotFoundException;

    boolean sendToServicing(Long vehicle) throws RecordNotFoundException;
    boolean servicingComplete(Long vehicleId,Integer odometerReading);
    List<Vehicle> findAll();

}
