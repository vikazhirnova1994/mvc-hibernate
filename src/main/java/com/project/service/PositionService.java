package com.project.service;

import com.project.dao.EmployeeDao;
import com.project.dao.IDao;
import com.project.domain.Employee;
import com.project.domain.Position;
import com.project.util.mapper.PositionMapper;
import com.project.util.form.PositionFrom;
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
public class PositionService implements IService<PositionFrom, Long> {

    @Autowired
    private EmployeeDao employeeDAO;

    @Autowired
    private  IDao<Position, Long> positionDao;

    @Override
    public List<PositionFrom> getAll() {
        return positionDao.getAll()
                .stream()
                .map(PositionMapper::entityToPositionFrom)
                .collect(Collectors.toList());
    }

    @Override
    public void save(PositionFrom positionModel) {
        Position position = PositionMapper.positionFormToEntity(positionModel);
        positionDao.save(position);
    }

    @Override
    public PositionFrom get(Long id) {
        return PositionMapper.entityToPositionFrom(positionDao.get(id));
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
    public void update(PositionFrom positionModel) {
        Position position = PositionMapper.positionFormToEntity(positionModel);
        positionDao.update(position);
    }
}
