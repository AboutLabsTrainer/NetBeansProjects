package de.ibb.web.basic.jdbc;

public final class BatchDecision {
    private BatchDecision() {}

    public static boolean useBatch(int count) {
        // TODO: ab 100 Zeilen Batch, darunter einzeln.
        throw new UnsupportedOperationException("TODO");
    }
}
