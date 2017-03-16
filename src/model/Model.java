package model;

import javax.swing.JPanel;

public interface Model {
	public Utente getUtenteGlobale();
	public void setUtenteGlobale(Utente ut);
	public void cambiaInterfaccia(JPanel pan, String titolo);
}