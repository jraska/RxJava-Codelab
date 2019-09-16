package com.jraska.rx.codelab;

import com.jraska.rx.codelab.http.GitHubApi;
import com.jraska.rx.codelab.http.HttpModule;
import org.junit.Before;
import org.junit.Test;

public class Task2_Transformations {

  private static final String LOGIN = "defunkt";
  private GitHubApi gitHubApi = HttpModule.mockedGitHubApi();

  @Before
  public void setUp() {
    RxLogging.enableObservableSubscribeLogging();
  }

  @Test
  public void map_convertUserDto() {
    // TODO: Use gitHubApi to get and print string representation of user with `LOGIN`. Use `User` and `GitHubConverter`
  }

  @Test
  public void flatMap_getFirstUserDetailAfterGettingList() {
    // TODO:  Use gitHubApi to first get list of users and subsequently get its first user by other request
  }

  @Test
  public void replayAutoConnect_oneRequestForTwoSubscriptions() {
    // TODO: Get again the user with `LOGIN`, but subscribe and print twice only with 1 network request
  }
}
