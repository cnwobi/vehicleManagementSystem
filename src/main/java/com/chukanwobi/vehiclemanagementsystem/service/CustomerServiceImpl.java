package com.chukanwobi.vehiclemanagementsystem.service;

import com.chukanwobi.vehiclemanagementsystem.exception.RecordNotFoundException;
import com.chukanwobi.vehiclemanagementsystem.model.Customer;
import com.chukanwobi.vehiclemanagementsystem.repository.CustomerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getAuthenticatedCustomer() {
   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return customerRepository.findByUsername(auth.getName()).orElseThrow(()-> new RecordNotFoundException());
    }
}
