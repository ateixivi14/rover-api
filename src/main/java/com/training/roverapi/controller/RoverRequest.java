package com.training.roverapi.controller;

import com.training.roverapi.domain.Movement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoverRequest {
    
    private Long id;
    
    private int sizex;
    
    private int sizey;
    
    private Movement command;
    
}
