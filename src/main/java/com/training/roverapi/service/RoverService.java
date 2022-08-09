package com.training.roverapi.service;

import com.training.roverapi.controller.RoverRequest;
import com.training.roverapi.domain.Movement;
import com.training.roverapi.domain.MovementEngine;
import com.training.roverapi.domain.MovementEngineResponse;
import com.training.roverapi.dto.RoverDto;
import com.training.roverapi.entity.Rover;
import com.training.roverapi.repository.RoverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoverService {
    
    private final RoverRepository roverRepository;
    
    private final ObstacleHandler obstacleHandler;
    
    private final MovementEngine movementEngine;
    
    public RoverDto move(RoverRequest roverRequest) {
        
        Rover rover = roverRepository.findById(roverRequest.getId()).orElseThrow();
        
        MovementEngineResponse movementEngineResponse = movementEngine.activeEngineMovement(rover, roverRequest.getCommand());
        
        if (isObstacleBlocking(movementEngineResponse) || isOutTheMap(movementEngineResponse, roverRequest.getSizex(), roverRequest.getSizey())) {
                throw new RoverBlockedException();
        } 
        updateRover(rover, movementEngineResponse);
        roverRepository.save(rover);
        
        return RoverDto.toDto(rover);
    }

    private boolean isObstacleBlocking(MovementEngineResponse movementEngineResponse) {
        return obstacleHandler.existsObstacleByCoordinates(movementEngineResponse.getX(), movementEngineResponse.getY());
    }
    
    
    
    private boolean isOutTheMap(MovementEngineResponse movementEngineResponse, int mapX, int mapY) {
        return movementEngineResponse.getY() > mapY || movementEngineResponse.getX() > mapX;
    }
    
    private void updateRover(Rover rover, MovementEngineResponse movementEngineResponse) {
        rover.setY(movementEngineResponse.getY());
        rover.setX(movementEngineResponse.getX());
        rover.setDirection(movementEngineResponse.getDirection());
    }
}
