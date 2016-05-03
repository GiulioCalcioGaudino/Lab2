package it.polito.tdp.spellchecker.model;
import java.util.*;

public class TestModel {

	public static void main(String[] args) {
		List <String> ole = new LinkedList <String>();
	
		Dictionary devotoOli = new ItalianDictionary();
		ole.add("viso");
		ole.add("giraffa");
		ole.add("bisonte");
		ole.add("supelmelcato");
		
		devotoOli.loadDictionary();
	
		List<RichWord> l = devotoOli.spellCheckText(ole);
		
		for (RichWord r : l) {
			System.out.println(r.toString());
		}
		
	
		
		
			

	}

}
