package com.treynb.cnode.http;

import com.treynb.cnode.model.ReplyModel;
import com.treynb.cnode.model.TopicModel;
import com.treynb.cnode.model.UserModel;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SerializeData {
    public static ArrayList<TopicModel> toTopicList(JSONArray array) throws JSONException {

        if (null == array) {
            return null;
        }

        ArrayList<TopicModel> topicList = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            topicList.add(toTopic((JSONObject) array.get(i)));
        }

        return topicList;
    }


    public static TopicModel toTopic(JSONObject obj) throws JSONException {

        if (null == obj) {
            return null;
        }
        TopicModel topic = new TopicModel();


        topic.setId(obj.optString("id"));
        topic.setTitle(obj.optString("title"));
        topic.setTop(obj.optBoolean("top"));
        topic.setGood(obj.optBoolean("good"));
        topic.setTab(obj.optString("tab"));
        topic.setContent(obj.optString("content"));
        topic.setCreateAt(new DateTime(obj.optString("create_at")));
        topic.setReplyCount(obj.optInt("reply_count"));
        topic.setVisitCount(obj.optInt("visit_count"));
        topic.setAuthor(toUser((JSONObject) obj.get("author")));
        topic.setReplyList(toReplyList((JSONArray) obj.opt("replies")));

        return topic;
    }

    public static UserModel toUser(JSONObject obj) {
        if (null == obj) {
            return null;
        }

        UserModel author = new UserModel();

        author.setId(obj.optString("author_id"));
        author.setLoginName(obj.optString("loginname"));
        author.setAvatarUrl(obj.optString("avatar_url"));

        return author;
    }

    public static ArrayList<ReplyModel> toReplyList(JSONArray array) throws JSONException {
        if (null == array) {
            return null;
        }

        ArrayList<ReplyModel> replyList = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            replyList.add(toReply((JSONObject) array.get(i)));
        }


        return replyList;
    }

    public static ReplyModel toReply(JSONObject obj) {
        if (null == obj) {
            return null;
        }

        ReplyModel reply = new ReplyModel();

        reply.setId(obj.optString("id"));
        reply.setContent(obj.optString("content"));
        reply.setCreateAt(new DateTime(obj.opt("create_at")));

        reply.setAuthor(toUser((JSONObject) obj.opt("author")));

        return reply;
    }
}
