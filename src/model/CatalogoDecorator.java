package model;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CatalogoDecorator extends JPanel implements Catalogo {
	private static final long serialVersionUID = 1L;
	protected Catalogo catalogo;

	public CatalogoDecorator(Catalogo cat){
		this.catalogo = cat;
	}
	
	@Override
	public void notificaCarrello(){
		this.catalogo.notificaCarrello();
	}
	
	@Override
	public void vistaLoggato(boolean val) {
		this.catalogo.vistaLoggato(val);
	}

	@Override
	public void aggiungiContenuti(DefaultTableModel dm) {
		this.catalogo.aggiungiContenuti(dm);
	}

	@Override
	public void impostaDimensioni(int x, int y) {
		this.catalogo.impostaDimensioni(x, y);
	}

	@Override
	public String getComboBoxString() {
		return this.catalogo.getComboBoxString();
	}

	@Override
	public String getRicerca() {
		return this.catalogo.getRicerca();
	}

	@Override
	public JTable getTabella() {
		return this.catalogo.getTabella();
	}

	@Override
	public void aggiungiListener(ActionListener lst, int tipo) {
		this.catalogo.aggiungiListener(lst,tipo);		
	}

	@Override
	public void aggiungiMouseListener(MouseListener lst) {
		this.catalogo.aggiungiMouseListener(lst);
	}

	@Override
	public void impostaTestoPreview(String str) {
		this.catalogo.impostaTestoPreview(str);		
	}

	@Override
	public String getCodiceRiga() {
		return this.catalogo.getCodiceRiga();
	}

	@Override
	public void cambiaStatoPreviewCarrello(boolean val) {
		this.catalogo.cambiaStatoPreviewCarrello(val);
		
	}
	
	@Override
	public boolean getStatoPreview() {
		return this.catalogo.getStatoPreview();
	}

	@Override
	public void creaTabStrumentiSPeciali() {
		this.catalogo.creaTabStrumentiSPeciali();
	}

	@Override
	public void contaStrumenti() {
		this.catalogo.contaStrumenti();
	}

	@Override
	public boolean isSpeciale() {
		return this.catalogo.isSpeciale();
	}
	
	@Override
	public void setVisibilityBtn(boolean state) {
		this.catalogo.setVisibilityBtn(state);
	}	
}