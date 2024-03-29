package com.x.automower.simulation.navigation;

import com.x.automower.simulation.Mower;
import com.x.automower.simulation.math.Vector2D;

import java.util.HashSet;
import java.util.List;

public class SimpleNavigationMesh implements NavigationMesh {
    private final Vector2D minPosition;
    private final Vector2D maxPosition;
    private final HashSet<Vector2D> obstacles;

    public SimpleNavigationMesh(Vector2D minPosition, Vector2D maxPosition, List<Mower> mowers) {
        this.minPosition = minPosition;
        this.maxPosition = maxPosition;
        if (minPosition.getX() > maxPosition.getX() || minPosition.getY() > maxPosition.getY()) {
            throw new IllegalArgumentException("Lawn size must be greater than 0");
        }

        this.obstacles = new HashSet<>();
        for (var mower : mowers) {
            if (!isNavigable(mower.getPosition())) {
                throw new IllegalStateException("Mower is outside of lawn or there is another mower at the same position");
            }
            obstacles.add(mower.getPosition());
        }
    }

    @Override
    public boolean isNavigable(Vector2D position) {
        return position.getX() >= minPosition.getX() && position.getX() <= maxPosition.getX() &&
                position.getY() >= minPosition.getY() && position.getY() <= maxPosition.getY() &&
                // I assume that there can't be more than one mower at a given position
                !obstacles.contains(position);
    }

    @Override
    public void setPositionAsNavigable(Vector2D position) {
        obstacles.remove(position);
    }

    @Override
    public void setPositionAsNotNavigable(Vector2D position) {
        obstacles.add(position);
    }
}
