package com.jraska.rx.codelab.server

import java.util.concurrent.atomic.AtomicInteger

data class Log(
  val level: Level,
  val message: String,
  val id: Int = nextId.incrementAndGet()) {

  enum class Level constructor(internal var value: Int) {
    VERBOSE(0), DEBUG(1), INFO(2), WARNING(3), ERROR(4)
  }

  companion object {
    private val nextId = AtomicInteger()
  }
}
