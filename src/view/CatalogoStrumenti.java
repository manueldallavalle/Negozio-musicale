package view;
import javax.swing.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import controller.PreviewCarrelloController;
import model.Catalogo;

public class CatalogoStrumenti extends JPanel implements Catalogo{
	private static final long serialVersionUID = 1L;
	public JTable tabellaStrumenti;
	private JTextField txtRicerca;
	private JButton btnSearch;
	private JComboBox<String> comboBoxRicerca;
	private JScrollPane scrollPane;
	public final String[] colonneTab = {"Nome", "Prezzo", "" ,"",""};
	private JButton btnAnnulla;
	private JLabel apriCarrello;
	private JButton btnLogin;
	private PreviewCarrello pannelloCarrello;
	private JButton btnOfferteSpeciali;
	private JLabel icoCarrello;
	private PreviewCarrelloController controllerCarrello;
		
	public CatalogoStrumenti() {
		this.setLayout(null);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 71, 350, 253);
		
		tabellaStrumenti = new JTable(new String[0][0],colonneTab);
		scrollPane.setViewportView(tabellaStrumenti);
		tabellaStrumenti.setShowGrid(true);
		tabellaStrumenti.setGridColor(Color.GRAY);
		tabellaStrumenti.setShowHorizontalLines(true);
		tabellaStrumenti.setShowVerticalLines(true);
		tabellaStrumenti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		icoCarrello = new JLabel();
		icoCarrello.setIcon(new ImageIcon("img/carrelloHome.png"));
		icoCarrello.setBounds(322, 15, 45, 34);
		icoCarrello.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(icoCarrello);
		
		apriCarrello = new JLabel("\u25b8");
		apriCarrello.setBounds(360, 27, 17, 16);
		apriCarrello.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(apriCarrello);
		
		pannelloCarrello = new PreviewCarrello();
		pannelloCarrello.setBounds(112, 52, 258, 245);
		pannelloCarrello.setVisible(false);
		controllerCarrello = new PreviewCarrelloController(pannelloCarrello);
		add(pannelloCarrello);
		
		this.add(scrollPane);

		//parte Ricerca
		
		impostaDimensioni(388, 430);
		
		txtRicerca = new JTextField();
		txtRicerca.setBounds(18, 25, 93, 20);
		add(txtRicerca);
		txtRicerca.setColumns(10);
		
		btnSearch = new JButton();
		btnSearch.setIcon(new ImageIcon("img/ricerca.png"));
		btnSearch.setBounds(275, 26, 20, 20);
		btnSearch.setBorder(null);
		
		btnSearch.setOpaque(false);
		btnSearch.setContentAreaFilled(false);
		btnSearch.setBorderPainted(true);
		add(btnSearch);
		
		final String[] tipoRicerca = {
			"Categoria", "Prezzo", "Modello"
		};
		
		comboBoxRicerca = new JComboBox<String>();
		comboBoxRicerca.setBounds(123, 26, 148, 20);
		comboBoxRicerca.setModel(new DefaultComboBoxModel<String>(tipoRicerca));
		add(comboBoxRicerca);
		
		btnOfferteSpeciali=new JButton("Offerte solo per te");
		btnOfferteSpeciali.setBounds(18, 336, 350, 29);
		btnOfferteSpeciali.setVisible(false);
		btnAnnulla = new JButton("Indietro");
		btnAnnulla.setBounds(18, 370, 350, 29);
		btnOfferteSpeciali.setVisible(false);
		btnAnnulla.setVisible(false);
		add(btnOfferteSpeciali);
		add(btnAnnulla);
		
		
		btnLogin=new JButton();
		btnLogin.setBounds(6, 327, 360, 50);
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		Border emptyBorder = BorderFactory.createEmptyBorder();
		btnLogin.setBorder(emptyBorder);
		btnLogin.setContentAreaFilled(false); 
		btnLogin.setFocusPainted(false); 
		btnLogin.setOpaque(false);
		btnLogin.setIcon(new ImageIcon("img/login.png"));
		btnLogin.setToolTipText("Login & Registrazione");
		btnLogin.setVisible(false);
		add(btnLogin);
	}
	
	@Override
	public void notificaCarrello(){
		controllerCarrello.notificaCarrello();
	}	
	
	@Override
	public void vistaLoggato(boolean val){
		if(val){
			btnAnnulla.setVisible(true);
		}else{
			btnLogin.setVisible(true);
			impostaDimensioni(388, 380);
		}
	}
	
	@Override
	public void aggiungiContenuti(DefaultTableModel dm){
		tabellaStrumenti.setModel(dm);
		tabellaStrumenti.removeColumn(tabellaStrumenti.getColumnModel().getColumn(4));	// TOGLI COLONNA ID
		tabellaStrumenti.getColumnModel().getColumn(2).setPreferredWidth(16);
		tabellaStrumenti.getColumnModel().getColumn(3).setPreferredWidth(16);
		tabellaStrumenti.setRowHeight(30);
	}
	
   @Override
	public void impostaDimensioni(int x, int y){
		this.setPreferredSize(new Dimension(x, y));
	}
	
   @Override
	public String getComboBoxString(){
		return comboBoxRicerca.getSelectedItem().toString();
	}
	
   @Override
	public String getRicerca(){
		return txtRicerca.getText();
	}
	
   @Override
	public JTable getTabella(){
		return tabellaStrumenti;
	}
	
   @Override
	public void aggiungiListener(ActionListener lst, int tipo){
		switch(tipo){
			case 1: btnLogin.addActionListener(lst);
					break;
			case 2: btnSearch.addActionListener(lst);
					break;
			case 3: btnAnnulla.addActionListener(lst);
					break;
			case 4: btnOfferteSpeciali.addActionListener(lst);
					break;
		}
    } 
	
   @Override
    public void aggiungiMouseListener(MouseListener lst){
    	icoCarrello.addMouseListener(lst);
    }
	
   @Override
	public void impostaTestoPreview(String str){
		apriCarrello.setText(str);
	}

   @Override
	public String getCodiceRiga(){
		return tabellaStrumenti.getModel().getValueAt(tabellaStrumenti.getSelectedRow(),4).toString();
	}
	
   @Override
	public void cambiaStatoPreviewCarrello(boolean val){
		pannelloCarrello.setVisible(val);
	}
	
   @Override
	public boolean getStatoPreview(){
		return (apriCarrello.getText().indexOf("\u25b8") > -1);
	}
   
   @Override
	public void creaTabStrumentiSPeciali() {}
   
   @Override
	public void contaStrumenti() {}

	@Override
	public boolean isSpeciale() {
		return false;
	}
	
	@Override
	public void setVisibilityBtn(boolean state){
		btnOfferteSpeciali.setVisible(state);
	}
}
