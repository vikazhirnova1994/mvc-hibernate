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
    public void save(EmployeeForm employeeModel) {
       Employee employee =  EmployeeMapper.employeeFormToEntity(employeeModel);
        Optional<Position> customerDB = positionDAO.getByNamePosition(employeeModel.getPosition());
        if(customerDB.isEmpty()){
            Position position = new Position();
            position.setPosition(employeeModel.getPosition());
            positionDAO.save(position);
            employee.setPosition(position);
        }
        if (customerDB.isPresent()) {
            Position oldPosition = customerDB.get();
            employee.setPosition(oldPosition);
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
    public void update(EmployeeForm employeeRequestModel) {
        Employee employee =  EmployeeMapper.employeeFormToEntity(employeeRequestModel);
        Optional<Position> customerDB = positionDAO.getByNamePosition(employeeRequestModel.getPosition());
        if(customerDB.isEmpty()){
            Position newPosition = new Position();
            newPosition.setPosition(employeeRequestModel.getPosition());
            employee.setPosition(newPosition);
            positionDAO.save(newPosition);
        }
        if (customerDB.isPresent()){
            Position oldPosition = customerDB.get();
//            oldPosition.setPosition(employeeRequestModel.getPosition());
            employee.setPosition(oldPosition);
        }
        employeeDAO.update(employee);
    }
}
