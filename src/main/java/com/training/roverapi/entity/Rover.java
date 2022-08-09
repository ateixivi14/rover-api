package com.training.roverapi.entity;

import com.training.roverapi.domain.Direction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rover {
    
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    
    private int x;
    
    private int y;
    
    @Enumerated(EnumType.STRING)
    private Direction direction;
    
}
