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
@ApiModel(value = "model for adding data in position table")
public class PositionFrom {

    @ApiModelProperty(value = "id of employee", example = "1")
    private Long positionId;

    @NotNull
    @NotBlank(message = "required")
    @ApiModelProperty(value = "name of position", example = "java junior", required = true)
    private String position;
}
