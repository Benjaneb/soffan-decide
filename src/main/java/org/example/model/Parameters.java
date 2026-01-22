package org.example.model;

/**
 * Container for all parameters relevant for evaluating each of the Launch Interceptor Conditions (LICs)
 */
public final class Parameters {
    public final double LENGTH1, RADIUS1, EPSILON, AREA1, DIST, LENGTH2, RADIUS2, AREA2;
    public final int QPTS, QUADS, NPTS, KPTS, APTS, BPTS, CPTS, DPTS, EPTS, FPTS, GPTS;
    
    public Parameters(
        double LENGTH1, double RADIUS1, double EPSILON, double AREA1,
        int QPTS, int QUADS, double DIST, int NPTS, int KPTS,
        int APTS, int BPTS, int CPTS, int DPTS, int EPTS, int FPTS, int GPTS,
        double LENGTH2, double RADIUS2, double AREA2
    ) {
        this.LENGTH1 = LENGTH1;
        this.RADIUS1 = RADIUS1;
        this.EPSILON = EPSILON;
        this.AREA1 = AREA1;
        this.QPTS = QPTS;
        this.QUADS = QUADS;
        this.DIST = DIST;
        this.NPTS = NPTS;
        this.KPTS = KPTS;
        this.APTS = APTS;
        this.BPTS = BPTS;
        this.CPTS = CPTS;
        this.DPTS = DPTS;
        this.EPTS = EPTS;
        this.FPTS = FPTS;
        this.GPTS = GPTS;
        this.LENGTH2 = LENGTH2;
        this.RADIUS2 = RADIUS2;
        this.AREA2 = AREA2;
    }
}