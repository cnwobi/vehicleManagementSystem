package com.chukanwobi.vehiclemanagementsystem.repository;

import com.chukanwobi.vehiclemanagementsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByUsername(String userName);

}
