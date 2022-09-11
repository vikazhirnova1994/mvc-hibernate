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
public class ProjectService implements IService<ProjectFrom, Long> {

    private final CustomerDao customerDao;
    private final IDao<Project, Long> projectDao;

    public ProjectService(IDao<Project, Long> projectDao, CustomerDao customerDao) {
        this.projectDao = projectDao;
        this.customerDao = customerDao;
    }
    @Transactional
    @Override
    public List<ProjectFrom> getAll() {
        return projectDao.getAll()
                .stream()
                .map(ProjectMapper::entityToProjectForm)
                .collect(Collectors.toList());
    }
    @Transactional
    @Override
    public void save(ProjectFrom projectModel) {
        Project project = ProjectMapper.projectFormToEntity(projectModel);
        Optional<Customer> customerDB = customerDao.getByName(projectModel.getNameCustomer());
       if(customerDB.isEmpty()){
           Customer newCustomer = new Customer();
           newCustomer.setName(projectModel.getNameCustomer());
           newCustomer.setEmail(projectModel.getEmailCustomer());
           customerDao.save(newCustomer);
           newCustomer.addProject(project);
       }

       if (customerDB.isPresent()){
           Customer existCustomer = customerDB.get();
           existCustomer.addProject(project);
       }
        projectDao.save(project);
    }
    @Transactional
    @Override
    public ProjectFrom get(Long id) {
        Project project = projectDao.get(id);
        ProjectFrom projectModel = ProjectMapper.entityToProjectForm(projectDao.get(id));
        return projectModel;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        projectDao.delete(id);
    }

    @Transactional
    @Override
    public void update(ProjectFrom projectModel) {
        Project project =  ProjectMapper.projectFormToEntity(projectModel);
        String nameCustomer = projectModel.getNameCustomer();
        Optional<Customer> customerDB = customerDao.getByName(nameCustomer);
        if(customerDB.isEmpty()){
            Customer newCustomer = new Customer();
            newCustomer.setName(nameCustomer);
            newCustomer.setEmail(projectModel.getEmailCustomer());
            customerDao.save(newCustomer);
            newCustomer.addProject(project);
        }
        if (customerDB.isPresent()){
            customerDB.get().addProject(project);
        }
        projectDao.update(project);
    }

}
