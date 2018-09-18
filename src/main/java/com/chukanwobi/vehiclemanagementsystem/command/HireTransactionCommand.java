package com.chukanwobi.vehiclemanagementsystem.command;

import com.chukanwobi.vehiclemanagementsystem.model.Rate;
import lombok.Data;
@Data
public class HireTransactionCommand {

    private Long id;

    private Long customerId;

    private Long vehicleId;

    private Long rateId;


    private int initialOdometerReading;
    private int finalOdometerReading;
}
