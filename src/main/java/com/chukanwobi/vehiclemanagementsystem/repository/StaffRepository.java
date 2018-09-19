package com.chukanwobi.vehiclemanagementsystem.repository;

import com.chukanwobi.vehiclemanagementsystem.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff,Long> {

    Optional<Staff> findByUsername(String username);
}
