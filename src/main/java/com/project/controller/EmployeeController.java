package com.project.controller;

import com.project.domain.Customer;
import com.project.domain.Employee;
import com.project.domain.Project;
import com.project.service.CustomerService;
import com.project.service.EmployeeService;
import com.project.service.IService;
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
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/list")
    public String listUsers(Model theModel) {
        List<Employee> theUsers = employeeService.getAll();
        theModel.addAttribute("users", theUsers);
        return "employee/list-employee";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        Employee theUser = new Employee();
        theModel.addAttribute("user", theUser);
        System.out.println((theUser.getLastName()));
        return "employee/employee-form";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") Employee employee) {
        System.out.println(employee);
        employeeService.save(employee);
        return "redirect:/employee/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("userId") int id,
                                    Model theModel) {
        Employee employee  = employeeService.get(id);
        theModel.addAttribute("user", employee);
        return "employee/employee-form";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") int id) {
        employeeService.delete(id);
        return "redirect:/employee/list";
    }
}
