package com.chukanwobi.vehiclemanagementsystem.defaultData;

import com.chukanwobi.vehiclemanagementsystem.model.*;
import com.chukanwobi.vehiclemanagementsystem.model.vehicle.FuelType;
import com.chukanwobi.vehiclemanagementsystem.model.vehicle.Status;
import com.chukanwobi.vehiclemanagementsystem.model.vehicle.Transmission;
import com.chukanwobi.vehiclemanagementsystem.model.vehicle.Vehicle;
import com.chukanwobi.vehiclemanagementsystem.repository.CustomerRepository;
import com.chukanwobi.vehiclemanagementsystem.repository.HireTransactionRepository;
import com.chukanwobi.vehiclemanagementsystem.repository.StaffRepository;
import com.chukanwobi.vehiclemanagementsystem.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
private HireTransactionRepository hireTransactionRepository;
@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;
@Autowired
private VehicleRepository vehicleRepository;
@Autowired
private StaffRepository staffRepository;

@Autowired
private CustomerRepository customerRepository;
    public Bootstrap(HireTransactionRepository hireTransactionRepository) {
        this.hireTransactionRepository = hireTransactionRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Customer customer = new Customer("Chuka","Nwobi","c.nwobi@outlook.com","c.nwobi");
        customer.setPassword(bCryptPasswordEncoder.encode("password"));


        Customer customer1 = new Customer("Zuby","Zabeleta","z.zabeleta@outlook.com","z.zabeleta");
        customer1.setPassword(bCryptPasswordEncoder.encode("password"));
           customerRepository.save(customer1);
        Staff staff = new Staff();
        staff.setUsername("d.trump");
        staff.setEmail("d.trump@gov.us");
        staff.setPassword(bCryptPasswordEncoder.encode("password"));
        staff.setFirstName("Donald");
        staff.setLastName("Trump");
       staffRepository.save(staff);


        Vehicle vehicle = new Vehicle("21232Ag","Bentley 2014",null,0);
        vehicle.setFuelType(FuelType.PETROL);
        vehicle.setTransmission(Transmission.AUTOMATIC);
        HireTransaction hireTransaction = new HireTransaction(customer,vehicle);

        hireTransactionRepository.save(hireTransaction);



        Vehicle vehicle1 = new Vehicle("RIR836","Ford Falcon", Status.AVAILABLE,200145);
        vehicle1.setTransmission(Transmission.MANUAL);
        vehicle1.setFuelType(FuelType.GAS);
        vehicleRepository.save(vehicle1);
    }
}
