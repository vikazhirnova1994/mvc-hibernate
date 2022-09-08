package com.project.service;

import com.project.dao.DAO;
import com.project.domain.Employee;
import com.project.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

@org.springframework.stereotype.Service
@Transactional
public class ProjectService implements IService<Project>{

    @Autowired
    private DAO<Project> projectDAO;

    @Override
    public List<Project> getAll() {
        return projectDAO.getAll();
    }

    @Override
    public void save(Project project) {
        projectDAO.save(project);
    }

    @Override
    public Project get(int id) {
        return projectDAO.get(id);
    }

    @Override
    public void delete(int id) {
        projectDAO.delete(id);
    }
}
