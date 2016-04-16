package it.polito.tdp.spellchecker.model;

import java.util.*;

public class Dictionary {

	final static boolean dicotomica = false;
	
	protected List<String> dizionario;
	protected List<RichWord> parole;

	public Dictionary() {
		dizionario = new LinkedList<String>();
		parole = new LinkedList<RichWord>();
	}

	public void loadDictionary() {};

	public List<RichWord> spellCheckText(List<String> inputTextList) {
		
		parole.clear();
		
		RichWord r;
		for (String s : inputTextList) {
			
			if (dicotomica) {
				if (binarySearch(s.toLowerCase())) 
					r = new RichWord(s, true);
				else r = new RichWord(s, false);
				parole.add(r);
				
			} else {
				if (dizionario.contains(s.toLowerCase())) 
					r = new RichWord(s, true);
				else 
					r = new RichWord(s, false);
				parole.add(r);
			}
		}
		return parole;
	}
	     
	
	
	/*
	 * Metodo che implementa la ricerca dicotomica
	 */
	private boolean binarySearch(String stemp) {
		
		int inizio = 0;
	     int fine = dizionario.size();

	     while (inizio!=fine){
	         int medio = inizio + (fine - inizio)/2;
	         if (stemp.compareToIgnoreCase(dizionario.get(medio))==0){
	             return true;
	         } else if (stemp.compareToIgnoreCase(dizionario.get(medio))>0){
	               inizio=medio +1;
	           } else {
	               fine=medio;
	           }
	     }
		
		return false;
	}
	
}
