package com.project.controller;

import com.project.domain.Employee;
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
@RequestMapping("/employee")
public class EmployeeController {

    private final IService<Employee> employeeIService;

    public EmployeeController(IService<Employee> employeeIService) {
        this.employeeIService = employeeIService;
    }

    @GetMapping("/list")
    public String listUsers(Model theModel) {
        List<Employee> employees = employeeIService.getAll();
        theModel.addAttribute("employees", employees);
        return "employee/list-employee";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        Employee employee = new Employee();
        theModel.addAttribute("employee", employee);
        System.out.println((employee.getLastName()));
        return "employee/employee-form";
    }

    @PostMapping("/saveEmployee")
    public String saveUser(@Valid @ModelAttribute("employee") Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "employee/employee-form";
        }
        System.out.println(employee);

        //      ИСПРАВИТЬ
        // получить из ui Position
        // передать Position на нижний слой
        employeeIService.save(employee);
        return "redirect:/employee/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("employeeId") int id, Model theModel) {
        Employee employee  = employeeIService.get(id);
        theModel.addAttribute("employee", employee);
        return "employee/employee-form";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("employeeId") int id) {
        employeeIService.delete(id);
        return "redirect:/employee/list";
    }
}
