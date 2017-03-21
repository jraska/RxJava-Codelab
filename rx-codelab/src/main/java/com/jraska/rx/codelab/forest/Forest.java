package com.jraska.rx.codelab.forest;

public final class Forest {
    public static final Forest AMAZON = new Forest(4);

    final int countOfTrees;

    private Forest(int countOfTrees) {
        this.countOfTrees = countOfTrees;
    }
}
