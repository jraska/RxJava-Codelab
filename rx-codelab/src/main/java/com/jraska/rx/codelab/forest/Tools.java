package com.jraska.rx.codelab.forest;

import io.reactivex.Observable;

public final class Tools {
    public static Observable<Log> chainSaw(Tree tree) {
        return Observable.fromArray(new Log(), new Log(), new Log());
    }

    public static Observable<Firewood> machineChop(Log log) {
        return Observable.fromArray(new Firewood(), new Firewood(), new Firewood());
    }

    public static Firewood chop(Log log) {
        return new Firewood();
    }

    public static Log handSaw(Tree tree) {
        return new Log();
    }

    private Tools() {
        throw new AssertionError("No instances");
    }
}
