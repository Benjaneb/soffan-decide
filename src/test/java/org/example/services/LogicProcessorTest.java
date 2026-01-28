package org.example.services;

import org.example.model.Connector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LogicProcessorTest {
    private final LogicProcessor processor = new LogicProcessor();

    @Test
    @DisplayName("Example 1: Successful example shown in the requirements file")
    void example1MatchesSpec() {
        boolean[] conditionsMet = new boolean[] {
            false, true, true, true, false,
            false, false, false, false, false,
            false, false, false, false, false
        };

        Connector[][] connectors = new Connector[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                connectors[i][j] = Connector.NOTUSED;
            }
        }

        setSymmetric(connectors, 0, 0, Connector.ANDD);
        setSymmetric(connectors, 0, 1, Connector.ANDD);
        setSymmetric(connectors, 0, 2, Connector.ORR);
        setSymmetric(connectors, 0, 3, Connector.ANDD);
        setSymmetric(connectors, 1, 1, Connector.ANDD);
        setSymmetric(connectors, 1, 2, Connector.ORR);
        setSymmetric(connectors, 1, 3, Connector.ORR);
        setSymmetric(connectors, 2, 2, Connector.ANDD);
        setSymmetric(connectors, 2, 3, Connector.ANDD);
        setSymmetric(connectors, 3, 3, Connector.ANDD);

        boolean[][] pum = processor.process(conditionsMet, connectors);

        assertFalse(pum[0][1]);
        assertTrue(pum[0][2]);
        assertFalse(pum[0][3]);
        assertTrue(pum[0][4]);
        assertTrue(pum[1][2]);
        assertTrue(pum[1][3]);
        assertTrue(pum[2][3]);

        for (int i = 4; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                assertTrue(pum[i][j]);
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 4; j < 15; j++) {
                assertTrue(pum[i][j]);
            }
        }
    }

    private void setSymmetric(Connector[][] connectors, int i, int j, Connector connector) {
        connectors[i][j] = connector;
        connectors[j][i] = connector;
    }
}
