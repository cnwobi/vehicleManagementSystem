package com.chukanwobi.vehiclemanagementsystem.repository;

import com.chukanwobi.vehiclemanagementsystem.model.Customer;
import com.chukanwobi.vehiclemanagementsystem.model.Hire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
@RepositoryRestResource(collectionResourceRel = "hires", path = "hires")
public interface HireRepository extends JpaRepository<Hire,Long> {
    List<Hire> findByCustomer(@Param("customerId") Long customerId);
}
