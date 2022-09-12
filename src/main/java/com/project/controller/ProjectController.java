package com.project.controller;

import com.project.service.IService;
import com.project.util.form.ProjectFrom;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */
@Controller
@RequestMapping("/project")
@Api(value = "ProjectController", description = "crud operation for project table")
public class ProjectController {

    private final IService<ProjectFrom, Long> projectService;

    public ProjectController(IService<ProjectFrom, Long> projectService) {
        this.projectService = projectService;
    }


    @GetMapping("/list")
    @ApiOperation(value = "list of position", notes="get list of project from project table", produces = ".jsp")
    public String listProjects(Model theModel) {
        List<ProjectFrom> projects = projectService.getAll();
        theModel.addAttribute("projects", projects);
        return "project/list-project";
    }

    @GetMapping("/showForm")
    @ApiOperation(value = "form for adding", notes="get form for adding new project in project table")
    public String showFormForAdd(Model theModel) {
        ProjectFrom project = new ProjectFrom();
        theModel.addAttribute("project", project);
        return "project/project-form";
    }

    @PostMapping("/saveProject")
    @ApiOperation(value = "save", notes="save new project in project table")
    public String saveProject(@Valid @ModelAttribute("project") ProjectFrom project, BindingResult result ) {
        if (result.hasErrors()) {
            return "project/project-form";
        }
        projectService.save(project);
        return "redirect:/project/list";
    }

    @GetMapping("/updateForm")
    @ApiOperation(value = "form for update", notes="get form for update existing project in project table")
    public String showFormForUpdate(@RequestParam("projectId") Long id, Model theModel) {
        ProjectFrom project  = projectService.get(id);
        theModel.addAttribute("project", project);
        return "project/update-project-form";

    }
    @PostMapping("/updateProject")
    @ApiOperation(value = "update", notes="update existing project in project table")
    public String updateProject(@Valid @ModelAttribute("project") ProjectFrom project, BindingResult result) {
        if(result.hasErrors()){
            return "project/project-form";
        }
        projectService.update(project);
        return "redirect:/project/list";
    }


    @GetMapping("/delete")
    @ApiOperation(value = "delete", notes="delete existing project in project table, using his id")
    public String deleteProject(@RequestParam("projectId") Long id) {
        projectService.delete(id);
        return "redirect:/project/list";
    }
}
