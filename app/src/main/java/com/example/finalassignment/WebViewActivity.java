package com.example.finalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WebViewActivity extends AppCompatActivity {


    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent secondIntent = getIntent();

        String message = secondIntent.getStringExtra("COURSE_NAME");
        title=(TextView) findViewById(R.id.title);
        title.setText(message);

    }


    public void back(View view){

    }
}