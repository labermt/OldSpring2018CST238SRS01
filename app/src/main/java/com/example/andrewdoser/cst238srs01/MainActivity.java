package com.example.andrewdoser.cst238srs01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.*;
import java.lang.CharSequence;




public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
public abstract class TextValidator implements TextWatcher {
    private final TextView textView;

    public TextValidator(TextView textView) {
        this.textView = textView;
    }

    public abstract void validate(TextView textView, String text);

    @Override
    final public void afterTextChanged(Editable textView) {
        String text = textView.getText().toString();
        validate(textView, text);
    }

    @Override
    final public void
    beforeTextChanged(CharSequence s, int start, int count, int after) {
        /* Needs to be implemented, but we are not using it. */
    }

    @Override
    final public void
    onTextChanged(CharSequence s, int start, int before, int count) {
        /* Needs to be implemented, but we are not using it. */
    }
}