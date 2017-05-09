package com.jraska.rx.codelab.http;

public final class Repo {
  public final String name;
  public final String description;
  public final int stars;
  public final int forks;
  public final int size;

  public Repo(String name, String description, int stars, int forks, int size) {
    this.name = name;
    this.description = description;
    this.stars = stars;
    this.forks = forks;
    this.size = size;
  }

  @Override
  public String toString() {
    return "Repo{" +
      "name='" + name + '\'' +
      ", description='" + description + '\'' +
      ", stars=" + stars +
      ", forks=" + forks +
      ", size=" + size +
      '}';
  }
}
