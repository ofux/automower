package com.x.automower.simulation.navigation;

import com.x.automower.simulation.math.Transform;

public class MoveForwardInstruction implements NavigationInstruction {
    @Override
    public Transform apply(NavigationMesh navigationMesh, Transform transform) {
        var nextPosition = transform.getOrientation().getForwardPosition(transform.getPosition());
        if (navigationMesh.isNavigable(nextPosition)) {
            navigationMesh.setPositionAsNavigable(transform.getPosition());
            navigationMesh.setPositionAsNotNavigable(nextPosition);
            return transform.withPosition(nextPosition);
        }
        return transform;
    }

    @Override
    public String getCode() {
        return "F";
    }
}
