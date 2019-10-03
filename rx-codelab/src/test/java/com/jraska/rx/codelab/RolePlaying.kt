package com.jraska.rx.codelab

import io.reactivex.Observable
import org.junit.Test

class RolePlaying {
  private val appSchedulerProvider = SchedulerProvider.testSchedulers()

  @Test
  fun equipYourLegoMan() {
    val legoRepository = Observable.just(LegoBody(), LegoBody())

    val armedLegoMans = legoRepository
      .map { LegoMan(it, LegoHat()) }
      .map { ArmedLegoMan(it, LegoWeapon()) }

    armedLegoMans.subscribe { println(it) }
  }

  @Test
  fun equipYourLegoManFromDifferentThreads() {
    val legoRepository = Observable.just(LegoBody(), LegoBody())

    val armedLegoMans = legoRepository
      .subscribeOn(appSchedulerProvider.io)
      .map { LegoMan(it, LegoHat()) }
      .observeOn(appSchedulerProvider.main)
      .map { ArmedLegoMan(it, LegoWeapon()) }
      .observeOn(appSchedulerProvider.io)

    armedLegoMans.subscribe { println(it) }
  }
}

data class ArmedLegoMan(val legoMan: LegoMan, val legoWeapon: LegoWeapon)

class LegoMan(val legoBody: LegoBody, val legoMan: LegoHat)

class LegoWeapon

class LegoHat

class LegoBody
