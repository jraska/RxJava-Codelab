package com.jraska.rx.codelab.forest;

import java.util.List;

public final class BasketOfWood {
  public static int BASKET_SIZE = 4;

  private final List<Firewood> firewood;

  public BasketOfWood(List<Firewood> firewood) {
    if (firewood.size() != BASKET_SIZE) {
      throw new IllegalArgumentException("Basket of firewood must have exactly size of " + BASKET_SIZE + " items");
    }

    this.firewood = firewood;
  }

  @Override
  public String toString() {
    return "Basket of " + firewood.size() + " firewood pieces";
  }
}
