package com.example.dliu.myshoppingdemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TextView textView1= (TextView) findViewById(R.id.tv_find);
        TextView textView2= (TextView) findViewById(R.id.tv_find);
        Log.i("TestActivity", "TestActivity: onCreate:"+textView1+","+textView2);

    }
}
