package com.x.automower.simulation;

import com.x.automower.simulation.math.Orientation;
import com.x.automower.simulation.math.Transform;
import com.x.automower.simulation.math.Vector2D;
import com.x.automower.simulation.navigation.MoveForwardAction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;

class SceneTest {

    @Test
    void simulate() {
        var mowers = asList(
                new Mower(new Transform(new Vector2D(2, 2), Orientation.NORTH), asList(
                        new MoveForwardAction()
                ))
        );
        var scene = new Scene(new Vector2D(10, 10), mowers);
        scene.simulate();

        Assertions.assertThat(mowers.get(0).getTransform().getPosition()).isEqualTo(new Vector2D(2, 3));
    }
}