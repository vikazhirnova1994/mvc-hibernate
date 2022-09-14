package com.project.util.mapper;

import com.project.domain.Employee;
import com.project.util.form.EmployeeForm;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

public final  class EmployeeMapper {
    public static Employee employeeFormToEntity(EmployeeForm employeeForm) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeForm.getEmployeeId());
        employee.setFirstName(employeeForm.getFirstName());
        employee.setLastName(employeeForm.getLastName());
        return employee;
    }

    public static EmployeeForm entityToEmployeeFrom(Employee entity){
        EmployeeForm employeeRequestModel = new EmployeeForm();
        employeeRequestModel.setEmployeeId(entity.getEmployeeId());
        employeeRequestModel.setFirstName(entity.getFirstName());
        employeeRequestModel.setLastName(entity.getLastName());
        employeeRequestModel.setPosition(entity.getPosition().getPosition());
        return employeeRequestModel;
    }
}
