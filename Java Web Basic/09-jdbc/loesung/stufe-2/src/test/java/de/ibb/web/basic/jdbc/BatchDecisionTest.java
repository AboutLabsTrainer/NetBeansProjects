package de.ibb.web.basic.jdbc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BatchDecisionTest {
    @Test
    void batchesFromOneHundredRows() {
        assertFalse(BatchDecision.useBatch(99));
        assertTrue(BatchDecision.useBatch(100));
    }
}
