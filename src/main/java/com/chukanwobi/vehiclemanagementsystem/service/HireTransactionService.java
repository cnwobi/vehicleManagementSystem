package com.chukanwobi.vehiclemanagementsystem.service;

import com.chukanwobi.vehiclemanagementsystem.exception.CustomerException;
import com.chukanwobi.vehiclemanagementsystem.model.HireTransaction;

public interface HireTransactionService {

HireTransaction findHireTransactionById(Long id);
HireTransaction saveNewHireByVehicleIdAndCustomerId(Long vehicleId,Long customerId) throws CustomerException;

HireTransaction findHireTransactionByIdAndCompleteTransaction(Long transactionId);
}
