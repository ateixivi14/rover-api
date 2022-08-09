package com.training.roverapi.service;

import com.training.roverapi.controller.RoverRequest;
import com.training.roverapi.domain.Direction;
import com.training.roverapi.domain.Movement;
import com.training.roverapi.domain.MovementEngine;
import com.training.roverapi.domain.MovementEngineResponse;
import com.training.roverapi.dto.RoverDto;
import com.training.roverapi.entity.Rover;
import com.training.roverapi.repository.RoverRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoverServiceShould {
    
    private static Long ROVER_ID = 1L;
    private static int X_INITIAL_COORDINATE = 5;
    private static int Y_INITIAL_COORDINATE = 5;
    private static int X_FINAL_COORDINATE = 5;
    private static int Y_FINAL_COORDINATE = 6;
    private static Direction INITIAL_DIRECTION = Direction.NORTH;
    private static Direction FINAL_DIRECTION = Direction.NORTH;
    
    private static Rover ROVER = Rover.builder()
            .id(ROVER_ID)
            .direction(INITIAL_DIRECTION)
            .x(X_INITIAL_COORDINATE)
            .y(Y_INITIAL_COORDINATE)
            .build();
    
    private static MovementEngineResponse MOVEMENT_RESPONSE =  MovementEngineResponse.builder()
            .direction(FINAL_DIRECTION)
            .y(Y_FINAL_COORDINATE)
            .x(X_INITIAL_COORDINATE)
            .direction(FINAL_DIRECTION)
            .build();

    private RoverRequest roverRequest = RoverRequest.builder()
            .id(ROVER_ID)
            .command(Movement.FORWARD)
            .sizex(10)
            .sizey(10)
            .build();
    
    @Mock 
    private RoverRepository roverRepository;
    
    @Mock
    private ObstacleHandler obstacleHandler;
    
    @Mock
    private MovementEngine movementEngine;
    
    @InjectMocks
    private RoverService roverService;
    
    @Test
    public void move_rover_correctly() {
        
        when(roverRepository.findById(ROVER_ID)).thenReturn(Optional.of(ROVER));
        when(movementEngine.activeEngineMovement(ROVER, Movement.FORWARD))
                .thenReturn(MOVEMENT_RESPONSE);
        
        when(obstacleHandler.existsObstacleByCoordinates(MOVEMENT_RESPONSE.getX(), MOVEMENT_RESPONSE.getY()))
                .thenReturn(false);

        RoverDto roverDto = roverService.move(roverRequest);
        
        verify(roverRepository, times(1)).save(ROVER);
        
        assertThat(roverDto.getX()).isEqualTo(X_FINAL_COORDINATE);
        assertThat(roverDto.getY()).isEqualTo(Y_FINAL_COORDINATE);
        assertThat(roverDto.getDirection()).isEqualTo(FINAL_DIRECTION.name());
        
    }
    
    @Test
    public void throw_an_exception_when_an_obstacle_is_blocking() {
        when(roverRepository.findById(ROVER_ID)).thenReturn(Optional.of(ROVER));
        when(movementEngine.activeEngineMovement(ROVER, Movement.FORWARD))
                .thenReturn(MOVEMENT_RESPONSE);

        when(obstacleHandler.existsObstacleByCoordinates(MOVEMENT_RESPONSE.getX(), MOVEMENT_RESPONSE.getY()))
                .thenReturn(true);

        assertThrows(RoverBlockedException.class, () -> {
            roverService.move(roverRequest);
        });

        verify(roverRepository, times(0)).save(ROVER);
    }
    
    @Test
    public void throw_an_exception_when_its_out_of_map() {
        roverRequest.setSizex(5);
        roverRequest.setSizex(5);

        when(roverRepository.findById(ROVER_ID)).thenReturn(Optional.of(ROVER));
        when(movementEngine.activeEngineMovement(ROVER, Movement.FORWARD))
                .thenReturn(MOVEMENT_RESPONSE);

        when(obstacleHandler.existsObstacleByCoordinates(MOVEMENT_RESPONSE.getX(), MOVEMENT_RESPONSE.getY()))
                .thenReturn(false);

        assertThrows(RoverBlockedException.class, () -> {
            roverService.move(roverRequest);
        });

        verify(roverRepository, times(0)).save(ROVER);
        
    }
    
}
