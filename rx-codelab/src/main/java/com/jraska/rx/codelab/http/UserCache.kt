package com.jraska.rx.codelab.http

import io.reactivex.Observable

/**
 * Showcase purpose - not a cache at all :P
 */
object UserCache {
  fun getUser(login: String): Observable<User> {
    return Observable.fromCallable { getUserSync(login) }
  }

  private fun getUserSync(login: String): User {
    return User(login, "", true, "")
  }
}
