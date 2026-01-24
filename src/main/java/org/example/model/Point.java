package org.example.model;

/**
 * Represents a geographical coordinate
 */
public record Point(double x, double y){
    public boolean equals(Point p) {
        return Math.abs(x - p.x()) < 0.000001
            && Math.abs(y - p.y()) < 0.000001;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public Point minus(Point p) {
        return new Point(x - p.x(), y - p.y());
    }

    public double dotProd(Point p) {
        return x * p.x() + y * p.y();
    }

    /**
     * Returns the angle formed by three points, with p2 being the vertex.
     * Assumes p1 != p2 and p3 != p2.
     */
    public static double threePointAngle(Point p1, Point p2, Point p3) {
        Point vec1 = p1.minus(p2);
        Point vec2 = p3.minus(p2);
        double angle = Math.acos(
            vec1.dotProd(vec2) / (vec1.length() * vec2.length())
        );
        return angle;
    }
}
