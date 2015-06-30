package com.cmm.dev.calculator__;

import java.util.ArrayList;


/**
 *
 * @author Michael Vilsmeier
 * History (Verlauf) für Taschenrechner Eingaben
 *
 */
@SuppressWarnings("serial")
public class History extends ArrayList<String> {

	/**
	 *
	 */
	private final String NO_ENTRIES = "keine Eintraege";
	private int LAST_ELEMENT;
	/**
	 * Konstruktor
	 */

	public History(){
		this.add(NO_ENTRIES);
		LAST_ELEMENT = 0;

		//TODO nur zum Test
		this.add("1+1");	//index :0
		this.add("2");
		this.add("1+2");
		this.add("3");
		this.add("1+4");
		this.add("5");		//index :5
		LAST_ELEMENT = 6;
	}

	/**
	 * Methoden
	 */

	/*
	 *
	 */
	public String getPreElement(){
		if (LAST_ELEMENT == -1){
			return NO_ENTRIES;
		}
		else if(LAST_ELEMENT == 0){
			LAST_ELEMENT--;
			return this.get(LAST_ELEMENT+1);
			}
		else if (LAST_ELEMENT > 0 ){
			LAST_ELEMENT--;
			return this.get(LAST_ELEMENT+1);
		}
		return "";
	}

	public String getNextElement(){
		if (LAST_ELEMENT < this.size()){
			if(LAST_ELEMENT < 0){
				LAST_ELEMENT = 1;
			}
			LAST_ELEMENT++;
			return this.get(LAST_ELEMENT-1);
		}
		else if (LAST_ELEMENT == this.size()){
			LAST_ELEMENT++;
			return this.get(this.size()-2);
		}
		LAST_ELEMENT--;
		return "";
	}

	public boolean addElement(String s){
		int old_size = this.size();
		if(this.get(0).equals(NO_ENTRIES)){
			this.remove(0);
		}
		super.add(s);
		if (this.size() == old_size){
			return false;
		}
		LAST_ELEMENT++;
		return true;
	}
}
