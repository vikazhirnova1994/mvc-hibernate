package com.project.controller;

import com.project.util.model.EmployeeModel;
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

    private final IService<EmployeeModel, Long> employeeIService;

    public EmployeeController(IService<EmployeeModel, Long> employeeIService) {
        this.employeeIService = employeeIService;
    }

    @GetMapping("/list")
    public String listUsers(Model theModel) {
        List<EmployeeModel> employees = employeeIService.getAll();
        theModel.addAttribute("employees", employees);
        return "employee/list-employees";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        EmployeeModel employeeModel = new EmployeeModel();
        theModel.addAttribute("employeeModel", employeeModel);
        return "employee/employee-form";
    }

    @PostMapping("/saveEmployee")
    public String saveUser(@Valid @ModelAttribute("employeeModel") EmployeeModel employeeModel, BindingResult result) {
        if (result.hasErrors()) {
            return "employee/employee-form";
        }
       employeeIService.save(employeeModel);
        return "redirect:/employee/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("employeeId") Long id, Model theModel) {
        EmployeeModel employee  = employeeIService.get(id);
        theModel.addAttribute("employee", employee);
        return "employee/employee-form";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("employeeId") Long id) {
        employeeIService.delete(id);
        return "redirect:/employee/list";
    }
}
