package com.project.util.form;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

@Getter
@Setter
public class EmployeeForm {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String position;
}
