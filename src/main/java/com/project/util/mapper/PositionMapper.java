package com.project.util.mapper;

import com.project.domain.Position;
import com.project.util.form.PositionFrom;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

public final class PositionMapper {

    public static Position positionFormToEntity(PositionFrom positionFrom) {
        Position position = new Position();
        position.setPositionId(positionFrom.getPositionId());
        position.setPosition(positionFrom.getPosition());
        return position;
    }

    public static PositionFrom entityToPositionFrom(Position entity) {
        PositionFrom positionModel = new PositionFrom();
        positionModel.setPositionId(entity.getPositionId());
        positionModel.setPosition(entity.getPosition());
        return positionModel;
    }
}
