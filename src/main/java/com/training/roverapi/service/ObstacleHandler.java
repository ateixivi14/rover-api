package com.training.roverapi.service;

import com.training.roverapi.dto.ObstacleDto;
import com.training.roverapi.entity.Obstacle;
import com.training.roverapi.repository.ObstacleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ObstacleHandler {
    
    private final ObstacleRepository obstacleRepository;
    
    public void saveObstacles(List<ObstacleDto> obstacleList) {
        List<Obstacle> obstacles = obstacleList.stream().map(this::toObstacle).toList();
        obstacleRepository.saveAll(obstacles);
    }
    
    public boolean existsObstacleByCoordinates(int x, int y) {
        return obstacleRepository.existsObstacleByCoordinateXAndCoordinateY(x,y);
    }
    
    private Obstacle toObstacle(ObstacleDto obstacleDto) {
        return Obstacle.builder()
                .id(obstacleDto.getId())
                .coordinateX(obstacleDto.getCoordinateX())
                .coordinateY(obstacleDto.getCoordinateY())
                .build();
    }
}
