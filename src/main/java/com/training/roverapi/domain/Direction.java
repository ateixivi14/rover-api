package com.training.roverapi.domain;

import java.util.Map;

//n = north, e = east, w = west, s = south
public enum Direction {
    NORTH,
    EAST,
    WEST,
    SOUTH;

    public static Map<Direction, Direction> TURN_LEFT_RULES = Map.ofEntries(
            Map.entry(Direction.NORTH, Direction.WEST),
            Map.entry(Direction.WEST, Direction.SOUTH),
            Map.entry(Direction.SOUTH, Direction.EAST),
            Map.entry(Direction.EAST, Direction.NORTH)
    );

    public static Map<Direction, Direction> TURN_RIGHT_RULES = Map.ofEntries(
            Map.entry(Direction.NORTH, Direction.EAST),
            Map.entry(Direction.EAST, Direction.SOUTH),
            Map.entry(Direction.SOUTH, Direction.WEST),
            Map.entry(Direction.WEST, Direction.NORTH)
    );
}
