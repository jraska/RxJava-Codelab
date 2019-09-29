package com.jraska.rx.codelab.http

data class User(
  val login: String,
  val avatarUrl: String,
  val isAdmin: Boolean,
  val gitHubUrl: String
)
