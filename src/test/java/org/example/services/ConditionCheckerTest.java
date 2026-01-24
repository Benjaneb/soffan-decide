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
    @DisplayName("Test condition 2 with succeeding data")
    void testCondition2Succeeds() {
        Point[] points = {
            new Point(2, 0), // Not correct angle with next 2
            new Point(1, 0),
            new Point(0, 0),
            new Point(-1, -1),
        };

        // Test that last triplet is counted as forming an angle of PI +- 0.01
        assertTrue(checker.checkCondition2(points, 0.01));
    }

    @Test
    @DisplayName("Test condition 2 with no points that can form good angle")
    void testCondition2Fails() {
        Point[] points = {
            new Point(2, 0),
            new Point(1, 0),
            new Point(0, 0),
            new Point(-2, 0),
        };

        assertFalse(checker.checkCondition2(points, 0.01));
    }
}
