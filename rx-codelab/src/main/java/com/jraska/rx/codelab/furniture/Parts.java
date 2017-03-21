package com.jraska.rx.codelab.furniture;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public final class Parts {
    public static Observable<Screw> boxOfTenScrews() {
        return Observable.range(1, 10)
                .map(value -> new Screw());
    }

    public static List<Screw> fiveScrews() {
        ArrayList<Screw> screws = new ArrayList<>();
        for (int i = 0; i < Carpenter.SCREWS_FOR_CHAIR; i++) {
            screws.add(new Screw());
        }
        return screws;
    }

    public static Screw screw() {
        return new Screw();
    }

    public static Observable<Rivet> rivet(Screw screw) {
        return Observable.just(new Rivet());
    }

    public static Observable<Screw> flakeyScrew() {
        return Observable.fromCallable(() -> {
            if (Math.random() > 0.5) {
                throw new RuntimeException("I'm flakey");
            }

            return screw();
        });
    }

    private Parts() {
        throw new AssertionError("No instances");
    }
}
