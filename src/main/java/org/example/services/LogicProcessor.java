package org.example.services;

import org.example.model.Connector;

public class LogicProcessor {
    public boolean[][] process(boolean[] conditionsMet, Connector[][] connectors) {
        final int N = 15;
        boolean[][] preliminaryUnlockingsMat = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Connector connector = connectors[i][j];
                switch (connector) {
                    case NOTUSED:
                        preliminaryUnlockingsMat[i][j] = true;
                        break;
                    case ANDD:
                        preliminaryUnlockingsMat[i][j] = conditionsMet[i] && conditionsMet[j];
                        break;
                    case ORR:
                        preliminaryUnlockingsMat[i][j] = conditionsMet[i] || conditionsMet[j];
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown: " + connector);
                }
            }
        }

        return preliminaryUnlockingsMat;
    }
}
