package com.jraska.rx.codelab.solution;

import com.jraska.rx.codelab.http.GitHubApi;
import com.jraska.rx.codelab.http.GitHubConverter;
import com.jraska.rx.codelab.http.HttpModule;
import com.jraska.rx.codelab.http.UserWithRepos;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.schedulers.Schedulers;

public class Solution_Task5_IntoPractice_GitHub {
  private static final String LOGIN = "defunkt"; // One of GitHub founders. <3 GitHub <3

  GitHubApi gitHubApi;

  @Before
  public void before() {
    gitHubApi = HttpModule.mockedGitHubApi();
  }

  @Test
  public void map_printUser() {
    gitHubApi.getUser(LOGIN)
      .map(GitHubConverter::convert)
      .subscribe(System.out::println);
  }

  @Test
  public void flatMap_getFirstUserAndPrintHim() {
    gitHubApi.getFirstUsers()
      .flatMap(gitHubUsers -> gitHubApi.getUser(gitHubUsers.get(0).login))
      .map(GitHubConverter::convert)
      .subscribe(System.out::println);
  }

  @Test
  public void zip_getUserAndHisRepos() {
    gitHubApi.getUser(LOGIN)
      .map(GitHubConverter::convert)
      .zipWith(gitHubApi.getRepos(LOGIN)
        .map(GitHubConverter::convert), UserWithRepos::new)
      .subscribe(System.out::println);
  }

  @Test
  public void zip_subscribeOn_twoUserAndReposInParallel() {
    gitHubApi.getUser(LOGIN)
      .map(GitHubConverter::convert)
      .subscribeOn(Schedulers.io())
      .zipWith(gitHubApi.getRepos(LOGIN)
        .map(GitHubConverter::convert), UserWithRepos::new)
      .subscribe(System.out::println);
  }

  @Test
  public void zip_subscribeOn_twoSerialRequestsWithScheduler() {
    // NOTE: Use Thread.sleep to keep the unit test running, or you can use blockingSubscribe from RxJava

    gitHubApi.getUser(LOGIN)
      .map(GitHubConverter::convert)
      .subscribeOn(Schedulers.single())
      .zipWith(gitHubApi.getRepos(LOGIN)
        .map(GitHubConverter::convert)
        .subscribeOn(Schedulers.single()), UserWithRepos::new)
      .subscribe(System.out::println);

    sleep(2000); // In real code the application just continues, but this is unit test
  }

  @Test
  public void subscribeOn_observeOn_printRequestsFromDifferentThread() {
    printWithThreadId("");

    gitHubApi.getUser(LOGIN)
      .map(GitHubConverter::convert)
      .subscribeOn(Schedulers.io())
      .doOnNext(this::printWithThreadId)
      .zipWith(gitHubApi.getRepos(LOGIN)
        .map(GitHubConverter::convert)
        .subscribeOn(Schedulers.io())
        .doOnNext(this::printWithThreadId), UserWithRepos::new)
      .observeOn(Schedulers.single())
      .subscribe(this::printWithThreadId);

    sleep(2000); // In real code the application just continues, but this is unit test
  }

  void printWithThreadId(Object object) {
    System.out.println("Thread id: " + Thread.currentThread().getId() + ", " + object);
  }

  static void sleep(long timeoutMs) {
    try {
      Thread.sleep(timeoutMs);
    } catch (InterruptedException e) {
      throw new RuntimeException(e); // Being lazy now
    }
  }
}
