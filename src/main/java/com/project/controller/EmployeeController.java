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
    public String formForAddEmployee(Model theModel) {
        EmployeeForm employeeForm = new EmployeeForm();
        theModel.addAttribute("employeeModel", employeeForm);
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
    public String formForUpdateEmployee(@RequestParam("employeeId") Long id, Model theModel) {
        EmployeeForm employee  = employeeIService.get(id);
        theModel.addAttribute("employee", employee);
        return "employee/update-employee-form";
    }

    @PutMapping("/updateEmployee")
    @ApiOperation(value = "update", notes="update existing employee in employee table")
    public String updateEmployee(@Valid @ModelAttribute("employee") EmployeeForm employeeForm, BindingResult result) {
        if(result.hasErrors()){
            return "employee/update-employee-form";
        }
        employeeIService.update(employeeForm);
        return "redirect:/employee/list";
    }

    @GetMapping("/deleteForm")
    @ApiOperation(value = "form for delete", notes="get form for delete existing employee in employee table, using his id")
    public String formForDeleteEmployee(@RequestParam("employeeId") Long id, Model theModel) {
        EmployeeForm employeeForm  = employeeIService.get(id);
        theModel.addAttribute("employee", employeeForm);
        return "employee/delete-employee-form";
    }

    @DeleteMapping(value = "/deleteEmployee")
    @ApiOperation(value = "delete", notes="delete existing employee in employee table")
    public String deleteEmployee(@Valid @ModelAttribute("employee") EmployeeForm employeeForm, BindingResult result) {
        if(result.hasErrors()){
            return "employee/delete-employee-form";
        }
        employeeIService.delete(employeeForm.getEmployeeId());
        return "redirect:/employee/list";
    }
}
