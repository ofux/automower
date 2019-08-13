package com.x.automower.simulation.navigation;

import com.x.automower.simulation.math.Transform;

public class TurnLeftInstruction implements NavigationInstruction {
    @Override
    public Transform apply(NavigationMesh navigationMesh, Transform transform) {
        return transform.withOrientation(transform.getOrientation().turnLeft());
    }

    @Override
    public String getCode() {
        return "L";
    }
}
