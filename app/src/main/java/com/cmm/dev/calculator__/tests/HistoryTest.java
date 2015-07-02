package com.cmm.dev.calculator__.tests;

import android.util.Log;
import junit.framework.TestCase;
import com.cmm.dev.calculator__.History;

public class HistoryTest extends TestCase {

    History testHistory = new History();

    public void testaddElement() {

        for(int i=1 ; i <= 30 ; i++){
            testHistory.addElement(i + "+" + i + "=" + i+i);
            assertEquals (testHistory.size(), i+1);
            }
    }

    public void testgetPreElement() throws Exception {

        int count = testHistory.size();
        for(int i=testHistory.size(); i > 2 ; i--){
            assertEquals(testHistory.getPreElement(), testHistory.get(count-1));
            count--;
        }
    }
    public void testgetNextElement() throws Exception {

        int count = 0;
        for(int i=0 ; i < testHistory.size()-1 ; i++){
            assertEquals(testHistory.getNextElement(), testHistory.get(count+1));
            count++;
        }
    }

}
