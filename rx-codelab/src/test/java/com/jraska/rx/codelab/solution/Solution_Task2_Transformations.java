package com.jraska.rx.codelab.solution;

import com.jraska.rx.codelab.RxLogging;
import com.jraska.rx.codelab.http.GitHubApi;
import com.jraska.rx.codelab.http.GitHubConverter;
import com.jraska.rx.codelab.http.HttpModule;
import com.jraska.rx.codelab.http.User;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;

public class Solution_Task2_Transformations {
  private static final String LOGIN = "defunkt";
  private GitHubApi gitHubApi = HttpModule.mockedGitHubApi();

  @Before
  public void setUp() {
    RxLogging.enableObservableSubscribeLogging();
  }

  @Test
  public void map_convertUserDto() {
    gitHubApi.getUser(LOGIN)
      .map(GitHubConverter::convert)
      .map(User::toString)
      .subscribe(System.out::println);
  }

  @Test
  public void flatMap_getFirstUserDetailAfterGettingList() {
    gitHubApi.getFirstUsers()
      .flatMap(gitHubUsers -> gitHubApi.getUser(gitHubUsers.get(0).login))
      .map(GitHubConverter::convert)
      .subscribe(System.out::println);
  }

  @Test
  public void replayAutoConnect_oneRequestForTwoSubscriptions() {
    Observable<User> observable = gitHubApi.getUser(LOGIN)
      .map(GitHubConverter::convert)
      .replay(1)
      .autoConnect();

    observable.subscribe(System.out::println);
    observable.subscribe(System.out::println);
  }
}
