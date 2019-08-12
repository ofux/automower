package com.x.automower.simulation;

import com.x.automower.simulation.math.Orientation;
import com.x.automower.simulation.math.Transform;
import com.x.automower.simulation.math.Vector2D;
import com.x.automower.simulation.navigation.MoveForwardAction;
import com.x.automower.simulation.navigation.NavMeshUtils;
import com.x.automower.simulation.navigation.TurnLeftAction;
import com.x.automower.simulation.navigation.TurnRightAction;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class MowerTest {

    @Test
    void simulateExample1() {
        // Arrange
        var navMesh = NavMeshUtils.createNavMesh(new Vector2D(0, 0), new Vector2D(5, 5));
        var mower = new Mower(new Transform(new Vector2D(1, 2), Orientation.NORTH), asList(
                new TurnLeftAction(),
                new MoveForwardAction(),
                new TurnLeftAction(),
                new MoveForwardAction(),
                new TurnLeftAction(),
                new MoveForwardAction(),
                new TurnLeftAction(),
                new MoveForwardAction(),
                new MoveForwardAction()
        ));

        // Act
        mower.simulate(navMesh);

        // Assert
        assertThat(mower.getTransform()).isEqualTo(new Transform(new Vector2D(1, 3), Orientation.NORTH));
    }

    @Test
    void simulateExample2() {
        // Arrange
        var navMesh = NavMeshUtils.createNavMesh(new Vector2D(0, 0), new Vector2D(5, 5));
        var mower = new Mower(new Transform(new Vector2D(3, 3), Orientation.EAST), asList(
                new MoveForwardAction(),
                new MoveForwardAction(),
                new TurnRightAction(),
                new MoveForwardAction(),
                new MoveForwardAction(),
                new TurnRightAction(),
                new MoveForwardAction(),
                new TurnRightAction(),
                new TurnRightAction(),
                new MoveForwardAction()
        ));

        // Act
        mower.simulate(navMesh);

        // Assert
        assertThat(mower.getTransform()).isEqualTo(new Transform(new Vector2D(5, 1), Orientation.EAST));
    }
}