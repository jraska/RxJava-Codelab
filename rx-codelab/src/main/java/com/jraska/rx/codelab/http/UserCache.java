package com.jraska.rx.codelab.http;

import io.reactivex.Observable;

/**
 * Showcase purpose - not a cache at all :P
 */
public class UserCache {
  public static Observable<User> getUser(String login) {
    return Observable.fromCallable(() -> getUserSync(login));
  }

  private static User getUserSync(String login) {
    return new User(login, "", true, "");
  }
}
