package org.example;

import org.example.model.*;
import org.example.services.*;

public class LaunchDecider {
    private final ConditionChecker conditionChecker;
    private final LogicProcessor logicProcessor;
    private final PolicyChecker policyChecker;

    public LaunchDecider(ConditionChecker conditionChecker,
                         LogicProcessor logicProcessor,
                         PolicyChecker policyChecker) {
        this.conditionChecker = conditionChecker;
        this.logicProcessor = logicProcessor;
        this.policyChecker = policyChecker;
    }

    public void decide(Point[] points, Parameters params, Connector[][] connectors, boolean[] preliminaryUnlockingsVec) {
        boolean[] conditionsMet = conditionChecker.check(points, params);
        boolean[][] preliminaryUnlockingsMat = logicProcessor.process(conditionsMet, connectors);
        boolean[] finalUnlockings = policyChecker.check(preliminaryUnlockingsMat, preliminaryUnlockingsVec);
        boolean launch = decideLaunch(finalUnlockings);
        String result = launch ? "YES" : "NO";
        System.out.println(result);
    }

    /**
     * If all elements of the Final Unlocking Vector (FUV) are true, a launch should occur.
     * @param finalUnlockings
     * @return if all elements are true
     */
    private boolean decideLaunch(boolean[] finalUnlockings) {
        for (boolean b : finalUnlockings) if (!b) return false;
        return true;
    }
}