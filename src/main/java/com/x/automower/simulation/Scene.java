package com.x.automower.simulation;

import com.x.automower.simulation.math.Vector2D;
import com.x.automower.simulation.navigation.NavigationMesh;
import com.x.automower.simulation.navigation.SimpleNavigationMesh;

import java.util.List;

public class Scene {
    private final List<Mower> mowers;
    private final NavigationMesh navigationMesh;

    public Scene(Vector2D lawnSize, List<Mower> mowers) {
        this.mowers = mowers;
        this.navigationMesh = new SimpleNavigationMesh(new Vector2D(0, 0), lawnSize, mowers);
    }

    public void simulate() {
        mowers.forEach(mower -> mower.simulate(navigationMesh));
    }
}
