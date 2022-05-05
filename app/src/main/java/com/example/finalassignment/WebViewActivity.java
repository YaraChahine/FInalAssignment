package com.example.finalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class WebViewActivity extends AppCompatActivity {


    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent secondIntent = getIntent();

        //We retrieve the url sent from the first page and we save it in a variable
        String message = secondIntent.getStringExtra("COURSE_NAME");



        //we then enable javascript in our webview and load the url.
        WebView view=findViewById(R.id.webview);
        view.getSettings().setJavaScriptEnabled(true);
        view.setWebViewClient(new WebViewClient());
        view.loadUrl(message);

    }


    public void back(View view){
        Intent intent= new Intent(this,MainActivity.class);


        startActivity(intent); //The following function is used in order to take the user to MainActivity

    }
}