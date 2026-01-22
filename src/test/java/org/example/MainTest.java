package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MainTest {
    /**
     * CONTRACT: The main method should execute successfully and return without 
     * error when provided with a standard empty string array as input.
     */
    @Test
    @DisplayName("Inital test: Run main()")
    void testMainRunSuccess() {
        // Arrange
        String[] args = {};

        // Act & Assert
        assertDoesNotThrow(() -> {
            Main.main(args);
        }, "The contract requires that the program handles valid empty inputs without crashing.");
    }
}