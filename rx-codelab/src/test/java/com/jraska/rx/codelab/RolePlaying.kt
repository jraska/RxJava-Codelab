package com.jraska.rx.codelab

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.junit.Test

class RolePlaying {
  private val schedulerProvider = LegoSchedulersProvider(Schedulers.trampoline(), Schedulers.trampoline())

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
      .subscribeOn(schedulerProvider.leftHand)
      .map { LegoMan(it, LegoHat()) }
      .observeOn(schedulerProvider.rightHand)
      .map { ArmedLegoMan(it, LegoWeapon()) }
      .observeOn(schedulerProvider.leftHand)

    armedLegoMans.subscribe { println(it) }
  }
}

data class ArmedLegoMan(val legoMan: LegoMan, val legoWeapon: LegoWeapon)

class LegoMan(val legoBody: LegoBody, val legoMan: LegoHat)

class LegoWeapon

class LegoHat

class LegoBody

class LegoSchedulersProvider(
  val leftHand: Scheduler,
  val rightHand: Scheduler
)
