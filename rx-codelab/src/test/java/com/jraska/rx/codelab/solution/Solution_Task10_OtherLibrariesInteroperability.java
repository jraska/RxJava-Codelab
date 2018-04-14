package com.jraska.rx.codelab.solution;

import org.junit.Test;

//import hu.akarnokd.rxjava.interop.RxJavaInterop;
//import io.reactivex.BackpressureStrategy;
//import io.reactivex.Observable;
//import io.reactivex.Single;
//import io.reactivex.processors.FlowableProcessor;
//import io.reactivex.processors.PublishProcessor;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//import rx.subjects.Subject;

public class Solution_Task10_OtherLibrariesInteroperability {
//  @Test
//  public void givenRxJava1and2() {
//    rx.Observable<String> stringObservable = RxJavaInterop.toV1Observable(Observable.just("hi").toFlowable(BackpressureStrategy.DROP));
//    Observable<String> stringObservable2 = RxJavaInterop.toV2Observable(stringObservable);
//
//    Subject<Object, Object> objectSubject = RxJavaInterop.toV1Subject(PublishProcessor.create());
//    FlowableProcessor<Object> flowableProcessor = RxJavaInterop.toV2Processor(objectSubject);
//  }
//
//  @Test
//  public void reactiveStreams_reactor_rxjava() {
//    Observable<String> hello = Observable.just("Hello");
//    Flux<String> flux = Flux.from(hello.toFlowable(BackpressureStrategy.DROP));
//    flux.subscribe(System.out::println);
//
//    Single<String> single = Single.just("Hello Single");
//    Mono<String> mono = Mono.from(single.toFlowable());
//    mono.subscribe(System.out::println);
//  }
}
