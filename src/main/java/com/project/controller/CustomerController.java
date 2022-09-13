package com.project.controller;

import com.project.service.IService;
import com.project.util.form.CustomerForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

@Controller
@RequestMapping("/customer")
@Api(value = "CustomerController", description = "crud operation for customer table")
public class CustomerController {

    @Autowired
    private  IService<CustomerForm, Long> customerIService;


    @GetMapping("/list")
    @ApiOperation(value = "list of customers", notes="get list of customers from customer table", produces = ".jsp")
    public String listCustomers(Model theModel) {
        List<CustomerForm> customers = customerIService.getAll();
        theModel.addAttribute("customers", customers);
        return "customer/list-customer";
    }

    @GetMapping("/showForm")
    @ApiOperation(value = "form for adding", notes="get form for adding new customer in customer table")
    public String showFormForAdd(Model theModel) {
        CustomerForm customer = new CustomerForm();
        theModel.addAttribute("customer", customer);
        return "customer/customer-form";
    }

    @PostMapping("/saveCustomer")
    @ApiOperation(value = "save", notes="save new customer in customer table")
    public String saveCustomer(@Valid @ModelAttribute("customer") CustomerForm customer, BindingResult result) {
        if(result.hasErrors()){
            return "customer/customer-form";
        }
        customerIService.save(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/updateForm")
    @ApiOperation(value = "form for update", notes="get form for update existing customer in customer table")
    public String showFormForUpdate(@RequestParam("customerId") Long id, Model theModel) {
        CustomerForm customer = customerIService.get(id);
        theModel.addAttribute("customer", customer);
        return "customer/update-customer-form";
    }

    @PostMapping("/updateCustomer")
    @ApiOperation(value = "update", notes="update existing customer in customer table")
    public String updateCustomer(@Valid @ModelAttribute("customer") CustomerForm customer, BindingResult result) {
        if(result.hasErrors()){
            return "customer/customer-form";
        }
        customerIService.update(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/delete")
    @ApiOperation(value = "delete", notes="delete existing customer in customer table, using his id")
    public String deleteCustomer(@RequestParam("customerId") Long id) {
        customerIService.delete(id);
        return "redirect:/customer/list";
    }
}
