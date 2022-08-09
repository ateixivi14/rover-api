package com.training.roverapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovementEngineResponse {
    
    private int x;
    private int y;
    private Direction direction;
}
