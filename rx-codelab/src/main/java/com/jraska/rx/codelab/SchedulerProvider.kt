package com.jraska.rx.codelab

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class SchedulerProvider internal constructor(
  val main: Scheduler,
  val io: Scheduler,
  val computation: Scheduler
) {
  companion object {
    fun testSchedulers(): SchedulerProvider {
      return SchedulerProvider(Schedulers.trampoline(), Schedulers.trampoline(), Schedulers.trampoline())
    }

    fun realSchedulers(): SchedulerProvider {
      return SchedulerProvider(Schedulers.trampoline(), Schedulers.io(), Schedulers.computation())
    }
  }
}
