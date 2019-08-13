package com.x.automower.simulation.navigation;

import com.x.automower.simulation.math.Transform;

public interface NavigationInstruction {
    Transform apply(NavigationMesh navigationMesh, Transform agent);
    String getCode();
}
