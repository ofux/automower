package com.x.automower.simulation;

import com.x.automower.simulation.math.Orientation;
import com.x.automower.simulation.math.Transform;
import com.x.automower.simulation.math.Vector2D;
import com.x.automower.simulation.navigation.MoveForwardInstruction;
import com.x.automower.simulation.navigation.NavMeshUtils;
import com.x.automower.simulation.navigation.TurnLeftInstruction;
import com.x.automower.simulation.navigation.TurnRightInstruction;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class MowerTest {

    @Test
    void simulateExample1() {
        // Arrange
        var navMesh = NavMeshUtils.createNavMesh(new Vector2D(0, 0), new Vector2D(5, 5));
        var mower = new Mower(new Transform(new Vector2D(1, 2), Orientation.NORTH), asList(
                new TurnLeftInstruction(),
                new MoveForwardInstruction(),
                new TurnLeftInstruction(),
                new MoveForwardInstruction(),
                new TurnLeftInstruction(),
                new MoveForwardInstruction(),
                new TurnLeftInstruction(),
                new MoveForwardInstruction(),
                new MoveForwardInstruction()
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
                new MoveForwardInstruction(),
                new MoveForwardInstruction(),
                new TurnRightInstruction(),
                new MoveForwardInstruction(),
                new MoveForwardInstruction(),
                new TurnRightInstruction(),
                new MoveForwardInstruction(),
                new TurnRightInstruction(),
                new TurnRightInstruction(),
                new MoveForwardInstruction()
        ));

        // Act
        mower.simulate(navMesh);

        // Assert
        assertThat(mower.getTransform()).isEqualTo(new Transform(new Vector2D(5, 1), Orientation.EAST));
    }
}