package com.cst238srs01.happybirthday2you;


import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;


//TextValidator taken from https://stackoverflow.com/questions/2763022/android-how-can-i-validate-edittext-input/11838715#11838715
public abstract class TextValidator implements TextWatcher {
    private final TextView textView;

    public TextValidator(TextView textView) {
        this.textView = textView;
    }

    public abstract void validate(TextView textView, String text);

    @Override
    final public void afterTextChanged(Editable s) {
        String text = textView.getText().toString();
        validate(textView, text);
    }

    @Override
    final public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Don't care */ }

    @Override
    final public void onTextChanged(CharSequence s, int start, int before, int count) { /* Don't care */ }
}