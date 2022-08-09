package com.training.roverapi.dto;

import com.training.roverapi.entity.Rover;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class RoverDto {

    private final int x;

    private final int y;

    private final String direction;
    
    public static RoverDto toDto(Rover rover) {
        return RoverDto.builder()
                .x(rover.getX())
                .y(rover.getY())
                .direction(rover.getDirection().name())
                .build();
    }
    
}
