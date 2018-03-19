package com.jraska.rx.codelab.nature;

public final class Water {
  private final long ordinal;

  public Water(long ordinal) {
    this.ordinal = ordinal;
  }

  @Override
  public String toString() {
    return "Water #" + ordinal;
  }
}
