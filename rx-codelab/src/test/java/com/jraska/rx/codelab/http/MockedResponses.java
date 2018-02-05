package com.jraska.rx.codelab.http;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.RealResponseBody;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

final class MockedResponses {
  private final Map<HttpUrl, String> responseUrlMap;

  MockedResponses() {
    responseUrlMap = initMap();
  }

  Response get(Request request) {
    String json = responseUrlMap.get(request.url());
    if (json == null) {
      return null;
    }

    RealResponseBody responseBody = realJsonBody(json);

    return new Response.Builder()
      .request(request)
      .code(200)
      .protocol(Protocol.HTTP_1_1)
      .message("OK Mock Response")
      .body(responseBody)
      .build();
  }

  private RealResponseBody realJsonBody(String rawResponse) {
    Source source = Okio.source(new ByteArrayInputStream(rawResponse.getBytes()));
    BufferedSource buffer = Okio.buffer(source);
    return new RealResponseBody("application/json", 0, buffer);
  }

  private static Map<HttpUrl, String> initMap() {
    HashMap<HttpUrl, String> map = new HashMap<>();
    map.put(HttpUrl.parse("https://api.github.com/users?since=0"), JsonResponses.FIRST_USERS);
    map.put(HttpUrl.parse("https://api.github.com/users/mojombo"), JsonResponses.USER_MOJOMBO);
    map.put(HttpUrl.parse("https://api.github.com/users/defunkt"), JsonResponses.USER_DEFUNKT);
    map.put(HttpUrl.parse("https://api.github.com/users/defunkt/repos"), JsonResponses.REPOS_DEFUNKT);

    return map;
  }

  static class JsonResponses {
    static final String REPOS_DEFUNKT = "[\n" +
      "  {\n" +
      "    \"id\": 1861402,\n" +
      "    \"name\": \"ace\",\n" +
      "    \"full_name\": \"defunkt/ace\",\n" +
      "    \"owner\": {\n" +
      "      \"login\": \"defunkt\",\n" +
      "      \"id\": 2,\n" +
      "      \"avatar_url\": \"https://avatars3.githubusercontent.com/u/2?v=3\",\n" +
      "      \"gravatar_id\": \"\",\n" +
      "      \"url\": \"https://api.github.com/users/defunkt\",\n" +
      "      \"html_url\": \"https://github.com/defunkt\",\n" +
      "      \"followers_url\": \"https://api.github.com/users/defunkt/followers\",\n" +
      "      \"following_url\": \"https://api.github.com/users/defunkt/following{/other_user}\",\n" +
      "      \"gists_url\": \"https://api.github.com/users/defunkt/gists{/gist_id}\",\n" +
      "      \"starred_url\": \"https://api.github.com/users/defunkt/starred{/owner}{/repo}\",\n" +
      "      \"subscriptions_url\": \"https://api.github.com/users/defunkt/subscriptions\",\n" +
      "      \"organizations_url\": \"https://api.github.com/users/defunkt/orgs\",\n" +
      "      \"repos_url\": \"https://api.github.com/users/defunkt/repos\",\n" +
      "      \"events_url\": \"https://api.github.com/users/defunkt/events{/privacy}\",\n" +
      "      \"received_events_url\": \"https://api.github.com/users/defunkt/received_events\",\n" +
      "      \"type\": \"User\",\n" +
      "      \"site_admin\": true\n" +
      "    },\n" +
      "    \"private\": false,\n" +
      "    \"html_url\": \"https://github.com/defunkt/ace\",\n" +
      "    \"description\": \"Ajax.org Cloud9 Editor\",\n" +
      "    \"fork\": true,\n" +
      "    \"url\": \"https://api.github.com/repos/defunkt/ace\",\n" +
      "    \"forks_url\": \"https://api.github.com/repos/defunkt/ace/forks\",\n" +
      "    \"keys_url\": \"https://api.github.com/repos/defunkt/ace/keys{/key_id}\",\n" +
      "    \"collaborators_url\": \"https://api.github.com/repos/defunkt/ace/collaborators{/collaborator}\",\n" +
      "    \"teams_url\": \"https://api.github.com/repos/defunkt/ace/teams\",\n" +
      "    \"hooks_url\": \"https://api.github.com/repos/defunkt/ace/hooks\",\n" +
      "    \"issue_events_url\": \"https://api.github.com/repos/defunkt/ace/issues/events{/number}\",\n" +
      "    \"events_url\": \"https://api.github.com/repos/defunkt/ace/events\",\n" +
      "    \"assignees_url\": \"https://api.github.com/repos/defunkt/ace/assignees{/user}\",\n" +
      "    \"branches_url\": \"https://api.github.com/repos/defunkt/ace/branches{/branch}\",\n" +
      "    \"tags_url\": \"https://api.github.com/repos/defunkt/ace/tags\",\n" +
      "    \"blobs_url\": \"https://api.github.com/repos/defunkt/ace/git/blobs{/sha}\",\n" +
      "    \"git_tags_url\": \"https://api.github.com/repos/defunkt/ace/git/tags{/sha}\",\n" +
      "    \"git_refs_url\": \"https://api.github.com/repos/defunkt/ace/git/refs{/sha}\",\n" +
      "    \"trees_url\": \"https://api.github.com/repos/defunkt/ace/git/trees{/sha}\",\n" +
      "    \"statuses_url\": \"https://api.github.com/repos/defunkt/ace/statuses/{sha}\",\n" +
      "    \"languages_url\": \"https://api.github.com/repos/defunkt/ace/languages\",\n" +
      "    \"stargazers_url\": \"https://api.github.com/repos/defunkt/ace/stargazers\",\n" +
      "    \"contributors_url\": \"https://api.github.com/repos/defunkt/ace/contributors\",\n" +
      "    \"subscribers_url\": \"https://api.github.com/repos/defunkt/ace/subscribers\",\n" +
      "    \"subscription_url\": \"https://api.github.com/repos/defunkt/ace/subscription\",\n" +
      "    \"commits_url\": \"https://api.github.com/repos/defunkt/ace/commits{/sha}\",\n" +
      "    \"git_commits_url\": \"https://api.github.com/repos/defunkt/ace/git/commits{/sha}\",\n" +
      "    \"comments_url\": \"https://api.github.com/repos/defunkt/ace/comments{/number}\",\n" +
      "    \"issue_comment_url\": \"https://api.github.com/repos/defunkt/ace/issues/comments{/number}\",\n" +
      "    \"contents_url\": \"https://api.github.com/repos/defunkt/ace/contents/{+path}\",\n" +
      "    \"compare_url\": \"https://api.github.com/repos/defunkt/ace/compare/{base}...{head}\",\n" +
      "    \"merges_url\": \"https://api.github.com/repos/defunkt/ace/merges\",\n" +
      "    \"archive_url\": \"https://api.github.com/repos/defunkt/ace/{archive_format}{/ref}\",\n" +
      "    \"downloads_url\": \"https://api.github.com/repos/defunkt/ace/downloads\",\n" +
      "    \"issues_url\": \"https://api.github.com/repos/defunkt/ace/issues{/number}\",\n" +
      "    \"pulls_url\": \"https://api.github.com/repos/defunkt/ace/pulls{/number}\",\n" +
      "    \"milestones_url\": \"https://api.github.com/repos/defunkt/ace/milestones{/number}\",\n" +
      "    \"notifications_url\": \"https://api.github.com/repos/defunkt/ace/notifications{?since,all,participating}\",\n" +
      "    \"labels_url\": \"https://api.github.com/repos/defunkt/ace/labels{/name}\",\n" +
      "    \"releases_url\": \"https://api.github.com/repos/defunkt/ace/releases{/id}\",\n" +
      "    \"deployments_url\": \"https://api.github.com/repos/defunkt/ace/deployments\",\n" +
      "    \"created_at\": \"2011-06-07T18:41:40Z\",\n" +
      "    \"updated_at\": \"2016-12-27T18:48:28Z\",\n" +
      "    \"pushed_at\": \"2011-11-16T18:37:42Z\",\n" +
      "    \"git_url\": \"git://github.com/defunkt/ace.git\",\n" +
      "    \"ssh_url\": \"git@github.com:defunkt/ace.git\",\n" +
      "    \"clone_url\": \"https://github.com/defunkt/ace.git\",\n" +
      "    \"svn_url\": \"https://github.com/defunkt/ace\",\n" +
      "    \"homepage\": \"http://ace.ajax.org\",\n" +
      "    \"size\": 4405,\n" +
      "    \"stargazers_count\": 11,\n" +
      "    \"watchers_count\": 11,\n" +
      "    \"language\": \"JavaScript\",\n" +
      "    \"has_issues\": false,\n" +
      "    \"has_projects\": true,\n" +
      "    \"has_downloads\": true,\n" +
      "    \"has_wiki\": true,\n" +
      "    \"has_pages\": false,\n" +
      "    \"forks_count\": 5,\n" +
      "    \"mirror_url\": null,\n" +
      "    \"open_issues_count\": 0,\n" +
      "    \"forks\": 5,\n" +
      "    \"open_issues\": 0,\n" +
      "    \"watchers\": 11,\n" +
      "    \"default_branch\": \"master\"\n" +
      "  },\n" +
      "  {\n" +
      "    \"id\": 388149,\n" +
      "    \"name\": \"colored\",\n" +
      "    \"full_name\": \"defunkt/colored\",\n" +
      "    \"owner\": {\n" +
      "      \"login\": \"defunkt\",\n" +
      "      \"id\": 2,\n" +
      "      \"avatar_url\": \"https://avatars3.githubusercontent.com/u/2?v=3\",\n" +
      "      \"gravatar_id\": \"\",\n" +
      "      \"url\": \"https://api.github.com/users/defunkt\",\n" +
      "      \"html_url\": \"https://github.com/defunkt\",\n" +
      "      \"followers_url\": \"https://api.github.com/users/defunkt/followers\",\n" +
      "      \"following_url\": \"https://api.github.com/users/defunkt/following{/other_user}\",\n" +
      "      \"gists_url\": \"https://api.github.com/users/defunkt/gists{/gist_id}\",\n" +
      "      \"starred_url\": \"https://api.github.com/users/defunkt/starred{/owner}{/repo}\",\n" +
      "      \"subscriptions_url\": \"https://api.github.com/users/defunkt/subscriptions\",\n" +
      "      \"organizations_url\": \"https://api.github.com/users/defunkt/orgs\",\n" +
      "      \"repos_url\": \"https://api.github.com/users/defunkt/repos\",\n" +
      "      \"events_url\": \"https://api.github.com/users/defunkt/events{/privacy}\",\n" +
      "      \"received_events_url\": \"https://api.github.com/users/defunkt/received_events\",\n" +
      "      \"type\": \"User\",\n" +
      "      \"site_admin\": true\n" +
      "    },\n" +
      "    \"private\": false,\n" +
      "    \"html_url\": \"https://github.com/defunkt/colored\",\n" +
      "    \"description\": \"Colors in your terminal. Unmaintained.\",\n" +
      "    \"fork\": false,\n" +
      "    \"url\": \"https://api.github.com/repos/defunkt/colored\",\n" +
      "    \"forks_url\": \"https://api.github.com/repos/defunkt/colored/forks\",\n" +
      "    \"keys_url\": \"https://api.github.com/repos/defunkt/colored/keys{/key_id}\",\n" +
      "    \"collaborators_url\": \"https://api.github.com/repos/defunkt/colored/collaborators{/collaborator}\",\n" +
      "    \"teams_url\": \"https://api.github.com/repos/defunkt/colored/teams\",\n" +
      "    \"hooks_url\": \"https://api.github.com/repos/defunkt/colored/hooks\",\n" +
      "    \"issue_events_url\": \"https://api.github.com/repos/defunkt/colored/issues/events{/number}\",\n" +
      "    \"events_url\": \"https://api.github.com/repos/defunkt/colored/events\",\n" +
      "    \"assignees_url\": \"https://api.github.com/repos/defunkt/colored/assignees{/user}\",\n" +
      "    \"branches_url\": \"https://api.github.com/repos/defunkt/colored/branches{/branch}\",\n" +
      "    \"tags_url\": \"https://api.github.com/repos/defunkt/colored/tags\",\n" +
      "    \"blobs_url\": \"https://api.github.com/repos/defunkt/colored/git/blobs{/sha}\",\n" +
      "    \"git_tags_url\": \"https://api.github.com/repos/defunkt/colored/git/tags{/sha}\",\n" +
      "    \"git_refs_url\": \"https://api.github.com/repos/defunkt/colored/git/refs{/sha}\",\n" +
      "    \"trees_url\": \"https://api.github.com/repos/defunkt/colored/git/trees{/sha}\",\n" +
      "    \"statuses_url\": \"https://api.github.com/repos/defunkt/colored/statuses/{sha}\",\n" +
      "    \"languages_url\": \"https://api.github.com/repos/defunkt/colored/languages\",\n" +
      "    \"stargazers_url\": \"https://api.github.com/repos/defunkt/colored/stargazers\",\n" +
      "    \"contributors_url\": \"https://api.github.com/repos/defunkt/colored/contributors\",\n" +
      "    \"subscribers_url\": \"https://api.github.com/repos/defunkt/colored/subscribers\",\n" +
      "    \"subscription_url\": \"https://api.github.com/repos/defunkt/colored/subscription\",\n" +
      "    \"commits_url\": \"https://api.github.com/repos/defunkt/colored/commits{/sha}\",\n" +
      "    \"git_commits_url\": \"https://api.github.com/repos/defunkt/colored/git/commits{/sha}\",\n" +
      "    \"comments_url\": \"https://api.github.com/repos/defunkt/colored/comments{/number}\",\n" +
      "    \"issue_comment_url\": \"https://api.github.com/repos/defunkt/colored/issues/comments{/number}\",\n" +
      "    \"contents_url\": \"https://api.github.com/repos/defunkt/colored/contents/{+path}\",\n" +
      "    \"compare_url\": \"https://api.github.com/repos/defunkt/colored/compare/{base}...{head}\",\n" +
      "    \"merges_url\": \"https://api.github.com/repos/defunkt/colored/merges\",\n" +
      "    \"archive_url\": \"https://api.github.com/repos/defunkt/colored/{archive_format}{/ref}\",\n" +
      "    \"downloads_url\": \"https://api.github.com/repos/defunkt/colored/downloads\",\n" +
      "    \"issues_url\": \"https://api.github.com/repos/defunkt/colored/issues{/number}\",\n" +
      "    \"pulls_url\": \"https://api.github.com/repos/defunkt/colored/pulls{/number}\",\n" +
      "    \"milestones_url\": \"https://api.github.com/repos/defunkt/colored/milestones{/number}\",\n" +
      "    \"notifications_url\": \"https://api.github.com/repos/defunkt/colored/notifications{?since,all,participating}\",\n" +
      "    \"labels_url\": \"https://api.github.com/repos/defunkt/colored/labels{/name}\",\n" +
      "    \"releases_url\": \"https://api.github.com/repos/defunkt/colored/releases{/id}\",\n" +
      "    \"deployments_url\": \"https://api.github.com/repos/defunkt/colored/deployments\",\n" +
      "    \"created_at\": \"2009-11-28T06:16:20Z\",\n" +
      "    \"updated_at\": \"2017-04-12T15:33:09Z\",\n" +
      "    \"pushed_at\": \"2017-02-14T12:49:33Z\",\n" +
      "    \"git_url\": \"git://github.com/defunkt/colored.git\",\n" +
      "    \"ssh_url\": \"git@github.com:defunkt/colored.git\",\n" +
      "    \"clone_url\": \"https://github.com/defunkt/colored.git\",\n" +
      "    \"svn_url\": \"https://github.com/defunkt/colored\",\n" +
      "    \"homepage\": \"\",\n" +
      "    \"size\": 120,\n" +
      "    \"stargazers_count\": 255,\n" +
      "    \"watchers_count\": 255,\n" +
      "    \"language\": \"Ruby\",\n" +
      "    \"has_issues\": false,\n" +
      "    \"has_projects\": true,\n" +
      "    \"has_downloads\": false,\n" +
      "    \"has_wiki\": false,\n" +
      "    \"has_pages\": false,\n" +
      "    \"forks_count\": 41,\n" +
      "    \"mirror_url\": null,\n" +
      "    \"open_issues_count\": 8,\n" +
      "    \"forks\": 41,\n" +
      "    \"open_issues\": 8,\n" +
      "    \"watchers\": 255,\n" +
      "    \"default_branch\": \"master\"\n" +
      "  },\n" +
      "  {\n" +
      "    \"id\": 12220,\n" +
      "    \"name\": \"currency_converter\",\n" +
      "    \"full_name\": \"defunkt/currency_converter\",\n" +
      "    \"owner\": {\n" +
      "      \"login\": \"defunkt\",\n" +
      "      \"id\": 2,\n" +
      "      \"avatar_url\": \"https://avatars3.githubusercontent.com/u/2?v=3\",\n" +
      "      \"gravatar_id\": \"\",\n" +
      "      \"url\": \"https://api.github.com/users/defunkt\",\n" +
      "      \"html_url\": \"https://github.com/defunkt\",\n" +
      "      \"followers_url\": \"https://api.github.com/users/defunkt/followers\",\n" +
      "      \"following_url\": \"https://api.github.com/users/defunkt/following{/other_user}\",\n" +
      "      \"gists_url\": \"https://api.github.com/users/defunkt/gists{/gist_id}\",\n" +
      "      \"starred_url\": \"https://api.github.com/users/defunkt/starred{/owner}{/repo}\",\n" +
      "      \"subscriptions_url\": \"https://api.github.com/users/defunkt/subscriptions\",\n" +
      "      \"organizations_url\": \"https://api.github.com/users/defunkt/orgs\",\n" +
      "      \"repos_url\": \"https://api.github.com/users/defunkt/repos\",\n" +
      "      \"events_url\": \"https://api.github.com/users/defunkt/events{/privacy}\",\n" +
      "      \"received_events_url\": \"https://api.github.com/users/defunkt/received_events\",\n" +
      "      \"type\": \"User\",\n" +
      "      \"site_admin\": true\n" +
      "    },\n" +
      "    \"private\": false,\n" +
      "    \"html_url\": \"https://github.com/defunkt/currency_converter\",\n" +
      "    \"description\": null,\n" +
      "    \"fork\": false,\n" +
      "    \"url\": \"https://api.github.com/repos/defunkt/currency_converter\",\n" +
      "    \"forks_url\": \"https://api.github.com/repos/defunkt/currency_converter/forks\",\n" +
      "    \"keys_url\": \"https://api.github.com/repos/defunkt/currency_converter/keys{/key_id}\",\n" +
      "    \"collaborators_url\": \"https://api.github.com/repos/defunkt/currency_converter/collaborators{/collaborator}\",\n" +
      "    \"teams_url\": \"https://api.github.com/repos/defunkt/currency_converter/teams\",\n" +
      "    \"hooks_url\": \"https://api.github.com/repos/defunkt/currency_converter/hooks\",\n" +
      "    \"issue_events_url\": \"https://api.github.com/repos/defunkt/currency_converter/issues/events{/number}\",\n" +
      "    \"events_url\": \"https://api.github.com/repos/defunkt/currency_converter/events\",\n" +
      "    \"assignees_url\": \"https://api.github.com/repos/defunkt/currency_converter/assignees{/user}\",\n" +
      "    \"branches_url\": \"https://api.github.com/repos/defunkt/currency_converter/branches{/branch}\",\n" +
      "    \"tags_url\": \"https://api.github.com/repos/defunkt/currency_converter/tags\",\n" +
      "    \"blobs_url\": \"https://api.github.com/repos/defunkt/currency_converter/git/blobs{/sha}\",\n" +
      "    \"git_tags_url\": \"https://api.github.com/repos/defunkt/currency_converter/git/tags{/sha}\",\n" +
      "    \"git_refs_url\": \"https://api.github.com/repos/defunkt/currency_converter/git/refs{/sha}\",\n" +
      "    \"trees_url\": \"https://api.github.com/repos/defunkt/currency_converter/git/trees{/sha}\",\n" +
      "    \"statuses_url\": \"https://api.github.com/repos/defunkt/currency_converter/statuses/{sha}\",\n" +
      "    \"languages_url\": \"https://api.github.com/repos/defunkt/currency_converter/languages\",\n" +
      "    \"stargazers_url\": \"https://api.github.com/repos/defunkt/currency_converter/stargazers\",\n" +
      "    \"contributors_url\": \"https://api.github.com/repos/defunkt/currency_converter/contributors\",\n" +
      "    \"subscribers_url\": \"https://api.github.com/repos/defunkt/currency_converter/subscribers\",\n" +
      "    \"subscription_url\": \"https://api.github.com/repos/defunkt/currency_converter/subscription\",\n" +
      "    \"commits_url\": \"https://api.github.com/repos/defunkt/currency_converter/commits{/sha}\",\n" +
      "    \"git_commits_url\": \"https://api.github.com/repos/defunkt/currency_converter/git/commits{/sha}\",\n" +
      "    \"comments_url\": \"https://api.github.com/repos/defunkt/currency_converter/comments{/number}\",\n" +
      "    \"issue_comment_url\": \"https://api.github.com/repos/defunkt/currency_converter/issues/comments{/number}\",\n" +
      "    \"contents_url\": \"https://api.github.com/repos/defunkt/currency_converter/contents/{+path}\",\n" +
      "    \"compare_url\": \"https://api.github.com/repos/defunkt/currency_converter/compare/{base}...{head}\",\n" +
      "    \"merges_url\": \"https://api.github.com/repos/defunkt/currency_converter/merges\",\n" +
      "    \"archive_url\": \"https://api.github.com/repos/defunkt/currency_converter/{archive_format}{/ref}\",\n" +
      "    \"downloads_url\": \"https://api.github.com/repos/defunkt/currency_converter/downloads\",\n" +
      "    \"issues_url\": \"https://api.github.com/repos/defunkt/currency_converter/issues{/number}\",\n" +
      "    \"pulls_url\": \"https://api.github.com/repos/defunkt/currency_converter/pulls{/number}\",\n" +
      "    \"milestones_url\": \"https://api.github.com/repos/defunkt/currency_converter/milestones{/number}\",\n" +
      "    \"notifications_url\": \"https://api.github.com/repos/defunkt/currency_converter/notifications{?since,all,participating}\",\n" +
      "    \"labels_url\": \"https://api.github.com/repos/defunkt/currency_converter/labels{/name}\",\n" +
      "    \"releases_url\": \"https://api.github.com/repos/defunkt/currency_converter/releases{/id}\",\n" +
      "    \"deployments_url\": \"https://api.github.com/repos/defunkt/currency_converter/deployments\",\n" +
      "    \"created_at\": \"2008-04-24T09:34:31Z\",\n" +
      "    \"updated_at\": \"2016-08-05T16:22:04Z\",\n" +
      "    \"pushed_at\": \"2008-04-24T09:36:14Z\",\n" +
      "    \"git_url\": \"git://github.com/defunkt/currency_converter.git\",\n" +
      "    \"ssh_url\": \"git@github.com:defunkt/currency_converter.git\",\n" +
      "    \"clone_url\": \"https://github.com/defunkt/currency_converter.git\",\n" +
      "    \"svn_url\": \"https://github.com/defunkt/currency_converter\",\n" +
      "    \"homepage\": \"\",\n" +
      "    \"size\": 374,\n" +
      "    \"stargazers_count\": 8,\n" +
      "    \"watchers_count\": 8,\n" +
      "    \"language\": \"Objective-C\",\n" +
      "    \"has_issues\": true,\n" +
      "    \"has_projects\": true,\n" +
      "    \"has_downloads\": true,\n" +
      "    \"has_wiki\": true,\n" +
      "    \"has_pages\": false,\n" +
      "    \"forks_count\": 3,\n" +
      "    \"mirror_url\": null,\n" +
      "    \"open_issues_count\": 0,\n" +
      "    \"forks\": 3,\n" +
      "    \"open_issues\": 0,\n" +
      "    \"watchers\": 8,\n" +
      "    \"default_branch\": \"master\"\n" +
      "  },\n" +
      "  {\n" +
      "    \"id\": 18570642,\n" +
      "    \"name\": \"d3\",\n" +
      "    \"full_name\": \"defunkt/d3\",\n" +
      "    \"owner\": {\n" +
      "      \"login\": \"defunkt\",\n" +
      "      \"id\": 2,\n" +
      "      \"avatar_url\": \"https://avatars3.githubusercontent.com/u/2?v=3\",\n" +
      "      \"gravatar_id\": \"\",\n" +
      "      \"url\": \"https://api.github.com/users/defunkt\",\n" +
      "      \"html_url\": \"https://github.com/defunkt\",\n" +
      "      \"followers_url\": \"https://api.github.com/users/defunkt/followers\",\n" +
      "      \"following_url\": \"https://api.github.com/users/defunkt/following{/other_user}\",\n" +
      "      \"gists_url\": \"https://api.github.com/users/defunkt/gists{/gist_id}\",\n" +
      "      \"starred_url\": \"https://api.github.com/users/defunkt/starred{/owner}{/repo}\",\n" +
      "      \"subscriptions_url\": \"https://api.github.com/users/defunkt/subscriptions\",\n" +
      "      \"organizations_url\": \"https://api.github.com/users/defunkt/orgs\",\n" +
      "      \"repos_url\": \"https://api.github.com/users/defunkt/repos\",\n" +
      "      \"events_url\": \"https://api.github.com/users/defunkt/events{/privacy}\",\n" +
      "      \"received_events_url\": \"https://api.github.com/users/defunkt/received_events\",\n" +
      "      \"type\": \"User\",\n" +
      "      \"site_admin\": true\n" +
      "    },\n" +
      "    \"private\": false,\n" +
      "    \"html_url\": \"https://github.com/defunkt/d3\",\n" +
      "    \"description\": \"A JavaScript visualization library for HTML and SVG.\",\n" +
      "    \"fork\": true,\n" +
      "    \"url\": \"https://api.github.com/repos/defunkt/d3\",\n" +
      "    \"forks_url\": \"https://api.github.com/repos/defunkt/d3/forks\",\n" +
      "    \"keys_url\": \"https://api.github.com/repos/defunkt/d3/keys{/key_id}\",\n" +
      "    \"collaborators_url\": \"https://api.github.com/repos/defunkt/d3/collaborators{/collaborator}\",\n" +
      "    \"teams_url\": \"https://api.github.com/repos/defunkt/d3/teams\",\n" +
      "    \"hooks_url\": \"https://api.github.com/repos/defunkt/d3/hooks\",\n" +
      "    \"issue_events_url\": \"https://api.github.com/repos/defunkt/d3/issues/events{/number}\",\n" +
      "    \"events_url\": \"https://api.github.com/repos/defunkt/d3/events\",\n" +
      "    \"assignees_url\": \"https://api.github.com/repos/defunkt/d3/assignees{/user}\",\n" +
      "    \"branches_url\": \"https://api.github.com/repos/defunkt/d3/branches{/branch}\",\n" +
      "    \"tags_url\": \"https://api.github.com/repos/defunkt/d3/tags\",\n" +
      "    \"blobs_url\": \"https://api.github.com/repos/defunkt/d3/git/blobs{/sha}\",\n" +
      "    \"git_tags_url\": \"https://api.github.com/repos/defunkt/d3/git/tags{/sha}\",\n" +
      "    \"git_refs_url\": \"https://api.github.com/repos/defunkt/d3/git/refs{/sha}\",\n" +
      "    \"trees_url\": \"https://api.github.com/repos/defunkt/d3/git/trees{/sha}\",\n" +
      "    \"statuses_url\": \"https://api.github.com/repos/defunkt/d3/statuses/{sha}\",\n" +
      "    \"languages_url\": \"https://api.github.com/repos/defunkt/d3/languages\",\n" +
      "    \"stargazers_url\": \"https://api.github.com/repos/defunkt/d3/stargazers\",\n" +
      "    \"contributors_url\": \"https://api.github.com/repos/defunkt/d3/contributors\",\n" +
      "    \"subscribers_url\": \"https://api.github.com/repos/defunkt/d3/subscribers\",\n" +
      "    \"subscription_url\": \"https://api.github.com/repos/defunkt/d3/subscription\",\n" +
      "    \"commits_url\": \"https://api.github.com/repos/defunkt/d3/commits{/sha}\",\n" +
      "    \"git_commits_url\": \"https://api.github.com/repos/defunkt/d3/git/commits{/sha}\",\n" +
      "    \"comments_url\": \"https://api.github.com/repos/defunkt/d3/comments{/number}\",\n" +
      "    \"issue_comment_url\": \"https://api.github.com/repos/defunkt/d3/issues/comments{/number}\",\n" +
      "    \"contents_url\": \"https://api.github.com/repos/defunkt/d3/contents/{+path}\",\n" +
      "    \"compare_url\": \"https://api.github.com/repos/defunkt/d3/compare/{base}...{head}\",\n" +
      "    \"merges_url\": \"https://api.github.com/repos/defunkt/d3/merges\",\n" +
      "    \"archive_url\": \"https://api.github.com/repos/defunkt/d3/{archive_format}{/ref}\",\n" +
      "    \"downloads_url\": \"https://api.github.com/repos/defunkt/d3/downloads\",\n" +
      "    \"issues_url\": \"https://api.github.com/repos/defunkt/d3/issues{/number}\",\n" +
      "    \"pulls_url\": \"https://api.github.com/repos/defunkt/d3/pulls{/number}\",\n" +
      "    \"milestones_url\": \"https://api.github.com/repos/defunkt/d3/milestones{/number}\",\n" +
      "    \"notifications_url\": \"https://api.github.com/repos/defunkt/d3/notifications{?since,all,participating}\",\n" +
      "    \"labels_url\": \"https://api.github.com/repos/defunkt/d3/labels{/name}\",\n" +
      "    \"releases_url\": \"https://api.github.com/repos/defunkt/d3/releases{/id}\",\n" +
      "    \"deployments_url\": \"https://api.github.com/repos/defunkt/d3/deployments\",\n" +
      "    \"created_at\": \"2014-04-08T18:45:26Z\",\n" +
      "    \"updated_at\": \"2016-05-13T16:00:52Z\",\n" +
      "    \"pushed_at\": \"2014-04-08T18:46:26Z\",\n" +
      "    \"git_url\": \"git://github.com/defunkt/d3.git\",\n" +
      "    \"ssh_url\": \"git@github.com:defunkt/d3.git\",\n" +
      "    \"clone_url\": \"https://github.com/defunkt/d3.git\",\n" +
      "    \"svn_url\": \"https://github.com/defunkt/d3\",\n" +
      "    \"homepage\": \"http://d3js.org\",\n" +
      "    \"size\": 34521,\n" +
      "    \"stargazers_count\": 2,\n" +
      "    \"watchers_count\": 2,\n" +
      "    \"language\": \"JavaScript\",\n" +
      "    \"has_issues\": false,\n" +
      "    \"has_projects\": true,\n" +
      "    \"has_downloads\": true,\n" +
      "    \"has_wiki\": true,\n" +
      "    \"has_pages\": false,\n" +
      "    \"forks_count\": 1,\n" +
      "    \"mirror_url\": null,\n" +
      "    \"open_issues_count\": 0,\n" +
      "    \"forks\": 1,\n" +
      "    \"open_issues\": 0,\n" +
      "    \"watchers\": 2,\n" +
      "    \"default_branch\": \"master\"\n" +
      "  },\n" +
      "  {\n" +
      "    \"id\": 91988,\n" +
      "    \"name\": \"defunkt.github.com\",\n" +
      "    \"full_name\": \"defunkt/defunkt.github.com\",\n" +
      "    \"owner\": {\n" +
      "      \"login\": \"defunkt\",\n" +
      "      \"id\": 2,\n" +
      "      \"avatar_url\": \"https://avatars3.githubusercontent.com/u/2?v=3\",\n" +
      "      \"gravatar_id\": \"\",\n" +
      "      \"url\": \"https://api.github.com/users/defunkt\",\n" +
      "      \"html_url\": \"https://github.com/defunkt\",\n" +
      "      \"followers_url\": \"https://api.github.com/users/defunkt/followers\",\n" +
      "      \"following_url\": \"https://api.github.com/users/defunkt/following{/other_user}\",\n" +
      "      \"gists_url\": \"https://api.github.com/users/defunkt/gists{/gist_id}\",\n" +
      "      \"starred_url\": \"https://api.github.com/users/defunkt/starred{/owner}{/repo}\",\n" +
      "      \"subscriptions_url\": \"https://api.github.com/users/defunkt/subscriptions\",\n" +
      "      \"organizations_url\": \"https://api.github.com/users/defunkt/orgs\",\n" +
      "      \"repos_url\": \"https://api.github.com/users/defunkt/repos\",\n" +
      "      \"events_url\": \"https://api.github.com/users/defunkt/events{/privacy}\",\n" +
      "      \"received_events_url\": \"https://api.github.com/users/defunkt/received_events\",\n" +
      "      \"type\": \"User\",\n" +
      "      \"site_admin\": true\n" +
      "    },\n" +
      "    \"private\": false,\n" +
      "    \"html_url\": \"https://github.com/defunkt/defunkt.github.com\",\n" +
      "    \"description\": \"My GitHub Page\",\n" +
      "    \"fork\": false,\n" +
      "    \"url\": \"https://api.github.com/repos/defunkt/defunkt.github.com\",\n" +
      "    \"forks_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/forks\",\n" +
      "    \"keys_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/keys{/key_id}\",\n" +
      "    \"collaborators_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/collaborators{/collaborator}\",\n" +
      "    \"teams_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/teams\",\n" +
      "    \"hooks_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/hooks\",\n" +
      "    \"issue_events_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/issues/events{/number}\",\n" +
      "    \"events_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/events\",\n" +
      "    \"assignees_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/assignees{/user}\",\n" +
      "    \"branches_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/branches{/branch}\",\n" +
      "    \"tags_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/tags\",\n" +
      "    \"blobs_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/git/blobs{/sha}\",\n" +
      "    \"git_tags_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/git/tags{/sha}\",\n" +
      "    \"git_refs_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/git/refs{/sha}\",\n" +
      "    \"trees_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/git/trees{/sha}\",\n" +
      "    \"statuses_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/statuses/{sha}\",\n" +
      "    \"languages_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/languages\",\n" +
      "    \"stargazers_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/stargazers\",\n" +
      "    \"contributors_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/contributors\",\n" +
      "    \"subscribers_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/subscribers\",\n" +
      "    \"subscription_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/subscription\",\n" +
      "    \"commits_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/commits{/sha}\",\n" +
      "    \"git_commits_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/git/commits{/sha}\",\n" +
      "    \"comments_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/comments{/number}\",\n" +
      "    \"issue_comment_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/issues/comments{/number}\",\n" +
      "    \"contents_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/contents/{+path}\",\n" +
      "    \"compare_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/compare/{base}...{head}\",\n" +
      "    \"merges_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/merges\",\n" +
      "    \"archive_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/{archive_format}{/ref}\",\n" +
      "    \"downloads_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/downloads\",\n" +
      "    \"issues_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/issues{/number}\",\n" +
      "    \"pulls_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/pulls{/number}\",\n" +
      "    \"milestones_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/milestones{/number}\",\n" +
      "    \"notifications_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/notifications{?since,all,participating}\",\n" +
      "    \"labels_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/labels{/name}\",\n" +
      "    \"releases_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/releases{/id}\",\n" +
      "    \"deployments_url\": \"https://api.github.com/repos/defunkt/defunkt.github.com/deployments\",\n" +
      "    \"created_at\": \"2008-12-17T07:53:14Z\",\n" +
      "    \"updated_at\": \"2016-12-28T22:00:47Z\",\n" +
      "    \"pushed_at\": \"2014-08-05T00:38:47Z\",\n" +
      "    \"git_url\": \"git://github.com/defunkt/defunkt.github.com.git\",\n" +
      "    \"ssh_url\": \"git@github.com:defunkt/defunkt.github.com.git\",\n" +
      "    \"clone_url\": \"https://github.com/defunkt/defunkt.github.com.git\",\n" +
      "    \"svn_url\": \"https://github.com/defunkt/defunkt.github.com\",\n" +
      "    \"homepage\": \"http://defunkt.io\",\n" +
      "    \"size\": 3011,\n" +
      "    \"stargazers_count\": 68,\n" +
      "    \"watchers_count\": 68,\n" +
      "    \"language\": null,\n" +
      "    \"has_issues\": false,\n" +
      "    \"has_projects\": true,\n" +
      "    \"has_downloads\": false,\n" +
      "    \"has_wiki\": false,\n" +
      "    \"has_pages\": true,\n" +
      "    \"forks_count\": 51,\n" +
      "    \"mirror_url\": null,\n" +
      "    \"open_issues_count\": 3,\n" +
      "    \"forks\": 51,\n" +
      "    \"open_issues\": 3,\n" +
      "    \"watchers\": 68,\n" +
      "    \"default_branch\": \"master\"\n" +
      "  },\n" +
      "  {\n" +
      "    \"id\": 628288,\n" +
      "    \"name\": \"djangode\",\n" +
      "    \"full_name\": \"defunkt/djangode\",\n" +
      "    \"owner\": {\n" +
      "      \"login\": \"defunkt\",\n" +
      "      \"id\": 2,\n" +
      "      \"avatar_url\": \"https://avatars3.githubusercontent.com/u/2?v=3\",\n" +
      "      \"gravatar_id\": \"\",\n" +
      "      \"url\": \"https://api.github.com/users/defunkt\",\n" +
      "      \"html_url\": \"https://github.com/defunkt\",\n" +
      "      \"followers_url\": \"https://api.github.com/users/defunkt/followers\",\n" +
      "      \"following_url\": \"https://api.github.com/users/defunkt/following{/other_user}\",\n" +
      "      \"gists_url\": \"https://api.github.com/users/defunkt/gists{/gist_id}\",\n" +
      "      \"starred_url\": \"https://api.github.com/users/defunkt/starred{/owner}{/repo}\",\n" +
      "      \"subscriptions_url\": \"https://api.github.com/users/defunkt/subscriptions\",\n" +
      "      \"organizations_url\": \"https://api.github.com/users/defunkt/orgs\",\n" +
      "      \"repos_url\": \"https://api.github.com/users/defunkt/repos\",\n" +
      "      \"events_url\": \"https://api.github.com/users/defunkt/events{/privacy}\",\n" +
      "      \"received_events_url\": \"https://api.github.com/users/defunkt/received_events\",\n" +
      "      \"type\": \"User\",\n" +
      "      \"site_admin\": true\n" +
      "    },\n" +
      "    \"private\": false,\n" +
      "    \"html_url\": \"https://github.com/defunkt/djangode\",\n" +
      "    \"description\": \"Utilities functions for node.js that borrow some useful concepts from Django\",\n" +
      "    \"fork\": true,\n" +
      "    \"url\": \"https://api.github.com/repos/defunkt/djangode\",\n" +
      "    \"forks_url\": \"https://api.github.com/repos/defunkt/djangode/forks\",\n" +
      "    \"keys_url\": \"https://api.github.com/repos/defunkt/djangode/keys{/key_id}\",\n" +
      "    \"collaborators_url\": \"https://api.github.com/repos/defunkt/djangode/collaborators{/collaborator}\",\n" +
      "    \"teams_url\": \"https://api.github.com/repos/defunkt/djangode/teams\",\n" +
      "    \"hooks_url\": \"https://api.github.com/repos/defunkt/djangode/hooks\",\n" +
      "    \"issue_events_url\": \"https://api.github.com/repos/defunkt/djangode/issues/events{/number}\",\n" +
      "    \"events_url\": \"https://api.github.com/repos/defunkt/djangode/events\",\n" +
      "    \"assignees_url\": \"https://api.github.com/repos/defunkt/djangode/assignees{/user}\",\n" +
      "    \"branches_url\": \"https://api.github.com/repos/defunkt/djangode/branches{/branch}\",\n" +
      "    \"tags_url\": \"https://api.github.com/repos/defunkt/djangode/tags\",\n" +
      "    \"blobs_url\": \"https://api.github.com/repos/defunkt/djangode/git/blobs{/sha}\",\n" +
      "    \"git_tags_url\": \"https://api.github.com/repos/defunkt/djangode/git/tags{/sha}\",\n" +
      "    \"git_refs_url\": \"https://api.github.com/repos/defunkt/djangode/git/refs{/sha}\",\n" +
      "    \"trees_url\": \"https://api.github.com/repos/defunkt/djangode/git/trees{/sha}\",\n" +
      "    \"statuses_url\": \"https://api.github.com/repos/defunkt/djangode/statuses/{sha}\",\n" +
      "    \"languages_url\": \"https://api.github.com/repos/defunkt/djangode/languages\",\n" +
      "    \"stargazers_url\": \"https://api.github.com/repos/defunkt/djangode/stargazers\",\n" +
      "    \"contributors_url\": \"https://api.github.com/repos/defunkt/djangode/contributors\",\n" +
      "    \"subscribers_url\": \"https://api.github.com/repos/defunkt/djangode/subscribers\",\n" +
      "    \"subscription_url\": \"https://api.github.com/repos/defunkt/djangode/subscription\",\n" +
      "    \"commits_url\": \"https://api.github.com/repos/defunkt/djangode/commits{/sha}\",\n" +
      "    \"git_commits_url\": \"https://api.github.com/repos/defunkt/djangode/git/commits{/sha}\",\n" +
      "    \"comments_url\": \"https://api.github.com/repos/defunkt/djangode/comments{/number}\",\n" +
      "    \"issue_comment_url\": \"https://api.github.com/repos/defunkt/djangode/issues/comments{/number}\",\n" +
      "    \"contents_url\": \"https://api.github.com/repos/defunkt/djangode/contents/{+path}\",\n" +
      "    \"compare_url\": \"https://api.github.com/repos/defunkt/djangode/compare/{base}...{head}\",\n" +
      "    \"merges_url\": \"https://api.github.com/repos/defunkt/djangode/merges\",\n" +
      "    \"archive_url\": \"https://api.github.com/repos/defunkt/djangode/{archive_format}{/ref}\",\n" +
      "    \"downloads_url\": \"https://api.github.com/repos/defunkt/djangode/downloads\",\n" +
      "    \"issues_url\": \"https://api.github.com/repos/defunkt/djangode/issues{/number}\",\n" +
      "    \"pulls_url\": \"https://api.github.com/repos/defunkt/djangode/pulls{/number}\",\n" +
      "    \"milestones_url\": \"https://api.github.com/repos/defunkt/djangode/milestones{/number}\",\n" +
      "    \"notifications_url\": \"https://api.github.com/repos/defunkt/djangode/notifications{?since,all,participating}\",\n" +
      "    \"labels_url\": \"https://api.github.com/repos/defunkt/djangode/labels{/name}\",\n" +
      "    \"releases_url\": \"https://api.github.com/repos/defunkt/djangode/releases{/id}\",\n" +
      "    \"deployments_url\": \"https://api.github.com/repos/defunkt/djangode/deployments\",\n" +
      "    \"created_at\": \"2010-04-25T16:41:30Z\",\n" +
      "    \"updated_at\": \"2016-09-22T18:47:25Z\",\n" +
      "    \"pushed_at\": \"2010-04-25T16:42:56Z\",\n" +
      "    \"git_url\": \"git://github.com/defunkt/djangode.git\",\n" +
      "    \"ssh_url\": \"git@github.com:defunkt/djangode.git\",\n" +
      "    \"clone_url\": \"https://github.com/defunkt/djangode.git\",\n" +
      "    \"svn_url\": \"https://github.com/defunkt/djangode\",\n" +
      "    \"homepage\": \"\",\n" +
      "    \"size\": 191,\n" +
      "    \"stargazers_count\": 5,\n" +
      "    \"watchers_count\": 5,\n" +
      "    \"language\": \"JavaScript\",\n" +
      "    \"has_issues\": false,\n" +
      "    \"has_projects\": true,\n" +
      "    \"has_downloads\": true,\n" +
      "    \"has_wiki\": true,\n" +
      "    \"has_pages\": false,\n" +
      "    \"forks_count\": 3,\n" +
      "    \"mirror_url\": null,\n" +
      "    \"open_issues_count\": 0,\n" +
      "    \"forks\": 3,\n" +
      "    \"open_issues\": 0,\n" +
      "    \"watchers\": 5,\n" +
      "    \"default_branch\": \"master\"\n" +
      "  },\n" +
      "  {\n" +
      "    \"id\": 2448060,\n" +
      "    \"name\": \"dodgeball.github.com\",\n" +
      "    \"full_name\": \"defunkt/dodgeball.github.com\",\n" +
      "    \"owner\": {\n" +
      "      \"login\": \"defunkt\",\n" +
      "      \"id\": 2,\n" +
      "      \"avatar_url\": \"https://avatars3.githubusercontent.com/u/2?v=3\",\n" +
      "      \"gravatar_id\": \"\",\n" +
      "      \"url\": \"https://api.github.com/users/defunkt\",\n" +
      "      \"html_url\": \"https://github.com/defunkt\",\n" +
      "      \"followers_url\": \"https://api.github.com/users/defunkt/followers\",\n" +
      "      \"following_url\": \"https://api.github.com/users/defunkt/following{/other_user}\",\n" +
      "      \"gists_url\": \"https://api.github.com/users/defunkt/gists{/gist_id}\",\n" +
      "      \"starred_url\": \"https://api.github.com/users/defunkt/starred{/owner}{/repo}\",\n" +
      "      \"subscriptions_url\": \"https://api.github.com/users/defunkt/subscriptions\",\n" +
      "      \"organizations_url\": \"https://api.github.com/users/defunkt/orgs\",\n" +
      "      \"repos_url\": \"https://api.github.com/users/defunkt/repos\",\n" +
      "      \"events_url\": \"https://api.github.com/users/defunkt/events{/privacy}\",\n" +
      "      \"received_events_url\": \"https://api.github.com/users/defunkt/received_events\",\n" +
      "      \"type\": \"User\",\n" +
      "      \"site_admin\": true\n" +
      "    },\n" +
      "    \"private\": false,\n" +
      "    \"html_url\": \"https://github.com/defunkt/dodgeball.github.com\",\n" +
      "    \"description\": \"yes\",\n" +
      "    \"fork\": false,\n" +
      "    \"url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com\",\n" +
      "    \"forks_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/forks\",\n" +
      "    \"keys_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/keys{/key_id}\",\n" +
      "    \"collaborators_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/collaborators{/collaborator}\",\n" +
      "    \"teams_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/teams\",\n" +
      "    \"hooks_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/hooks\",\n" +
      "    \"issue_events_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/issues/events{/number}\",\n" +
      "    \"events_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/events\",\n" +
      "    \"assignees_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/assignees{/user}\",\n" +
      "    \"branches_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/branches{/branch}\",\n" +
      "    \"tags_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/tags\",\n" +
      "    \"blobs_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/git/blobs{/sha}\",\n" +
      "    \"git_tags_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/git/tags{/sha}\",\n" +
      "    \"git_refs_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/git/refs{/sha}\",\n" +
      "    \"trees_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/git/trees{/sha}\",\n" +
      "    \"statuses_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/statuses/{sha}\",\n" +
      "    \"languages_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/languages\",\n" +
      "    \"stargazers_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/stargazers\",\n" +
      "    \"contributors_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/contributors\",\n" +
      "    \"subscribers_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/subscribers\",\n" +
      "    \"subscription_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/subscription\",\n" +
      "    \"commits_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/commits{/sha}\",\n" +
      "    \"git_commits_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/git/commits{/sha}\",\n" +
      "    \"comments_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/comments{/number}\",\n" +
      "    \"issue_comment_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/issues/comments{/number}\",\n" +
      "    \"contents_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/contents/{+path}\",\n" +
      "    \"compare_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/compare/{base}...{head}\",\n" +
      "    \"merges_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/merges\",\n" +
      "    \"archive_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/{archive_format}{/ref}\",\n" +
      "    \"downloads_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/downloads\",\n" +
      "    \"issues_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/issues{/number}\",\n" +
      "    \"pulls_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/pulls{/number}\",\n" +
      "    \"milestones_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/milestones{/number}\",\n" +
      "    \"notifications_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/notifications{?since,all,participating}\",\n" +
      "    \"labels_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/labels{/name}\",\n" +
      "    \"releases_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/releases{/id}\",\n" +
      "    \"deployments_url\": \"https://api.github.com/repos/defunkt/dodgeball.github.com/deployments\",\n" +
      "    \"created_at\": \"2011-09-24T03:01:09Z\",\n" +
      "    \"updated_at\": \"2016-09-22T18:48:29Z\",\n" +
      "    \"pushed_at\": \"2011-09-24T03:01:22Z\",\n" +
      "    \"git_url\": \"git://github.com/defunkt/dodgeball.github.com.git\",\n" +
      "    \"ssh_url\": \"git@github.com:defunkt/dodgeball.github.com.git\",\n" +
      "    \"clone_url\": \"https://github.com/defunkt/dodgeball.github.com.git\",\n" +
      "    \"svn_url\": \"https://github.com/defunkt/dodgeball.github.com\",\n" +
      "    \"homepage\": \"\",\n" +
      "    \"size\": 534,\n" +
      "    \"stargazers_count\": 6,\n" +
      "    \"watchers_count\": 6,\n" +
      "    \"language\": \"Ruby\",\n" +
      "    \"has_issues\": false,\n" +
      "    \"has_projects\": true,\n" +
      "    \"has_downloads\": true,\n" +
      "    \"has_wiki\": true,\n" +
      "    \"has_pages\": false,\n" +
      "    \"forks_count\": 5,\n" +
      "    \"mirror_url\": null,\n" +
      "    \"open_issues_count\": 0,\n" +
      "    \"forks\": 5,\n" +
      "    \"open_issues\": 0,\n" +
      "    \"watchers\": 6,\n" +
      "    \"default_branch\": \"master\"\n" +
      "  },\n" +
      "  {\n" +
      "    \"id\": 5171653,\n" +
      "    \"name\": \"dotenv\",\n" +
      "    \"full_name\": \"defunkt/dotenv\",\n" +
      "    \"owner\": {\n" +
      "      \"login\": \"defunkt\",\n" +
      "      \"id\": 2,\n" +
      "      \"avatar_url\": \"https://avatars3.githubusercontent.com/u/2?v=3\",\n" +
      "      \"gravatar_id\": \"\",\n" +
      "      \"url\": \"https://api.github.com/users/defunkt\",\n" +
      "      \"html_url\": \"https://github.com/defunkt\",\n" +
      "      \"followers_url\": \"https://api.github.com/users/defunkt/followers\",\n" +
      "      \"following_url\": \"https://api.github.com/users/defunkt/following{/other_user}\",\n" +
      "      \"gists_url\": \"https://api.github.com/users/defunkt/gists{/gist_id}\",\n" +
      "      \"starred_url\": \"https://api.github.com/users/defunkt/starred{/owner}{/repo}\",\n" +
      "      \"subscriptions_url\": \"https://api.github.com/users/defunkt/subscriptions\",\n" +
      "      \"organizations_url\": \"https://api.github.com/users/defunkt/orgs\",\n" +
      "      \"repos_url\": \"https://api.github.com/users/defunkt/repos\",\n" +
      "      \"events_url\": \"https://api.github.com/users/defunkt/events{/privacy}\",\n" +
      "      \"received_events_url\": \"https://api.github.com/users/defunkt/received_events\",\n" +
      "      \"type\": \"User\",\n" +
      "      \"site_admin\": true\n" +
      "    },\n" +
      "    \"private\": false,\n" +
      "    \"html_url\": \"https://github.com/defunkt/dotenv\",\n" +
      "    \"description\": \"Loads environment variables from `.env`. \",\n" +
      "    \"fork\": true,\n" +
      "    \"url\": \"https://api.github.com/repos/defunkt/dotenv\",\n" +
      "    \"forks_url\": \"https://api.github.com/repos/defunkt/dotenv/forks\",\n" +
      "    \"keys_url\": \"https://api.github.com/repos/defunkt/dotenv/keys{/key_id}\",\n" +
      "    \"collaborators_url\": \"https://api.github.com/repos/defunkt/dotenv/collaborators{/collaborator}\",\n" +
      "    \"teams_url\": \"https://api.github.com/repos/defunkt/dotenv/teams\",\n" +
      "    \"hooks_url\": \"https://api.github.com/repos/defunkt/dotenv/hooks\",\n" +
      "    \"issue_events_url\": \"https://api.github.com/repos/defunkt/dotenv/issues/events{/number}\",\n" +
      "    \"events_url\": \"https://api.github.com/repos/defunkt/dotenv/events\",\n" +
      "    \"assignees_url\": \"https://api.github.com/repos/defunkt/dotenv/assignees{/user}\",\n" +
      "    \"branches_url\": \"https://api.github.com/repos/defunkt/dotenv/branches{/branch}\",\n" +
      "    \"tags_url\": \"https://api.github.com/repos/defunkt/dotenv/tags\",\n" +
      "    \"blobs_url\": \"https://api.github.com/repos/defunkt/dotenv/git/blobs{/sha}\",\n" +
      "    \"git_tags_url\": \"https://api.github.com/repos/defunkt/dotenv/git/tags{/sha}\",\n" +
      "    \"git_refs_url\": \"https://api.github.com/repos/defunkt/dotenv/git/refs{/sha}\",\n" +
      "    \"trees_url\": \"https://api.github.com/repos/defunkt/dotenv/git/trees{/sha}\",\n" +
      "    \"statuses_url\": \"https://api.github.com/repos/defunkt/dotenv/statuses/{sha}\",\n" +
      "    \"languages_url\": \"https://api.github.com/repos/defunkt/dotenv/languages\",\n" +
      "    \"stargazers_url\": \"https://api.github.com/repos/defunkt/dotenv/stargazers\",\n" +
      "    \"contributors_url\": \"https://api.github.com/repos/defunkt/dotenv/contributors\",\n" +
      "    \"subscribers_url\": \"https://api.github.com/repos/defunkt/dotenv/subscribers\",\n" +
      "    \"subscription_url\": \"https://api.github.com/repos/defunkt/dotenv/subscription\",\n" +
      "    \"commits_url\": \"https://api.github.com/repos/defunkt/dotenv/commits{/sha}\",\n" +
      "    \"git_commits_url\": \"https://api.github.com/repos/defunkt/dotenv/git/commits{/sha}\",\n" +
      "    \"comments_url\": \"https://api.github.com/repos/defunkt/dotenv/comments{/number}\",\n" +
      "    \"issue_comment_url\": \"https://api.github.com/repos/defunkt/dotenv/issues/comments{/number}\",\n" +
      "    \"contents_url\": \"https://api.github.com/repos/defunkt/dotenv/contents/{+path}\",\n" +
      "    \"compare_url\": \"https://api.github.com/repos/defunkt/dotenv/compare/{base}...{head}\",\n" +
      "    \"merges_url\": \"https://api.github.com/repos/defunkt/dotenv/merges\",\n" +
      "    \"archive_url\": \"https://api.github.com/repos/defunkt/dotenv/{archive_format}{/ref}\",\n" +
      "    \"downloads_url\": \"https://api.github.com/repos/defunkt/dotenv/downloads\",\n" +
      "    \"issues_url\": \"https://api.github.com/repos/defunkt/dotenv/issues{/number}\",\n" +
      "    \"pulls_url\": \"https://api.github.com/repos/defunkt/dotenv/pulls{/number}\",\n" +
      "    \"milestones_url\": \"https://api.github.com/repos/defunkt/dotenv/milestones{/number}\",\n" +
      "    \"notifications_url\": \"https://api.github.com/repos/defunkt/dotenv/notifications{?since,all,participating}\",\n" +
      "    \"labels_url\": \"https://api.github.com/repos/defunkt/dotenv/labels{/name}\",\n" +
      "    \"releases_url\": \"https://api.github.com/repos/defunkt/dotenv/releases{/id}\",\n" +
      "    \"deployments_url\": \"https://api.github.com/repos/defunkt/dotenv/deployments\",\n" +
      "    \"created_at\": \"2012-07-24T21:43:19Z\",\n" +
      "    \"updated_at\": \"2016-10-23T19:22:03Z\",\n" +
      "    \"pushed_at\": \"2012-07-24T04:30:34Z\",\n" +
      "    \"git_url\": \"git://github.com/defunkt/dotenv.git\",\n" +
      "    \"ssh_url\": \"git@github.com:defunkt/dotenv.git\",\n" +
      "    \"clone_url\": \"https://github.com/defunkt/dotenv.git\",\n" +
      "    \"svn_url\": \"https://github.com/defunkt/dotenv\",\n" +
      "    \"homepage\": null,\n" +
      "    \"size\": 75,\n" +
      "    \"stargazers_count\": 4,\n" +
      "    \"watchers_count\": 4,\n" +
      "    \"language\": \"Ruby\",\n" +
      "    \"has_issues\": false,\n" +
      "    \"has_projects\": true,\n" +
      "    \"has_downloads\": true,\n" +
      "    \"has_wiki\": true,\n" +
      "    \"has_pages\": false,\n" +
      "    \"forks_count\": 3,\n" +
      "    \"mirror_url\": null,\n" +
      "    \"open_issues_count\": 0,\n" +
      "    \"forks\": 3,\n" +
      "    \"open_issues\": 0,\n" +
      "    \"watchers\": 4,\n" +
      "    \"default_branch\": \"master\"\n" +
      "  },\n" +
      "  {\n" +
      "    \"id\": 1336779,\n" +
      "    \"name\": \"dotjs\",\n" +
      "    \"full_name\": \"defunkt/dotjs\",\n" +
      "    \"owner\": {\n" +
      "      \"login\": \"defunkt\",\n" +
      "      \"id\": 2,\n" +
      "      \"avatar_url\": \"https://avatars3.githubusercontent.com/u/2?v=3\",\n" +
      "      \"gravatar_id\": \"\",\n" +
      "      \"url\": \"https://api.github.com/users/defunkt\",\n" +
      "      \"html_url\": \"https://github.com/defunkt\",\n" +
      "      \"followers_url\": \"https://api.github.com/users/defunkt/followers\",\n" +
      "      \"following_url\": \"https://api.github.com/users/defunkt/following{/other_user}\",\n" +
      "      \"gists_url\": \"https://api.github.com/users/defunkt/gists{/gist_id}\",\n" +
      "      \"starred_url\": \"https://api.github.com/users/defunkt/starred{/owner}{/repo}\",\n" +
      "      \"subscriptions_url\": \"https://api.github.com/users/defunkt/subscriptions\",\n" +
      "      \"organizations_url\": \"https://api.github.com/users/defunkt/orgs\",\n" +
      "      \"repos_url\": \"https://api.github.com/users/defunkt/repos\",\n" +
      "      \"events_url\": \"https://api.github.com/users/defunkt/events{/privacy}\",\n" +
      "      \"received_events_url\": \"https://api.github.com/users/defunkt/received_events\",\n" +
      "      \"type\": \"User\",\n" +
      "      \"site_admin\": true\n" +
      "    },\n" +
      "    \"private\": false,\n" +
      "    \"html_url\": \"https://github.com/defunkt/dotjs\",\n" +
      "    \"description\": \"~/.js — No longer maintained, sorry.\",\n" +
      "    \"fork\": false,\n" +
      "    \"url\": \"https://api.github.com/repos/defunkt/dotjs\",\n" +
      "    \"forks_url\": \"https://api.github.com/repos/defunkt/dotjs/forks\",\n" +
      "    \"keys_url\": \"https://api.github.com/repos/defunkt/dotjs/keys{/key_id}\",\n" +
      "    \"collaborators_url\": \"https://api.github.com/repos/defunkt/dotjs/collaborators{/collaborator}\",\n" +
      "    \"teams_url\": \"https://api.github.com/repos/defunkt/dotjs/teams\",\n" +
      "    \"hooks_url\": \"https://api.github.com/repos/defunkt/dotjs/hooks\",\n" +
      "    \"issue_events_url\": \"https://api.github.com/repos/defunkt/dotjs/issues/events{/number}\",\n" +
      "    \"events_url\": \"https://api.github.com/repos/defunkt/dotjs/events\",\n" +
      "    \"assignees_url\": \"https://api.github.com/repos/defunkt/dotjs/assignees{/user}\",\n" +
      "    \"branches_url\": \"https://api.github.com/repos/defunkt/dotjs/branches{/branch}\",\n" +
      "    \"tags_url\": \"https://api.github.com/repos/defunkt/dotjs/tags\",\n" +
      "    \"blobs_url\": \"https://api.github.com/repos/defunkt/dotjs/git/blobs{/sha}\",\n" +
      "    \"git_tags_url\": \"https://api.github.com/repos/defunkt/dotjs/git/tags{/sha}\",\n" +
      "    \"git_refs_url\": \"https://api.github.com/repos/defunkt/dotjs/git/refs{/sha}\",\n" +
      "    \"trees_url\": \"https://api.github.com/repos/defunkt/dotjs/git/trees{/sha}\",\n" +
      "    \"statuses_url\": \"https://api.github.com/repos/defunkt/dotjs/statuses/{sha}\",\n" +
      "    \"languages_url\": \"https://api.github.com/repos/defunkt/dotjs/languages\",\n" +
      "    \"stargazers_url\": \"https://api.github.com/repos/defunkt/dotjs/stargazers\",\n" +
      "    \"contributors_url\": \"https://api.github.com/repos/defunkt/dotjs/contributors\",\n" +
      "    \"subscribers_url\": \"https://api.github.com/repos/defunkt/dotjs/subscribers\",\n" +
      "    \"subscription_url\": \"https://api.github.com/repos/defunkt/dotjs/subscription\",\n" +
      "    \"commits_url\": \"https://api.github.com/repos/defunkt/dotjs/commits{/sha}\",\n" +
      "    \"git_commits_url\": \"https://api.github.com/repos/defunkt/dotjs/git/commits{/sha}\",\n" +
      "    \"comments_url\": \"https://api.github.com/repos/defunkt/dotjs/comments{/number}\",\n" +
      "    \"issue_comment_url\": \"https://api.github.com/repos/defunkt/dotjs/issues/comments{/number}\",\n" +
      "    \"contents_url\": \"https://api.github.com/repos/defunkt/dotjs/contents/{+path}\",\n" +
      "    \"compare_url\": \"https://api.github.com/repos/defunkt/dotjs/compare/{base}...{head}\",\n" +
      "    \"merges_url\": \"https://api.github.com/repos/defunkt/dotjs/merges\",\n" +
      "    \"archive_url\": \"https://api.github.com/repos/defunkt/dotjs/{archive_format}{/ref}\",\n" +
      "    \"downloads_url\": \"https://api.github.com/repos/defunkt/dotjs/downloads\",\n" +
      "    \"issues_url\": \"https://api.github.com/repos/defunkt/dotjs/issues{/number}\",\n" +
      "    \"pulls_url\": \"https://api.github.com/repos/defunkt/dotjs/pulls{/number}\",\n" +
      "    \"milestones_url\": \"https://api.github.com/repos/defunkt/dotjs/milestones{/number}\",\n" +
      "    \"notifications_url\": \"https://api.github.com/repos/defunkt/dotjs/notifications{?since,all,participating}\",\n" +
      "    \"labels_url\": \"https://api.github.com/repos/defunkt/dotjs/labels{/name}\",\n" +
      "    \"releases_url\": \"https://api.github.com/repos/defunkt/dotjs/releases{/id}\",\n" +
      "    \"deployments_url\": \"https://api.github.com/repos/defunkt/dotjs/deployments\",\n" +
      "    \"created_at\": \"2011-02-07T07:01:33Z\",\n" +
      "    \"updated_at\": \"2017-05-07T17:33:12Z\",\n" +
      "    \"pushed_at\": \"2016-02-20T23:21:29Z\",\n" +
      "    \"git_url\": \"git://github.com/defunkt/dotjs.git\",\n" +
      "    \"ssh_url\": \"git@github.com:defunkt/dotjs.git\",\n" +
      "    \"clone_url\": \"https://github.com/defunkt/dotjs.git\",\n" +
      "    \"svn_url\": \"https://github.com/defunkt/dotjs\",\n" +
      "    \"homepage\": \"http://bit.ly/dotjs\",\n" +
      "    \"size\": 1316,\n" +
      "    \"stargazers_count\": 3158,\n" +
      "    \"watchers_count\": 3158,\n" +
      "    \"language\": \"Ruby\",\n" +
      "    \"has_issues\": true,\n" +
      "    \"has_projects\": true,\n" +
      "    \"has_downloads\": true,\n" +
      "    \"has_wiki\": false,\n" +
      "    \"has_pages\": true,\n" +
      "    \"forks_count\": 370,\n" +
      "    \"mirror_url\": null,\n" +
      "    \"open_issues_count\": 21,\n" +
      "    \"forks\": 370,\n" +
      "    \"open_issues\": 21,\n" +
      "    \"watchers\": 3158,\n" +
      "    \"default_branch\": \"master\"\n" +
      "  },\n" +
      "  {\n" +
      "    \"id\": 69384,\n" +
      "    \"name\": \"electron-wordwrap\",\n" +
      "    \"full_name\": \"defunkt/electron-wordwrap\",\n" +
      "    \"owner\": {\n" +
      "      \"login\": \"defunkt\",\n" +
      "      \"id\": 2,\n" +
      "      \"avatar_url\": \"https://avatars3.githubusercontent.com/u/2?v=3\",\n" +
      "      \"gravatar_id\": \"\",\n" +
      "      \"url\": \"https://api.github.com/users/defunkt\",\n" +
      "      \"html_url\": \"https://github.com/defunkt\",\n" +
      "      \"followers_url\": \"https://api.github.com/users/defunkt/followers\",\n" +
      "      \"following_url\": \"https://api.github.com/users/defunkt/following{/other_user}\",\n" +
      "      \"gists_url\": \"https://api.github.com/users/defunkt/gists{/gist_id}\",\n" +
      "      \"starred_url\": \"https://api.github.com/users/defunkt/starred{/owner}{/repo}\",\n" +
      "      \"subscriptions_url\": \"https://api.github.com/users/defunkt/subscriptions\",\n" +
      "      \"organizations_url\": \"https://api.github.com/users/defunkt/orgs\",\n" +
      "      \"repos_url\": \"https://api.github.com/users/defunkt/repos\",\n" +
      "      \"events_url\": \"https://api.github.com/users/defunkt/events{/privacy}\",\n" +
      "      \"received_events_url\": \"https://api.github.com/users/defunkt/received_events\",\n" +
      "      \"type\": \"User\",\n" +
      "      \"site_admin\": true\n" +
      "    },\n" +
      "    \"private\": false,\n" +
      "    \"html_url\": \"https://github.com/defunkt/electron-wordwrap\",\n" +
      "    \"description\": null,\n" +
      "    \"fork\": false,\n" +
      "    \"url\": \"https://api.github.com/repos/defunkt/electron-wordwrap\",\n" +
      "    \"forks_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/forks\",\n" +
      "    \"keys_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/keys{/key_id}\",\n" +
      "    \"collaborators_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/collaborators{/collaborator}\",\n" +
      "    \"teams_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/teams\",\n" +
      "    \"hooks_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/hooks\",\n" +
      "    \"issue_events_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/issues/events{/number}\",\n" +
      "    \"events_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/events\",\n" +
      "    \"assignees_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/assignees{/user}\",\n" +
      "    \"branches_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/branches{/branch}\",\n" +
      "    \"tags_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/tags\",\n" +
      "    \"blobs_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/git/blobs{/sha}\",\n" +
      "    \"git_tags_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/git/tags{/sha}\",\n" +
      "    \"git_refs_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/git/refs{/sha}\",\n" +
      "    \"trees_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/git/trees{/sha}\",\n" +
      "    \"statuses_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/statuses/{sha}\",\n" +
      "    \"languages_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/languages\",\n" +
      "    \"stargazers_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/stargazers\",\n" +
      "    \"contributors_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/contributors\",\n" +
      "    \"subscribers_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/subscribers\",\n" +
      "    \"subscription_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/subscription\",\n" +
      "    \"commits_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/commits{/sha}\",\n" +
      "    \"git_commits_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/git/commits{/sha}\",\n" +
      "    \"comments_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/comments{/number}\",\n" +
      "    \"issue_comment_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/issues/comments{/number}\",\n" +
      "    \"contents_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/contents/{+path}\",\n" +
      "    \"compare_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/compare/{base}...{head}\",\n" +
      "    \"merges_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/merges\",\n" +
      "    \"archive_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/{archive_format}{/ref}\",\n" +
      "    \"downloads_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/downloads\",\n" +
      "    \"issues_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/issues{/number}\",\n" +
      "    \"pulls_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/pulls{/number}\",\n" +
      "    \"milestones_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/milestones{/number}\",\n" +
      "    \"notifications_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/notifications{?since,all,participating}\",\n" +
      "    \"labels_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/labels{/name}\",\n" +
      "    \"releases_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/releases{/id}\",\n" +
      "    \"deployments_url\": \"https://api.github.com/repos/defunkt/electron-wordwrap/deployments\",\n" +
      "    \"created_at\": \"2008-10-29T20:03:17Z\",\n" +
      "    \"updated_at\": \"2016-09-22T18:46:22Z\",\n" +
      "    \"pushed_at\": \"2008-10-29T20:28:21Z\",\n" +
      "    \"git_url\": \"git://github.com/defunkt/electron-wordwrap.git\",\n" +
      "    \"ssh_url\": \"git@github.com:defunkt/electron-wordwrap.git\",\n" +
      "    \"clone_url\": \"https://github.com/defunkt/electron-wordwrap.git\",\n" +
      "    \"svn_url\": \"https://github.com/defunkt/electron-wordwrap\",\n" +
      "    \"homepage\": \"\",\n" +
      "    \"size\": 76,\n" +
      "    \"stargazers_count\": 4,\n" +
      "    \"watchers_count\": 4,\n" +
      "    \"language\": null,\n" +
      "    \"has_issues\": true,\n" +
      "    \"has_projects\": true,\n" +
      "    \"has_downloads\": true,\n" +
      "    \"has_wiki\": true,\n" +
      "    \"has_pages\": false,\n" +
      "    \"forks_count\": 3,\n" +
      "    \"mirror_url\": null,\n" +
      "    \"open_issues_count\": 0,\n" +
      "    \"forks\": 3,\n" +
      "    \"open_issues\": 0,\n" +
      "    \"watchers\": 4,\n" +
      "    \"default_branch\": \"master\"\n" +
      "  },\n" +
      "  {\n" +
      "    \"id\": 3596,\n" +
      "    \"name\": \"fixture_scenarios_builder\",\n" +
      "    \"full_name\": \"defunkt/fixture_scenarios_builder\",\n" +
      "    \"owner\": {\n" +
      "      \"login\": \"defunkt\",\n" +
      "      \"id\": 2,\n" +
      "      \"avatar_url\": \"https://avatars3.githubusercontent.com/u/2?v=3\",\n" +
      "      \"gravatar_id\": \"\",\n" +
      "      \"url\": \"https://api.github.com/users/defunkt\",\n" +
      "      \"html_url\": \"https://github.com/defunkt\",\n" +
      "      \"followers_url\": \"https://api.github.com/users/defunkt/followers\",\n" +
      "      \"following_url\": \"https://api.github.com/users/defunkt/following{/other_user}\",\n" +
      "      \"gists_url\": \"https://api.github.com/users/defunkt/gists{/gist_id}\",\n" +
      "      \"starred_url\": \"https://api.github.com/users/defunkt/starred{/owner}{/repo}\",\n" +
      "      \"subscriptions_url\": \"https://api.github.com/users/defunkt/subscriptions\",\n" +
      "      \"organizations_url\": \"https://api.github.com/users/defunkt/orgs\",\n" +
      "      \"repos_url\": \"https://api.github.com/users/defunkt/repos\",\n" +
      "      \"events_url\": \"https://api.github.com/users/defunkt/events{/privacy}\",\n" +
      "      \"received_events_url\": \"https://api.github.com/users/defunkt/received_events\",\n" +
      "      \"type\": \"User\",\n" +
      "      \"site_admin\": true\n" +
      "    },\n" +
      "    \"private\": false,\n" +
      "    \"html_url\": \"https://github.com/defunkt/fixture_scenarios_builder\",\n" +
      "    \"description\": \"Build your fixtures in Ruby.\",\n" +
      "    \"fork\": false,\n" +
      "    \"url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder\",\n" +
      "    \"forks_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/forks\",\n" +
      "    \"keys_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/keys{/key_id}\",\n" +
      "    \"collaborators_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/collaborators{/collaborator}\",\n" +
      "    \"teams_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/teams\",\n" +
      "    \"hooks_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/hooks\",\n" +
      "    \"issue_events_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/issues/events{/number}\",\n" +
      "    \"events_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/events\",\n" +
      "    \"assignees_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/assignees{/user}\",\n" +
      "    \"branches_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/branches{/branch}\",\n" +
      "    \"tags_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/tags\",\n" +
      "    \"blobs_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/git/blobs{/sha}\",\n" +
      "    \"git_tags_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/git/tags{/sha}\",\n" +
      "    \"git_refs_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/git/refs{/sha}\",\n" +
      "    \"trees_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/git/trees{/sha}\",\n" +
      "    \"statuses_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/statuses/{sha}\",\n" +
      "    \"languages_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/languages\",\n" +
      "    \"stargazers_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/stargazers\",\n" +
      "    \"contributors_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/contributors\",\n" +
      "    \"subscribers_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/subscribers\",\n" +
      "    \"subscription_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/subscription\",\n" +
      "    \"commits_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/commits{/sha}\",\n" +
      "    \"git_commits_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/git/commits{/sha}\",\n" +
      "    \"comments_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/comments{/number}\",\n" +
      "    \"issue_comment_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/issues/comments{/number}\",\n" +
      "    \"contents_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/contents/{+path}\",\n" +
      "    \"compare_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/compare/{base}...{head}\",\n" +
      "    \"merges_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/merges\",\n" +
      "    \"archive_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/{archive_format}{/ref}\",\n" +
      "    \"downloads_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/downloads\",\n" +
      "    \"issues_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/issues{/number}\",\n" +
      "    \"pulls_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/pulls{/number}\",\n" +
      "    \"milestones_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/milestones{/number}\",\n" +
      "    \"notifications_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/notifications{?since,all,participating}\",\n" +
      "    \"labels_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/labels{/name}\",\n" +
      "    \"releases_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/releases{/id}\",\n" +
      "    \"deployments_url\": \"https://api.github.com/repos/defunkt/fixture_scenarios_builder/deployments\",\n" +
      "    \"created_at\": \"2008-03-12T06:24:02Z\",\n" +
      "    \"updated_at\": \"2017-04-01T14:45:15Z\",\n" +
      "    \"pushed_at\": \"2008-11-12T22:58:39Z\",\n" +
      "    \"git_url\": \"git://github.com/defunkt/fixture_scenarios_builder.git\",\n" +
      "    \"ssh_url\": \"git@github.com:defunkt/fixture_scenarios_builder.git\",\n" +
      "    \"clone_url\": \"https://github.com/defunkt/fixture_scenarios_builder.git\",\n" +
      "    \"svn_url\": \"https://github.com/defunkt/fixture_scenarios_builder\",\n" +
      "    \"homepage\": \"http://errtheblog.com/posts/61-fixin-fixtures\",\n" +
      "    \"size\": 96,\n" +
      "    \"stargazers_count\": 12,\n" +
      "    \"watchers_count\": 12,\n" +
      "    \"language\": \"Ruby\",\n" +
      "    \"has_issues\": true,\n" +
      "    \"has_projects\": true,\n" +
      "    \"has_downloads\": true,\n" +
      "    \"has_wiki\": true,\n" +
      "    \"has_pages\": false,\n" +
      "    \"forks_count\": 5,\n" +
      "    \"mirror_url\": null,\n" +
      "    \"open_issues_count\": 0,\n" +
      "    \"forks\": 5,\n" +
      "    \"open_issues\": 0,\n" +
      "    \"watchers\": 12,\n" +
      "    \"default_branch\": \"master\"\n" +
      "  }\n" +
      "]";

    static final String USER_DEFUNKT = "{\n" +
      "  \"login\": \"defunkt\",\n" +
      "  \"id\": 2,\n" +
      "  \"avatar_url\": \"https://avatars3.githubusercontent.com/u/2?v=3\",\n" +
      "  \"gravatar_id\": \"\",\n" +
      "  \"url\": \"https://api.github.com/users/defunkt\",\n" +
      "  \"html_url\": \"https://github.com/defunkt\",\n" +
      "  \"followers_url\": \"https://api.github.com/users/defunkt/followers\",\n" +
      "  \"following_url\": \"https://api.github.com/users/defunkt/following{/other_user}\",\n" +
      "  \"gists_url\": \"https://api.github.com/users/defunkt/gists{/gist_id}\",\n" +
      "  \"starred_url\": \"https://api.github.com/users/defunkt/starred{/owner}{/repo}\",\n" +
      "  \"subscriptions_url\": \"https://api.github.com/users/defunkt/subscriptions\",\n" +
      "  \"organizations_url\": \"https://api.github.com/users/defunkt/orgs\",\n" +
      "  \"repos_url\": \"https://api.github.com/users/defunkt/repos\",\n" +
      "  \"events_url\": \"https://api.github.com/users/defunkt/events{/privacy}\",\n" +
      "  \"received_events_url\": \"https://api.github.com/users/defunkt/received_events\",\n" +
      "  \"type\": \"User\",\n" +
      "  \"site_admin\": true,\n" +
      "  \"name\": \"Chris Wanstrath\",\n" +
      "  \"company\": \"@github \",\n" +
      "  \"blog\": \"http://chriswanstrath.com/\",\n" +
      "  \"location\": \"San Francisco\",\n" +
      "  \"email\": null,\n" +
      "  \"hireable\": true,\n" +
      "  \"bio\": \"\uD83C\uDF54 \",\n" +
      "  \"public_repos\": 107,\n" +
      "  \"public_gists\": 273,\n" +
      "  \"followers\": 16181,\n" +
      "  \"following\": 208,\n" +
      "  \"created_at\": \"2007-10-20T05:24:19Z\",\n" +
      "  \"updated_at\": \"2017-03-30T21:03:38Z\"\n" +
      "}";

    static final String USER_MOJOMBO = "{\n" +
      "  \"login\": \"mojombo\",\n" +
      "  \"id\": 1,\n" +
      "  \"avatar_url\": \"https://avatars3.githubusercontent.com/u/1?v=3\",\n" +
      "  \"gravatar_id\": \"\",\n" +
      "  \"url\": \"https://api.github.com/users/mojombo\",\n" +
      "  \"html_url\": \"https://github.com/mojombo\",\n" +
      "  \"followers_url\": \"https://api.github.com/users/mojombo/followers\",\n" +
      "  \"following_url\": \"https://api.github.com/users/mojombo/following{/other_user}\",\n" +
      "  \"gists_url\": \"https://api.github.com/users/mojombo/gists{/gist_id}\",\n" +
      "  \"starred_url\": \"https://api.github.com/users/mojombo/starred{/owner}{/repo}\",\n" +
      "  \"subscriptions_url\": \"https://api.github.com/users/mojombo/subscriptions\",\n" +
      "  \"organizations_url\": \"https://api.github.com/users/mojombo/orgs\",\n" +
      "  \"repos_url\": \"https://api.github.com/users/mojombo/repos\",\n" +
      "  \"events_url\": \"https://api.github.com/users/mojombo/events{/privacy}\",\n" +
      "  \"received_events_url\": \"https://api.github.com/users/mojombo/received_events\",\n" +
      "  \"type\": \"User\",\n" +
      "  \"site_admin\": false,\n" +
      "  \"name\": \"Tom Preston-Werner\",\n" +
      "  \"company\": null,\n" +
      "  \"blog\": \"http://tom.preston-werner.com\",\n" +
      "  \"location\": \"San Francisco\",\n" +
      "  \"email\": null,\n" +
      "  \"hireable\": null,\n" +
      "  \"bio\": null,\n" +
      "  \"public_repos\": 61,\n" +
      "  \"public_gists\": 62,\n" +
      "  \"followers\": 20169,\n" +
      "  \"following\": 11,\n" +
      "  \"created_at\": \"2007-10-20T05:24:19Z\",\n" +
      "  \"updated_at\": \"2017-04-29T10:00:07Z\"\n" +
      "}";

    static final String FIRST_USERS = "[\n" +
      "  {\n" +
      "    \"login\": \"mojombo\",\n" +
      "    \"id\": 1,\n" +
      "    \"avatar_url\": \"https://avatars3.githubusercontent.com/u/1?v=3\",\n" +
      "    \"gravatar_id\": \"\",\n" +
      "    \"url\": \"https://api.github.com/users/mojombo\",\n" +
      "    \"html_url\": \"https://github.com/mojombo\",\n" +
      "    \"followers_url\": \"https://api.github.com/users/mojombo/followers\",\n" +
      "    \"following_url\": \"https://api.github.com/users/mojombo/following{/other_user}\",\n" +
      "    \"gists_url\": \"https://api.github.com/users/mojombo/gists{/gist_id}\",\n" +
      "    \"starred_url\": \"https://api.github.com/users/mojombo/starred{/owner}{/repo}\",\n" +
      "    \"subscriptions_url\": \"https://api.github.com/users/mojombo/subscriptions\",\n" +
      "    \"organizations_url\": \"https://api.github.com/users/mojombo/orgs\",\n" +
      "    \"repos_url\": \"https://api.github.com/users/mojombo/repos\",\n" +
      "    \"events_url\": \"https://api.github.com/users/mojombo/events{/privacy}\",\n" +
      "    \"received_events_url\": \"https://api.github.com/users/mojombo/received_events\",\n" +
      "    \"type\": \"User\",\n" +
      "    \"site_admin\": false\n" +
      "  },\n" +
      "  {\n" +
      "    \"login\": \"defunkt\",\n" +
      "    \"id\": 2,\n" +
      "    \"avatar_url\": \"https://avatars3.githubusercontent.com/u/2?v=3\",\n" +
      "    \"gravatar_id\": \"\",\n" +
      "    \"url\": \"https://api.github.com/users/defunkt\",\n" +
      "    \"html_url\": \"https://github.com/defunkt\",\n" +
      "    \"followers_url\": \"https://api.github.com/users/defunkt/followers\",\n" +
      "    \"following_url\": \"https://api.github.com/users/defunkt/following{/other_user}\",\n" +
      "    \"gists_url\": \"https://api.github.com/users/defunkt/gists{/gist_id}\",\n" +
      "    \"starred_url\": \"https://api.github.com/users/defunkt/starred{/owner}{/repo}\",\n" +
      "    \"subscriptions_url\": \"https://api.github.com/users/defunkt/subscriptions\",\n" +
      "    \"organizations_url\": \"https://api.github.com/users/defunkt/orgs\",\n" +
      "    \"repos_url\": \"https://api.github.com/users/defunkt/repos\",\n" +
      "    \"events_url\": \"https://api.github.com/users/defunkt/events{/privacy}\",\n" +
      "    \"received_events_url\": \"https://api.github.com/users/defunkt/received_events\",\n" +
      "    \"type\": \"User\",\n" +
      "    \"site_admin\": true\n" +
      "  },\n" +
      "  {\n" +
      "    \"login\": \"pjhyett\",\n" +
      "    \"id\": 3,\n" +
      "    \"avatar_url\": \"https://avatars3.githubusercontent.com/u/3?v=3\",\n" +
      "    \"gravatar_id\": \"\",\n" +
      "    \"url\": \"https://api.github.com/users/pjhyett\",\n" +
      "    \"html_url\": \"https://github.com/pjhyett\",\n" +
      "    \"followers_url\": \"https://api.github.com/users/pjhyett/followers\",\n" +
      "    \"following_url\": \"https://api.github.com/users/pjhyett/following{/other_user}\",\n" +
      "    \"gists_url\": \"https://api.github.com/users/pjhyett/gists{/gist_id}\",\n" +
      "    \"starred_url\": \"https://api.github.com/users/pjhyett/starred{/owner}{/repo}\",\n" +
      "    \"subscriptions_url\": \"https://api.github.com/users/pjhyett/subscriptions\",\n" +
      "    \"organizations_url\": \"https://api.github.com/users/pjhyett/orgs\",\n" +
      "    \"repos_url\": \"https://api.github.com/users/pjhyett/repos\",\n" +
      "    \"events_url\": \"https://api.github.com/users/pjhyett/events{/privacy}\",\n" +
      "    \"received_events_url\": \"https://api.github.com/users/pjhyett/received_events\",\n" +
      "    \"type\": \"User\",\n" +
      "    \"site_admin\": false\n" +
      "  },\n" +
      "  {\n" +
      "    \"login\": \"wycats\",\n" +
      "    \"id\": 4,\n" +
      "    \"avatar_url\": \"https://avatars3.githubusercontent.com/u/4?v=3\",\n" +
      "    \"gravatar_id\": \"\",\n" +
      "    \"url\": \"https://api.github.com/users/wycats\",\n" +
      "    \"html_url\": \"https://github.com/wycats\",\n" +
      "    \"followers_url\": \"https://api.github.com/users/wycats/followers\",\n" +
      "    \"following_url\": \"https://api.github.com/users/wycats/following{/other_user}\",\n" +
      "    \"gists_url\": \"https://api.github.com/users/wycats/gists{/gist_id}\",\n" +
      "    \"starred_url\": \"https://api.github.com/users/wycats/starred{/owner}{/repo}\",\n" +
      "    \"subscriptions_url\": \"https://api.github.com/users/wycats/subscriptions\",\n" +
      "    \"organizations_url\": \"https://api.github.com/users/wycats/orgs\",\n" +
      "    \"repos_url\": \"https://api.github.com/users/wycats/repos\",\n" +
      "    \"events_url\": \"https://api.github.com/users/wycats/events{/privacy}\",\n" +
      "    \"received_events_url\": \"https://api.github.com/users/wycats/received_events\",\n" +
      "    \"type\": \"User\",\n" +
      "    \"site_admin\": false\n" +
      "  },\n" +
      "  {\n" +
      "    \"login\": \"ezmobius\",\n" +
      "    \"id\": 5,\n" +
      "    \"avatar_url\": \"https://avatars3.githubusercontent.com/u/5?v=3\",\n" +
      "    \"gravatar_id\": \"\",\n" +
      "    \"url\": \"https://api.github.com/users/ezmobius\",\n" +
      "    \"html_url\": \"https://github.com/ezmobius\",\n" +
      "    \"followers_url\": \"https://api.github.com/users/ezmobius/followers\",\n" +
      "    \"following_url\": \"https://api.github.com/users/ezmobius/following{/other_user}\",\n" +
      "    \"gists_url\": \"https://api.github.com/users/ezmobius/gists{/gist_id}\",\n" +
      "    \"starred_url\": \"https://api.github.com/users/ezmobius/starred{/owner}{/repo}\",\n" +
      "    \"subscriptions_url\": \"https://api.github.com/users/ezmobius/subscriptions\",\n" +
      "    \"organizations_url\": \"https://api.github.com/users/ezmobius/orgs\",\n" +
      "    \"repos_url\": \"https://api.github.com/users/ezmobius/repos\",\n" +
      "    \"events_url\": \"https://api.github.com/users/ezmobius/events{/privacy}\",\n" +
      "    \"received_events_url\": \"https://api.github.com/users/ezmobius/received_events\",\n" +
      "    \"type\": \"User\",\n" +
      "    \"site_admin\": false\n" +
      "  },\n" +
      "  {\n" +
      "    \"login\": \"bmizerany\",\n" +
      "    \"id\": 46,\n" +
      "    \"avatar_url\": \"https://avatars1.githubusercontent.com/u/46?v=3\",\n" +
      "    \"gravatar_id\": \"\",\n" +
      "    \"url\": \"https://api.github.com/users/bmizerany\",\n" +
      "    \"html_url\": \"https://github.com/bmizerany\",\n" +
      "    \"followers_url\": \"https://api.github.com/users/bmizerany/followers\",\n" +
      "    \"following_url\": \"https://api.github.com/users/bmizerany/following{/other_user}\",\n" +
      "    \"gists_url\": \"https://api.github.com/users/bmizerany/gists{/gist_id}\",\n" +
      "    \"starred_url\": \"https://api.github.com/users/bmizerany/starred{/owner}{/repo}\",\n" +
      "    \"subscriptions_url\": \"https://api.github.com/users/bmizerany/subscriptions\",\n" +
      "    \"organizations_url\": \"https://api.github.com/users/bmizerany/orgs\",\n" +
      "    \"repos_url\": \"https://api.github.com/users/bmizerany/repos\",\n" +
      "    \"events_url\": \"https://api.github.com/users/bmizerany/events{/privacy}\",\n" +
      "    \"received_events_url\": \"https://api.github.com/users/bmizerany/received_events\",\n" +
      "    \"type\": \"User\",\n" +
      "    \"site_admin\": false\n" +
      "  }\n" +
      "]";
  }
}
