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
public class EmployeeModel {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String position;
}
