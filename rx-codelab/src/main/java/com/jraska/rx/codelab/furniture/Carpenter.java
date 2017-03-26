package com.jraska.rx.codelab.furniture;

import com.jraska.rx.codelab.forest.Log;

import java.util.List;

public final class Carpenter {
  public static final int SCREWS_FOR_CHAIR = 5;
  public static final int SCREWS_FOR_TABLE = 20;
  public static final int RIVETS_FOR_SOFA = 20;

  public static Chair chair(Log log, List<Screw> fiveScrews) {
    if (fiveScrews.size() < SCREWS_FOR_CHAIR) {
      throw new IllegalArgumentException("I cannot do chair out of this!");
    }

    return new Chair();
  }

  public static Table table(Log log, List<Screw> twentyScrews) {
    if (twentyScrews.size() < SCREWS_FOR_TABLE) {
      throw new IllegalArgumentException("I cannot do table out of " + twentyScrews.size() + " screws!");
    }

    return new Table();
  }

  public static Sofa sofa(Log log, List<Rivet> twentyRivets) {
    if (twentyRivets.size() < SCREWS_FOR_TABLE) {
      throw new IllegalArgumentException("I cannot do table out of " + twentyRivets.size() + " rivets!");
    }

    return new Sofa();
  }
}
