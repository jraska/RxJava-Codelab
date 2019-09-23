package com.jraska.rx.codelab.server;

import java.util.concurrent.atomic.AtomicInteger;

public class Log {
  private static AtomicInteger nextId = new AtomicInteger();

  private final int id = nextId.incrementAndGet();
  final Level level;
  final String message;

  public Log(Level level, String message) {
    this.level = level;
    this.message = message;
  }

  @Override
  public String toString() {
    return "Log{" +
      "id=" + id +
      ", level=" + level +
      ", message='" + message + '\'' +
      '}';
  }

  public enum Level {
    VERBOSE(0), DEBUG(1), INFO(2), WARNING(3), ERROR(4);

    int value;

    Level(int value) {
      this.value = value;
    }
  }
}
