package com.x.automower.simulation;

import com.x.automower.service.MowerRenderer;
import com.x.automower.simulation.math.Vector2D;
import com.x.automower.simulation.navigation.NavigationMesh;
import com.x.automower.simulation.navigation.SimpleNavigationMesh;

import java.util.List;

public class Simulation {
    private final List<Mower> mowers;
    private final NavigationMesh navigationMesh;
    private final MowerRenderer mowerRenderer;

    public Simulation(Vector2D lawnSize, List<Mower> mowers, MowerRenderer mowerRenderer) {
        this.mowers = mowers;
        this.navigationMesh = new SimpleNavigationMesh(new Vector2D(0, 0), lawnSize, mowers);
        this.mowerRenderer = mowerRenderer;
    }

    public void simulate() {
        mowers.forEach(mower -> {
            mower.simulate(navigationMesh);
            mowerRenderer.render(mower);
        });
    }
}
