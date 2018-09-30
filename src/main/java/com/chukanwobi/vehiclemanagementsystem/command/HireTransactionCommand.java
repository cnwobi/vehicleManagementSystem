package com.chukanwobi.vehiclemanagementsystem.command;

import jpos.CashDrawer;
import jpos.Scanner;
import lombok.Data;
@Data
public class HireTransactionCommand {

    private Long id;

    private Long customerId;
    private Long vehicleId;
    private int initialOdometerReading;
    private int finalOdometerReading;


}
