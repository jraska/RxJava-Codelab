package com.jraska.rx.codelab;

import com.jraska.rx.codelab.nature.Earth;
import com.jraska.rx.codelab.nature.Universe;

import org.junit.Before;
import org.junit.Test;

public class Task7_HotObservables {
  Earth earth;

  @Before
  public void before() {
    earth = Universe.bigBang().planetEarth();
  }

  @Test
  public void test() {
    earth.thamesRiver().blockingSubscribe(System.out::println);
  }
}
