package com.treynb.cnode.model;

import com.treynb.cnode.util.FormatUtil;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.ArrayList;

public class TopicModel implements Serializable{
    private String id;
    private String title;
    private String content;
    private String tab;
    private DateTime createAt;
    private boolean top;
    private boolean good;
    private int replyCount;
    private int visitCount;
    private UserModel author;
    private ArrayList<ReplyModel> replyList;

    public ArrayList<ReplyModel> getReplyList() {
        return replyList;
    }

    public void setReplyList(ArrayList<ReplyModel> replyList) {
        this.replyList = replyList;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }


    public String getId() {
        return id;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return FormatUtil.contentToHTML(content);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }

    public DateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(DateTime createAt) {
        this.createAt = createAt;
    }

    public String getTabText() {
        String tabText = "未知";

        if (tab.equals("share")) {
            tabText = "分享";
        } else if (tab.equals("ask")) {
            tabText = "问答";
        } else if (tab.equals("job")) {
            tabText = "招聘";
        }

        return tabText;
    }

    public String getLabelText(){
        if(top){
            return "置顶";
        }

        if(good){
            return "精华";
        }

        return null;
    }
    public String getHandledContent() {
        return FormatUtil.contentToHTML(content);
    }
}
