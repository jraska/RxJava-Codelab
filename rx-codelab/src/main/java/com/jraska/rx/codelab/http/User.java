package com.jraska.rx.codelab.http;

public final class User {
  public final String login;
  public final String avatarUrl;
  public final String gitHubUrl;

  public final boolean isAdmin;

  public User(String login, String avatarUrl,
              boolean isAdmin, String gitHubUrl) {
    this.login = login;
    this.avatarUrl = avatarUrl;
    this.isAdmin = isAdmin;
    this.gitHubUrl = gitHubUrl;
  }

  @Override
  public String toString() {
    return "User{" +
      "login='" + login + '\'' +
      ", avatarUrl='" + avatarUrl + '\'' +
      ", gitHubUrl='" + gitHubUrl + '\'' +
      ", isAdmin=" + isAdmin +
      '}';
  }
}
