package com.jraska.rx.codelab.solution;

import com.jraska.rx.codelab.LoggingObserver;
import com.jraska.rx.codelab.forest.BasketOfWood;
import com.jraska.rx.codelab.forest.Fireplace;
import com.jraska.rx.codelab.forest.Firewood;
import com.jraska.rx.codelab.forest.Forest;
import com.jraska.rx.codelab.forest.Log;
import com.jraska.rx.codelab.forest.Lumberjack;
import com.jraska.rx.codelab.forest.Tools;
import com.jraska.rx.codelab.forest.Tree;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class Solution_Task2_TransformationsCuttingTheForest {
  Fireplace fireplace;

  @Before
  public void before() {
    fireplace = new Fireplace();
  }

  @Test
  public void map_fromOnePieceExactlyToOnePieceOfOtherStuff() {
    Observable<Tree> treeObservable = Lumberjack.cut(Forest.AMAZON);
    Observable<Log> logsObservable = treeObservable.map(Tools::handSaw);
    Observable<Firewood> woodObservable = logsObservable.map(Tools::chop);

    fireplace.subscribeForBurn(woodObservable);
  }

  @Test
  public void flatMap_chainSawProducesMoreLogs() {
    Observable<Firewood> woodObservable = Lumberjack.cut(Forest.AMAZON)
      .flatMap(Tools::chainSaw)
      .map(Tools::chop);

    fireplace.subscribeForBurn(woodObservable);
  }

  @Test
  public void buffer_createBasketsOfWood() {
    Observable<BasketOfWood> basketsObservable = Lumberjack.cut(Forest.AMAZON)
      .flatMap(Tools::chainSaw)
      .map(Tools::chop)
      .buffer(BasketOfWood.BASKET_SIZE)
      .map(BasketOfWood::new);

    fireplace.subscribeForBasketBurn(basketsObservable);
  }

  @Test
  public void flatMap_buffer_createBasketsOfWoodWithCuttingMachine() {
    Observable<BasketOfWood> basketsObservable = Lumberjack.cut(Forest.AMAZON)
      .flatMap(Tools::chainSaw)
      .flatMap(Tools::machineChop)
      .buffer(BasketOfWood.BASKET_SIZE)
      .map(BasketOfWood::new);

    fireplace.subscribeForBasketBurn(basketsObservable);
  }

  @Test
  public void debounce_filterTooFastEvents() {
    Observable<Firewood> woodObservable = Lumberjack.cut(Forest.AMAZON)
      .flatMap((tree) -> {
        Thread.sleep(tree.treeOrdinal() * 100);
        return Tools.chainSaw(tree);
      }).compose(LoggingObserver.transformer())
      .debounce(250, TimeUnit.MILLISECONDS)
      .map(Tools::chop);

    fireplace.subscribeForBurn(woodObservable);
  }
}
