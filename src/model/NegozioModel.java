package model;

import javax.swing.JPanel;

import view.Pannello;

public class NegozioModel implements Model{
	private Pannello framePrincipale;
	private Utente utenteGlobale;
	
	public NegozioModel(){
		this.framePrincipale = new Pannello(this);
	}
	
	public Utente getUtenteGlobale() {
		return utenteGlobale;
	}

	public void setUtenteGlobale(Utente ut) {
		this.utenteGlobale = ut;
	}

	public void cambiaInterfaccia(JPanel pan, String titolo) {
		this.framePrincipale.getContentPane().removeAll();
		this.framePrincipale.getContentPane().add(pan);
		this.framePrincipale.setTitle(titolo);
		this.framePrincipale.pack();
	}
	
	public void chiudiConnessione(){
		DBManager.chiudiConnessione(DBManager.getConnessione());
	}
	
	public void aggiornaGrafica(){
		this.framePrincipale.pack();
		this.framePrincipale.repaint();
		this.framePrincipale.revalidate();
	}
}