package com.project.controller;

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

    private final IService<Customer> customerIService;

    public CustomerController(IService<Customer> customerIService) {
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
        System.out.println(customer.getName());
        System.out.println(customer.getEmail());
        if(result.hasErrors()){
            return "customer/customer-form";
        }
        customerIService.save(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("customerId") int id, Model theModel) {
        Customer customer = customerIService.get(id);
        theModel.addAttribute("customer", customer);
        return "customer/customer-form";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("customerId") int id) {
        customerIService.delete(id);
        return "redirect:/customer/list";
    }
}
