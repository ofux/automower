package com.x.automower.simulation.navigation;

import com.x.automower.simulation.math.Vector2D;
import org.junit.jupiter.api.Test;

import static com.x.automower.simulation.navigation.NavMeshUtils.createNavMesh;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleNavigationMeshTest {

    @Test
    void constructorWithObstacles() {
        // Arrange - Act
        var navMesh = createNavMesh(new Vector2D(-10, 0), new Vector2D(10, 5), new Vector2D(3, 4), new Vector2D(-3, 0));

        // Assert
        assertThat(navMesh.isNavigable(new Vector2D(0, 0))).isTrue();
        assertThat(navMesh.isNavigable(new Vector2D(3, 4))).isFalse();
        assertThat(navMesh.isNavigable(new Vector2D(-3, 0))).isFalse();
        assertThat(navMesh.isNavigable(new Vector2D(11, 0))).isFalse();
    }

    @Test
    void constructorWithConflictingObstacles() {
        assertThatThrownBy(() -> {
            // Act
            createNavMesh(new Vector2D(-10, 0), new Vector2D(10, 5), new Vector2D(3, 4), new Vector2D(3, 4));
        })
                // Assert
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void constructorWithOutsideObstacles() {
        assertThatThrownBy(() -> {
            // Act
            createNavMesh(new Vector2D(-10, 0), new Vector2D(10, 5), new Vector2D(30, 4));
        })
                // Assert
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void constructorWithNegativeXSize() {
        assertThatThrownBy(() -> {
            // Act
            createNavMesh(new Vector2D(0, 0), new Vector2D(-1, 5));
        })
                // Assert
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void constructorWithNegativeYSize() {
        assertThatThrownBy(() -> {
            // Act
            createNavMesh(new Vector2D(0, 0), new Vector2D(5, -1));
        })
                // Assert
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isNavigable() {
        // Arrange
        var navMesh = createNavMesh(new Vector2D(0, 0), new Vector2D(10, 5), new Vector2D(3, 4), new Vector2D(3, 0));

        // Act
        var navigable = navMesh.isNavigable(new Vector2D(4, 4));
        var navigableOnBorder = navMesh.isNavigable(new Vector2D(10, 5));
        var navigableOnLowBorder = navMesh.isNavigable(new Vector2D(0, 0));
        var notNavigableOutsideX = navMesh.isNavigable(new Vector2D(11, 4));
        var notNavigableOutsideXLow = navMesh.isNavigable(new Vector2D(-1, 4));
        var notNavigableOutsideY = navMesh.isNavigable(new Vector2D(0, 6));
        var notNavigableOutsideYLow = navMesh.isNavigable(new Vector2D(0, -1));
        var notNavigableObstacle = navMesh.isNavigable(new Vector2D(3, 4));

        // Assert
        assertThat(navigable).isTrue();
        assertThat(navigableOnBorder).isTrue();
        assertThat(navigableOnLowBorder).isTrue();
        assertThat(notNavigableOutsideX).isFalse();
        assertThat(notNavigableOutsideXLow).isFalse();
        assertThat(notNavigableOutsideY).isFalse();
        assertThat(notNavigableOutsideYLow).isFalse();
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