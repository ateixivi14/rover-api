package com.training.roverapi.engine;

import com.training.roverapi.domain.Direction;
import com.training.roverapi.domain.Movement;
import com.training.roverapi.service.MovementEngine;
import com.training.roverapi.domain.MovementEngineResponse;
import com.training.roverapi.entity.Rover;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MovementEngineShould {
    
    private final MovementEngine movementEngine = new MovementEngine();
    
    public static MovementEngineResponse FORWARD_MOVEMENT_IN_NORTH = MovementEngineResponse.builder()
            .x(5)
            .y(6)
            .direction(Direction.NORTH)
            .build();

    public static MovementEngineResponse BACKWARD_MOVEMENT_IN_NORTH = MovementEngineResponse.builder()
            .x(5)
            .y(4)
            .direction(Direction.NORTH)
            .build();

    public static MovementEngineResponse FORWARD_MOVEMENT_IN_WEST = MovementEngineResponse.builder()
            .x(6)
            .y(5)
            .direction(Direction.WEST)
            .build();

    public static MovementEngineResponse BACKWARD_MOVEMENT_IN_WEST = MovementEngineResponse.builder()
            .x(4)
            .y(5)
            .direction(Direction.WEST)
            .build();

    public static MovementEngineResponse ROTATE_LEFT_FROM_NORTH = MovementEngineResponse.builder()
            .x(5)
            .y(5)
            .direction(Direction.WEST)
            .build();

    public static MovementEngineResponse ROTATE_RIGHT_FROM_NORTH = MovementEngineResponse.builder()
            .x(5)
            .y(5)
            .direction(Direction.EAST)
            .build();

    @Test
    public void apply_correct_movement_in_y_axis() {
        Rover rover = Rover.builder()
                .x(5)
                .y(5)
                .direction(Direction.NORTH)
                .build();
        
        assertThat(movementEngine.activeEngineMovement(rover, Movement.FORWARD)).isEqualTo(FORWARD_MOVEMENT_IN_NORTH);
        assertThat(movementEngine.activeEngineMovement(rover, Movement.BACKWARD)).isEqualTo(BACKWARD_MOVEMENT_IN_NORTH);
    }

    @Test
    public void apply_correct_movement_in_x_axis() {
        Rover rover = Rover.builder()
                .x(5)
                .y(5)
                .direction(Direction.WEST)
                .build();

        assertThat(movementEngine.activeEngineMovement(rover, Movement.FORWARD)).isEqualTo(FORWARD_MOVEMENT_IN_WEST);
        assertThat(movementEngine.activeEngineMovement(rover, Movement.BACKWARD)).isEqualTo(BACKWARD_MOVEMENT_IN_WEST);
    }
    
    @Test
    public void apply_correct_movement_when_rotate() {
        Rover rover = Rover.builder()
                .x(5)
                .y(5)
                .direction(Direction.NORTH)
                .build();

        assertThat(movementEngine.activeEngineMovement(rover, Movement.TURN_LEFT)).isEqualTo(ROTATE_LEFT_FROM_NORTH);
        assertThat(movementEngine.activeEngineMovement(rover, Movement.TURN_RIGHT)).isEqualTo(ROTATE_RIGHT_FROM_NORTH);

    }
    
}
