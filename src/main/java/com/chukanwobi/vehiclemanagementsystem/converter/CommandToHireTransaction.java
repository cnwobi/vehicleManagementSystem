package com.chukanwobi.vehiclemanagementsystem.converter;

import com.chukanwobi.vehiclemanagementsystem.command.HireTransactionCommand;
import com.chukanwobi.vehiclemanagementsystem.model.HireTransaction;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommandToHireTransaction implements Converter<HireTransactionCommand,HireTransaction> {
    @Override
    public HireTransaction convert(HireTransactionCommand hireTransactionCommand) {
        return null;
    }
}
