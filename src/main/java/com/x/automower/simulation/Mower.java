package com.x.automower.simulation;

import com.x.automower.simulation.math.Transform;
import com.x.automower.simulation.math.Vector2D;
import com.x.automower.simulation.navigation.NavigationInstruction;
import com.x.automower.simulation.navigation.NavigationMesh;

import java.util.List;

public class Mower {
    private Transform transform;

    private final List<NavigationInstruction> navigationInstructions;

    public Mower(Transform transform, List<NavigationInstruction> navigationInstructions) {
        this.transform = transform;
        this.navigationInstructions = navigationInstructions;
    }

    public Transform getTransform() {
        return transform;
    }

    public Vector2D getPosition() {
        return transform.getPosition();
    }

    public void simulate(NavigationMesh navigationMesh) {
        navigationInstructions.forEach(instruction -> transform = instruction.apply(navigationMesh, transform));
    }
}
