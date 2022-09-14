package com.project.controller;

import com.project.service.IService;
import com.project.util.form.EmployeeForm;
import com.project.util.form.PositionFrom;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "PositionController", description = "crud operation for position table")
public class PositionController {

    @Autowired
    private  IService<PositionFrom, Long> positionService;

    @GetMapping("/list")
    @ApiOperation(value = "list of position", notes="get list of position from position table", produces = ".jsp")
    public String listPositions(Model theModel) {
        List<PositionFrom> positions = positionService.getAll();
        theModel.addAttribute("positions", positions);
        return "position/list-position";
    }

    @GetMapping("/showForm")
    @ApiOperation(value = "form for adding", notes="get form for adding new position in position table")
    public String formToSavePosition(Model theModel) {
        PositionFrom positionFrom = new PositionFrom();
        theModel.addAttribute("positionModel", positionFrom);
        return "position/position-form";
    }

    @PostMapping("/savePosition")
    @ApiOperation(value = "save", notes="save new position in position table")
    public String savePosition(@Valid @ModelAttribute("positionModel") PositionFrom positionFrom, BindingResult result) {
        if (result.hasErrors()) {
            return "position/position-form";
        }
        positionService.save(positionFrom);
        return "redirect:/position/list";
    }

    @GetMapping("/updateForm")
    @ApiOperation(value = "form for update", notes="get form for update existing position in position table")
    public String formToUpdatePosition(@RequestParam("positionId") Long id, Model theModel) {
        PositionFrom positionFrom  = positionService.get(id);
        theModel.addAttribute("position", positionFrom);
        return "position/update-position-form";
    }

    @PutMapping("/updatePosition")
    @ApiOperation(value = "update", notes="update existing position in position table")
    public String updatePosition(@Valid @ModelAttribute("position") PositionFrom positionFrom, BindingResult result) {
        if(result.hasErrors()){
            return "position/position-form";
        }
        positionService.update(positionFrom);
        return "redirect:/position/list";
    }

    @GetMapping("/deleteForm")
    @ApiOperation(value = "form for delete", notes="get form for delete existing position in position table, using his id")
    public String formToDeletePosition(@RequestParam("positionId") Long id, Model theModel) {
        PositionFrom positionFrom  = positionService.get(id);
        theModel.addAttribute("position", positionFrom);
        return "position/delete-position-form";
    }

    @DeleteMapping(value = "/deletePosition")
    @ApiOperation(value = "delete", notes="delete existing position in position table")
    public String deleteEmployee(@Valid @ModelAttribute("position") PositionFrom positionFrom, BindingResult result) {
        if(result.hasErrors()){
            return "position/delete-position-form";
        }
        positionService.delete(positionFrom.getPositionId());
        return "redirect:/position/list";
    }
}
