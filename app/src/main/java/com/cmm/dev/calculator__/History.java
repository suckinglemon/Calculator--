package com.cmm.dev.calculator__;

import java.util.ArrayList;


/**
 *
 * @author Michi
 * History (Verlauf) für Taschenrechner eingaben
 *
 */
@SuppressWarnings("serial")
public class History extends ArrayList<String>{

	/**
	 *
	 */
	private final String NO_ENTRIES = "keine Eintraege";

	/**
	 * Konstruktor
	 */

	public History(){
		this.add(NO_ENTRIES);
	}

	/**
	 * Methoden
	 */

	public void showTerm(){
		for(int i = 0 ; i < this.size() ; i++) {
			System.out.println(this.get(i));			//TODO weil System.out wegmuss
		}
	}



	public boolean addTerm(String s){
		int old_size = this.size();
		if(this.get(0).equals(NO_ENTRIES)){
			this.remove(0);
		}
		super.add(s);
		if (this.size() == old_size){
			return false;
		}
		return true;
	}
}
