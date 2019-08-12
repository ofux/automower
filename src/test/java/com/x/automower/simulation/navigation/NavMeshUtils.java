package com.x.automower.simulation.navigation;

import com.x.automower.simulation.Mower;
import com.x.automower.simulation.math.Orientation;
import com.x.automower.simulation.math.Transform;
import com.x.automower.simulation.math.Vector2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class NavMeshUtils {

    public static NavigationMesh createSimpleNavMesh() {
        return new SimpleNavigationMesh(new Vector2D(0, 0), new Vector2D(10, 10), new ArrayList<>());
    }

    public static NavigationMesh createNavMesh(Vector2D minPosition, Vector2D maxPosition, Vector2D... obstacles) {
        var mowers = Arrays.stream(obstacles)
                .map(obstacle -> new Mower(new Transform(obstacle, Orientation.NORTH), new ArrayList<>()))
                .collect(Collectors.toCollection(ArrayList::new));
        return new SimpleNavigationMesh(minPosition, maxPosition, mowers);
    }
}
