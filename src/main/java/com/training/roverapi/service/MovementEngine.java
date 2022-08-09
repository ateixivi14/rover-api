package com.training.roverapi.service;

import com.training.roverapi.domain.Direction;
import com.training.roverapi.domain.Movement;
import com.training.roverapi.domain.MovementEngineResponse;
import com.training.roverapi.entity.Rover;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.training.roverapi.domain.Direction.TURN_LEFT_RULES;
import static com.training.roverapi.domain.Direction.TURN_RIGHT_RULES;

@Component
public class MovementEngine {
    
    public static List<Movement> ADVANCE_MOVEMENTS = List.of(Movement.FORWARD, Movement.BACKWARD);
    
    
    public MovementEngineResponse activeEngineMovement(Rover rover, Movement movement) {
        switch(rover.getDirection()) {
            case NORTH, SOUTH -> {
                 return ADVANCE_MOVEMENTS.contains(movement) ? getMovementEngineResponse(rover.getX(), move(rover.getY(), movement), rover.getDirection())
                        : rotate(rover, movement);
           
            }
            case EAST, WEST-> {
                return ADVANCE_MOVEMENTS.contains(movement) ? getMovementEngineResponse(move(rover.getX(), movement), rover.getY(), rover.getDirection())
                    : rotate(rover, movement);

            }
            default -> {
                return getMovementEngineResponse(rover.getX(), rover.getY(), rover.getDirection());
            } 
        }
    }
    
    private int move(int coordinate, Movement movement) {
        switch (movement) {
            case FORWARD -> {
                return coordinate + 1;
            }
            case BACKWARD -> {
                return coordinate-1;
            }
            default -> {
                return coordinate;
            }
        }
    }
    
    private MovementEngineResponse rotate(Rover rover, Movement movement) {
        switch (movement) {
            case TURN_LEFT -> {
                return getMovementEngineResponse(rover.getX(), rover.getY(), TURN_LEFT_RULES.get(rover.getDirection()));
            }
            case TURN_RIGHT -> {
                return getMovementEngineResponse(rover.getX(), rover.getY(), TURN_RIGHT_RULES.get(rover.getDirection()));
            }
            default -> {
                return getMovementEngineResponse(rover.getX(), rover.getY(), rover.getDirection());
            }
        }
    }
    
    private MovementEngineResponse getMovementEngineResponse(int x, int y, Direction direction) {
        return MovementEngineResponse.builder()
                .direction(direction)
                .x(x)
                .y(y)
                .build();
    }
}
