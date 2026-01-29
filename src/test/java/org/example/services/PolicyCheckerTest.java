package org.example.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class PolicyCheckerTest {
    PolicyChecker policyChecker = new PolicyChecker();

    @Test
    @DisplayName("Test example 1 in the requirements works for final unlockings")
    void testExample1WorksForFinalUnlockings() {
        boolean[][] preliminaryUnlockingsMat = new boolean[15][15];
        for (boolean[] row : preliminaryUnlockingsMat)
            Arrays.fill(row, true);

        preliminaryUnlockingsMat[0][1] = false;
        preliminaryUnlockingsMat[1][0] = false;
        preliminaryUnlockingsMat[0][3] = false;
        preliminaryUnlockingsMat[3][0] = false;

        boolean[] preliminaryUnlockingsVec = {
            true, false, true, false,
            true, false, true, false,
            true, false, true, false,
            true, false, true
        };

        boolean[] finalUnlockings = policyChecker.check(preliminaryUnlockingsMat, preliminaryUnlockingsVec);

        assertFalse(finalUnlockings[0]);
        for (int i = 1; i < finalUnlockings.length; i++)
            assertTrue(finalUnlockings[i]);
    }
}
