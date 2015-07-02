package com.cmm.dev.calculator__;

import java.util.TreeMap;


/**
 *
 * @author Michael Vilsmeier
 * History (Verlauf) für Taschenrechner Eingaben
 *
 */
@SuppressWarnings("serial")
public class History extends TreeMap<Integer, String[]> {

    /**
     *
     */
    private static final String[] NO_ENTRIES = {"No Entries!", ""};
    private int CURR_ELEMENT;
    /**
     * Konstruktor
     */

    public History(){
        this.put(0, new String[]{});
        CURR_ELEMENT = -1;

		/*
		//TODO nur zum Test
		this.add("1+1");	//index :0
		this.add("2");
		this.add("1+2");
		this.add("3");
		this.add("1+4");
		this.add("5");		//index :5
		LAST_ELEMENT = 6;
		*/
    }

    /**
     * Methoden
     */

	/*
	 *
	 */
    public String[] getPreElement() {
        if (CURR_ELEMENT == -1) {
            return NO_ENTRIES;
        }
        if (CURR_ELEMENT > 1) {
            CURR_ELEMENT--;
        }
        return this.get(CURR_ELEMENT);
    }

    public String[] getNextElement(){
        if (CURR_ELEMENT == -1) {
            return NO_ENTRIES;
        }
        if(this.lastKey() != CURR_ELEMENT){
            CURR_ELEMENT++;
        }
        return this.get(CURR_ELEMENT);
    }

    public void addElement(String[] s){
        if(CURR_ELEMENT == -1){
            CURR_ELEMENT = 1;
        }
        else {
            CURR_ELEMENT = this.lastKey() + 1;
        }
        this.put(CURR_ELEMENT, new String[]{s[0], s[1]});
    }
}
