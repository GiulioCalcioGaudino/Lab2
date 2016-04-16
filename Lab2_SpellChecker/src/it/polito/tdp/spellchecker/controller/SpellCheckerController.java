package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.*;
import it.polito.tdp.spellchecker.model.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class SpellCheckerController {

	private Dictionary dizionario;
	private ItalianDictionary dizionarioItaliano;
	private EnglishDictionary dizionarioInglese;
	
	List<String> listaDaCorreggere = new LinkedList<String>();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblStato;

	@FXML
	private ComboBox<String> boxLingua;

	@FXML
	private TextArea txtDaCorreggere;

	@FXML
	private TextFlow txtCorretto;

	@FXML
	private Label lblErrori;

	@FXML
	void doClearText(ActionEvent event) {
		// Reset dell'interfaccia grafica
		txtDaCorreggere.clear();
		listaDaCorreggere.clear();
		txtCorretto.getChildren().clear();
		lblErrori.setText("");
		lblStato.setText("");
	}

	void setModel(ItalianDictionary dizionarioItaliano, EnglishDictionary dizionarioInglese) {
		
		this.dizionarioItaliano = dizionarioItaliano;
		dizionarioItaliano.loadDictionary();
		this.dizionarioInglese = dizionarioInglese;
		dizionarioInglese.loadDictionary();
	}

	@FXML
	void doActivation(ActionEvent event) {
		
		// L'utente non può inserire del testo prima di aver selezionato la lingua
		if (boxLingua.getValue() != null) {
			txtDaCorreggere.setDisable(false);
			txtDaCorreggere.clear();
		}
	}

	@FXML
	void doSpellCheck(ActionEvent event) {

		// Inizializzazione
		txtCorretto.getChildren().clear();
		listaDaCorreggere.clear();
		
		// Gestisco la selezione della lingua
		if (boxLingua.getValue().compareTo("English") == 0) {
			dizionario = dizionarioInglese;
		} else {
			dizionario = dizionarioItaliano;
		}

		// Prendo il testo da correggere
		String inputText = txtDaCorreggere.getText();
		if (inputText.isEmpty())
			return;
		
		// Divido il testo usando gli spazi
		StringTokenizer st = new StringTokenizer(inputText, " ");
		while (st.hasMoreTokens()) {
			listaDaCorreggere.add(st.nextToken().trim().toLowerCase());
		}
		
		// Chiamo la funzione per la correzione del testo e calcolo il tempo impiegato per la correzione
		long l1 = System.nanoTime();
		List<RichWord> lista = dizionario.spellCheckText(listaDaCorreggere);
		long l2 = System.nanoTime();

		// Stampo il rich text
		int errori = 0;
		Text richText = new Text("");
		
		for (RichWord r : lista) {
			if (r.isCorretta() == true) {
				richText = new Text(r.getParola() + " ");
			} else {
				richText = new Text(r.getParola() + " ");
				richText.setFill(Color.RED);
				errori++;
			}
			txtCorretto.getChildren().add(richText);
		}
		
		// Aggiorno il contenuto della label.
		if (errori == 1)
			lblErrori.setText("Il testo contiene 1 errore");
		else if (errori > 1) 
			lblErrori.setText("Il testo contiene " + errori + " errori");
		else
			lblErrori.setText("Non ci sono errori");
		
		lblStato.setText("Spell check completato in " + (l2 - l1) / 1E9 + " secondi");
	}

	@FXML
	void initialize() {
		assert lblStato != null : "fx:id=\"lblStato\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert boxLingua != null : "fx:id=\"boxLingua\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert txtDaCorreggere != null : "fx:id=\"txtDaCorreggere\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert txtCorretto != null : "fx:id=\"txtCorretto\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert lblErrori != null : "fx:id=\"lblErrori\" was not injected: check your FXML file 'SpellChecker.fxml'.";

		// Inizializzazione dei componenti
		txtDaCorreggere.setText("Selezionare una lingua");
		txtDaCorreggere.setDisable(true);
		boxLingua.getItems().addAll("English", "Italian");
		
		lblErrori.setText("");
		lblStato.setText("");
	}
}
