package com.project.util.form;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

@Getter
@Setter
public class CustomerForm {
    private Long customerId;
    private String name;
    private String email;
}
