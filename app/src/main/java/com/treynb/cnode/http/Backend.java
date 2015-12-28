package com.treynb.cnode.http;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Backend {
    public static void fetchTopicList(RequestParams params, final BackendCallback callback) {
        BaseHttp.get("/api/v1/topics", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    callback.onSuccess(SerializeData.toTopicList((JSONArray) response.get("data")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void fetchTopic(String id, final BackendCallback callback) {
        BaseHttp.get("/api/v1/topic/" + id, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    callback.onSuccess(SerializeData.toTopic((JSONObject) response.get("data")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


