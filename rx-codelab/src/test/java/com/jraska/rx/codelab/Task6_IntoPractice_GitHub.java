package com.jraska.rx.codelab;

import com.jraska.rx.codelab.http.GitHubApi;
import com.jraska.rx.codelab.http.GitHubRepo;
import com.jraska.rx.codelab.http.GitHubUser;
import com.jraska.rx.codelab.http.HttpModule;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import io.reactivex.Observable;

@Ignore // to avoid running out of GitHub rate limit too soon
public class Task6_IntoPractice_GitHub {
  private static final String LOGIN = "defunkt"; // One of GitHub founders. <3 GitHub <3

  GitHubApi gitHubApi;

  @Before
  public void before() {
    gitHubApi = HttpModule.gitHubApi();
  }

  @Test
  public void map_printUser() {
    Observable<GitHubUser> userObservable = gitHubApi.getUser(LOGIN);

    // TODO(josef): Map GitHubUser object into User and print it out. User has toString implemented.
    // NOTE: You can find GitHubConverter useful for converting between different object types.
  }

  @Test
  public void flatMap_getFirstUserAndPrintHim() {
    Observable<List<GitHubUser>> firstUsers = gitHubApi.getFirstUsers();

//    Observable<GitHubUser> userObservable = gitHubApi.getUser(loginVariable);

    // TODO(josef): Pick first user 'login' from the list. Perform another request and print the user.
  }

  @Test
  public void zip_getUserAndHisRepos() {
    Observable<GitHubUser> userObservable = gitHubApi.getUser(LOGIN);
    Observable<List<GitHubRepo>> reposObservable = gitHubApi.getRepos(LOGIN);

    // TODO(josef): Get User with his Repos to create Observable<UserWithRepos> and print the user with repos.
    // NOTE: You can find GitHubConverter useful for converting between different object types.
  }

  @Test
  public void zip_subscribeOn_twoUserAndReposInParallel() {
    Observable<GitHubUser> userObservable = gitHubApi.getUser(LOGIN);
    Observable<List<GitHubRepo>> reposObservable = gitHubApi.getRepos(LOGIN);

    // TODO(josef): Get User with his Repos to in parallel to create Observable<UserWithRepos> and print the user with repos.
    // NOTE: Use Thread.sleep to keep the unit test running, or you can use blockingSubscribe from RxJava
  }

  @Test
  public void zip_subscribeOn_twoSerialRequestsWithScheduler() {
    Observable<GitHubUser> userObservable = gitHubApi.getUser(LOGIN);
    Observable<List<GitHubRepo>> reposObservable = gitHubApi.getRepos(LOGIN);

    // TODO: Use Schedulers.single() to run requests from previous test in serial order, but be scheduled out of current thread
    // NOTE: Use Thread.sleep to keep the unit test running, or you can use blockingSubscribe from RxJava

    sleep(2000); // In real code the application just continues, but this is unit test
  }

  @Test
  public void subscribeOn_observeOn_printRequestsFromDifferentThread() {
    Observable<GitHubUser> userObservable = gitHubApi.getUser(LOGIN);
    Observable<List<GitHubRepo>> reposObservable = gitHubApi.getRepos(LOGIN);

    // TODO: Lets play around and make the requests run in parallel, but log the emits from the same thread. You can use printWithThreadId to check that

    printWithThreadId("Current thread");

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
