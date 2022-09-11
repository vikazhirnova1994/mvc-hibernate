package com.project.service;

import com.project.dao.PositionDao;
import com.project.domain.Customer;
import com.project.util.mapper.EmployeeMapper;
import com.project.util.model.EmployeeModel;
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
public class EmployeeService implements IService<EmployeeModel, Long>{

    private final IDao<Employee, Long> employeeDAO;

    private final PositionDao positionDAO;

    public EmployeeService(IDao<Employee, Long> employeeDAO, PositionDao positionDAO) {
        this.employeeDAO = employeeDAO;
        this.positionDAO = positionDAO;
    }

    @Override
    public List<EmployeeModel> getAll() {
        return employeeDAO.getAll()
                        .stream()
                        .map(EmployeeMapper::employeeToEmployeeRequestModel)
                        .collect(Collectors.toList());
    }

    @Override
    public void save(EmployeeModel employeeModel) {
       Employee employee =  EmployeeMapper.employeeRequestModelToEmployee(employeeModel);
       Position position = new Position();
       position.setPosition(employeeModel.getPosition());
       positionDAO.save(position);
       employee.setPosition(position);
       employeeDAO.save(employee);
    }

    @Override
    public EmployeeModel get(Long id) {
        return EmployeeMapper.employeeToEmployeeRequestModel(employeeDAO.get(id));
    }

    @Override
    public void delete(Long id) {
        employeeDAO.delete(id);
    }

    @Override
    public void update(EmployeeModel employeeRequestModel) {
        Employee employee =  EmployeeMapper.employeeRequestModelToEmployee(employeeRequestModel);
        Optional<Position> customerDB = positionDAO.getByNamePosition(employeeRequestModel.getPosition());
        if(customerDB.isEmpty()){
            Position newPosition = new Position();
            positionDAO.save(newPosition);
            employee.setPosition(newPosition);
        }
        if (customerDB.isPresent()){
            Position oldPosition = customerDB.get();
            oldPosition.setPosition(employeeRequestModel.getPosition());
            positionDAO.update(oldPosition);
            employee.setPosition(oldPosition);
        }
        employeeDAO.update(employee);
    }
}
