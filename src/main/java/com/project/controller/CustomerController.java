package com.project.controller;

import com.project.domain.Customer;
import com.project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listUsers(Model theModel) {
        List<Customer> theUsers = customerService.getAll();
        theModel.addAttribute("users", theUsers);
        return "customer/list-customer";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        Customer theUser = new Customer();
        theModel.addAttribute("user", theUser);
        return "customer/customer-form";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") Customer customer) {
        customerService.save(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("userId") int id,
                                    Model theModel) {
        Customer customer = customerService.get(id);
        theModel.addAttribute("user", customer);
        return "customer/customer-form";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") int id) {
        customerService.delete(id);
        return "redirect:/customer/list";
    }
}
