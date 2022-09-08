package com.project.service;

import com.project.domain.Customer;
import com.project.domain.Employee;

import java.util.List;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

public interface EmployeeService {
    public List<Employee> getAll();

    public void save(Employee employee);

    public Employee get(int id);

    public void delete(int id);
}
