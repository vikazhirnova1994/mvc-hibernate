package com.project.util.form;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

@Getter
@Setter
public class ProjectFrom {
    private Long projectId;
    private String name;
    private String createAt;
    private String finishAt;
    private String nameCustomer;
    private String emailCustomer;
}
