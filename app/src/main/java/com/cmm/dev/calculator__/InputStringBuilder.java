package com.cmm.dev.calculator__;

import android.view.View;

/**
 * Created by Johann on 26.06.2015.
 */
public class InputStringBuilder {

    private StringBuffer inputBuffer = new StringBuffer();
    public String outcomeString = new String("");

    public void appendNumber1 (View view) {
        inputBuffer.append("1");
        saveInHistory();
        updateTextView();
    }

    private void saveInHistory () {

    }

    private void updateTextView () {


    }
}
