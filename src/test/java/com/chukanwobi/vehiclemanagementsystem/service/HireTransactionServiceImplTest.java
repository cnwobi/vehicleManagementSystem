package com.chukanwobi.vehiclemanagementsystem.service;

import com.chukanwobi.vehiclemanagementsystem.exception.CustomerException;
import com.chukanwobi.vehiclemanagementsystem.exception.VehicleException;
import com.chukanwobi.vehiclemanagementsystem.model.Customer;
import com.chukanwobi.vehiclemanagementsystem.model.HireTransaction;
import com.chukanwobi.vehiclemanagementsystem.model.Vehicle;
import com.chukanwobi.vehiclemanagementsystem.repository.CustomerRepository;
import com.chukanwobi.vehiclemanagementsystem.repository.HireTransactionRepository;
import com.chukanwobi.vehiclemanagementsystem.repository.VehicleRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HireTransactionServiceImplTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private HireTransactionService hireTransactionService;
    @Mock
    private HireTransactionRepository hireTransactionRepository;
    @Mock
    private VehicleRepository vehicleRepository;
    @Mock
    private CustomerRepository customerRepository;
    private Vehicle vehicle;

    private Vehicle oldHire;
    private Customer customer;
    private HireTransaction hireTransaction;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        hireTransactionService = new HireTransactionServiceImpl(hireTransactionRepository, vehicleRepository, customerRepository);
        customer = new Customer();
        customer.setId(2l);
        oldHire = new Vehicle();
        oldHire.setId(3l);
        customer.setCurrentlyHiring(oldHire);
        vehicle = new Vehicle();
        vehicle.setId(4l);
    }

    @Test
    public void findHireTransactionById() {
        hireTransaction = new HireTransaction();
        when(hireTransactionRepository.findById(any())).thenReturn(Optional.of(hireTransaction));

        assertEquals(hireTransaction, hireTransactionService.findHireTransactionById(2l));
        verify(hireTransactionRepository, times(1)).findById(2l);

    }

    @Test
    public void testCustomerAttemptsToHireVehicleBeforeReturningOldVehicle() throws CustomerException {

        when(vehicleRepository.findById(any())).thenReturn(Optional.of(vehicle));
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        thrown.expect(CustomerException.class);
        thrown.expectMessage("Cannot hire a new car as customer with id 2 has not returned Vehicle with id 3");
        hireTransactionService.saveNewHireByVehicleIdAndCustomerId(4l, 2l);
    }
@Test
    public void testVehicleStatusHireForNewHire() throws VehicleException{
        vehicle.setStatus(Vehicle.Status.HIRE);
        customer.setCurrentlyHiring(null);
        when(vehicleRepository.findById(any())).thenReturn(Optional.of(vehicle));
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        thrown.expect(VehicleException.class);
        thrown.expectMessage("Cannot complete hire current vehicle status is HIRE");
        hireTransactionService.saveNewHireByVehicleIdAndCustomerId(4l, 2l);

    }

    @Test
    public void testVehicleStatusServiceForNewHire() throws VehicleException {

        vehicle.setStatus(Vehicle.Status.SERVICE);
        customer.setCurrentlyHiring(null);
        when(vehicleRepository.findById(any())).thenReturn(Optional.of(vehicle));
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        thrown.expect(VehicleException.class);
        thrown.expectMessage("Cannot complete hire current vehicle status is SERVICE");
        hireTransactionService.saveNewHireByVehicleIdAndCustomerId(4l, 2l);

    }

    @Test
    public void successfulServiceForNewHire(){
        vehicle.setStatus(Vehicle.Status.AVAILABLE);
        customer.setCurrentlyHiring(null);
        when(vehicleRepository.findById(any())).thenReturn(Optional.of(vehicle));
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
       HireTransaction hireTransaction = hireTransactionService.saveNewHireByVehicleIdAndCustomerId(4l, 2l);
            assertNotNull(hireTransaction);
            assertEquals(vehicle.getId(),hireTransaction.getVehicle().getId());
            assertEquals(customer.getId(),hireTransaction.getCustomer().getId());
    }

}