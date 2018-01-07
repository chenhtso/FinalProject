package com.example.koishi.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

public class UIActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        init();
    }

    private void init() {
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("第一个标签")
                .setContent(R.id.alwayswet));

        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("第二个标签")
                .setContent(R.id.isanimal));

        Intent intent = new Intent();
        intent.setAction("com.example.koishi.finalproject.FIREUP");
        sendBroadcast(intent);
    }

    private void setTimeAndPassword() {

    }

}
