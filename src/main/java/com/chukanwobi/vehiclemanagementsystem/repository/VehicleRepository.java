package com.chukanwobi.vehiclemanagementsystem.repository;

import com.chukanwobi.vehiclemanagementsystem.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
