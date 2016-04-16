package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

public class EnglishDictionary extends Dictionary {

	@Override
	public void loadDictionary() {
		
		try {
			
			FileReader fr = new FileReader("rsc/English.txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			
			while ((word = br.readLine()) != null) {
				// Aggiungo word alla struttura dati
				dizionario.add(word.toLowerCase());
			}
			
			// Ordino il dizionario (per la ricerca dicotomica)
			Collections.sort(dizionario);
			
			br.close();
			
		} catch (IOException e) {
			
			System.out.println("Errore nella lettura del file");
		}
	}

}
