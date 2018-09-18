package com.chukanwobi.vehiclemanagementsystem.repository;

import com.chukanwobi.vehiclemanagementsystem.model.HireTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

public interface HireTransactionRepository extends JpaRepository<HireTransaction,Long> {
    List<HireTransaction> findByCustomer(@Param("customerId") Long customerId);
}
