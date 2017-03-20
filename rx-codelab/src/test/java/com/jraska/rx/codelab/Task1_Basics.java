package com.jraska.rx.codelab;

import org.junit.Test;

public class Task1_Basics {
    @Test
    public void dummyObservable() {
        // TODO(josef):  Create Observable with single String value, subscribe to it and print it to console (Observable.just)
    }

    @Test
    public void arrayObservable() {
        // TODO(josef):  Create Observable with ints 1, 2, 3, 4, 5, subscribe to it and print each value to console (Observable.fromArray)
    }

    @Test
    public void helloOperator() {
        // TODO(josef):  Create Observable with ints 1 .. 10 subscribe to it and print only odd values (Observable.range, observable.filter)
    }

    @Test
    public void receivingError() {
        // TODO(josef):  Create Observable which emits an error and print the console (Observable.error), subscribe with onError handling
    }
}
