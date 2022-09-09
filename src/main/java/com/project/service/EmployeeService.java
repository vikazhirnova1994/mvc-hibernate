package com.project.service;

import com.project.dao.IDao;
import com.project.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

@Service
@Transactional
public class EmployeeService implements IService<Employee>{

    @Autowired
    private IDao<Employee> employeeDAO;

    @Override
    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    @Override
    public void save(Employee employee) {
        employeeDAO.save(employee);
    }

    @Override
    public Employee get(int id) {
        return employeeDAO.get(id);
    }

    @Override
    public void delete(int id) {
        employeeDAO.delete(id);
    }
}
