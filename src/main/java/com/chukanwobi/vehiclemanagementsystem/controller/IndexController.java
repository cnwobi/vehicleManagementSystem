package com.chukanwobi.vehiclemanagementsystem.controller;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller

public class IndexController {

    @GetMapping({"", "/", "/index"})
    public String getIndex(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_STAFF")){
            return "redirect:/staff/home";
        }
        if(request.isUserInRole("ROLE_ADMIN")){
            return "redirect:/admin/home";
        }
        if(request.isUserInRole("ROLE_CUSTOMER")){
            return "redirect:/customer/home";
        }
        return "/error";
    }
}
