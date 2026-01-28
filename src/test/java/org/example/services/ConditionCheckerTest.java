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
    @DisplayName("Test condition 4 finds consequtive points in sufficient amount of quadrants")
    void testCondition4Succeeds() {
        Point[] points = {
            new Point( 1,  1),  // I
            new Point(-1,  1),  // II
            new Point(-1, -1),  // III
            new Point( 1, -1)   // IV
        };

        // Any 3 consecutive points here span 3 quadrants
        assertTrue(checker.checkCondition4(points, 3, 2));
    }

    @Test
    @DisplayName("Test condition 4 when there aren't enough points to cover quadrants")
    void testCondition4Fails() {
        Point[] points = {
            new Point(1, 1),    // I
            new Point(2, 2),    // I
            new Point(-1, 1),   // II
            new Point(-2, 2)    // II
        };

        // Any 3 consecutive points only span quadrants I and II
        assertFalse(checker.checkCondition4(points, 3, 2));
    }

    @Test
    @DisplayName("Test condition 4 rejects invalid input")
    void testCondition4RejectsInvalidInput() {
        Point[] points = {
            new Point(1, 1),
            new Point(-1, 1),
            new Point(-1, -1)
        };

        // QPTS > NUM_POINTS, QUADS > 3
        assertFalse(checker.checkCondition4(points, 4, 4));
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
    @DisplayName("Test condition 6 succeeds with a point far from the line")
    void testCondition6Succeeds() {
        Point[] points = {
            new Point(0, 0),
            new Point(0, 1),
            new Point(1, 0),
        };

        assertTrue(checker.checkCondition6(points, 3, 0.5));
    }

    @Test
    @DisplayName("Test condition 6 fails when all points are close to the line")
    void testCondition6Fails() {
        Point[] points = {
            new Point(0, 0),
            new Point(0.5, 0),
            new Point(1, 0),
        };

        assertFalse(checker.checkCondition6(points, 3, 0.1));
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

    @DisplayName("Test condition 10 succeeds")
    void testCondition10Succeeds() {
        // Points 1, 3 & 5 forms area 2 > 1
        // Other points just form a line (no area)
        Point[] points = {
            new Point(2, 0),
            new Point(0, 0),
            new Point(1, 0),
            new Point(2, 0),
            new Point(-1, 0),
            new Point(2, 2),
        };

        assertTrue(checker.checkCondition10(points, 1, 1, 1));
    }

    @Test
    @DisplayName("Test condition 10 fails")
    void testCondition10Fails() {
        // Points 1, 3 & 5 forms area 2 < 3
        // Other points just form a line (no area)
        Point[] points = {
            new Point(2, 0),
            new Point(0, 0),
            new Point(1, 0),
            new Point(2, 0),
            new Point(-1, 0),
            new Point(2, 2),
        };

        assertFalse(checker.checkCondition10(points, 1, 1, 3));
    }

    @Test
    @DisplayName("Test condition 10 rejects invalid input")
    void testCondition10RejectInvalidInput() {
        Point[] points = {
            new Point(2, 0),
            new Point(0, 0),
            new Point(1, 0),
            new Point(2, 0),
            new Point(-1, 0),
            new Point(2, 2),
        };

        // EPTS should not be allowed to be <1, therefore set to 0
        assertFalse(checker.checkCondition10(points, 0, 1, 1));
    }

    @Test
    @DisplayName("Test condition 12 succeeds")
    void testCondition12Succeeds() {
        Point[] points = {
            new Point(0, 0),
            new Point(0, 1),
            new Point(0, 2),
            new Point(0, 0),
            new Point(0, 3)
        };

        // Pair (0,0) → (0,2): distance = 2 > length1
        // Pair (0,1) → (0,0): distance = 1 < length2
        assertTrue(checker.checkCondition12(points, 2, 1.5, 1.2));
    }

    @Test
    @DisplayName("Test condition 12 fails")
    void testCondition12Fails() {
        Point[] points = {
            new Point(0, 0),
            new Point(2, 0),
            new Point(0, 2),
            new Point(2, 2)
        };

        // All distances are ≥ 2
        assertFalse(checker.checkCondition12(points, 1, 1.0, 1.5));
    }

    @Test
    @DisplayName("Test condition 12 rejects invalid input")
    void testCondition12RejectInvalidInput() {
        Point[] points = {
            new Point(0, 0),
            new Point(1, 1)
        };

        // All arguments are invalid
        assertFalse(checker.checkCondition12(points, 0, -1, -1));
    }
}
