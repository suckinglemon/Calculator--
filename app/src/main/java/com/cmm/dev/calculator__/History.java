package calculator;

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
	private final String NO_ENTIRIES = "keine Einträge";
	
    /**
     * Konstruktor
     */
	
    History(){
    	this.add(NO_ENTIRIES);
    }
        
    /**
     * Methoden
     */
    
    public void show(){
    	for(int i = 0 ; i < this.size() ; i++) {
    		System.out.println(this.get(i));
    	}
    }
    
    public boolean addTerm(String s){
    	int old_size = this.size();
    	if(this.get(0).equals(NO_ENTIRIES)){
    		this.remove(0);
    	}
    	super.add(s);
    	if (this.size() == old_size){
    		return false;
    	}
    	return true;
    }
}
