package com.project.controller;

import com.project.domain.Customer;
import com.project.domain.Customer;
import com.project.service.IService;
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
public class CustomerController {

    private final IService<Customer, Long> customerIService;

    public CustomerController(IService<Customer, Long> customerIService) {
        this.customerIService = customerIService;
    }

    @GetMapping("/list")
    public String listUsers(Model theModel) {
        List<Customer> customers = customerIService.getAll();
        theModel.addAttribute("customers", customers);
        return "customer/list-customer";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        Customer customer = new Customer();
        theModel.addAttribute("customer", customer);
        return "customer/customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveUser(@Valid @ModelAttribute("customer") Customer customer, BindingResult result) {
        if(result.hasErrors()){
            return "customer/customer-form";
        }
        customerIService.save(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("customerId") Long id, Model theModel) {
        Customer customer = customerIService.get(id);
        theModel.addAttribute("customer", customer);
        return "customer/update-customer-form";
    }

    @PostMapping("/updateCustomer")
    public String updateUser(@Valid @ModelAttribute("customer") Customer customer, BindingResult result) {
        if(result.hasErrors()){
            return "customer/customer-form";
        }
        customerIService.update(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("customerId") Long id) {
        customerIService.delete(id);
        return "redirect:/customer/list";
    }
}
