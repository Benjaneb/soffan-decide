package org.example.services;

public class PolicyChecker {
    public boolean[] check(boolean[][] preliminaryUnlockingsMat, boolean[] preliminaryUnlockingsVec) {
        int N = preliminaryUnlockingsVec.length; // expect 15
        boolean[] finalUnlockings = new boolean[N];

        for (int i = 0; i < N; i++) {
            boolean rowTrue = true;
            for (boolean cell : preliminaryUnlockingsMat[i])
                rowTrue &= cell;
            finalUnlockings[i] = !preliminaryUnlockingsVec[i] || rowTrue;
        }
        return finalUnlockings;
    }
}
