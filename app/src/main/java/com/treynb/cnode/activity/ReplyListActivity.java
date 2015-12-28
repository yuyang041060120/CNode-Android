package com.treynb.cnode.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.treynb.cnode.R;
import com.treynb.cnode.adapter.ReplyListAdapter;
import com.treynb.cnode.model.ReplyModel;

import java.util.ArrayList;

public class ReplyListActivity extends AppCompatActivity {

    private ListView lv_replyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_list);

        lv_replyList = (ListView) findViewById(R.id.lv_reply_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        ArrayList<ReplyModel> replyList = (ArrayList<ReplyModel>) intent.getExtras().getSerializable("replyList");
        lv_replyList.setAdapter(new ReplyListAdapter(this, replyList));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
