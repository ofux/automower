package com.x.automower.simulation.math;

public enum Orientation {
    NORTH(new Vector2D(0, 1), "N"),
    EAST(new Vector2D(1, 0), "E"),
    WEST(new Vector2D(-1, 0), "W"),
    SOUTH(new Vector2D(0, -1), "S");

    private final Vector2D positionModifier;
    private final String code;

    Orientation(Vector2D positionModifier, String code) {
        this.positionModifier = positionModifier;
        this.code = code;
    }

    public Vector2D getForwardPosition(Vector2D position) {
        return position.add(positionModifier);
    }

    public Orientation turnLeft() {
        switch (this) {
            case NORTH:
                return WEST;
            case EAST:
                return NORTH;
            case WEST:
                return SOUTH;
            case SOUTH:
                return EAST;
        }
        throw new IllegalStateException("Undefined orientation");
    }

    public Orientation turnRight() {
        switch (this) {
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case WEST:
                return NORTH;
            case SOUTH:
                return WEST;
        }
        throw new IllegalStateException("Undefined orientation");
    }

    public String getCode() {
        return code;
    }
}

