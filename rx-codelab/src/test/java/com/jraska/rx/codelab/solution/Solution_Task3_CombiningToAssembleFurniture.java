package com.jraska.rx.codelab.solution;

import com.jraska.rx.codelab.forest.Forest;
import com.jraska.rx.codelab.forest.Log;
import com.jraska.rx.codelab.forest.Lumberjack;
import com.jraska.rx.codelab.forest.Tools;
import com.jraska.rx.codelab.furniture.Carpenter;
import com.jraska.rx.codelab.furniture.Chair;
import com.jraska.rx.codelab.furniture.Parts;
import com.jraska.rx.codelab.furniture.Rivet;
import com.jraska.rx.codelab.furniture.Screw;
import com.jraska.rx.codelab.furniture.Sofa;
import com.jraska.rx.codelab.furniture.Table;

import org.junit.Test;

import java.util.List;

import io.reactivex.Observable;

public class Solution_Task3_CombiningToAssembleFurniture {
  @Test
  public void zip_doSomeChair() {
    Observable<Log> logObservable = Lumberjack.cut(Forest.AMAZON).map(Tools::handSaw);
    Observable<List<Screw>> screwsObservable = Parts.boxOfTenScrews().buffer(Carpenter.SCREWS_FOR_CHAIR);

    Observable<Chair> chairObservable = logObservable.zipWith(screwsObservable, Carpenter::chair);

    chairObservable.subscribe(System.out::println);
  }

  @Test
  public void concatWith_doTableNow() {
    Observable<Log> logObservable = Lumberjack.cut(Forest.AMAZON).map(Tools::handSaw);
    Observable<List<Screw>> screwsObservable = Parts.boxOfTenScrews()
      .concatWith(Parts.boxOfTenScrews())
      .buffer(Carpenter.SCREWS_FOR_TABLE);

    Observable<Table> tableObservable = logObservable.zipWith(screwsObservable, Carpenter::table);

    tableObservable.subscribe(System.out::println);
  }

  @Test
  public void startWith_doAnotherTable() {
    Observable<Log> logObservable = Lumberjack.cut(Forest.AMAZON).map(Tools::handSaw);
    Observable<List<Screw>> screwsObservable = Parts.boxOfTenScrews()
      .startWith(Parts.fiveScrews())
      .startWith(Parts.fiveScrews())
      .buffer(Carpenter.SCREWS_FOR_TABLE);

    Observable<Table> tableObservable = logObservable.zipWith(screwsObservable, Carpenter::table);

    tableObservable.subscribe(System.out::println);
  }

  @Test
  public void flatMapZip_makeSomeSofa() {
    Observable<Log> logObservable = Lumberjack.cut(Forest.AMAZON).map(Tools::handSaw);
    Observable<List<Rivet>> screwsObservable = Parts.boxOfTenScrews()
      .concatWith(Parts.boxOfTenScrews())
      .flatMap(Parts::rivet)
      .buffer(Carpenter.RIVETS_FOR_SOFA);

    Observable<Sofa> sofaObservable = logObservable.zipWith(screwsObservable, Carpenter::sofa);

    sofaObservable.subscribe(System.out::println);
  }
}
