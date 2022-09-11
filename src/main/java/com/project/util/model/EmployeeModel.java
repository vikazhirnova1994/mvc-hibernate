package com.project.util.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

@Getter
@Setter
public class EmployeeModel implements Serializable {

    @NotNull
    @NotBlank(message = "required")
    private Long employeeId;

    @NotNull
    @NotBlank(message = "required")
    private String firstName;

    @NotNull
    @NotBlank(message = "required")
    private String lastName;

    @NotNull
    @NotBlank(message = "required")
    private String position;
}
