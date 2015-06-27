package com.cmm.dev.calculator__;


/**
 * Created by Johann on 26.06.2015.
 */
public class InputStringBuilder {

    private StringBuffer inputBuffer;
    public String outcomeString;

    // Konstruktor
    public void inputStringBuilder () {
        inputBuffer = new StringBuffer();
        outcomeString = "";
    }

    // Hängt Zahl an inputBuffer an
    public void appendNumber (char c) {
        inputBuffer.append(c);
        updateTextView();
    }

    private static void updateTextView () {


    }
}
