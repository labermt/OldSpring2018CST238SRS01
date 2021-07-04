package com.example.andrewdoser.cst238srs01;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

public abstract class TextValidator implements TextWatcher {
    private final TextView textView;

    public TextValidator(TextView textView) {
        this.textView = textView;
    }

    public abstract void validate(TextView textView, String text);

    @Override
    final public void afterTextChanged(Editable textView) {
        String text = this.textView.getText().toString();
        validate(this.textView, text);
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
