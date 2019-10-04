package com.jraska.rx.codelab.http

import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

abstract class HttpModule private constructor() {

  init {
    throw AssertionError("No instances")
  }

  companion object {
    private val dispatcher = Dispatcher()

    fun mockedGitHubApi(): GitHubApi {
      return gitHubRetrofitBuilder()
        .client(okClientBuilder()
          .addInterceptor(MockResponsesInterceptor())
          .build())
        .build()
        .create(GitHubApi::class.java)
    }

    fun gitHubApi(): GitHubApi {
      return gitHubRetrofitBuilder()
        .client(okClientBuilder().build())
        .build()
        .create(GitHubApi::class.java)
    }

    fun httpBinApi(): HttpBinApi {
      return httpBinRetrofit().create(HttpBinApi::class.java)
    }

    private fun gitHubRetrofitBuilder(): Retrofit.Builder {
      return Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .validateEagerly(true)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    private fun httpBinRetrofit(): Retrofit {
      return Retrofit.Builder()
        .baseUrl("http://httpbin.org")
        .validateEagerly(true)
        .client(okClientBuilder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    }

    private fun okClientBuilder(): OkHttpClient.Builder {
      val timeFormat = SimpleDateFormat("HH:mm:ss.SSS")
      val loggingInterceptor = HttpLoggingInterceptor { message -> println(timeFormat.format(Date()) + ": " + message) }
        .setLevel(Level.BASIC)

      return OkHttpClient.Builder()
        .dispatcher(dispatcher)
        .addInterceptor(loggingInterceptor)
    }

    fun awaitNetworkRequests() {
      Thread.sleep(25)
      // There is a small delay during thread hopping in Rx
      while (dispatcher.runningCallsCount() > 0) {
        Thread.sleep(25)
      }

      Thread.sleep(25)
      // To allow consuming results, There is a small delay during thread hopping in Rx
    }
  }
}
