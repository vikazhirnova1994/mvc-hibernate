package com.project.util.mapper;

import com.project.domain.Customer;
import com.project.domain.Employee;
import com.project.domain.Project;
import com.project.util.model.EmployeeModel;
import com.project.util.model.ProjectModel;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

public final class ProjectMapper {

    public static Project projectModelToProject(ProjectModel projectModel) {
        Project project = new Project();
        //if (projectModel.get() != null) project.setCustomer(new Customer(projectModel.getCustomerId()));
        project.setProjectId(projectModel.getProjectId());
        project.setName(projectModel.getName());
        project.setCreateAt(projectModel.getCreateAt());
        project.setFinishAt(projectModel.getFinishAt());
        return project;
    }

    public static ProjectModel projectToProjectModel(Project project) {
        ProjectModel projectModel = new ProjectModel();
        projectModel.setProjectId(project.getProjectId());
        projectModel.setName(project.getName());
        projectModel.setCreateAt(project.getCreateAt());
        projectModel.setFinishAt(project.getFinishAt());
        projectModel.setNameCustomer(project.getCustomer().getName());
        projectModel.setEmailCustomer(project.getCustomer().getEmail());
        return projectModel;
    }
}
