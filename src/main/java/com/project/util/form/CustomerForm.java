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
@ApiModel(value = "model for adding data in customer table")
public class CustomerForm {

    @ApiModelProperty(value = "id of customer", example = "1")
    private Long customerId;

    @NotNull
    @NotBlank(message = "required")
    @ApiModelProperty(value = "name of customer", example = "aston", required = true)
    private String name;

    @NotNull
    @NotBlank(message = "required")
    @ApiModelProperty(value = "email of customer", example = "aston@gmail.com", required = true)
    private String email;
}
