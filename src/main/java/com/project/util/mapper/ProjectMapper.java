package com.project.util.mapper;

import com.project.domain.Project;
import com.project.util.form.ProjectFrom;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

public final class ProjectMapper {

    public static Project projectFormToEntity(ProjectFrom projectFrom) {
        Project project = new Project();
        project.setProjectId(projectFrom.getProjectId());
        project.setName(projectFrom.getName());
        project.setCreateAt(projectFrom.getCreateAt());
        project.setFinishAt(projectFrom.getFinishAt());
        return project;
    }

    public static ProjectFrom entityToProjectForm(Project entity) {
        ProjectFrom projectModel = new ProjectFrom();
        projectModel.setProjectId(entity.getProjectId());
        projectModel.setName(entity.getName());
        projectModel.setCreateAt(entity.getCreateAt());
        projectModel.setFinishAt(entity.getFinishAt());
        projectModel.setNameCustomer(entity.getCustomer().getName());
        projectModel.setEmailCustomer(entity.getCustomer().getEmail());
        return projectModel;
    }
}
