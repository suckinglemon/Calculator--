package com.cmm.dev.calculator__;

import java.util.ArrayList;


/**
 *
 * @author Michael Vilsmeier
 * History (Verlauf) für Taschenrechner Eingaben
 *
 */
@SuppressWarnings("serial")
public class History extends ArrayList<String>{

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
	}

	/**
	 * Methoden
	 */

	/*
	 * 
	 */
	public String getPreElement(){
		if (LAST_ELEMENT < 0){
			return NO_ENTRIES;
		}
		else if(LAST_ELEMENT == 0){
			return this.get(LAST_ELEMENT);
			}
		else if (LAST_ELEMENT > 0 ){
			LAST_ELEMENT--;
			return this.get(LAST_ELEMENT);
		}
		return "";
	}

	public String getNextElement(){
		if (LAST_ELEMENT < this.size()){
			LAST_ELEMENT++;
			return this.get(LAST_ELEMENT);
		}
		else if(LAST_ELEMENT == this.size()){
			return this.get(LAST_ELEMENT);
		}
		else if (LAST_ELEMENT > this.size()){
			return this.get(this.size());
		}
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
