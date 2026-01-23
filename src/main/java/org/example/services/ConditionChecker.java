package org.example.services;

import org.example.model.Point;
import org.example.model.Parameters;

public class ConditionChecker {
    public boolean[] check(Point[] points, Parameters params) {
        boolean[] conditionsMet = new boolean[15];
        // compute LIC 0..14. Implement each LIC as a private method for testability.
        conditionsMet[1] = checkCondition1(points, params.RADIUS1);
        return conditionsMet;
    }

    // Example private helper signatures:
    // private boolean lic0(Point[] points, double length1) { ... }

    private boolean checkCondition1(Point[] points, double radius1) {
        for (int i = 0; i < points.length - 2; i++) {
            double meanX = (points[i].x() + points[i + 1].x() + points[i + 2].x()) / 3;
            double meanY = (points[i].y() + points[i + 1].y() + points[i + 2].y()) / 3;
            Point meanPoint = new Point(meanX, meanY);

            boolean allWithinCircle = Point.distance(points[i], meanPoint) <= radius1
                    && Point.distance(points[i + 1], meanPoint) <= radius1
                    && Point.distance(points[i + 2], meanPoint) <= radius1;
            if (!allWithinCircle)
                return true;
        }
        return false;
    }
}
