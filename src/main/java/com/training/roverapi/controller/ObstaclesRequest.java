package com.training.roverapi.controller;

import com.training.roverapi.dto.ObstacleDto;
import com.training.roverapi.entity.Obstacle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObstaclesRequest {
    
    List<ObstacleDto> obstacles;
    
}
