package org.example.util;

import org.example.model.Point;
import java.lang.Math;

public final class Utils {
    private Utils() {}

    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < 0.000001;
    }

    public static boolean equals(Point p1, Point p2) {
        return doubleEquals(p1.x(), p2.x())
            && doubleEquals(p1.y(), p2.y());
    }

    public static double length(Point p) {
        return Math.sqrt(p.x() * p.x() + p.y() * p.y());
    }

    public static Point minus(Point p1, Point p2) {
        return new Point(p1.x() - p2.x(), p1.y() - p2.y());
    }

    public static double dotProd(Point p1, Point p2) {
        return p1.x() * p2.x() + p1.y() * p2.y();
    }

    /**
     * Returns the angle formed by three points, with p2 being the vertex.
     * Assumes p1 != p2 and p3 != p2.
     */
    public static double threePointAngle(Point p1, Point p2, Point p3) {
        Point vec1 = minus(p1, p2);
        Point vec2 = minus(p3, p2);
        double angle = Math.acos(
            dotProd(vec1, vec2) / (length(vec1) * length(vec2))
        );
        return angle;
    }

    public static double distance(Point p1, Point p2) {
        double deltaX = p1.x() - p2.x();
        double deltaY = p1.y() - p2.y();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }


    /**
     * Returns the area formed by three Point instances.
     */
    public static double triangleArea(Point p1, Point p2, Point p3) {
        return Math.abs(
            (p2.x() - p1.x()) * (p3.y() - p1.y())
          - (p2.y() - p1.y()) * (p3.x() - p1.x())
        ) / 2.0;
    }

    public static double distanceFromLine(Point lineStart, Point lineEnd, Point point) {
        if (equals(lineStart, lineEnd)) return distance(lineStart, point);
        double dx = lineEnd.x() - lineStart.x();
        double dy = lineEnd.y() - lineStart.y();
        double numerator = Math.abs(dy * (point.x() - lineStart.x()) - dx * (point.y() - lineStart.y()));
        double denominator = Math.sqrt(dx * dx + dy * dy);
        return numerator / denominator;
    }

    /*
    * Caluculate radius of a circle intersecting all the points in a triangle
    * using formula R = abc/4*area, where a, b and c are side lengths
    */
    public static double circumcircleRadius(Point p1, Point p2, Point p3) {
        // Calculating length of sides
        double a = distance(p1, p2);
        double b = distance(p2, p3);
        double c = distance(p1, p3);

        // Using herons formula for the area of triangle
        double s = (a + b + c) / 2.0;
        double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));

        // Handle division by 0, an area of 0 means that the points are collinear and would create an infinitely large circle
        if (doubleEquals(area, 0.0)) {
            return 0;
        }

        // Using the formula R = abc/4*area
        return (a * b * c) / (4.0 * area);
    }
    
    /**
     * Helper for LIC4. Returns which quadrant a Point belongs to, according to custom prioritization from specification.
     */
    public static int quadrant(Point p) {
        if (p.x() >= 0 && p.y() >= 0) return 1; // Quadrant I
        if (p.x() <  0 && p.y() >= 0) return 2; // Quadrant II
        if (p.x() <  0 && p.y() <  0) return 3; // Quadrant III
        return 4;                               // Quadrant IV
    }
}
