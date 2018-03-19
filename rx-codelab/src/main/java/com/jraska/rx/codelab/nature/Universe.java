package com.jraska.rx.codelab.nature;

public final class Universe {
  public static Universe bigBang() {
    Universe universe = new Universe();
    universe.evolve();
    return universe;
  }

  private void evolve() {
    River amazon = new River(5);
    amazon.startFlow();

    River thames = new River(100);
    thames.startFlow();
    theEarth = new Earth(amazon, thames);
  }

  private Earth theEarth;

  private Universe() {
  }

  public Earth planetEarth() {
    return theEarth;
  }
}
