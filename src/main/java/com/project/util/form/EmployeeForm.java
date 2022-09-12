package com.project.util.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

@Getter
@Setter
@ApiModel(value = "model for adding data in employee table, also adding data in position table if position does not exist yet")
public class EmployeeForm {

    @ApiModelProperty(value = "id of employee", example = "1")
    private Long employeeId;

    @NotNull
    @NotBlank(message = "required")
    @ApiModelProperty(value = "firstName of employee", example = "vika", required = true)
    private String firstName;

    @NotNull
    @NotBlank(message = "required")
    @ApiModelProperty(value = "lastName of emploee", example = "zhirnova", required = true)
    private String lastName;

    @NotNull
    @NotBlank(message = "required")
    @ApiModelProperty(value = "name of position", example = "java junior", required = true)
    private String position;
}
