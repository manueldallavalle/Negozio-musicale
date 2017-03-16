package model;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public interface Catalogo{
	public void vistaLoggato(boolean val);
	public void aggiungiContenuti(DefaultTableModel dm);
	public void impostaDimensioni(int x, int y);
	public String getComboBoxString();
	public String getRicerca();
	public JTable getTabella();
	public void aggiungiListener(ActionListener lst, int tipo);
	public void aggiungiMouseListener(MouseListener lst);
	public void impostaTestoPreview(String str);
	public String getCodiceRiga();
	public void cambiaStatoPreviewCarrello(boolean val);
	public boolean getStatoPreview();
	public void creaTabStrumentiSPeciali() ;
	public void contaStrumenti();
	public boolean isSpeciale();
	public void notificaCarrello();
	public void setVisibilityBtn(boolean state);
}
