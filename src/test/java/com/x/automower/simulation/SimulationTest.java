package com.x.automower.simulation;

import com.x.automower.parsing.OrientationParser;
import com.x.automower.parsing.TransformParser;
import com.x.automower.parsing.Vector2DParser;
import com.x.automower.service.ConsoleMowerRenderer;
import com.x.automower.service.MowerRenderer;
import com.x.automower.simulation.math.Orientation;
import com.x.automower.simulation.math.Transform;
import com.x.automower.simulation.math.Vector2D;
import com.x.automower.simulation.navigation.MoveForwardAction;
import com.x.automower.simulation.navigation.TurnLeftAction;
import com.x.automower.simulation.navigation.TurnRightAction;
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
                        new MoveForwardAction()
                ))
        );
        var scene = new Simulation(new Vector2D(10, 10), mowers, mowerRenderer);
        scene.simulate();

        Assertions.assertThat(mowers.get(0).getTransform()).isEqualTo(new Transform(new Vector2D(2, 3), Orientation.NORTH));
    }

    @Test
    void simulateOneMowerOnASingleCellLawn() {
        var mowers = asList(
                new Mower(new Transform(new Vector2D(0, 0), Orientation.EAST), asList(
                        new MoveForwardAction(), // outside (does nothing)
                        new TurnRightAction(),
                        new TurnLeftAction(),
                        new TurnRightAction()
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
                        new TurnRightAction(),
                        new TurnRightAction(),
                        new MoveForwardAction(),
                        new MoveForwardAction(),
                        new MoveForwardAction(), // outside (does nothing)
                        new MoveForwardAction(), // outside again (does nothing)
                        new TurnRightAction(),
                        new MoveForwardAction()
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
                        new MoveForwardAction()
                )),
                new Mower(new Transform(new Vector2D(5, 2), Orientation.NORTH), asList(
                        new MoveForwardAction(),
                        new MoveForwardAction(),
                        new TurnRightAction(),
                        new MoveForwardAction()
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
                        new MoveForwardAction()
                )),
                new Mower(new Transform(new Vector2D(5, 3), Orientation.NORTH), asList(
                        new TurnLeftAction(),
                        new MoveForwardAction(),
                        new MoveForwardAction(),
                        new MoveForwardAction(), // hit (does nothing)
                        new MoveForwardAction(), // hit again (does nothing)
                        new TurnRightAction(),
                        new MoveForwardAction()
                ))
        );
        var scene = new Simulation(new Vector2D(10, 10), mowers, mowerRenderer);
        scene.simulate();

        Assertions.assertThat(mowers.get(0).getTransform()).isEqualTo(new Transform(new Vector2D(2, 3), Orientation.NORTH));
        Assertions.assertThat(mowers.get(1).getTransform()).isEqualTo(new Transform(new Vector2D(3, 4), Orientation.NORTH));
    }
}