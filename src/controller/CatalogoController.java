package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.ButtonColumn;
import model.Carrello;
import model.Catalogo;
import model.DBManager;
import model.EnumQuery;
import model.NegozioModel;
import model.Strumento;
import view.CatalogoStrumenti;
import view.CatalogoStrumentiSpeciali;
import view.DettagliStrumento;
import view.Login;
import view.MenuPrincipale;

public class CatalogoController{
	private NegozioModel model;
	private Catalogo view;
	private DefaultTableModel dm;
	public final String[] colonneTab = {"Nome", "Prezzo", "" ,"",""};
	
	public CatalogoController(NegozioModel model, Catalogo view) {
		this.model = model;
		this.view = view;
		this.view.aggiungiListener(new BackLogin(),1);
		this.view.aggiungiListener(new RiempiRicercaListener(),2);
		this.view.aggiungiListener(new Back(), 3);
		this.view.aggiungiListener(new Offerte(), 4);
		view.aggiungiMouseListener(new ClickPreviewCarrello());
		view.vistaLoggato(model.getUtenteGlobale() != null);
		if(view.isSpeciale()) view.creaTabStrumentiSPeciali();
		
		if(view.isSpeciale() && model.getUtenteGlobale() != null) view.setVisibilityBtn(false); 
		else if (!view.isSpeciale() && model.getUtenteGlobale() != null) view.setVisibilityBtn(true);
				
		riempiTabella();
		model.aggiornaGrafica();
	} 
	
    private void riempiTabella(){
			List<HashMap<String,Object>> risultatoQuery;
			 if(model.getUtenteGlobale()!= null){
				 if(view.isSpeciale()){
					 risultatoQuery = DBManager.selectQuery(EnumQuery.GET_STRUMENTOSPECIALE.getValore() + " WHERE codice NOT IN (SELECT strumento from venditastrumento WHERE cliente = ?);",
					 model.getUtenteGlobale().getUsername());			 
				 }
				 else{
					 risultatoQuery = DBManager.selectQuery(EnumQuery.GET_STRUMENTO.getValore() + " WHERE codice NOT IN (SELECT strumento from venditastrumento WHERE cliente = ?);",
							 model.getUtenteGlobale().getUsername());
				 }
					
    		}else{
				 if(view.isSpeciale()) risultatoQuery = DBManager.selectQuery(EnumQuery.GET_STRUMENTOSPECIALE.getValore()+";");
				 else risultatoQuery = DBManager.selectQuery(EnumQuery.GET_STRUMENTO.getValore()+";");
			 }
				dm = new DefaultTableModel() {
				private static final long serialVersionUID = 1L;
				@Override
			    public boolean isCellEditable(int row, int column) { 
				    if(column == 2 || column == 3) return true;
		            else return false;	
				}
			};
		    dm.setColumnIdentifiers(colonneTab);
		    
			Iterator<HashMap<String,Object>> itr = risultatoQuery.iterator();
		    while (itr.hasNext()) {
		    	HashMap<String,Object> riga = itr.next();
		    	dm.addRow(new Object[] { riga.get("nome").toString(),riga.get("prezzo").toString()+" \u20ac", "", "", riga.get("codice").toString()});
		    }
		    
		    view.aggiungiContenuti(dm);
		    
	    	new ButtonColumn(view.getTabella(), azioneDtg, 2);
	        new ButtonColumn(view.getTabella(), azioneCrl, 3);
    }
    
    
	public Action azioneCrl = new AbstractAction(){
	    private static final long serialVersionUID = 1L;        	
	    public void actionPerformed(ActionEvent e){
	    	if(!(model.getUtenteGlobale()==null)){
		    	Carrello.aggiungiStrumento(getStrumentoRiga());
		    	JOptionPane.showMessageDialog(null,"Strumento aggiunto al carrello!");
		    	view.notificaCarrello();
	    	}
	    	else{
	    		JOptionPane.showMessageDialog(null,"Devi essere registrato");
	    	}
	    }
	};	
	
	
	public Action azioneDtg = new AbstractAction(){
	    private static final long serialVersionUID = 1L;        	
	    public void actionPerformed(ActionEvent e){
	    	DettagliStrumento view = new DettagliStrumento(getStrumentoRiga(), false);
			@SuppressWarnings("unused")
			DettagliController controller = new DettagliController(model,view);
			model.cambiaInterfaccia(view, "Dettagli Strumento");
	    }
	};
	
	public Strumento getStrumentoRiga(){
	   	 String codice = view.getCodiceRiga();
	   	
	   	 Strumento strumento = null;	
	   	 List<HashMap<String,Object>> risultatoQuery;
	   	 
	     if(view.isSpeciale()){
	    	 risultatoQuery = DBManager.selectQuery(EnumQuery.GET_DETTAGLI_STRUMENTISPECIALE.getValore(), 
						codice);
	     }else{
	    	 risultatoQuery = DBManager.selectQuery(EnumQuery.GET_DETTAGLI_STRUMENTI.getValore(), 
						codice);
	     }

		Iterator<HashMap<String,Object>> itr = risultatoQuery.iterator();
		
	    while (itr.hasNext()) {
	    	HashMap<String,Object> riga = itr.next();
			 strumento = new Strumento(Integer.parseInt(riga.get("codice").toString()),
		 				  riga.get("categoria").toString(),
		 				  riga.get("nome").toString(),
		 				  (riga.get("prezzo").toString().isEmpty()) ? 0 :Float.parseFloat(riga.get("prezzo").toString()),
		 				  riga.get("datapresenzasito").toString(),
		 				  (riga.get("peso").toString().isEmpty()) ? 0 :Float.parseFloat(riga.get("peso").toString()),
		 				  riga.get("descrizione").toString(),
		 				  (riga.get("tipo").toString().isEmpty()) ? 0 :Integer.parseInt(riga.get("tipo").toString()),
		 				  (riga.get("sconto").toString().isEmpty()) ? 0 :Float.parseFloat(riga.get("sconto").toString()),
		 				  riga.get("livello").toString(),
		 				  Boolean.getBoolean(riga.get("usato").toString()),
		 				  Integer.parseInt(riga.get("pezzisconto").toString()));
	    }
	   	 
	   	List<HashMap<String,Object>> risultatoImg = DBManager.selectQuery(EnumQuery.GET_IMMAGINI_STRUMENTO.getValore(), 
					codice);

		Iterator<HashMap<String,Object>> itrImg = risultatoImg.iterator();
	    while (itrImg.hasNext()) {
	    	HashMap<String,Object> riga = itrImg.next();
			try {
			 strumento.aggiungiImg(new URL("http://musicaunivr.netsons.org/imgStrumenti/" +riga.get("link").toString()));
			} catch (MalformedURLException e) {}
	    }

	   	return strumento;
	}
    
    class ClickPreviewCarrello implements MouseListener {
    	public void mouseEntered(MouseEvent e){}
    	public void mouseExited(MouseEvent e) {}
    	public void mouseClicked(MouseEvent e) {
			boolean isPreviewChiusa = view.getStatoPreview();
			view.cambiaStatoPreviewCarrello(isPreviewChiusa);
			if(isPreviewChiusa){
				view.impostaTestoPreview("\u25be");
			}else{
				view.impostaTestoPreview("\u25b8");
			}
    	}
    	public void mousePressed(MouseEvent e) {}
    	public void mouseReleased(MouseEvent e) {}
    }
   
    class BackLogin implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Login nuovaVista = new Login();
    	    model.cambiaInterfaccia(nuovaVista, "Login");
    	    @SuppressWarnings("unused")
			LoginController controller = new LoginController(model, nuovaVista);
        }
    }
    
    class Back implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MenuPrincipale nuovaVista = new MenuPrincipale();
    	    model.cambiaInterfaccia(nuovaVista, "Menu Principale");
    	    @SuppressWarnings("unused")
			MenuController controller = new MenuController(model, nuovaVista);
        }
    }
    
    class Offerte implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			 CatalogoStrumentiSpeciali nuovaVista = new CatalogoStrumentiSpeciali(new CatalogoStrumenti());
			 model.cambiaInterfaccia(nuovaVista, "Strumenti Speciali");
		     @SuppressWarnings("unused")
	         CatalogoController controller = new CatalogoController(model, nuovaVista);
		     
		}
    }

	class RiempiRicercaListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			List<HashMap<String,Object>> risultatoQuery = null;
			if(view.getComboBoxString().equals("Categoria")){
				 if(model.getUtenteGlobale()!= null) risultatoQuery = DBManager.selectQuery(EnumQuery.RICERCA_STRUMENTO_CATEGORIA.getValore() + " AND codice NOT IN (SELECT strumento from venditastrumento WHERE cliente = ?);",
						 view.getRicerca(),	
						  model.getUtenteGlobale().getUsername());
					 else risultatoQuery = DBManager.selectQuery(EnumQuery.RICERCA_STRUMENTO_CATEGORIA.getValore()+";",
							 view.getRicerca());
			}else if(view.getComboBoxString().equals("Prezzo")){
				if(model.getUtenteGlobale()!= null) risultatoQuery = DBManager.selectQuery(EnumQuery.RICERCA_STRUMENTO_PREZZO.getValore() + " AND codice NOT IN (SELECT strumento from venditastrumento WHERE cliente = ?);",
						view.getRicerca(),	
						  model.getUtenteGlobale().getUsername());
					 else risultatoQuery = DBManager.selectQuery(EnumQuery.RICERCA_STRUMENTO_PREZZO.getValore()+";",
							 view.getRicerca());
			}else if(view.getComboBoxString().equals("Modello")){
					int tipo=1;
					if(view.getRicerca().equals("Professionale") || view.getRicerca().equals("Strumento Professionale")) tipo=1;
					else if(view.getRicerca().equals("Scuola di musica")) tipo=2;
					if(model.getUtenteGlobale()!= null) risultatoQuery = DBManager.selectQuery(EnumQuery.RICERCA_STRUMENTO_MODELLO.getValore() + " AND codice NOT IN (SELECT strumento from venditastrumento WHERE cliente = ?);",
							  ""+tipo,	
							  model.getUtenteGlobale().getUsername());
						 else risultatoQuery = DBManager.selectQuery(EnumQuery.RICERCA_STRUMENTO_MODELLO.getValore()+";",
								 ""+tipo);	
			}
			if(risultatoQuery.isEmpty()){
				JOptionPane.showMessageDialog(null, "Nessuno strumento trovato");
				riempiTabella();
			}else{
				dm = new DefaultTableModel() {
					private static final long serialVersionUID = 1L;
					@Override
				    public boolean isCellEditable(int row, int column) { 
					    if(column == 2 || column == 3) return true;
			            else return false;	
					}
				};
				//rimuovo elementi da tabella
				while (dm.getRowCount() > 0) dm.removeRow(0);
				
				//inserisco elementi ottenuti da ricerca
				dm.setColumnIdentifiers(colonneTab);
				
				Iterator<HashMap<String,Object>> itr = risultatoQuery.iterator();
			    while (itr.hasNext()) {
			    	HashMap<String,Object> riga = itr.next();
			    	dm.addRow(new Object[] { riga.get("nome").toString(),riga.get("prezzo").toString()+" \u20ac", "", "", riga.get("codice").toString()});
			    }
	            
	            view.aggiungiContenuti(dm);
	         }
		}
	}
}

