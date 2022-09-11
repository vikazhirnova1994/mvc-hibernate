package com.project.service;

import com.project.dao.EmployeeDao;
import com.project.dao.IDao;
import com.project.domain.Employee;
import com.project.domain.Position;
import com.project.domain.Project;
import com.project.util.mapper.EmployeeMapper;
import com.project.util.mapper.PositionMapper;
import com.project.util.mapper.ProjectMapper;
import com.project.util.model.PositionModel;
import com.project.util.model.ProjectModel;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PositionService implements IService<PositionModel, Long> {

    @Autowired
    private EmployeeDao employeeDAO;

    @Autowired
    private  IDao<Position, Long> positionDao;

    @Override
    public List<PositionModel> getAll() {
        return positionDao.getAll()
                .stream()
                .map(PositionMapper::positionToPositionModel)
                .collect(Collectors.toList());
    }

    @Override
    public void save(PositionModel positionModel) {
        Position position = PositionMapper.positionModelToPosition(positionModel);
        positionDao.save(position);
    }

    @Override
    public PositionModel get(Long id) {
        return PositionMapper.positionToPositionModel(positionDao.get(id));
    }

    @Override
    public void delete(Long id) {
        Optional<Employee> employeeDB = employeeDAO.getEmployeeByPosition(positionDao.get(id));
        if(employeeDB.isPresent()) {
            employeeDAO.delete(employeeDB.get().getEmployeeId());
        }
        positionDao.delete(id);
    }

    @Override
    public void update(PositionModel positionModel) {
        Position position = PositionMapper.positionModelToPosition(positionModel);
        positionDao.update(position);
    }
}
