package com.treynb.cnode.model;

import com.treynb.cnode.util.FormatUtil;

import org.joda.time.DateTime;

import java.io.Serializable;

public class ReplyModel implements Serializable{
    private String id;
    private String content;
    private DateTime createAt;
    private UserModel author;

    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(DateTime createAt) {
        this.createAt = createAt;
    }

    public String getHandledContent() {
        return FormatUtil.contentToHTML(content);
    }
}
