package it.polito.tdp.spellchecker.model;

public class RichWord {

	@Override
	public String toString() {
		return "RichWord [parola=" + parola + ", corretta=" + corretta + "]";
	}

	private String parola;
	private boolean corretta;

	public RichWord(String parola, boolean corretta) {
		this.corretta = corretta;
		this.parola = parola;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public boolean isCorretta() {
		return corretta;
	}

	public void setCorretta(boolean corretta) {
		this.corretta = corretta;
	}

}
