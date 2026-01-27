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
    @DisplayName("Test condition 0")
    void testCondition0() {
        Point[] points = {
            new Point(0, 0), 
            new Point(1, 0), // Distance of 1 
            new Point(-1, 0), // Distance of 2
            new Point(5, 5) // Distance of 2
        };
        
        // Test that there is a set of two points greater than the provided length
        assertTrue(checker.checkCondition0(points, 0));
        assertTrue(checker.checkCondition0(points, 2));
        assertFalse(checker.checkCondition0(points, 10)); // Length greater than distance between points
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

    @Test
    @DisplayName("Test condition 3 for data which contains a triangle with sufficient area")
    void testCondition3Succeeds() {
        Point[] points = {
            new Point(0, 1),
            new Point(1, 1),
            new Point(-1, -1),
            new Point(0, 1.5)
        };

        // Test that a triplet's area is larger than 1 (largest one formed by the points is 1.5)
        assertFalse(checker.checkCondition3(points, 2));
        assertTrue(checker.checkCondition3(points, 1));
    }

    @Test
    @DisplayName("Test condition 3 with invalid argument")
    void testCondition3Fails() {
        // Arbitrary data
        Point[] points = {
            new Point(0, 0),
            new Point(0, 0),
            new Point(0, 0)
        };

        // AREA1 ≥ 0
        assertFalse(checker.checkCondition3(points, -0.01));
    }

    @Test
    @DisplayName("Test condition 5 succeeds when x decreases")
    void testCondition5Succeeds() {
        Point[] points = {
            new Point(1, 0),
            new Point(0, 0),
        };

        assertTrue(checker.checkCondition5(points));
    }

    @Test
    @DisplayName("Test condition 5 fails when x never decreases")
    void testCondition5Fails() {
        Point[] points = {
            new Point(0, 0),
            new Point(1, 0),
            new Point(1, 2),
        };

        assertFalse(checker.checkCondition5(points));
    }

    @Test
    @DisplayName("Test condition 9 succeeds with points at correct angle")
    void testCondition9Succeeds() {
        // A, B & C form the correct angle
        Point[] points = {
            new Point(2, 0),
            new Point(1, 0), // A
            new Point(2, 0),
            new Point(0, 0), // B
            new Point(2, 1),
            new Point(-1, -1), // C
        };

        // Test that last triplet is counted as forming an angle of PI +- 0.01
        assertTrue(checker.checkCondition9(points, 0.01, 1, 1));
    }

    @Test
    @DisplayName("Test condition 9 fails with no three points at correct angle")
    void testCondition9Fails() {
        Point[] points = {
            new Point(2, 0),
            new Point(1, 0),
            new Point(0, 0),
            new Point(-2, 0),
            new Point(-3, 0),
        };

        assertFalse(checker.checkCondition9(points, 0.01, 1, 1));
    }

    @Test
    @DisplayName("Test condition 9 rejects invalid input")
    void testCondition9RejectsInvalidInput() {
        // A, B & C form the correct angle
        Point[] points = {
            new Point(2, 0),
            new Point(1, 0), // A
            new Point(2, 0),
            new Point(0, 0), // B
            new Point(2, 1),
            new Point(-1, -1), // C
        };

        // CPTS should not be allowed to be <1, therefore set to 0
        assertFalse(checker.checkCondition9(points, 0.01, 0, 1));
    }
}
