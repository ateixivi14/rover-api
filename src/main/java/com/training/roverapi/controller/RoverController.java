package com.training.roverapi.controller;

import com.training.roverapi.dto.RoverDto;
import com.training.roverapi.service.ObstacleHandler;
import com.training.roverapi.service.RoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mars-rover")
public class RoverController {
    
    private final RoverService roverService;
    
    private final ObstacleHandler obstacleHandler;

    @PostMapping("/move")
    public RoverDto moveRover(@RequestBody RoverRequest roverRequest) {
        return roverService.move(roverRequest);
    }
    
    @PostMapping("/obstacles")
    public void introduceObstacles(@RequestBody ObstaclesRequest obstaclesRequest) {
        obstacleHandler.saveObstacles(obstaclesRequest.getObstacles());
    }
    
}
