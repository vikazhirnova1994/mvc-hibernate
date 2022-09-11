package com.project.controller;

import com.project.service.IService;
import com.project.util.model.ProjectModel;
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
public class ProjectController {

    private final IService<ProjectModel, Long> projectService;

    public ProjectController(IService<ProjectModel, Long> projectService) {
        this.projectService = projectService;
    }


    @GetMapping("/list")
    public String listUsers(Model theModel) {
        List<ProjectModel> projects = projectService.getAll();
        theModel.addAttribute("projects", projects);
        return "project/list-project";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        ProjectModel project = new ProjectModel();
        theModel.addAttribute("project", project);
        return "project/project-form";
    }

    @PostMapping("/saveProject")
    public String saveUser(@Valid @ModelAttribute("project") ProjectModel project, BindingResult result ) {
        if (result.hasErrors()) {
            return "project/project-form";
        }
        projectService.save(project);
        return "redirect:/project/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("projectId") Long id, Model theModel) {
        ProjectModel project  = projectService.get(id);
        theModel.addAttribute("project", project);
        return "project/update-project-form";

    }
    @PostMapping("/updateProject")
    public String updateUser(@RequestParam("projectId") Long id, @Valid @ModelAttribute("project") ProjectModel project, BindingResult result) {
        if(result.hasErrors()){
            return "project/project-form";
        }
        projectService.update(project);
        return "redirect:/project/list";
    }


    @GetMapping("/delete")
    public String deleteUser(@RequestParam("projectId") Long id) {
        projectService.delete(id);
        return "redirect:/project/list";
    }
}
