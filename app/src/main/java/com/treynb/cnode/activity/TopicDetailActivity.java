package com.treynb.cnode.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.treynb.cnode.R;
import com.treynb.cnode.http.Backend;
import com.treynb.cnode.http.BackendCallback;
import com.treynb.cnode.http.BaseHttp;
import com.treynb.cnode.model.ReplyModel;
import com.treynb.cnode.model.TopicModel;
import com.treynb.cnode.model.UserModel;
import com.treynb.cnode.util.FormatUtil;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class TopicDetailActivity extends AppCompatActivity {
    private TopicModel topic = new TopicModel();

    @Bind(R.id.pb_loading)
    ProgressBar pbLoading;

    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Bind(R.id.tv_content)
    WebView tvContent;

    @Bind(R.id.ly_layout)
    LinearLayout layout;

    @Bind(R.id.iv_avatar_url)
    ImageView ivAvatarUrl;

    @Bind(R.id.tv_loginname)
    TextView tvLoginname;

    @Bind(R.id.tv_create_at)
    TextView tvCreateAt;

    @Bind(R.id.tv_tab)
    TextView tvTab;

    @Bind(R.id.tv_count)
    TextView tvCount;

    @Bind(R.id.tv_from)
    TextView tvFrom;

    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return (motionEvent.getAction() == MotionEvent.ACTION_MOVE);
            }
        });

        Intent intent = getIntent();
        fetchData(intent.getStringExtra("id"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topic_detail_menu, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_reply_count:
                if (topic.getReplyList().isEmpty()) {
                    return true;
                }
                Intent intent = new Intent(this, ReplyListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("replyList", topic.getReplyList());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void fetchData(String id) {
        Backend.fetchTopic(id, new BackendCallback() {
            @Override
            public void onSuccess(Object object) {
                topic= (TopicModel) object;
                render();
            }

            @Override
            public void onFail() {

            }
        });
    }

    public void render() {
        layout.removeView(pbLoading);
        tvTitle.setText(topic.getTitle());
        tvContent.loadData(topic.getHandledContent(), "text/html;charset=UTF-8", "UTF-8");
        tvLoginname.setText(topic.getAuthor().getLoginName());
        tvCount.setText(topic.getReplyCount() + "/" + topic.getVisitCount());
        tvCreateAt.setText("发布于" + FormatUtil.dateToTimeBefore(topic.getCreateAt()));
        tvTab.setText(topic.getTabText());
        tvFrom.setVisibility(View.VISIBLE);
        topic.getAuthor().loadAvatar(ivAvatarUrl);

        MenuItem menuItem = menu.findItem(R.id.menu_reply_count);
        menuItem.setTitle("回复(" + topic.getReplyList().size() + ")");
    }


}
