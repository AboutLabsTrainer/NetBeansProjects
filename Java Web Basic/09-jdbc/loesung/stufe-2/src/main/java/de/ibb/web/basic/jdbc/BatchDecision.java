package de.ibb.web.basic.jdbc;

public final class BatchDecision {
    private BatchDecision() {}

    public static boolean useBatch(int count) {
        return count >= 100;
    }
}
