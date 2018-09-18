package com.chukanwobi.vehiclemanagementsystem;

import com.chukanwobi.vehiclemanagementsystem.model.Customer;
import com.chukanwobi.vehiclemanagementsystem.model.Hire;
import com.chukanwobi.vehiclemanagementsystem.model.Vehicle;
import com.chukanwobi.vehiclemanagementsystem.repository.HireRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
private HireRepository hireRepository;

    public Bootstrap(HireRepository hireRepository) {
        this.hireRepository = hireRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Customer customer = new Customer("Chuka","Nwobi","c.nwobi@outlook.com");
        Vehicle vehicle = new Vehicle("21232Ag","Bentley 2014",null,0);
        Hire hire = new Hire(customer,vehicle);
        hireRepository.save(hire);
    }
}
