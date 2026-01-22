package org.example;

import org.example.model.*;
import org.example.services.*;

public class LaunchDecider {
    private final CMVBuilder cmvBuilder;
    private final LogicProcessor logicProcessor;
    private final PolicyChecker policyChecker;

    public LaunchDecider(CMVBuilder cmvBuilder,
                         LogicProcessor logicProcessor,
                         PolicyChecker policyChecker) {
        this.cmvBuilder = cmvBuilder;
        this.logicProcessor = logicProcessor;
        this.policyChecker = policyChecker;
    }

    // inputs follow PDF: points array, parameters, LCM, PUV
    public void decide(Point[] points, Parameters params, Connector[][] lcm, boolean[] puv) {
        boolean[] cmv = cmvBuilder.buildCMV(points, params);
        boolean[][] pum = logicProcessor.buildPUM(cmv, lcm);
        boolean[] fuv = policyChecker.buildFUV(pum, puv);
        boolean launch = decideLaunch(fuv);
        String result = launch ? "YES" : "NO";
        System.out.println(result);
    }

    /**
     * If all elements of the Final Unlocking Vector (FUV) are true, a launch should occur.
     * @param fuv
     * @return if all elements are true
     */
    private boolean decideLaunch(boolean[] fuv) {
        for (boolean b : fuv) if (!b) return false;
        return true;
    }
}