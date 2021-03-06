package com.chukanwobi.vehiclemanagementsystem.service;

import com.chukanwobi.vehiclemanagementsystem.exception.RecordNotFoundException;
import com.chukanwobi.vehiclemanagementsystem.exception.VehicleException;

import com.chukanwobi.vehiclemanagementsystem.model.Vehicle;
import com.chukanwobi.vehiclemanagementsystem.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    private VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;    }

    @Override
    public Vehicle findById(Long vehicleId) throws RecordNotFoundException {
        return vehicleRepository.findById(vehicleId).orElseThrow(() -> new RecordNotFoundException("Vehicle with ID " + vehicleId + " was not found"));
    }




    @Override
    public boolean sendToServicing(Long vehicleId) throws RecordNotFoundException,VehicleException {
        Vehicle vehicle = findById(vehicleId);
        if (vehicle.isAvailable()) {
            vehicle.setStatus(Vehicle.Status.SERVICE);
            return true;        }
        throw new VehicleException("Vehicle not available for service");
    }

    @Override
    public boolean servicingComplete(Long vehicleId, Integer odometer) {
        return false;
    }

    @Override
    public List<Vehicle> findAll() {

        return vehicleRepository.findAll();
    }
}
