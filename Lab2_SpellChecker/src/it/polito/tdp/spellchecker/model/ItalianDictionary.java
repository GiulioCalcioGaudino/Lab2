package it.polito.tdp.spellchecker.model;
import java.io.*;

public class ItalianDictionary extends Dictionary{

public void loadDictionary() {
	
	try {  FileReader fr = new FileReader("rsc/Italian.txt");
	BufferedReader br = new BufferedReader(fr);
	String word;
	while ((word = br.readLine()) != null) { 
		dizionario.add(word) ; 
		}
		br.close();       
		} catch (IOException e){ 
			System. out .println("Errore nella lettura del file");
			}
	}
	}
	


