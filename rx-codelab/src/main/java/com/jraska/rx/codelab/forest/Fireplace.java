package com.jraska.rx.codelab.forest;

import io.reactivex.Observable;

import static java.lang.System.out;

public final class Fireplace {

  private int burnOrdinal;
  private int basketBurnOrdinal;

  public void subscribeForBurn(Observable<Firewood> woodObservable) {
    woodObservable.subscribe(bunchOfWood -> out.println("#" + ++burnOrdinal + " Fireplace burning"));
  }

  public void subscribeForBasketBurn(Observable<BasketOfWood> basketWoodObservable) {
    basketWoodObservable.subscribe(bunchOfWood -> out.println("#" + ++basketBurnOrdinal + " What a great fire!"));
  }
}
