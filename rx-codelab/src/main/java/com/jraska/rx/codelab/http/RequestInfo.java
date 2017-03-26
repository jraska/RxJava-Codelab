package com.jraska.rx.codelab.http;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public final class RequestInfo {
  @SerializedName("args")
  @Expose
  public Map<String, String> args;
  @SerializedName("headers")
  @Expose
  public Map<String, String> headers;
  @SerializedName("origin")
  @Expose
  public String origin;
  @SerializedName("url")
  @Expose
  public String url;
  @SerializedName("data")
  @Expose
  public String data;
  @SerializedName("json")
  @Expose
  public String json;

  @Override
  public String toString() {
    return "RequestInfo{" +
      "args=" + args +
      ",\nheaders=" + headers +
      ",\norigin='" + origin + '\'' +
      ",\nurl='" + url + '\'' +
      ",\ndata='" + data + '\'' +
      ",\njson='" + json + '\'' +
      '}';
  }
}
