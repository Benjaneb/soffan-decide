package org.example.util;

import org.example.model.Point;
import java.lang.Math;

public final class Utils {
    private Utils() {}

    /*
    * Enum for CompType
    */
    public enum CompType { LT, EQ, GT }

    /*
    * Compares doubles
    */
    public static CompType doubleCompare(double a, double b)
    {
        if (Math.abs(a-b) < 0.000001) return CompType.EQ ;
        if (a<b) return CompType.LT;
        return CompType.GT ;
    }

    public static boolean equals(Point p1, Point p2) {
        return Math.abs(p1.x() - p2.x()) < 0.000001
            && Math.abs(p1.y() - p2.y()) < 0.000001;
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
}
