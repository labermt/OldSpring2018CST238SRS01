package com.example.andrewdoser.cst238srs01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText mName;
    EditText mMM;
    EditText mDD;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mName = findViewById(R.id.editTextName);
        mMM = findViewById(R.id.editTextMonth);
        mDD = findViewById(R.id.editTextDay);
        mButton = findViewById(R.id.buttonSubmit);
        mName.addTextChangedListener(new TextValidator(mName) {
            @Override public void validate(TextView textView, String text) {
                /* Insert your validation rules here */
            }
        });
        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        String name = mName.getText().toString();
                        String month = mMM.getText().toString();
                        String day = mDD.getText().toString();
                    }
                }
        );



    }




}
