package com.cmm.dev.calculator__.tests;
//http://rexstjohn.com/unit-testing-with-android-studio/
import android.test.InstrumentationTestCase;

public class TestHistory extends InstrumentationTestCase {
    public void test() throws Exception {
        final int expected = 1;
        final int reality = 5;
        assertEquals(expected, reality);
    }
}