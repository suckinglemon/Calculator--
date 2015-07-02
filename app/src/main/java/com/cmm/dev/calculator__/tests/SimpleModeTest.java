package com.cmm.dev.calculator__.tests;


import android.util.Log;
import com.cmm.dev.calculator__.SimpleMode;
import junit.framework.TestCase;


public class SimpleModeTest extends TestCase{

    public void testSolve(){

            //Strings for Solve-Methode
            String term1 = "1.0+2.5";
            String result1 = "3.5";
            String term2 = "1.1-101.1";
            String result2 = "-100.0";
            String term3 = "11*11";
            String result3 = "121.0";
            String term4 = "12/4";
            String result4 = "3.0";
            String term5 = "1/0";
            String result5 = "Infinity";

            //call methods and compare

            assertEquals(SimpleMode.solve(term1), result1);
            assertEquals(SimpleMode.solve(term2), result2);
            assertEquals(SimpleMode.solve(term3), result3);
            assertEquals(SimpleMode.solve(term4), result4);
            assertEquals(SimpleMode.solve(term5), result5);


            // Exceptions
            String exception1 = "-1*2";
            String exception2 = "*2-1";
            String exception3 = "1--5";
            String exception4 = "1..0+2";

            try{
                SimpleMode.solve(exception1);
                fail();
            }
            catch(IllegalArgumentException e1){
                Log.d(exception1, e1.toString());
            }
            try{
                SimpleMode.solve(exception2);
                fail();
            }
            catch(IllegalArgumentException e2){
                Log.d(exception2, e2.toString());
            }
            try{
                SimpleMode.solve(exception3);
                fail();
            }
            catch(IllegalArgumentException e3) {
                Log.d(exception2, e3.toString());
            }
            try{
                SimpleMode.solve(exception4);
                fail();
            }
            catch(IllegalArgumentException e4){
                Log.d(exception2, e4.toString());
            }
        }
}

