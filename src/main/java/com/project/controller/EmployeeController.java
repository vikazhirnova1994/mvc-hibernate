package com.project.controller;

import com.project.util.form.EmployeeForm;
import com.project.service.IService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "CustomerController", description = "crud operation for employee table")
public class EmployeeController {

    private final IService<EmployeeForm, Long> employeeIService;

    public EmployeeController(IService<EmployeeForm, Long> employeeIService) {
        this.employeeIService = employeeIService;

    }

    @GetMapping("/list")
    @ApiOperation(value = "list of employee", notes="get list of employee from employee table", produces = ".jsp")
    public String listEmployees(Model theModel) {
        List<EmployeeForm> employees = employeeIService.getAll();
        theModel.addAttribute("employees", employees);
        return "employee/list-employee";
    }

    @GetMapping("/showForm")
    @ApiOperation(value = "form for adding", notes="get form for adding new employee in employee table")
    public String showFormForAdd(Model theModel) {
        EmployeeForm employeeModel = new EmployeeForm();
        theModel.addAttribute("employeeModel", employeeModel);
        return "employee/employee-form";
    }

    @PostMapping("/saveEmployee")
    @ApiOperation(value = "save", notes="save new employee in employee table")
    public String saveEmployee(@Valid @ModelAttribute("employeeModel") EmployeeForm employeeModel, BindingResult result) {
        if (result.hasErrors()) {
            return "employee/employee-form";
        }
       employeeIService.save(employeeModel);
        return "redirect:/employee/list";
    }

    @GetMapping("/updateForm")
    @ApiOperation(value = "form for update", notes="get form for update existing employee in employee table")
    public String showFormForUpdate(@RequestParam("employeeId") Long id, Model theModel) {
        EmployeeForm employee  = employeeIService.get(id);
        theModel.addAttribute("employee", employee);
        return "employee/update-employee-form";
    }

    @PostMapping("/updateEmployee")
    @ApiOperation(value = "update", notes="update existing employee in employee table")
    public String updateEmployee(@Valid @ModelAttribute("employee") EmployeeForm employeeModel, BindingResult result) {
        if(result.hasErrors()){
            return "customer/customer-form";
        }
        employeeIService.update(employeeModel);
        return "redirect:/employee/list";
    }

    @GetMapping("/delete")
    @ApiOperation(value = "delete", notes="delete existing employee in employee table, using his id")
    public String deleteEmployee(@RequestParam("employeeId") Long id) {
        employeeIService.delete(id);
        return "redirect:/employee/list";
    }
}
