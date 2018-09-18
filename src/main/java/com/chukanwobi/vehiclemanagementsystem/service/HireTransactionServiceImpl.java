package com.chukanwobi.vehiclemanagementsystem.service;

import com.chukanwobi.vehiclemanagementsystem.exception.RecordNotFoundException;
import com.chukanwobi.vehiclemanagementsystem.model.HireTransaction;
import com.chukanwobi.vehiclemanagementsystem.repository.HireTransactionRepository;

public class HireTransactionServiceImpl implements HireTransactionService {
private HireTransactionRepository hireTransactionRepository;

    @Override
    public boolean hire(String hirerId) {
        return false;
    }

    @Override
    public HireTransaction findHireTransactionById(Long id) {
HireTransaction hireTransaction = hireTransactionRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Transaction with id "+ id +" was not found"));
        return hireTransaction;
    }
}
