package com.jraska.rx.codelab.server

import io.reactivex.Flowable
import io.reactivex.Observable

interface RxServer {

  fun debugLogsHot(): Observable<Log>

  fun allLogsHot(): Flowable<Log>
}
