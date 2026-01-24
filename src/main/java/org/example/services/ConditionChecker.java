package org.example.services;

import org.example.model.Point;
import org.example.model.Parameters;

public class ConditionChecker {
    public boolean[] check(Point[] points, Parameters params) {
        boolean[] conditionsMet = new boolean[15];
        // compute LIC 0..14. Implement each LIC as a private method for testability.
        // e.g. conditionsMet[0] = checkLIC0(points, params.LENGTH1);
        conditionsMet[2] = checkCondition2(points, params.EPSILON);
        return conditionsMet;
    }

    // Example private helper signatures:
    // private boolean lic0(Point[] points, double length1) { ... }
    // private boolean lic1(Point[] points, double radius1) { ... }
    // ... implement lic2..lic14

    public boolean checkCondition2(Point[] points, double epsilon) {
        for (int i = 0; i < points.length - 2; i++) {
            // Vertex should not coincide with either of the other two points
            if (points[i].equals(points[i + 1]) || points[i + 2].equals(points[i + 1]))
                continue;

            double angle = Point.threePointAngle(points[i], points[i + 1], points[i + 2]);
            if (angle < Math.PI - epsilon || angle > Math.PI + epsilon)
                return true;
        }
        return false;
    }
}
