package com.treynb.cnode.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.treynb.cnode.R;
import com.treynb.cnode.model.TopicModel;
import com.treynb.cnode.util.FormatUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.util.TextUtils;

public class TopicListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<TopicModel> topicList;

    public TopicListAdapter(Context context, ArrayList<TopicModel> topicList) {
        this.context = context;
        this.topicList = topicList;
    }

    @Override
    public int getCount() {
        return topicList.size();
    }

    @Override
    public Object getItem(int i) {
        return topicList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public String getItemStringId(int i) {
        return topicList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (null == view) {
            view = LayoutInflater.from(context).inflate(R.layout.topic_list_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        TopicModel topic = topicList.get(i);
        holder.tvTitle.setText(topic.getTitle());
        holder.tvCount.setText(topic.getReplyCount() + "/" + topic.getVisitCount());
        holder.tvCreateAt.setText(FormatUtil.dateToTimeBefore(topic.getCreateAt()));
        holder.tvLoginname.setText(topic.getAuthor().getLoginName());
        holder.tvTab.setText(topic.getTabText());

        String label=topic.getLabelText();
        if(TextUtils.isEmpty(label)){
            holder.tvLabel.setVisibility(View.GONE);
        }else{
            holder.tvLabel.setText(label);
            holder.tvLabel.setVisibility(View.VISIBLE);
        }
        topic.getAuthor().loadAvatar(holder.ivAvatarUrl);
        return view;
    }


    static class ViewHolder {
        @Bind(R.id.iv_avatar_url)
        ImageView ivAvatarUrl;
        @Bind(R.id.tv_label)
        TextView tvLabel;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_count)
        TextView tvCount;
        @Bind(R.id.tv_create_at)
        TextView tvCreateAt;
        @Bind(R.id.tv_tab)
        TextView tvTab;
        @Bind(R.id.tv_loginname)
        TextView tvLoginname;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
