package com.training.roverapi.controller;

import com.training.roverapi.domain.Direction;
import com.training.roverapi.domain.Movement;
import com.training.roverapi.dto.ObstacleDto;
import com.training.roverapi.dto.RoverDto;
import com.training.roverapi.service.ObstacleHandler;
import com.training.roverapi.service.RoverService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoverControllerShould {
    
    @Mock
    private RoverService roverService;
    
    @Mock
    private ObstacleHandler obstacleHandler;
    
    @InjectMocks
    private RoverController roverController;
    
    @Test
    public void return_rover_dto_with_updated_data() {
        
        RoverRequest roverRequest = RoverRequest.builder()
                .id(1L)
                .command(Movement.FORWARD)
                .sizex(10)
                .sizey(10)
                .build();

        RoverDto roverDto = RoverDto.builder()
                .x(5)
                .y(6)
                .direction(Direction.NORTH.name())
                .build();
        
        when(roverService.move(roverRequest)).thenReturn(roverDto);
        
        assertThat(roverController.moveRover(roverRequest)).isEqualTo(roverDto);
        
    }
    
    @Test
    public void introduce_obstacles() {
        ObstaclesRequest obstaclesRequest = ObstaclesRequest.builder()
                .obstacles(List.of(ObstacleDto.builder()
                                .coordinateY(5)
                                .coordinateX(5)
                        .build()))
                .build();
        
        roverController.introduceObstacles(obstaclesRequest);
        
        verify(obstacleHandler, times(1)).saveObstacles(obstaclesRequest.getObstacles());
    }
    
}
