package com.treynb.cnode.model;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.treynb.cnode.util.FormatUtil;

import java.io.Serializable;

public class UserModel implements Serializable{
    private String id;
    private String loginName;
    private String avatarUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void loadAvatar(ImageView imageView){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(true)
                .cacheOnDisk(true)
                .build();

        ImageLoader.getInstance().displayImage(FormatUtil.getCompatAvatarUrl(avatarUrl), imageView, options);
    }
}
