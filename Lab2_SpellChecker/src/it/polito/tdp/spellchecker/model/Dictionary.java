package it.polito.tdp.spellchecker.model;
import java.util.*;

public class Dictionary {
	
	List <String> dizionario;
	List <RichWord> parole;
	
	public Dictionary(){
		dizionario = new LinkedList();
		parole = new LinkedList();
	}
	
	
	public void loadDictionary() {
		
	}

	
	public List<RichWord> spellCheckText(List<String> inputTextList) {
		parole.clear();
		for(String s : inputTextList){
			RichWord rw= new RichWord(s.toLowerCase());

			if (binarySearch(s.toLowerCase())== false){ rw.setIscorretta(false);}
			parole.add(rw);}
			
			//if (!dizionario.contains(s.toLowerCase()))rw.setIscorretta(false); 
				//parole.add(rw);}
		
		
		return parole;
	}
	public boolean binarySearch(String key) 
	    {
	       int low = 0;
	       int high = dizionario.size() - 1;
	       int middle = (high+low) / 2; 
	        while(low <= high) {
             
	            if(key.compareToIgnoreCase(dizionario.get(middle))== 0) {
	                return true;
	            }
            if(key.compareToIgnoreCase(dizionario.get(middle)) > 0) {
	                 low = middle +1;
	             }
            else{
	                 high = middle - 1;
	            }
	             middle = (high+low) / 2;
	       }
	        return false;
   }

}
