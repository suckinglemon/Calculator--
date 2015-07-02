package com.cmm.dev.calculator__.tests;


import android.util.Log;
import com.cmm.dev.calculator__.ProgrammerMode;
import junit.framework.TestCase;

public class ProgrammerModeTest extends TestCase {

    // Test: toDec with 3 InputModes
    public void testtoDec() {
        assertEquals(ProgrammerMode.toDec(1, "123"), "123");   //from decimal
        assertEquals(ProgrammerMode.toDec(2, "1010"), "10");   //from bin
        assertEquals(ProgrammerMode.toDec(3, "F"), "15");      //from hexa

        try {
            ProgrammerMode.toDec(2, "-2");
            fail();
        } catch (Exception e1) {
            Log.d(e1.toString(), e1.toString());
        }
    }

    // Test: toBin with 3 InputModes
    public void testtoBin() {
        assertEquals(ProgrammerMode.toBin(1, "10"), "1010");      //from decimal
        assertEquals(ProgrammerMode.toBin(2, "0"), "0");        //from bin
        assertEquals(ProgrammerMode.toBin(3, "F"), "1111");       //from hexa

        try {
            ProgrammerMode.toBin(1, "--1");
            fail();
        } catch (Exception e1) {
            Log.d(e1.toString(), e1.toString());
        }
    }

    // Test: toBin with 3 InputModes
    public void testtoHex() {
        assertEquals(ProgrammerMode.toHex(1, "10"), "A");      //from decimal
        assertEquals(ProgrammerMode.toHex(2, "1010"), "A");    //from bin
        assertEquals(ProgrammerMode.toHex(3, "0"), "0");       //from hexa

        try {
            ProgrammerMode.toHex(2, "/1");
            fail();
        } catch (Exception e1) {
            Log.d(e1.toString(), e1.toString());
        }
    }

    // Test: solveHex
    public void testsolveHex() {
        assertEquals(ProgrammerMode.solveHex("ABC+DEF"), "18AB");
        assertEquals(ProgrammerMode.solveHex("F-5"), "A");
        assertEquals(ProgrammerMode.solveHex("5*5"), "19");
        assertEquals(ProgrammerMode.solveHex("A/2"), "5");

        try {
            ProgrammerMode.solveHex("A/0");
            fail();
        } catch (Exception e1) {
            Log.d(e1.toString(), e1.toString());
        }
    }

    // Test: solveBin
    public void testsolveBin(){
        assertEquals(ProgrammerMode.solveBin("1111+01"), "10000");
        assertEquals(ProgrammerMode.solveBin("1111-111"), "1000");
        assertEquals(ProgrammerMode.solveBin("1111101000/1010"), "1100100");
        assertEquals(ProgrammerMode.solveBin("1010/10"),"101");

        try {
            ProgrammerMode.solveBin("-1+1");
            fail();
        } catch (Exception e1) {
            Log.d(e1.toString(), e1.toString());
        }
    }
}