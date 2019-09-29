package com.jraska.rx.codelab;

import com.jraska.rx.codelab.http.*;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.jraska.rx.codelab.Utils.sleep;

public class Task5_Threading {
  private static final String LOGIN = "defunkt"; // One of GitHub founders. <3 GitHub <3

  private GitHubApi gitHubApi = HttpModule.mockedGitHubApi();

  @After
  public void after() {
    HttpModule.awaitNetworkRequests();
  }

  @Test
  public void zip_subscribeOn_getUserAndHisReposInParallel() {
    Observable<GitHubUser> userObservable = gitHubApi.getUser(LOGIN);
    Observable<List<GitHubRepo>> reposObservable = gitHubApi.getRepos(LOGIN);

    // TODO: Get and print Observable<UserWithRepos> whilst running both requests in parallel
  }

  @Test
  public void zip_subscribeOn_twoUserAndReposInSerialExplicitly() {
    Observable<GitHubUser> userObservable = gitHubApi.getUser(LOGIN);
    Observable<List<GitHubRepo>> reposObservable = gitHubApi.getRepos(LOGIN);

    // TODO: Get and print Observable<UserWithRepos> whilst running both requests in serial order using `Schedulers.single()`
  }

  @Test
  public void observeOn_receivingResultsOnDifferentThreads() {
    Observable<User> userObservable = gitHubApi.getUser(LOGIN).map(GitHubConverter.INSTANCE::convert);
    printWithThreadId("Test thread");

    // TODO: Get user and print him on different threads, use `observeOn`, `doOnNext` and `printWithThreadId` methods
  }

  private void printWithThreadId(Object object) {
    System.out.println("Thread id: " + Thread.currentThread().getId() + ", " + object);
  }
}
