package com.x.automower.simulation.math;

import java.util.Objects;

public class Transform {
    private final Vector2D position;
    private final Orientation orientation;

    public Transform(Vector2D position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public Vector2D getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Transform withPosition(Vector2D newPosition) {
        return new Transform(newPosition, this.orientation);
    }

    public Transform withOrientation(Orientation newOrientation) {
        return new Transform(this.position, newOrientation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transform transform = (Transform) o;
        return Objects.equals(position, transform.position) &&
                orientation == transform.orientation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, orientation);
    }

    @Override
    public String toString() {
        return "Transform{" +
                "position=" + position +
                ", orientation=" + orientation +
                '}';
    }
}
