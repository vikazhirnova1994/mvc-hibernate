package com.project.util.mapper;

import com.project.domain.Position;
import com.project.domain.Project;
import com.project.util.model.PositionModel;
import com.project.util.model.ProjectModel;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

public final class PositionMapper {

    public static Position positionModelToPosition(PositionModel positionModel) {
        Position position = new Position();
        position.setPositionId(positionModel.getPositionId());
        position.setPosition(positionModel.getPosition());
        return position;
    }

    public static PositionModel positionToPositionModel(Position position) {
        PositionModel positionModel = new PositionModel();
        positionModel.setPositionId(position.getPositionId());
        positionModel.setPosition(position.getPosition());
        return positionModel;
    }
}
