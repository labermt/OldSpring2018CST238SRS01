package com.example.happybirthday2you;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FinishedActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.FINISH_MESSAGE);
        TextView finish_text = findViewById(R.id.finishText);
        finish_text.setText(message);
    }
}
