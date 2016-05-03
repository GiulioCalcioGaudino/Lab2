package it.polito.tdp.spellchecker.model;

public class RichWord {

	private String parola;
	private boolean iscorretta;
	
	
	public RichWord(String parola) {
		this.parola = parola;
		this.iscorretta = true;
	}
	
	
	public String getParola() {
		return parola;
	}
	public void setParola(String parola) {
		this.parola = parola;
	}
	public boolean isIscorretta() {
		return iscorretta;
	}
	public void setIscorretta(boolean iscorretta) {
		this.iscorretta = iscorretta;
	}
	public String toString() {
		return "RichWord [parola=" + parola + ", corretta=" + iscorretta + "]";
	}
	
	
}
