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
        conditionsMet[3] = checkCondition3(points, params.AREA1);
        conditionsMet[4] = checkCondition4(points, params.QPTS, params.QUADS);
        conditionsMet[5] = checkCondition5(points);
        conditionsMet[8] = checkCondition8(points, params.APTS, params.BPTS, params.RADIUS1);
        conditionsMet[9] = checkCondition9(points, params.EPSILON, params.CPTS, params.DPTS);
        conditionsMet[10] = checkCondition10(points, params.EPTS, params.FPTS, params.AREA1);
        conditionsMet[12] = checkCondition12(points, params.KPTS, params.LENGTH1, params.LENGTH2);
        conditionsMet[14] = checkCondition14(points, params.EPTS, params.FPTS, params.AREA1, params.AREA2);
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

    /**
     * There exists at least one set of three consecutive data points that are the vertices of a triangle with area greater than AREA1.
    (0 ≤ AREA1)
     */
    public boolean checkCondition3(Point[] points, double area1) {
        // Check input
        if (area1 < 0) return false;

        for (int i = 0; i < points.length - 2; i++) {
            Point p1 = points[i];
            Point p2 = points[i+1];
            Point p3 = points[i+2];
            double triangleArea = Utils.triangleArea(p1, p2, p3);
            if (Utils.doubleCompare(triangleArea, area1) == Utils.CompType.GT) {
                return true;
            }       
        }
        return false;
    }

    /**
     * There exists at least one set of QPTS consecutive data points that lie in more than QUADS
     * quadrants. Where there is ambiguity as to which quadrant contains a given point, priority
     * of decision will be by quadrant number, i.e., I, II, III, IV. For example, the data point (0,0)
     * is in quadrant I, the point (-l,0) is in quadrant II, the point (0,-l) is in quadrant III, the point
     * (0,1) is in quadrant I and the point (1,0) is in quadrant I.
     * – (2 ≤ QPTS ≤ NUMPOINTS)
     * - (1 ≤ QUADS ≤ 3)
     */
    public boolean checkCondition4(Point[] points, int qpts, int quads) {
        // Check input
        if (qpts < 2 || qpts > points.length || quads < 1 || quads > 3)
            return false;

        for (int i = 0; i < points.length - qpts + 1; i++) {
            boolean[] seen = new boolean[4]; // which quadrants I-IV we've observed
            int count = 0;

            // Iterating qpts Points, starting from i
            for (int j = 0; j < qpts; j++) {
                int q = Utils.quadrant(points[i + j]);
                if (!seen[q - 1]) {
                    seen[q - 1] = true;
                    count++;
                }
            }
            if (count > quads) return true;
        }
        return false;
    }

    public boolean checkCondition5(Point[] points) {
        for(int i = 0; i < points.length-1; i++){
            Point p1 = points[i];
            Point p2 = points[i+1];
            double diff = p2.x() - p1.x();
            if (Utils.doubleCompare(diff, 0) == Utils.CompType.LT) {
                return true;
            }
        }
        return false;
    }

    /*
    * There exists at least one set of three data points separated by exactly A PTS and B PTS
    * consecutive intervening points, respectively, that cannot be contained within or on a circle of
    * radius RADIUS1. The condition is not met when NUMPOINTS < 5.
    * 
    * 1 ≤ A PTS, 1 ≤ B PTS
    * A PTS + B PTS ≤ (NUMPOINTS − 3)
    */
    public boolean checkCondition8(Point[] points, int apts, int bpts, double radius1) {
        int numpoints = points.length;

        if (numpoints < 5 || apts < 1 || bpts < 1 || apts + bpts > (numpoints -3)){
            return false;
        }

        for (int i = 0; i + apts + bpts + 2 < numpoints; i++){
            Point p1 = points[i];
            // "separated by" meaning that the point is apts + 1 away
            Point p2 = points[i + apts + 1];
            Point p3 = points[i + apts + bpts + 2];

            double radius = Utils.circumcircleRadius(p1, p2, p3);
            
            // If radius of circumcircle is greater than the maximum distance between two points, the triangle can be contained in a circle with the longest distance as it's diameter
            double maxDistance = Math.max(Math.max(Utils.distance(p1, p2), Utils.distance(p2, p3)), Utils.distance(p1, p3)); 
            if (Utils.doubleCompare(maxDistance, radius) == Utils.CompType.LT){
                radius = maxDistance/2;
            }

            if (Utils.doubleCompare(radius, radius1) == Utils.CompType.GT) {
                return true; // found a set of three points that cannot fit inside circle
            } 
        }

        return false;
    }
        
        

    public boolean checkCondition9(Point[] points, double epsilon, int cpts, int dpts) {
        // Check valid input
        if (cpts < 1 || dpts < 1 || cpts + dpts > points.length - 3)
            return false;

        for (int i = 0; i < points.length - (cpts + dpts + 2); i++) {
            Point p1 = points[i];
            Point p2 = points[i + cpts + 1];
            Point p3 = points[i + cpts + dpts + 2];

            // Vertex should not coincide with either of the other two points
            if (Utils.equals(p1, p2) || Utils.equals(p3, p2))
                continue;

            double angle = Utils.threePointAngle(p1, p2, p3);
            if (angle < Math.PI - epsilon || angle > Math.PI + epsilon)
                return true;
        }
        return false;
    }

    public boolean checkCondition10(Point[] points, int epts, int fpts, double area1) {
        // Check valid input
        if (epts < 1 || fpts < 1 || epts + fpts > points.length - 3)
            return false;

        for (int i = 0; i < points.length - (epts + fpts + 2); i++) {
            Point p1 = points[i];
            Point p2 = points[i + epts + 1];
            Point p3 = points[i + epts + fpts + 2];

            if (Utils.triangleArea(p1, p2, p3) > area1)
                return true;
        }
        return false;
    }
    
    public boolean checkCondition12(Point[] points, int kpts, double length1, double length2) {
        // Check valid input
        if (length1 < 0 || length2 < 0) return false;
        if (kpts < 1 || kpts > points.length - 2) return false;
        if (points.length < 3) return false;

        boolean foundLength1Pair = false;
        boolean foundLength2Pair = false;

        for (int i = 0; i < points.length - (kpts + 1); i++) {
            Point p1 = points[i];
            Point p2 = points[i + kpts + 1];

            double distance = Utils.distance(p1, p2);

            if (distance > length1) foundLength1Pair = true;
            if (distance < length2) foundLength2Pair = true;

            if (foundLength1Pair && foundLength2Pair) return true;
        }

        return false;
    }
  
    public boolean checkCondition14(Point[] points, int epts, int fpts, double area1, double area2) {
        // Check valid input
        if (epts < 1 || fpts < 1 || epts + fpts > points.length - 3) return false;
        if (area1 < 0 || area2 < 0) return false;
        if (points.length < 5) return false;

        boolean foundGreaterArea = false;
        boolean foundSmallerArea = false;

        for (int i = 0; i < points.length - (epts + fpts + 2); i++) {
            Point p1 = points[i];
            Point p2 = points[i + epts + 1];
            Point p3 = points[i + epts + fpts + 2];

            double area = Utils.triangleArea(p1, p2, p3);

            if (area > area1)   foundGreaterArea = true;
            if (area < area2)   foundSmallerArea = true;

            if (foundGreaterArea && foundSmallerArea) return true;
        }

        return false;
    }
}
