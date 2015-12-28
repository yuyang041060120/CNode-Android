package com.treynb.cnode.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.treynb.cnode.R;
import com.treynb.cnode.model.ReplyModel;
import com.treynb.cnode.util.FormatUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReplyListAdapter extends BaseAdapter {
    private ArrayList<ReplyModel> replyList;
    private Context context;

    public ReplyListAdapter(Context context, ArrayList<ReplyModel> replyList) {
        this.context = context;
        this.replyList = replyList;
    }

    @Override
    public int getCount() {
        return replyList.size();
    }

    @Override
    public Object getItem(int i) {
        return replyList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (null == view) {
            view = LayoutInflater.from(context).inflate(R.layout.reply_list_item, null);
            holder = new ViewHolder(view);
            holder.tvContent.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return (motionEvent.getAction() == MotionEvent.ACTION_MOVE);
                }
            });
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ReplyModel reply = replyList.get(i);
        holder.tvcreateAt.setText(FormatUtil.dateToTimeBefore(reply.getCreateAt()));
        holder.tvLoginname.setText(reply.getAuthor().getLoginName());
        holder.tvContent.setText(Html.fromHtml(reply.getContent()));
        holder.tvFloor.setText(i + 1 + "楼");
        reply.getAuthor().loadAvatar(holder.ivAvatarUrl);
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.iv_avatar_url)
        ImageView ivAvatarUrl;
        @Bind(R.id.tv_content)
        TextView tvContent;
        @Bind(R.id.tv_floor)
        TextView tvFloor;
        @Bind(R.id.tv_create_at)
        TextView tvcreateAt;
        @Bind(R.id.tv_loginname)
        TextView tvLoginname;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
