package com.jraska.rx.codelab.forest;

import io.reactivex.Observable;

public final class Lumberjack {
    public static Observable<Tree> cut(Forest forest){
        return Observable.range(1, forest.countOfTrees).map(Tree::new);
    }
}
