package com.jraska.rx.codelab;

import com.jraska.rx.codelab.nature.Earth;
import com.jraska.rx.codelab.nature.Universe;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.jraska.rx.codelab.Utils.sleep;

public class Task7_HotObservables {
  Earth earth;

  @Before
  public void before() {
    earth = Universe.bigBang().planetEarth();
  }

  @Test
  public void subscribeToHotObservable() {
    earth.thamesRiver().subscribe(System.out::println);
    earth.thamesRiver().subscribe(System.out::println);
  }

  @Test
  public void coldObservable() {
    earth.gimmeOil().subscribe(System.out::println);
    earth.gimmeOil().subscribe(System.out::println);
  }

  @After
  public void after() {
    sleep(500);
  }
}
