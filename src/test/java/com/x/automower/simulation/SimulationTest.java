package com.x.automower.simulation;

import com.x.automower.parsing.OrientationParser;
import com.x.automower.parsing.TransformParser;
import com.x.automower.parsing.Vector2DParser;
import com.x.automower.service.ConsoleMowerRenderer;
import com.x.automower.service.MowerRenderer;
import com.x.automower.simulation.math.Orientation;
import com.x.automower.simulation.math.Transform;
import com.x.automower.simulation.math.Vector2D;
import com.x.automower.simulation.navigation.MoveForwardInstruction;
import com.x.automower.simulation.navigation.TurnLeftInstruction;
import com.x.automower.simulation.navigation.TurnRightInstruction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;

class SimulationTest {

    private MowerRenderer mowerRenderer;

    @BeforeEach
    void setUp() {
        mowerRenderer = new ConsoleMowerRenderer(new TransformParser(new Vector2DParser(), new OrientationParser()));
    }

    @Test
    void simulateOneMower() {
        var mowers = asList(
                new Mower(new Transform(new Vector2D(2, 2), Orientation.NORTH), asList(
                        new MoveForwardInstruction()
                ))
        );
        var scene = new Simulation(new Vector2D(10, 10), mowers, mowerRenderer);
        scene.simulate();

        Assertions.assertThat(mowers.get(0).getTransform()).isEqualTo(new Transform(new Vector2D(2, 3), Orientation.NORTH));
    }

    @Test
    void simulateOneMowerWithoutNavigationInstruction() {
        var mowers = asList(
                new Mower(new Transform(new Vector2D(2, 2), Orientation.NORTH), asList())
        );
        var scene = new Simulation(new Vector2D(10, 10), mowers, mowerRenderer);
        scene.simulate();

        Assertions.assertThat(mowers.get(0).getTransform()).isEqualTo(new Transform(new Vector2D(2, 2), Orientation.NORTH));
    }

    @Test
    void simulateOneMowerOnASingleCellLawn() {
        var mowers = asList(
                new Mower(new Transform(new Vector2D(0, 0), Orientation.EAST), asList(
                        new MoveForwardInstruction(), // outside (does nothing)
                        new TurnRightInstruction(),
                        new TurnLeftInstruction(),
                        new TurnRightInstruction()
                ))
        );
        var scene = new Simulation(new Vector2D(0, 0), mowers, mowerRenderer);
        scene.simulate();

        Assertions.assertThat(mowers.get(0).getTransform()).isEqualTo(new Transform(new Vector2D(0, 0), Orientation.SOUTH));
    }

    @Test
    void simulateOneOutsideMower() {
        var mowers = asList(
                new Mower(new Transform(new Vector2D(2, 2), Orientation.NORTH), asList(
                        new TurnRightInstruction(),
                        new TurnRightInstruction(),
                        new MoveForwardInstruction(),
                        new MoveForwardInstruction(),
                        new MoveForwardInstruction(), // outside (does nothing)
                        new MoveForwardInstruction(), // outside again (does nothing)
                        new TurnRightInstruction(),
                        new MoveForwardInstruction()
                ))
        );
        var scene = new Simulation(new Vector2D(10, 10), mowers, mowerRenderer);
        scene.simulate();

        Assertions.assertThat(mowers.get(0).getTransform()).isEqualTo(new Transform(new Vector2D(1, 0), Orientation.WEST));
    }

    @Test
    void simulateTwoMowers() {
        var mowers = asList(
                new Mower(new Transform(new Vector2D(2, 2), Orientation.NORTH), asList(
                        new MoveForwardInstruction()
                )),
                new Mower(new Transform(new Vector2D(5, 2), Orientation.NORTH), asList(
                        new MoveForwardInstruction(),
                        new MoveForwardInstruction(),
                        new TurnRightInstruction(),
                        new MoveForwardInstruction()
                ))
        );
        var scene = new Simulation(new Vector2D(10, 10), mowers, mowerRenderer);
        scene.simulate();

        Assertions.assertThat(mowers.get(0).getTransform()).isEqualTo(new Transform(new Vector2D(2, 3), Orientation.NORTH));
        Assertions.assertThat(mowers.get(1).getTransform()).isEqualTo(new Transform(new Vector2D(6, 4), Orientation.EAST));
    }

    @Test
    void simulateTwoEncounteringMowers() {
        var mowers = asList(
                new Mower(new Transform(new Vector2D(2, 2), Orientation.NORTH), asList(
                        new MoveForwardInstruction()
                )),
                new Mower(new Transform(new Vector2D(5, 3), Orientation.NORTH), asList(
                        new TurnLeftInstruction(),
                        new MoveForwardInstruction(),
                        new MoveForwardInstruction(),
                        new MoveForwardInstruction(), // hit (does nothing)
                        new MoveForwardInstruction(), // hit again (does nothing)
                        new TurnRightInstruction(),
                        new MoveForwardInstruction()
                ))
        );
        var scene = new Simulation(new Vector2D(10, 10), mowers, mowerRenderer);
        scene.simulate();

        Assertions.assertThat(mowers.get(0).getTransform()).isEqualTo(new Transform(new Vector2D(2, 3), Orientation.NORTH));
        Assertions.assertThat(mowers.get(1).getTransform()).isEqualTo(new Transform(new Vector2D(3, 4), Orientation.NORTH));
    }
}