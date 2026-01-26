package org.example.services;

import org.example.model.Point;
import org.example.model.Parameters;
import org.example.util.Utils;

public class ConditionChecker {
    public boolean[] check(Point[] points, Parameters params) {
        boolean[] conditionsMet = new boolean[15];
        // compute LIC 0..14. Implement each LIC as a private method for testability.
        // e.g. conditionsMet[0] = checkLIC0(points, params.LENGTH1);
        conditionsMet[0] = checkCondition0(points, params.LENGTH1);
        conditionsMet[1] = checkCondition1(points, params.RADIUS1);
        conditionsMet[2] = checkCondition2(points, params.EPSILON);
        return conditionsMet;
    }

    // Example helper signatures:
    // public boolean lic0(Point[] points, double length1) { ... }

    /*
    Checks if there exists at least one set of two consecutive data points that are a distance greater than
    the length, LENGTH1, apart.
    (0 ≤ LENGTH1) 
    */
    public boolean checkCondition0(Point[] points, double length1) {
        for(int i = 0; i<points.length-1; i++){
            Point p1 = points[i];
            Point p2 = points[i+1];
            double dist = Utils.distance(p1, p2);
            if (Utils.doubleCompare(dist, length1) == Utils.CompType.GT) {
                return true;
            }            
        }return false;
    }

    public boolean checkCondition1(Point[] points, double radius1) {
        for (int i = 0; i < points.length - 2; i++) {
            double meanX = (points[i].x() + points[i + 1].x() + points[i + 2].x()) / 3;
            double meanY = (points[i].y() + points[i + 1].y() + points[i + 2].y()) / 3;
            Point meanPoint = new Point(meanX, meanY);

            boolean allWithinCircle = Utils.distance(points[i], meanPoint) <= radius1
                    && Utils.distance(points[i + 1], meanPoint) <= radius1
                    && Utils.distance(points[i + 2], meanPoint) <= radius1;
            if (!allWithinCircle)
                return true;
        }
        return false;
    }

    public boolean checkCondition2(Point[] points, double epsilon) {
        for (int i = 0; i < points.length - 2; i++) {
            // Vertex should not coincide with either of the other two points
            if (points[i].equals(points[i + 1]) || points[i + 2].equals(points[i + 1]))
                continue;

            double angle = Utils.threePointAngle(points[i], points[i + 1], points[i + 2]);
            if (angle < Math.PI - epsilon || angle > Math.PI + epsilon)
                return true;
        }
        return false;
    }
}
