package com.jraska.rx.codelab;

import com.jraska.rx.codelab.forest.Forest;
import com.jraska.rx.codelab.forest.Log;
import com.jraska.rx.codelab.forest.Lumberjack;
import com.jraska.rx.codelab.forest.Tools;
import com.jraska.rx.codelab.furniture.Chair;
import com.jraska.rx.codelab.furniture.Sofa;
import com.jraska.rx.codelab.furniture.Table;
import com.jraska.rx.codelab.http.*;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;

public class Task3_Combining {

  private static final String LOGIN = "defunkt";
  private GitHubApi gitHubApi = HttpModule.mockedGitHubApi();

  @Before
  public void setUp() {
    RxLogging.enableObservableSubscribeLogging();
  }

  @Test
  public void zipWith_userWithRepos() {
    gitHubApi.getUser(LOGIN)
      .zipWith(gitHubApi.getRepos(LOGIN), GitHubConverter::convert)
      .subscribe(System.out::println);

    // TODO: Get user with `LOGIN` and his repos and print them. Use `GitHubConverter` as zipper
  }

  @Test
  public void startWith_userInCache() {
    gitHubApi.getUser(LOGIN)
      .map(GitHubConverter::convert)
      .startWith(UserCache.getUser(LOGIN))
      .subscribe(System.out::println);

    // TODO: Get user with `LOGIN` and startWith a `UserCache.getUserSync(LOGIN)`, Subscribe and print both values
  }

  @Test
  public void combineLatest_cachedUserWithRepos() {
    // TODO: Get user with `LOGIN` and his repos using cache with only one repos request - Observable.combineLatest


  }

  @Test
  public void flatMapZip_makeSomeSofa() {
    Observable<Log> logObservable = Lumberjack.cut(Forest.AMAZON).map(Tools::handSaw);

    // TODO: Now Carpenter needs some Rivets to do Sofa, he can use flatMap with Parts.rivet to get some rivets needed for Sofas
    Observable<Sofa> sofaObservable = null;

//  sofaObservable.subscribe(out::println);
  }
}
