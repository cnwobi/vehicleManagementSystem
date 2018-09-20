package com.chukanwobi.vehiclemanagementsystem.converter;

import com.chukanwobi.vehiclemanagementsystem.command.HireTransactionCommand;
import com.chukanwobi.vehiclemanagementsystem.model.HireTransaction;
import org.springframework.core.convert.converter.Converter;

public class HireTransactionToCommand implements Converter<HireTransaction,HireTransactionCommand> {
    @Override
    public HireTransactionCommand convert(HireTransaction hireTransaction) {

        if(hireTransaction== null) {
            return null;
        }

        HireTransactionCommand command = new HireTransactionCommand();
command.setCustomerId(hireTransaction.getCustomer().getId());
command.setVehicleId(hireTransaction.getVehicle().getId());


        return command;
    }
}
