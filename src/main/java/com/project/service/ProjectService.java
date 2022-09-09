package com.project.service;

import com.project.dao.IDao;
import com.project.domain.Project;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

@Service
@Transactional
public class ProjectService implements IService<Project> {

    private final IDao<Project> projectDAO;

    public ProjectService(IDao<Project> projectDAO) {
        this.projectDAO = projectDAO;
    }

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
