package org.example.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import org.example.model.Point;

class ConditionCheckerTest {
    ConditionChecker checker = new ConditionChecker();

    /**
     * CONTRACT: ...
     */
    @Test
    @DisplayName("Test 1")
    void test1() {
        // Arrange
        boolean b = true;

        // Act & Assert
        assertTrue(b);
    }

    @Test
    @DisplayName("Test condition 1")
    void testCondition1() {
        Point[] points = {
            new Point(0, 0),
            new Point(1, 1),
            new Point(-1, -1),
            new Point(0, 3), // Not within radius=2 of previous 3
        };

        // Test that last triplet is counted as not fitting within radius
        assertTrue(checker.checkCondition1(points, 2));

        // Big circle, no triplet that's not encompassed by this radius
        assertFalse(checker.checkCondition1(points, 10));
    }
}
