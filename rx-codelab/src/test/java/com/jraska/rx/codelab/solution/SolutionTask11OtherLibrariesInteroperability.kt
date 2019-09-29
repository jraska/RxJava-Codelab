package com.jraska.rx.codelab.solution

//import hu.akarnokd.rxjava.interop.RxJavaInterop
//import io.reactivex.BackpressureStrategy
//import io.reactivex.Observable
//import io.reactivex.Single
//import io.reactivex.processors.PublishProcessor
//import org.junit.Test
//import reactor.core.publisher.Flux
//import reactor.core.publisher.Mono

class SolutionTask11OtherLibrariesInteroperability {
//  @Test
//  fun givenRxJava1and2() {
//    val stringObservable = RxJavaInterop.toV1Observable(Observable.just("hi").toFlowable(BackpressureStrategy.DROP))
//    val stringObservable2 = RxJavaInterop.toV2Observable(stringObservable)
//
//    val objectSubject = RxJavaInterop.toV1Subject(PublishProcessor.create<Any>())
//    val flowableProcessor = RxJavaInterop.toV2Processor(objectSubject)
//  }
//
//  @Test
//  fun reactiveStreams_reactor_rxjava() {
//    val hello = Observable.just("Hello")
//    val flux = Flux.from(hello.toFlowable(BackpressureStrategy.DROP))
//    flux.subscribe { println(it) }
//
//    val single = Single.just("Hello Single")
//    val mono = Mono.from(single.toFlowable())
//    mono.subscribe { println(it) }
//  }
}
