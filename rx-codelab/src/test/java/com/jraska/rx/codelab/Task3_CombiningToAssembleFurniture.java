package com.jraska.rx.codelab;

import com.jraska.rx.codelab.forest.Forest;
import com.jraska.rx.codelab.forest.Log;
import com.jraska.rx.codelab.forest.Lumberjack;
import com.jraska.rx.codelab.forest.Tools;
import com.jraska.rx.codelab.furniture.Chair;
import com.jraska.rx.codelab.furniture.Sofa;
import com.jraska.rx.codelab.furniture.Table;

import org.junit.Test;

import io.reactivex.Observable;

import static java.lang.System.out;

public class Task3_CombiningToAssembleFurniture {
    @Test
    public void zip_doSomeChair() {
        Observable<Log> logObservable = Lumberjack.cut(Forest.AMAZON).map(Tools::handSaw);

        // TODO: Carpenter wants to do some chairs, he can get some box of screws from Parts and he needs Logs of wood
        Observable<Chair> chairObservable = null;

        chairObservable.subscribe(out::println);
    }

    @Test
    public void concatWith_doTableNow() {
        Observable<Log> logObservable = Lumberjack.cut(Forest.AMAZON).map(Tools::handSaw);

        // TODO: We now need to  create Table, but one Box of screws is not enough, we can concatWith two boxes to have enough screws
        Observable<Table> tableObservable = null;

        tableObservable.subscribe(out::println);
    }

    @Test
    public void startWith_doAnotherTable() {
        Observable<Log> logObservable = Lumberjack.cut(Forest.AMAZON).map(Tools::handSaw);

        // TODO: We can achieve the same with just putting twice fiveScrews from Parts at the start of the Screws Observable
        Observable<Table> tableObservable = null;

        tableObservable.subscribe(out::println);
    }

    @Test
    public void flatMapZip_makeSomeSofa() {
        Observable<Log> logObservable = Lumberjack.cut(Forest.AMAZON).map(Tools::handSaw);

        // TODO: Now Carpenter needs some Rivets to do Sofa, he can use flatMap with Parts.rivet to get some rivets needed for Sofas
        Observable<Sofa> sofaObservable = null;

        sofaObservable.subscribe(out::println);
    }
}
