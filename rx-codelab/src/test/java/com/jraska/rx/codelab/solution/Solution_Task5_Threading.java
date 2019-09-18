package com.jraska.rx.codelab.solution;

import com.jraska.rx.codelab.http.*;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.After;
import org.junit.Test;

import java.util.List;

public class Solution_Task5_Threading {
  private static final String LOGIN = "defunkt"; // One of GitHub founders. <3 GitHub <3

  private GitHubApi gitHubApi = HttpModule.mockedGitHubApi();

  @After
  public void after() {
    HttpModule.awaitNetworkRequests();
  }

  @Test
  public void zip_subscribeOn_getUserAndHisReposInParallel() {
    gitHubApi.getUser(LOGIN)
      .subscribeOn(Schedulers.io())
      .zipWith(gitHubApi.getRepos(LOGIN), GitHubConverter::convert)
      .subscribe(System.out::println);
  }

  @Test
  public void zip_subscribeOn_twoUserAndReposInSerialExplicitly() {
    gitHubApi.getUser(LOGIN)
      .subscribeOn(Schedulers.single())
      .zipWith(gitHubApi.getRepos(LOGIN).subscribeOn(Schedulers.single()), GitHubConverter::convert)
      .subscribeOn(Schedulers.io())
      .subscribe(System.out::println);
  }

  @Test
  public void observeOn_receivingResultsOnDifferentThreads() {
    Observable<User> userObservable = gitHubApi.getUser(LOGIN).map(GitHubConverter::convert);
    printWithThreadId("Test thread");

    userObservable.doOnNext(this::printWithThreadId)
      .observeOn(Schedulers.single())
      .doOnNext(this::printWithThreadId)
      .observeOn(Schedulers.computation())
      .subscribe(this::printWithThreadId);
  }

  private void printWithThreadId(Object object) {
    System.out.println("Thread id: " + Thread.currentThread().getId() + ", " + object);
  }
}
