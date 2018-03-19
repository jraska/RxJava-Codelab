package com.jraska.rx.codelab.nature;

import java.util.concurrent.atomic.AtomicInteger;

public class Barrel {
  private static final AtomicInteger NEXT_ID = new AtomicInteger(0);

  private final int id;
  private final int ordinal;

  Barrel(int ordinal) {
    id = NEXT_ID.getAndIncrement();
    this.ordinal = ordinal;
  }

  @Override
  public String toString() {
    return "Barrel #" + ordinal + " id=" + id;
  }
}
