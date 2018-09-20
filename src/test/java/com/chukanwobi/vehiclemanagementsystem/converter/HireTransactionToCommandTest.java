package com.chukanwobi.vehiclemanagementsystem.converter;

import com.chukanwobi.vehiclemanagementsystem.command.HireTransactionCommand;
import com.chukanwobi.vehiclemanagementsystem.model.Customer;
import com.chukanwobi.vehiclemanagementsystem.model.HireTransaction;
import com.chukanwobi.vehiclemanagementsystem.model.Vehicle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class HireTransactionToCommandTest {
private HireTransactionToCommand hireTransactionConverter;
private static final Long VEHICLE_ID = 2l;
private static final Long CUSTOMER_ID = 1l;
private static final Long RATE_ID = 4l;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
hireTransactionConverter = new HireTransactionToCommand();

    }

    @Test
    public void convert() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(VEHICLE_ID);
        Customer customer = new Customer();
        customer.setId(CUSTOMER_ID);


        HireTransaction hireTransaction = new HireTransaction(customer,vehicle);

        HireTransactionCommand command = hireTransactionConverter.convert(hireTransaction);

        assertEquals(VEHICLE_ID,command.getVehicleId());

        assertEquals(CUSTOMER_ID,command.getCustomerId());

    }
}