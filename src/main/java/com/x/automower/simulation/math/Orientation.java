package com.x.automower.simulation.math;

public enum Orientation {
    NORTH(new Vector2D(0, 1)),
    EAST(new Vector2D(1, 0)),
    WEST(new Vector2D(-1, 0)),
    SOUTH(new Vector2D(0, -1));

    private final Vector2D positionModifier;

    Orientation(Vector2D positionModifier) {
        this.positionModifier = positionModifier;
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
}

