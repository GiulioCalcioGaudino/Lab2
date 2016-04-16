package it.polito.tdp.spellchecker.model;

import java.util.*;

public class TestModel {

	public static void main(String[] args) {

		Dictionary d1 = new ItalianDictionary();
		Dictionary d2 = new EnglishDictionary();

		d1.loadDictionary();
		d2.loadDictionary();

		List<String> parole = new LinkedList<String>();

		parole.add("sole");
		parole.add("abate");
		parole.add("yfgfgh");

		long l1 = System.nanoTime();

		List<RichWord> l = d1.spellCheckText(parole);
		long l2 = System.nanoTime();

		for (RichWord r : l) {
			System.out.println(r.toString());
		}

		System.out.println("Tempo impiegato: " + (l2 - l1));
	}

}
