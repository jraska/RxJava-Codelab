package com.jraska.rx.codelab.server

object RxServerFactory {
  fun create(): RxServer {
    return ChattyServer.create()
  }
}
