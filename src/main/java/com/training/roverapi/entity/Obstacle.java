package com.training.roverapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Obstacle {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    
    private int coordinateX;
    
    private int coordinateY;
}
