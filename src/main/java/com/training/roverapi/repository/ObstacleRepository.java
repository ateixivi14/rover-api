package com.training.roverapi.repository;

import com.training.roverapi.entity.Obstacle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObstacleRepository extends JpaRepository<Obstacle, Long> {
    boolean existsObstacleByCoordinateXAndCoordinateY(int x, int y);
}
