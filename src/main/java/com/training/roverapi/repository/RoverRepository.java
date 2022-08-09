package com.training.roverapi.repository;

import com.training.roverapi.entity.Rover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoverRepository extends JpaRepository<Rover, Long> {
}
