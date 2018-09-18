package com.chukanwobi.vehiclemanagementsystem.service;

import com.chukanwobi.vehiclemanagementsystem.model.Status;
import com.chukanwobi.vehiclemanagementsystem.model.Vehicle;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Override
    public boolean checkHireAvailability(Vehicle vehicle) {
        if(vehicle.getStatus()==Status.AVAILABLE|| vehicle.getStatus() ==null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean service(Vehicle vehicle) throws Exception{
        if(vehicle.getStatus()==Status.AVAILABLE|| vehicle.getStatus()==null){
            vehicle.setStatus(Status.SERVICE);
            return true;
        }
        throw new RuntimeException("Vehicle not available for service");
    }

    @Override
    public boolean serviceComplete(Vehicle vehicle, Integer odo) {
        return false;
    }


}
