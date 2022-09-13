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
@ApiModel(value = "model for adding data in project table, also adding data in customer table if customer does not exist yet")
public class ProjectFrom {

    @ApiModelProperty(value = "id of employee", example = "1")
    private Long projectId;

    @NotNull
    @NotBlank(message = "required")
    @ApiModelProperty(value = "name of project", example = "spring mvc", required = true)
    private String name;

    @NotNull
    @NotBlank(message = "required")
    @ApiModelProperty(value = "createAt of project", example = "2022.09.12", required = true)
    private String createAt;

    @ApiModelProperty(value = "finishAt of project", example = "2022.09.12")
    private String finishAt;

    @NotNull
    @NotBlank(message = "required")
    @ApiModelProperty(value = "Name Customer of project", example = "aston", required = true)
    private String nameCustomer;

    @NotNull
    @NotBlank(message = "required")
    @ApiModelProperty(value = "Email Customer of project", example = "aston@gmail.com", required = true)
    private String emailCustomer;
}
