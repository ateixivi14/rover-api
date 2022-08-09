package com.training.roverapi.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.roverapi.controller.ObstaclesRequest;
import com.training.roverapi.controller.RoverController;
import com.training.roverapi.controller.RoverRequest;
import com.training.roverapi.domain.Direction;
import com.training.roverapi.domain.Movement;
import com.training.roverapi.dto.ObstacleDto;
import com.training.roverapi.dto.RoverDto;
import com.training.roverapi.service.ObstacleHandler;
import com.training.roverapi.service.RoverService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoverController.class)
public class RoverApiIntegrationShould {

    private static final String API_ROVER = "/mars-rover";
    private static final String MOVE = "/move";
    private static final String OBSTACLES = "/obstacles";
    private static Long ROVER_ID = 1L;
    private static int X_INITIAL_COORDINATE = 5;
    private static int X_MAP_SIZE = 10;
    private static int Y_MAP_SIZE = 10;
    private static int Y_INITIAL_COORDINATE = 5;
    private static int X_FINAL_COORDINATE = 5;
    private static int Y_FINAL_COORDINATE = 6;
    private static Direction INITIAL_DIRECTION = Direction.NORTH;
    private static Direction FINAL_DIRECTION = Direction.NORTH;
    private static Movement COMMAND = Movement.FORWARD ;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @MockBean
    private RoverService roverService;
    
    @MockBean
    private ObstacleHandler obstacleHandler;

    private static final String ROVER_REQUEST = "{ " +
            "\"id\": \"" + ROVER_ID + "\"," +
            "\"sizex\":\"" + X_MAP_SIZE + "\"," +
            "\"sizey\":\"" + Y_MAP_SIZE +  "\"," +
            "\"command\":\"" + COMMAND + "\"" +
            "}";

    @Test
    void return_rover_dto_updated() throws Exception {
        when(roverService.move(createRoverRequest())).thenReturn(createRoverDto());

        RequestBuilder postRequest = post(API_ROVER + MOVE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ROVER_REQUEST);

        mockMvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(createRoverDto())));
    }
    
    @Test
    void add_obstacles() throws Exception {
        RequestBuilder postRequest = post(API_ROVER + OBSTACLES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createObstacleRequest()));

        mockMvc.perform(postRequest)
                .andExpect(status().isOk());
    }
    
    private ObstaclesRequest createObstacleRequest() {
       return ObstaclesRequest.builder()
                .obstacles(List.of(ObstacleDto.builder()
                                .coordinateX(10)
                                .coordinateY(10)
                        .build()))
                .build();
    }
    
    private RoverRequest createRoverRequest(){
        return RoverRequest.builder()
                .id(1L)
                .command(COMMAND)
                .sizex(X_MAP_SIZE)
                .sizey(Y_MAP_SIZE)
                .build();
    }
    
    private RoverDto createRoverDto() {
        return RoverDto.builder()
                .x(X_FINAL_COORDINATE)
                .y(Y_FINAL_COORDINATE)
                .direction(FINAL_DIRECTION.name())
                .build();

    }

}
