package com.project.controller;

import com.project.domain.Employee;
import com.project.domain.Project;
import com.project.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    private final IService<Project> projectService;

    public ProjectController(IService<Project> projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/list")
    public String listUsers(Model theModel) {
        List<Project> projects = projectService.getAll();
        theModel.addAttribute("users", projects);
        return "project/list-project";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        Project theUser = new Project();
        theModel.addAttribute("user", theUser);
        return "project/project-form";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user")  Project project ) {
        projectService.save(project);
        return "redirect:/project/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("userId") int id,
                                    Model theModel) {
        Project project  = projectService.get(id);
        theModel.addAttribute("user", project);
        return "employee/project-form";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") int id) {
        projectService.delete(id);
        return "redirect:/project/list";
    }
}
