package com.x.automower.simulation.navigation;

import com.x.automower.simulation.Mower;
import com.x.automower.simulation.math.Orientation;
import com.x.automower.simulation.math.Transform;
import com.x.automower.simulation.math.Vector2D;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.x.automower.simulation.navigation.NavMeshUtils.createNavMesh;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleNavigationMeshTest {

    @Test
    void isNavigable() {
        // Arrange
        var navMesh = createNavMesh(new Vector2D(-10, 0), new Vector2D(10, 5), new Vector2D(3, 4), new Vector2D(-3, 0));

        // Act
        var navigable = navMesh.isNavigable(new Vector2D(4, 4));
        var navigableOnBorder = navMesh.isNavigable(new Vector2D(10, 5));
        var navigableOnLowBorder = navMesh.isNavigable(new Vector2D(-10, 0));
        var notNavigableOutsideX = navMesh.isNavigable(new Vector2D(40, 4));
        var notNavigableOutsideY = navMesh.isNavigable(new Vector2D(0, -1));
        var notNavigableObstacle = navMesh.isNavigable(new Vector2D(3, 4));

        // Assert
        assertThat(navigable).isTrue();
        assertThat(navigableOnBorder).isTrue();
        assertThat(navigableOnLowBorder).isTrue();
        assertThat(notNavigableOutsideX).isFalse();
        assertThat(notNavigableOutsideY).isFalse();
        assertThat(notNavigableObstacle).isFalse();
    }

    @Test
    void setPositionAsNavigable() {
        // Arrange
        var navMesh = createNavMesh(new Vector2D(0, 0), new Vector2D(10, 10), new Vector2D(3, 4));
        assertThat(navMesh.isNavigable(new Vector2D(3, 4))).isFalse();

        // Act
        navMesh.setPositionAsNavigable(new Vector2D(3, 4));

        // Assert
        assertThat(navMesh.isNavigable(new Vector2D(3, 4))).isTrue();
    }

    @Test
    void setPositionAsNotNavigable() {
        // Arrange
        var navMesh = createNavMesh(new Vector2D(0, 0), new Vector2D(10, 10));
        assertThat(navMesh.isNavigable(new Vector2D(3, 4))).isTrue();

        // Act
        navMesh.setPositionAsNotNavigable(new Vector2D(3, 4));

        // Assert
        assertThat(navMesh.isNavigable(new Vector2D(3, 4))).isFalse();
    }
}