package org.example.model;

import java.lang.Math;

/**
 * Represents a geographical coordinate
 */
public record Point(double x, double y){
    public static double distance(Point p1, Point p2) {
        return Math.sqrt(p1 * p1 + p2 * p2);
    }
}
