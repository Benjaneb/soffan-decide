package org.example.model;

import java.lang.Math;

/**
 * Represents a geographical coordinate
 */
public record Point(double x, double y){
    public static double distance(Point p1, Point p2) {
        double deltaX = p1.x() - p2.x();
        double deltaY = p1.y() - p2.y();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
