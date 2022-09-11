package com.project.util.mapper;

import com.project.domain.Employee;
import com.project.util.model.EmployeeModel;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

public final  class EmployeeMapper {
    public static Employee employeeRequestModelToEmployee(EmployeeModel employeeRequestModel) {
        Employee employee = new Employee();
        employee.setFirstName(employeeRequestModel.getFirstName());
        employee.setLastName(employeeRequestModel.getLastName());
        return employee;
    }

    public static EmployeeModel employeeToEmployeeRequestModel(Employee employee) {
        EmployeeModel employeeRequestModel = new EmployeeModel();
        employeeRequestModel.setEmployeeId(employee.getEmployeeId());
        employeeRequestModel.setFirstName(employee.getFirstName());
        employeeRequestModel.setLastName(employee.getLastName());
        employeeRequestModel.setPosition(employee.getPosition().getPosition());
        return employeeRequestModel;
    }
}
