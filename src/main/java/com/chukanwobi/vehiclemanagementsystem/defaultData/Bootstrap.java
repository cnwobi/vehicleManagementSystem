package com.chukanwobi.vehiclemanagementsystem.defaultData;

import com.chukanwobi.vehiclemanagementsystem.model.Customer;
import com.chukanwobi.vehiclemanagementsystem.model.HireTransaction;
import com.chukanwobi.vehiclemanagementsystem.model.Staff;
import com.chukanwobi.vehiclemanagementsystem.model.Vehicle;
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


        Vehicle vehicle = new Vehicle();
        vehicle.setRegNumber("21232Ag");
        vehicle.setDescription("An iconic British made car...MAke England great again");
        vehicle.setFuelType(Vehicle.FuelType.PETROL);
        vehicle.setBrand(Vehicle.Brand.BENTLEY);
        vehicle.setModel("Mulsanne");
        vehicle.setTransmission(Vehicle.Transmission.AUTOMATIC);
        vehicle.setDailyBaseRate(100.00);

        vehicle.setImageUrl("https://s3.amazonaws.com/carhireapp/bentley.jpg");
        HireTransaction hireTransaction = new HireTransaction(customer,vehicle);

        hireTransactionRepository.save(hireTransaction);



        Vehicle vehicle1 = new Vehicle();
        vehicle1.setRegNumber("RIR836");
        vehicle1.setDescription("Ford Falcon is an iconic car");
        vehicle1.setStatus(Vehicle.Status.AVAILABLE);
        vehicle1.setModel("Ranger");
        vehicle1.setOdometer(200145);
        vehicle1.setTransmission(Vehicle.Transmission.MANUAL);
        vehicle1.setBrand(Vehicle.Brand.FORD);
        vehicle1.setFuelType(Vehicle.FuelType.GAS);
        vehicle1.setDailyBaseRate(500.50);
        vehicleRepository.save(vehicle1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setRegNumber("SRT321");
        vehicle2.setDescription("A premium vehicle");
        vehicle2.setBrand(Vehicle.Brand.HONDA);
        vehicle2.setModel("Accord");
        vehicle2.setOdometer(0);
        vehicle2.setTransmission(Vehicle.Transmission.MANUAL);
        vehicle2.setFuelType(Vehicle.FuelType.PETROL);
        vehicle2.setDailyBaseRate(200.34);

        vehicle2.setImageUrl("https://s3.amazonaws.com/carhireapp/honda.jpg");
        vehicleRepository.save(vehicle2);
    }
}
