package de.ibb.generics;

public final class Paar<A, B> {

    private final A erstes;
    private final B zweites;

    public Paar(A erstes, B zweites) {
        this.erstes = erstes;
        this.zweites = zweites;
    }

    public A erstes() {
        return erstes;
    }

    public B zweites() {
        return zweites;
    }

    public Paar<B, A> tauschen() {
        // TODO neues Paar, Seiten vertauscht
        return null;
    }
}
