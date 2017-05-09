package com.jraska.rx.codelab.forest;

public final class Tree {
  private final int ordinal;

  public Tree(int ordinal) {
    this.ordinal = ordinal;
  }

  public int treeOrdinal() {
    return ordinal;
  }

  @Override
  public String toString() {
    return "Tree{" +
      "ordinal=" + ordinal +
      '}';
  }
}
