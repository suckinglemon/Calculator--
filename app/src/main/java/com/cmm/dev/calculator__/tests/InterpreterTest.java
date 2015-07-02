package com.cmm.dev.calculator__.tests;

import android.util.Log;
import com.cmm.dev.calculator__.Interpreter;
import junit.framework.TestCase;

public class InterpreterTest extends TestCase {

    public void testSolve(){

        //Test-Strings for Solve-Methode
        String term1 = "(3*2.5)+(7-(8/2))";
        String result1 = "10.5";
        String term2 = "1+(3*3)";
        String result2 = "10";
        String term3 = "((9+8)*7+((((6/2)-5)*4)+3)-1)";
        String result3 = "113";
        String term4 = "10%4";
        String result4 = "2";
        String term5 = "(-12.345+678.90)*0.34-2+432+(-3)";
        String result5 = "653.6287";
        String term6 = "(-3*2)^3";
        String result6 = "-216";
        String term7 = "2^(-8+14)";
        String result7 = "64";
        String term8 = "3e^0";
        String result8 = "3.0";
        String term9 = "1/0";
        String result9 = "Error! Divided by 0!";
        String term10 = "1+)*1)";
        String result10 = "Error! Wrong order of brackets";
        String term11 = "-+1*3.0";
        String result11 = "Error!";

        //Aufruf und Vergleich

        assertEquals(Interpreter.solve(term1), result1);
        assertEquals(Interpreter.solve(term2), result2);
        assertEquals(Interpreter.solve(term3), result3);
        assertEquals(Interpreter.solve(term4), result4);
        assertEquals(Interpreter.solve(term5), result5);
        assertEquals(Interpreter.solve(term6), result6);
        assertEquals(Interpreter.solve(term7), result7);
        assertEquals(Interpreter.solve(term8), result8);
        assertEquals(Interpreter.solve(term9), result9);
        assertEquals(Interpreter.solve(term10), result10);
        assertEquals(Interpreter.solve(term11), result11);

        // Exceptions
        String exception1 = "    ";
        String exception2 = "1§2";

        try{
            Interpreter.solve(exception1);
            fail();
        }
        catch(IllegalArgumentException e1){
            Log.d(exception1, "IllegalArgumentException");
        }
        try{
            Interpreter.solve(exception2);
            fail();
        }
        catch(IllegalArgumentException e2){
            Log.d(exception2, "IllegalArgumentException");
        }
    }
}