package com.project.service;

import com.project.dao.CustomerDao;
import com.project.dao.IDao;
import com.project.domain.*;
import com.project.util.mapper.ProjectMapper;
import com.project.util.form.ProjectFrom;
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
public class ProjectService implements IService<ProjectFrom, Long> {

    private final CustomerDao customerDao;
    private final IDao<Project, Long> projectDao;

    public ProjectService(IDao<Project, Long> projectDao, CustomerDao customerDao) {
        this.projectDao = projectDao;
        this.customerDao = customerDao;
    }

    @Override
    public List<ProjectFrom> getAll() {
        return projectDao.getAll()
                .stream()
                .map(ProjectMapper::entityToProjectForm)
                .collect(Collectors.toList());
    }
    @Override
    public void save(ProjectFrom projectFrom) {
        Project project = ProjectMapper.projectFormToEntity(projectFrom);
        Optional<Customer> customerFromDbToGetByName = customerDao.getByName(projectFrom.getNameCustomer());
        if(customerFromDbToGetByName.isEmpty()){
            Customer newCustomer = createCouplingWithNewCustomer(projectFrom, project);
            customerDao.save(newCustomer);
        }
       if (customerFromDbToGetByName.isPresent()){
           createCouplingWithExistCustomer(project, customerFromDbToGetByName);
       }
        projectDao.save(project);
    }

    @Override
    public ProjectFrom get(Long id) {
        Project project = projectDao.get(id);
        ProjectFrom projectModel = ProjectMapper.entityToProjectForm(
                projectDao.get(id));
        return projectModel;
    }

    @Override
    public void delete(Long id) {
        projectDao.delete(id);
    }

    @Override
    public void update(ProjectFrom projectFrom) {
        Project project =  ProjectMapper.projectFormToEntity(projectFrom);
        Optional<Customer> customerFromDBToGetByName = customerDao.getByName(
                projectFrom.getNameCustomer());
        if(customerFromDBToGetByName.isEmpty()){
            Customer newCustomer = createCouplingWithNewCustomer(projectFrom, project);
            customerDao.save(newCustomer);
        }
        if (customerFromDBToGetByName.isPresent()){
            Customer existCustomer = updateCouplingWithExistCustomer(projectFrom, project, customerFromDBToGetByName);
            customerDao.update(existCustomer);
        }
        projectDao.update(project);
    }

    private Customer updateCouplingWithExistCustomer(ProjectFrom projectFrom, Project project, Optional<Customer> customerFromDBToGetByName) {
        Customer existCustomer = customerFromDBToGetByName.get();
        existCustomer.setName(projectFrom.getNameCustomer());
        existCustomer.setEmail(projectFrom.getEmailCustomer());
        existCustomer.addProject(project);
        project.setCustomer(existCustomer);
        return existCustomer;
    }

    private Customer createCouplingWithExistCustomer(Project project, Optional<Customer> customerFromDBToGetByName) {
        Customer existCustomer = customerFromDBToGetByName.get();
        existCustomer.addProject(project);
        project.setCustomer(existCustomer);
        return existCustomer;
    }

    private Customer createCouplingWithNewCustomer(ProjectFrom projectFrom, Project project) {
        Customer newCustomer = new Customer();
        newCustomer.setName(projectFrom.getNameCustomer());
        newCustomer.setEmail(projectFrom.getEmailCustomer());
        newCustomer.addProject(project);
        project.setCustomer(newCustomer);
        return newCustomer;
    }
}
