package com.chukanwobi.vehiclemanagementsystem.repository;

import com.chukanwobi.vehiclemanagementsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
