package com.chukanwobi.vehiclemanagementsystem.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {


    @ExceptionHandler({CustomerException.class,VehicleException.class})
    public ModelAndView handleCustomerAndVehicleException(Exception exception){
        log.debug("handling exception");
        log.debug(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hireError");
        modelAndView.addObject("exception", exception.getMessage());

        return modelAndView;
    }


}
