package com.chukanwobi.vehiclemanagementsystem.service;

import com.chukanwobi.vehiclemanagementsystem.model.HireTransaction;

public interface HireTransactionService {
boolean hire(String hirerId);
HireTransaction findHireTransactionById(Long id);
}
