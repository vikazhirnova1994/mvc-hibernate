package com.project.service;

import com.project.dao.PositionDao;
import com.project.util.mapper.EmployeeMapper;
import com.project.util.form.EmployeeForm;
import com.project.dao.IDao;
import com.project.domain.Employee;
import com.project.domain.Position;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

@Service
@Transactional
public class EmployeeService implements IService<EmployeeForm, Long>{

    private final IDao<Employee, Long> employeeDAO;

    private final PositionDao positionDAO;

    public EmployeeService(IDao<Employee, Long> employeeDAO, PositionDao positionDAO) {
        this.employeeDAO = employeeDAO;
        this.positionDAO = positionDAO;
    }

    @Override
    public List<EmployeeForm> getAll() {
        return employeeDAO.getAll()
                        .stream()
                        .map(EmployeeMapper::entityToEmployeeFrom)
                        .collect(Collectors.toList());
    }

    @Override
    public void save(EmployeeForm employeeForm) {
       Employee employee =  EmployeeMapper.employeeFormToEntity(employeeForm);
        Optional<Position> customerDB = positionDAO.getByNamePosition(employeeForm.getPosition());
        if(customerDB.isEmpty()){
            createCouplingWithNewEmployee(employeeForm, employee);
        }
        if (customerDB.isPresent()) {
            createCouplingWithExistEmployee(employee, customerDB);
        }
       employeeDAO.save(employee);
    }


    @Override
    public EmployeeForm get(Long id) {
        return EmployeeMapper.entityToEmployeeFrom(employeeDAO.get(id));
    }

    @Override
    public void delete(Long id) {
        employeeDAO.delete(id);
    }

    @Override
    public void update(EmployeeForm employeeForm) {
        Employee employee =  EmployeeMapper.employeeFormToEntity(employeeForm);
        Optional<Position> employeeFromDBToGetByName = positionDAO.getByNamePosition(employeeForm.getPosition());
        if(employeeFromDBToGetByName.isEmpty()){
            createCouplingWithNewEmployee(employeeForm, employee);
        }
        if (employeeFromDBToGetByName.isPresent()){
            createCouplingWithExistEmployee(employee, employeeFromDBToGetByName);
        }
        employeeDAO.update(employee);
    }


    private void createCouplingWithExistEmployee(Employee employee, Optional<Position> employeeFromDBToGetByName) {
        Position oldPosition = employeeFromDBToGetByName.get();
        employee.setPosition(oldPosition);
    }

    private void createCouplingWithNewEmployee(EmployeeForm employeeForm, Employee employee) {
        Position position = new Position();
        position.setPosition(employeeForm.getPosition());
        positionDAO.save(position);
        employee.setPosition(position);
    }
}
