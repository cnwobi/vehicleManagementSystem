package com.chukanwobi.vehiclemanagementsystem.defaultData;

import com.chukanwobi.vehiclemanagementsystem.model.*;
import com.chukanwobi.vehiclemanagementsystem.repository.HireTransactionRepository;
import com.chukanwobi.vehiclemanagementsystem.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
private HireTransactionRepository hireTransactionRepository;

@Autowired
private VehicleRepository vehicleRepository;

    public Bootstrap(HireTransactionRepository hireTransactionRepository) {
        this.hireTransactionRepository = hireTransactionRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Customer customer = new Customer("Chuka","Nwobi","c.nwobi@outlook.com");
        Vehicle vehicle = new Vehicle("21232Ag","Bentley 2014",null,0);
        HireTransaction hireTransaction = new HireTransaction(customer,vehicle);
        Rate rate = new Rate("Standard Daily Rate", 12.5);
        hireTransaction.setRate(rate);
        hireTransactionRepository.save(hireTransaction);

        Vehicle vehicle1 = new Vehicle("RIR836","Ford Falcon", Status.AVAILABLE,200145);
        vehicleRepository.save(vehicle1);
    }
}
