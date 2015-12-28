package com.treynb.cnode.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.treynb.cnode.R;
import com.treynb.cnode.adapter.TopicListAdapter;
import com.treynb.cnode.http.Backend;
import com.treynb.cnode.http.BackendCallback;
import com.treynb.cnode.model.TopicModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener {

    private ArrayList<TopicModel> topicList = new ArrayList<>();
    private TopicListAdapter adapter;
    private int page = 1;
    private String tab = "all";
    private int visibleCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lvTopicList = (ListView) findViewById(R.id.lv_topic_list);
        adapter = new TopicListAdapter(this, topicList);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        lvTopicList.setOnScrollListener(this);
        lvTopicList.addFooterView(new ProgressBar(this));
        lvTopicList.setAdapter(adapter);
        lvTopicList.setOnItemClickListener(this);
        fetchData();
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (SCROLL_STATE_IDLE == i && topicList.size() == visibleCount) {
            fetchData();
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        visibleCount = i + i1 - 1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void fetchData() {
        RequestParams params = new RequestParams();
        params.add("tab", tab);
        params.add("page", Integer.toString(page++));

        Backend.fetchTopicList(params, new BackendCallback() {
            @Override
            public void onSuccess(Object object) {
                topicList.addAll((ArrayList<TopicModel>) object);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        clearTopicList();
        switch (item.getItemId()) {
            case R.id.menu_all:
                tab = "all";
                setTitle(getString(R.string.menu_all));
                break;
            case R.id.menu_good:
                tab = "good";
                setTitle(getString(R.string.menu_good));
                break;
            case R.id.menu_ask:
                tab = "ask";
                setTitle(getString(R.string.menu_ask));
                break;
            case R.id.menu_share:
                tab = "share";
                setTitle(getString(R.string.menu_share));
                break;
            case R.id.menu_job:
                tab = "job";
                setTitle(getString(R.string.menu_job));
                break;
        }
        fetchData();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, TopicDetailActivity.class);
        intent.putExtra("id", adapter.getItemStringId(i));
        startActivity(intent);
    }

    public void clearTopicList() {
        topicList.clear();
        adapter.notifyDataSetChanged();
        page = 1;
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), getString(R.string.exit_message), Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
