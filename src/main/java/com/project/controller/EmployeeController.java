package com.project.controller;

import com.project.util.form.EmployeeForm;
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

    private final IService<EmployeeForm, Long> employeeIService;

    public EmployeeController(IService<EmployeeForm, Long> employeeIService) {
        this.employeeIService = employeeIService;

    }

    @GetMapping("/list")
    public String listEmployees(Model theModel) {
        List<EmployeeForm> employees = employeeIService.getAll();
        theModel.addAttribute("employees", employees);
        return "employee/list-employee";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        EmployeeForm employeeModel = new EmployeeForm();
        theModel.addAttribute("employeeModel", employeeModel);
        return "employee/employee-form";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("employeeModel") EmployeeForm employeeModel, BindingResult result) {
        if (result.hasErrors()) {
            return "employee/employee-form";
        }
       employeeIService.save(employeeModel);
        return "redirect:/employee/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("employeeId") Long id, Model theModel) {
        EmployeeForm employee  = employeeIService.get(id);
        theModel.addAttribute("employee", employee);
        return "employee/update-employee-form";
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(@Valid @ModelAttribute("employee") EmployeeForm employeeModel, BindingResult result) {
        if(result.hasErrors()){
            return "customer/customer-form";
        }
        employeeIService.update(employeeModel);
        return "redirect:/employee/list";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") Long id) {
        employeeIService.delete(id);
        return "redirect:/employee/list";
    }
}
