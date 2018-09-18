package com.chukanwobi.vehiclemanagementsystem.repository;

import com.chukanwobi.vehiclemanagementsystem.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyRateRepository extends JpaRepository<Rate,Long> {
}
