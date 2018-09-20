package com.chukanwobi.vehiclemanagementsystem.service;

import com.chukanwobi.vehiclemanagementsystem.exception.RecordNotFoundException;
import com.chukanwobi.vehiclemanagementsystem.model.vehicle.Vehicle;

public interface VehicleService {
    Vehicle findById(Long vehicleId) throws RecordNotFoundException;

    boolean sendToServicing(Long vehicle) throws RecordNotFoundException;
    boolean servicingComplete(Long vehicleId,Integer odometerReading);

}
