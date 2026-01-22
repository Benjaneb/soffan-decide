package org.example.services;

import org.example.model.Point;
import org.example.model.Parameters;

public class CMVBuilder {
    public boolean[] buildCMV(Point[] points, Parameters params) {
        boolean[] cmv = new boolean[15];
        // compute LIC 0..14. Implement each LIC as a private method for testability.
        // e.g. cmv[0] = checkLIC0(points, params.LENGTH1);
        return cmv;
    }

    // Example private helper signatures:
    // private boolean lic0(Point[] points, double length1) { ... }
    // private boolean lic1(Point[] points, double radius1) { ... }
    // ... implement lic2..lic14
}