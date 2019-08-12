package com.x.automower.simulation.navigation;

import com.x.automower.simulation.math.Vector2D;

public interface NavigationMesh {
    boolean isNavigable(Vector2D position);

    void setPositionAsNavigable(Vector2D position);

    void setPositionAsNotNavigable(Vector2D position);
}
