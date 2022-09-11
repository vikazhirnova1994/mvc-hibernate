package com.project.controller;

import com.project.service.IService;
import com.project.util.form.PositionFrom;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private  IService<PositionFrom, Long> positionService;

    @GetMapping("/list")
    public String listPositions(Model theModel) {
        List<PositionFrom> positions = positionService.getAll();
        theModel.addAttribute("positions", positions);
        return "position/list-position";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        PositionFrom positionModel = new PositionFrom();
        theModel.addAttribute("positionModel", positionModel);
        return "position/position-form";
    }

    @PostMapping("/savePosition")
    public String savePosition(@Valid @ModelAttribute("positionModel") PositionFrom positionModel, BindingResult result) {
        if (result.hasErrors()) {
            return "position/position-form";
        }
        positionService.save(positionModel);
        return "redirect:/position/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("positionId") Long id, Model theModel) {
        PositionFrom positionModel  = positionService.get(id);
        theModel.addAttribute("position", positionModel);
        return "position/update-position-form";
    }

    @PostMapping("/updatePosition")
    public String updatePosition(@Valid @ModelAttribute("position") PositionFrom positionModel, BindingResult result) {
        if(result.hasErrors()){
            return "position/position-form";
        }
        positionService.update(positionModel);
        return "redirect:/position/list";
    }


    @GetMapping("/delete")
    public String deletePosition(@RequestParam("positionId") Long id) {
        positionService.delete(id);
        return "redirect:/position/list";
    }
}
