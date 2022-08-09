package com.training.roverapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.training.roverapi.entity.Obstacle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class ObstacleDto {
    
    private final Long id;
    
    @JsonAlias("coordinate_X")
    private final int coordinateX;

    @JsonAlias("coordinate_Y")
    private final int coordinateY;
    
    public static ObstacleDto toDto(final Obstacle obstacle) {
        return ObstacleDto.builder()
                .id(obstacle.getId())
                .coordinateX(obstacle.getCoordinateX())
                .coordinateY(obstacle.getCoordinateY())
                .build();
    }
    
}
