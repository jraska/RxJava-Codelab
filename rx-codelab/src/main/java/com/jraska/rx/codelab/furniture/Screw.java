package com.jraska.rx.codelab.furniture;

public final class Screw {
    private static int counter;

    private final int id = ++counter;

    Screw() {
    }

    @Override
    public String toString() {
        return "Screw " + id;
    }
}
