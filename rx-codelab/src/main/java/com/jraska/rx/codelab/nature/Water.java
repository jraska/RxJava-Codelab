package com.jraska.rx.codelab.nature;

public final class Water {
  private final long id;

  public Water() {
    this(1);
  }

  public Water(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Water " + id;
  }
}
