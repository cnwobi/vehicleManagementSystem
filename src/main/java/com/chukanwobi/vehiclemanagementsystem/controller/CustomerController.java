package com.chukanwobi.vehiclemanagementsystem.controller;

import com.chukanwobi.vehiclemanagementsystem.model.Customer;
import com.chukanwobi.vehiclemanagementsystem.service.CustomerService;
import com.chukanwobi.vehiclemanagementsystem.service.HireTransactionService;
import com.chukanwobi.vehiclemanagementsystem.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private HireTransactionService hireTransactionService;

    @GetMapping("/home")
    public String getHomePage(Model model){

        model.addAttribute("customer",customerService.getAuthenticatedCustomer()) ;
        model.addAttribute("vehicles",vehicleService.findAll());

        return "customer/home";
    }

    @GetMapping("/hire/vehicle/{vehicleId}")
    public  String hire(@PathVariable String vehicleId, RedirectAttributes redirectAttributes){

        hireTransactionService.saveNewHireByVehicleIdAndCustomerId(Long.valueOf(vehicleId),customerService.getAuthenticatedCustomer().getId());

redirectAttributes.addFlashAttribute("success",true);
        return "redirect:/customer/home";

    }
}
