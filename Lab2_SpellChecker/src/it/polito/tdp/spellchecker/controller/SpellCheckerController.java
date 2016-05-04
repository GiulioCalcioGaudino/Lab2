package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.ResourceBundle;
import it.polito.tdp.spellchecker.model.*;
import it.polito.tdp.spellchecker.model.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.util.*;

public class SpellCheckerController {

	Dictionary devotoOli;
	List<String> listaDaCorreggere = new LinkedList<String>();
	boolean flag = true;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblTimer;

	@FXML
	private ComboBox<String> cbLanguage;

	@FXML
	private TextArea txtFrase;

	@FXML
	private Button btnSpellCheck;

	@FXML
	private TextFlow txtRisposta;

	@FXML
	private Label lblError;

	@FXML
	private Button btnClearText;

	@FXML
	void doClearText(ActionEvent event) {
		txtFrase.clear();
		// txtRisposta.clear();
		txtRisposta.getChildren().clear();
		listaDaCorreggere.clear();
		lblError.setText("");
		lblTimer.setText("");
	}

	@FXML
	void doActivation(ActionEvent event) {

		if (cbLanguage.getValue() != null) {

			txtFrase.setDisable(false);

			// txtRisposta.clear();
		}
	}

	@FXML
	void doSpellCheck(ActionEvent event) {

		// txtRisposta.clear();
		txtRisposta.getChildren().clear();
		listaDaCorreggere.clear();
		flag = true;

		if (cbLanguage.getValue() == null) {
			lblError.setText("Seleziona una lingua");
			return;
		}

		String lingua = (String) cbLanguage.getValue();

		if (lingua.compareTo("Italian") == 0) {
			devotoOli = new ItalianDictionary();
		} else {
			devotoOli = new EnglishDictionary();
		}

		devotoOli.loadDictionary();

		String inputText = txtFrase.getText();
		if (inputText.isEmpty())
			return;

		StringTokenizer st = new StringTokenizer(inputText, " ");
		while (st.hasMoreTokens()) {
			listaDaCorreggere.add(st.nextToken().trim().toLowerCase());
		}

		for (int j = 0; j < listaDaCorreggere.size(); j++) {
			//char[] parola = listaDaCorreggere.get(j).toCharArray();
			String nuovaStringa = listaDaCorreggere.get(j).replaceAll("[^a-z0-9\'אטילעש]+","");
			listaDaCorreggere.set(j, nuovaStringa);
//			for (int i = 0; i < parola.length; i++) {
//				if (!Character.isLetter(parola[i]) && Character.compare(parola[i], '\'') != 0) {
//					char[] parolaFin = Arrays.copyOfRange(parola, 0, i);
//					String nuovaStringa = new String(parolaFin);
//					listaDaCorreggere.set(j, nuovaStringa);
//					break;
//				}
//			}
		}

		long l1 = System.nanoTime();
		List<RichWord> paroleErrate = devotoOli.spellCheckText(listaDaCorreggere);
		long l2 = System.nanoTime();

		/*
		 * String result = ""; for(RichWord rw : paroleErrate){ if
		 * (!rw.isIscorretta()) result += rw.getParola() + " "; }
		 */

		Text richText = new Text("");

		for (RichWord r : paroleErrate) {
			if (r.isIscorretta() == true) {
				richText = new Text(r.getParola() + " ");
			} else {
				richText = new Text(r.getParola() + " ");
				richText.setFill(Color.RED);
				flag = false;
			}
			txtRisposta.getChildren().add(richText);
		}
		if (flag) {
			lblError.setText("Non ci sono errori");
			lblError.setTextFill(Color.BLACK);
		} else {
			lblError.setText("Il testo contiene errori!");
			lblError.setTextFill(Color.RED);
		}

		/*
		 * txtRisposta.setText(result);
		 * 
		 * if (txtRisposta.getText().compareTo("") != 0) { lblError.setText(
		 * "Il testo contiene errori!"); lblError.setTextFill(Color.RED); } else
		 * {lblError.setText("Non ci sono errori");
		 * lblError.setTextFill(Color.BLACK);}
		 */

		lblTimer.setText("Spell check completato in " + (l2 - l1) / 1E9 + " secondi");
	}

	@FXML
	void initialize() {
		assert lblTimer != null : "fx:id=\"lblTimer\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert cbLanguage != null : "fx:id=\"cbLanguage\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert txtFrase != null : "fx:id=\"txtFrase\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert txtRisposta != null : "fx:id=\"txtRisposta\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert lblError != null : "fx:id=\"lblError\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";

		// txtRisposta.setText("Selezionare una lingua");
		// txtRisposta.setDisable(true);

		cbLanguage.getItems().addAll("English", "Italian");

		lblError.setText("");
		lblTimer.setText("");

	}
}
