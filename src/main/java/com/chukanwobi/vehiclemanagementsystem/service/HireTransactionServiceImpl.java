package com.chukanwobi.vehiclemanagementsystem.service;

import com.chukanwobi.vehiclemanagementsystem.exception.CustomerException;
import com.chukanwobi.vehiclemanagementsystem.exception.RecordNotFoundException;
import com.chukanwobi.vehiclemanagementsystem.exception.VehicleException;
import com.chukanwobi.vehiclemanagementsystem.model.Customer;
import com.chukanwobi.vehiclemanagementsystem.model.HireTransaction;
import com.chukanwobi.vehiclemanagementsystem.model.Vehicle;
import com.chukanwobi.vehiclemanagementsystem.repository.CustomerRepository;
import com.chukanwobi.vehiclemanagementsystem.repository.HireTransactionRepository;
import com.chukanwobi.vehiclemanagementsystem.repository.VehicleRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class HireTransactionServiceImpl implements HireTransactionService {
    private HireTransactionRepository hireTransactionRepository;
    private VehicleRepository vehicleRepository;
    private CustomerRepository customerRepository;

    public HireTransactionServiceImpl(HireTransactionRepository hireTransactionRepository, VehicleRepository vehicleRepository, CustomerRepository customerRepository) {
        this.hireTransactionRepository = hireTransactionRepository;
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public HireTransaction findHireTransactionById(Long id) {
        HireTransaction hireTransaction = hireTransactionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Transaction with id " + id + " was not found"));
        return hireTransaction;
    }


    @Override
    @Nullable
    public HireTransaction saveNewHireByVehicleIdAndCustomerId(Long vehicleId, Long customerId) throws CustomerException, VehicleException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new RecordNotFoundException("Vehicle with Id " + vehicleId + " does not exist"));
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RecordNotFoundException("Customer with Id " + customerId + " does not exist"));

        if (vehicle.isAvailable() && customer.getCurrentlyHiring() == null) {
            HireTransaction hireTransaction = new HireTransaction(customer, vehicle);
            hireTransactionRepository.save(hireTransaction);
            return hireTransaction;
        } else if (customer.getCurrentlyHiring() != null) {
            throw new CustomerException("Cannot hire a new car as customer with id " + customer.getId() + " has not returned Vehicle with id " + customer.getCurrentlyHiring().getId());
        } else if (!vehicle.isAvailable()) {
            throw new VehicleException("Cannot complete hire current vehicle status is " + vehicle.getStatus().toString());
        }
        return null;
    }

    @Override
    public HireTransaction findHireTransactionByIdAndCompleteTransaction(Long transactionId) {
        return null;
    }
}
