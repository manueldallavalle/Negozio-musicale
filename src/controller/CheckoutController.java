package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import model.*;
import view.*;

public class CheckoutController {
	private NegozioModel model;
	private Checkout view;
	private boolean titolareScuola;

	public CheckoutController(NegozioModel model, Checkout view) {
		this.model = model;
		this.view = view;
		aggiornaCarrello();
	}

	private void aggiornaCarrello(){
		view.aggiungiListener(new TornaAlMenu(), 1);
		view.aggiungiListener(new VisualizzaSpedizione(), 2);
		
		int yElemento = 10;
		
		Iterator<Strumento> itr = Carrello.getStrumenti().iterator();
		ArrayList<Strumento> list = new ArrayList<Strumento>();
		boolean haElementi = itr.hasNext();
		
		titolareScuola = model.getUtenteGlobale().getTipoCliente().equals("Titolare scuola di musica");
		
		int cont = 0;
		float pesoTot = 0;
		
	    while (itr.hasNext()) {
	    	Strumento tmp = itr.next();
	    	if(!list.contains(tmp)){
	    		list.add(tmp);
	    		int pQuant = Carrello.getNStrumenti(tmp);

	    		JLabel lblImg = new JLabel();
	    		if(tmp.getImmagini().size() > 0)
	    			lblImg.setIcon(new ImageIconProxy(tmp.getImmagini().get(0), lblImg, 88, 68));
	    		else
	    			lblImg.setIcon(Funzioni.ridimensionaImg(new ImageIcon("img/no_immagine.png"),88,68));
				lblImg.setBounds(10, yElemento, 88, 68);
				view.aggiungiLabel(lblImg);
				
				JLabel lblNome = new JLabel(diviviStr(tmp.getNome()));
				lblNome.setBounds(129, yElemento, 153, 68);
				view.aggiungiLabel(lblNome);
				
				JTextField txtQuant = new JTextField(String.format("%d", pQuant));
				txtQuant.setBounds(318, yElemento+15, 40, 32);
				view.aggiungiTextField(txtQuant);
				txtQuant.setColumns(10);
				view.aggiungiMappaTxt(cont, txtQuant);
				
				JButton aBtn = new JButton();
				aBtn.setName("Abtn" + cont);
				aBtn.setBorderPainted(false);
				aBtn.setIcon(new ImageIcon("img/aggiornaCarrello.png"));
				aBtn.setBounds(360, yElemento+15, 40, 34);
				aBtn.addActionListener(new GestioneCarrello());
				aBtn.setBorder(null);
				aBtn.setBackground(Color.WHITE);
				view.aggiungiBtn(aBtn);
			
				JButton rBtn = new JButton();
				rBtn.setName("Rbtn" + cont);
				rBtn.setBorderPainted(false);
				rBtn.setIcon(new ImageIcon("img/togliCarrello.png"));
				rBtn.setBounds(390, yElemento+15, 40, 34);
				rBtn.addActionListener(new GestioneCarrello());
				rBtn.setBorder(null);
				rBtn.setBackground(Color.WHITE);
				view.aggiungiBtn(rBtn);
								
				if(titolareScuola && pQuant < tmp.getPezziSconto()){
					JLabel icoInfo = new JLabel();
			        icoInfo.setBounds(470, yElemento+26, 16, 16);
			        icoInfo.setIcon(new ImageIcon("img/info.png"));
			        icoInfo.setToolTipText(String.format("Acquistando almeno %d pezzi di questo prodotto, riceverai uno sconto del %.2f", tmp.getPezziSconto(), tmp.getSconto()) + "%");
			        view.aggiungiLabel(icoInfo);
				}
						
				JLabel lblTot = new JLabel();
				if(titolareScuola && pQuant >= tmp.getPezziSconto()){
					lblTot.setText(String.format("%.2f \u20ac", ((pQuant * tmp.getPrezzo()) - ((pQuant * tmp.getPrezzo()) * tmp.getSconto())/100)));
					JLabel icoInfo = new JLabel();
			        icoInfo.setBounds(470, yElemento+26, 16, 16);
			        icoInfo.setIcon(new ImageIcon("img/tickCircolare.png"));
			        icoInfo.setToolTipText(String.format("Grazie alla quantit\u00E0 selezionata stai usufrendo di uno sconto del %.2f ", tmp.getSconto()) + "%");
			        view.aggiungiLabel(icoInfo);
				}else{
					lblTot.setText(String.format("%.2f \u20ac", pQuant*tmp.getPrezzo()));
				}
				
				lblTot.setBounds(493, yElemento+26, 85, 16);
				view.aggiungiLabel(lblTot);
				
				JSeparator separator = new JSeparator();
				separator.setBounds(0, yElemento+75, 610, 16);
				view.aggiungiSeparator(separator);
				
				pesoTot += pQuant*tmp.getPeso();
				yElemento += 99;
	    	}
	    	cont++;
	    }
	    
        if(haElementi){
        	view.impostaPosizioniCarrello(1, 520, (yElemento + 100 <= 340) ? yElemento + 100 : 340, 61, 16);
        	view.impostaPosizioniCarrello(2, 570, (yElemento + 100 <= 340) ? yElemento + 100 : 340, 120, 16);
        	view.impostaTxtPrezzoTot(String.format("%.2f \u20ac", Carrello.getPrezzoTotale(titolareScuola)));
        	
        	view.impostaPosizioniCarrello(3, 22, (yElemento + 95 <= 335) ? yElemento + 95 : 335, 195, 29);
            view.impostaPosizioniCarrello(4, 229, (yElemento + 95 <= 335) ? yElemento + 95 : 335, 195, 29);
            view.cambiaConfermaOrdine(true);
        	
            view.impostaPosizioniCarrello(5, 25, 102, 610, (yElemento - 10 <= 223) ? yElemento - 10 : 231);
            view.impostaPesoTot(String.format("CARRELLO DELLA SPESA (%.2f KG)", pesoTot/1000));
 
            view.cambiaVisualeCheckout(1, yElemento); // CON ELEMENTI
            model.aggiornaGrafica();
        }else{
        	view.cambiaConfermaOrdine(false);
        	view.mostraLblVuoto();
        	view.impostaPesoTot("CARRELLO DELLA SPESA (0.00 KG)");
        	view.impostaTxtPrezzoTot(String.format("%.2f \u20ac", 0.00));
        	view.impostaPosizioniCarrello(1, 520, yElemento + 180, 61, 16);
        	view.impostaPosizioniCarrello(2, 570, yElemento + 180, 120, 16);
        	view.impostaPosizioniCarrello(3, 22, yElemento + 173, 195, 29);
        	view.cambiaVisualeCheckout(2, yElemento); // NO ELEMENTI
        	model.aggiornaGrafica();
        }
    }
	
	private String diviviStr(String str){
		int lunghezza = str.length();
		String fin = str;
		if(lunghezza > 25) fin = str.substring(0, 25) + "<br />" + str.substring(15, str.length());
		if(lunghezza > 45) fin = fin.substring(0, 45) + "<br />" + fin.substring(45, fin.length());
		return ("<html>" + fin + "</html>");
	}
	
    class GestioneCarrello implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String nomeBtn = ((JButton) e.getSource()).getName();
			int indexArray = Integer.parseInt(nomeBtn.split("btn")[1]);
			if(nomeBtn.charAt(0) == 'R'){
				Carrello.rimuoviStrumento(indexArray);
			}else{
				if(!view.getValTextFieldMappa(indexArray).isEmpty() && Funzioni.isInteger(view.getValTextFieldMappa(indexArray))){
					Carrello.aggiornaQuantita(Integer.parseInt(view.getValTextFieldMappa(indexArray)), indexArray);
				}
			}
			view.pulisciCarrello();
			aggiornaCarrello();
			view.ricaricaCarrello();
        }
    }
	
    class VisualizzaCarrello implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.cambiaPannello(1);
			aggiornaCarrello();
			model.aggiornaGrafica();
        }
    }
    
    class VisualizzaSpedizione implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.cambiaPannello(2);
			view.aggiungiListener(new VisualizzaCarrello(), 3);
			view.aggiungiListener(new GestioneCorrieri(), 4);
			view.aggiungiListener(new VisualizzaPagamento(), 5);
			
			boolean utenteSpeciale = false;
    		List<HashMap<String,Object>> checkUtenteTremila = DBManager.selectQuery(EnumQuery.CHECK_CLIENTE_SPESO_3000.getValore(),
    				model.getUtenteGlobale().getUsername());
    		utenteSpeciale = (checkUtenteTremila.size() >= 3) ? true : false;
			view.setUtenteSpeciale(utenteSpeciale);
			
			titolareScuola = model.getUtenteGlobale().getTipoCliente().equals("Titolare scuola di musica");
			
		/**	
			if(!model.getUtenteGlobale().getTipoCliente().equals("Occasionale")){
	        	view.impostaLblSubTotale("Subtotale (scontato):");
	        	view.impostaLblSpedizTratt("Spedizione e trattamento:");
	        }
			*/
	       // if(model.getUtenteGlobale().getTipoCliente().equals("Occasionale"))
	        	view.impostaTxtSubTotale(String.format("%.2f \u20ac", Carrello.getPrezzoTotale(titolareScuola)));
	       // else
	       // 	view.impostaTxtSubTotale(String.format("%.2f", Carrello.getPrezzoTotaleScontato()));
	        
	        view.impostaTxtPrezzoSpedizione((utenteSpeciale) ? String.format("<html><strike>%.2f \u20ac</strike></html>", view.getSpeseSpedizione()) :String.format("%.2f \u20ac", view.getSpeseSpedizione()));
	        
			//if(model.getUtenteGlobale().getTipoCliente().equals("Occasionale"))
	        	view.impostaSpedizionePrezzoTot(String.format("%.2f \u20ac", (utenteSpeciale) ? Carrello.getPrezzoTotale(titolareScuola) : Carrello.getPrezzoTotale(titolareScuola) + view.getSpeseSpedizione()));
	        //else*
	        //	view.impostaSpedizionePrezzoTot(String.format("%.2f \u20ac", (utenteSpeciale) ? Carrello.getPrezzoTotaleScontato() : Carrello.getPrezzoTotaleScontato() + view.getSpeseSpedizione()));
	        
	        if(utenteSpeciale) view.cambioInfoSpedizione(true);
	        
	        model.aggiornaGrafica();
        }
    }
    
    class VisualizzaPagamento implements ActionListener {
    	private boolean controllaCampiSpedizione(){
			boolean indirizzoV = view.getTxtIndirizzo().isEmpty();
			boolean civicoV = !Funzioni.isInteger(view.getTxtNIndirizzo()) || view.getTxtNIndirizzo().isEmpty();
			boolean cittaV = view.getTxtCitta().isEmpty();
			boolean capV = !Funzioni.isInteger(view.getTxtCap()) || view.getTxtCap().isEmpty() || view.getTxtCap().length() < 5;
	
			view.setIcoSpedizione(1, indirizzoV || civicoV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
			view.setIcoSpedizione(2, cittaV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
			view.setIcoSpedizione(3, capV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
			return !(indirizzoV || civicoV || cittaV || capV);
    	}
    	    	
    	public void actionPerformed(ActionEvent e) {
    		if(controllaCampiSpedizione()){
    			view.setIndirizzo(view.getTxtIndirizzo(), view.getTxtCitta(), view.getTxtNIndirizzo(), view.getTxtCap());
    			view.cambiaPannello(3);
    			model.aggiornaGrafica();
    			view.aggiungiListener(new VisualizzaSpedizione(), 6);
    			view.aggiungiListener(new GestionePagamenti(), 7);
    			view.aggiungiListener(new EffettuaPagamento(), 8);
    		}
    	}
    }
    
    class EffettuaPagamento implements ActionListener {
    	private boolean controllaCampiPagamento(){
    		if(view.getTipoPagamento().equals("Carta di credito")){
    			boolean intestarioV = view.getIntestarioCarta().isEmpty();
    			boolean cartaUnoV = !Funzioni.isInteger(view.getCodiceCartaUno()) || view.getCodiceCartaUno().length() < 4;
    			boolean cartaDueV = !Funzioni.isInteger(view.getCodiceCartaDue()) || view.getCodiceCartaDue().length() < 4;
    			boolean cartaTreV = !Funzioni.isInteger(view.getCodiceCartaTre()) || view.getCodiceCartaTre().length() < 4;
    			boolean cartaQuattroV = !Funzioni.isInteger(view.getCodiceCartaQuattro()) || view.getCodiceCartaQuattro().length() < 4;
    			boolean cvvV = !Funzioni.isInteger(view.getCodiceCartaCVV()) || view.getCodiceCartaCVV().length() < 3;
    			
    			view.setIcoPagamento(1, intestarioV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
    			view.setIcoPagamento(2, cartaUnoV || cartaDueV || cartaTreV || cartaQuattroV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
    			view.setIcoPagamento(3, cvvV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
    			
    			return !(intestarioV || cartaUnoV || cartaDueV || cartaTreV || cartaQuattroV || cvvV);
    		}else if(view.getTipoPagamento().equals("Bonifico")){
    			boolean ricevutaV = view.getRicevutaBonifico().isEmpty();
    			view.setIcoPagamento(4, ricevutaV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
    			return !ricevutaV;
    		}
    		
    		return true;
    	}
    	
    	private void venditaTerminata(){
    		Iterator<Strumento> itr = Carrello.getStrumenti().iterator();
    		ArrayList<Strumento> list = new ArrayList<Strumento>();
    		HashMap <String, ArrayList<Strumento>> mappa_vendute = new HashMap<String, ArrayList<Strumento>>();
    		
    		// AGGIUNTA DELL'ACQUISTO NEL DB
    	    while (itr.hasNext()) {
    	    	Strumento tmp = itr.next();
    	    	if(!list.contains(tmp)){
    	    		list.add(tmp);
    	    		int quantita = Carrello.getNStrumenti(tmp);
            		int inserisciDB = DBManager.updateQuery(EnumQuery.INSERISCI_ACQUISTO.getValore(), false,
            				tmp.getCodiceStrumento(),
            				model.getUtenteGlobale().getUsername(),
            				tmp.getPrezzo() * quantita,
            				Funzioni.getCurrTimestamp(),
            				Funzioni.getCurrTimestamp(),
            				model.getUtenteGlobale().getIndirizzoIP(),
            				view.getTipoPagamento(),
            				view.getCorriere(),
            				quantita
            				);
            		if(inserisciDB <= 0){
            			JOptionPane.showMessageDialog(null, "Errore nell'inserimento dell'acquisto nel DB!");
            		}
    	    	}
    	    }
    	    
    	    // INVIO E-MAIL AL VENDITORE
    		Iterator<Strumento> itr_codice = list.iterator();
    	    while (itr_codice.hasNext()) {
    	    	Strumento tmp = itr_codice.next();
    	    		List<HashMap<String,Object>> risultatoQuery = DBManager.selectQuery(EnumQuery.GET_STRUMENTI_DA_CLIENTE.getValore(),
    	    				""+tmp.getCodiceStrumento()); 
    	    		Iterator<HashMap<String,Object>> itr_query = risultatoQuery.iterator();
    	    		while (itr_query.hasNext()) {
    	    	    	HashMap<String,Object> riga = itr_query.next();
    	    	    	ArrayList<Strumento> arrcodicivenduti = mappa_vendute.get(riga.get("email").toString());
    	    	    	if(arrcodicivenduti == null){
    	    	    		arrcodicivenduti = new ArrayList<Strumento>();
    	    	    		mappa_vendute.put(riga.get("email").toString(), arrcodicivenduti);
    	    	    	}
    	    	    	arrcodicivenduti.add(tmp);	    	    		
    	    	    }
    	    }
    	    Iterator<Entry<String, ArrayList<Strumento>>> itr_mappe = mappa_vendute.entrySet().iterator();
    	    while (itr_mappe.hasNext()) {
    	    	Entry<String, ArrayList<Strumento>> el = itr_mappe.next();
    	    	if(!Funzioni.InviaMailVendita(el.getKey(), model.getUtenteGlobale(), el.getValue(), view.getTipoPagamento(), view.getCorriere(), view.getIndirizzoConCivico(), view.getCittaConCap())){
        			JOptionPane.showMessageDialog(null, "Errore nell'invio dell'e-mail al venditore!");
        		}
    	    }
    	    
    	    // SVUOTO CARRELLO
    	    Carrello.svuotaCarrello();
    	    
    	    // FINE PAGAMENTO
    		Timer timer = new Timer();
    		timer.schedule(new TimerTask() {
    			@Override
    			public void run() {
    				JOptionPane.showMessageDialog(null, "Il tuo pagamento \u00E8 stato verificato\nGrazie per aver acquistato presso il nostro negozio!");
    	    	    MenuPrincipale nuovaVista = new MenuPrincipale();
    	    	    model.cambiaInterfaccia(nuovaVista, "Menu principale");
    	    	    @SuppressWarnings("unused")
    				MenuController controller = new MenuController(model, nuovaVista);
    			}
    		}, 2500); //2.5 sec
    	}
    	
    	public void actionPerformed(ActionEvent e) {
    		if(controllaCampiPagamento()){
	    		if(view.getTipoPagamento().equals("Carta di credito")){
	    			view.disabilitaPagamento(2);
				}else if(view.getTipoPagamento().equals("Bonifico")){
					view.disabilitaPagamento(3);
				}else{
					view.disabilitaPagamento(1);
				}
				Thread a = new Thread(() -> { venditaTerminata(); });			
				a.start();
    		}
    	}
    }
    
    class GestioneCorrieri implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			float speseSpedizione = Float.parseFloat(((JRadioButton) e.getSource()).getName().split("#")[0]);
			String tipoCorriere = ((JRadioButton) e.getSource()).getName().split("#")[1];
			titolareScuola = model.getUtenteGlobale().getTipoCliente().equals("Titolare scuola di musica");
			view.setCorriere(speseSpedizione, tipoCorriere);
			view.impostaTxtPrezzoSpedizione((view.isUtenteSpeciale()) ? String.format("<html><strike>%.2f \u20ac</strike></html>", view.getSpeseSpedizione()) :String.format("%.2f \u20ac", view.getSpeseSpedizione()));
	        view.impostaSpedizionePrezzoTot(String.format("%.2f \u20ac", (view.isUtenteSpeciale()) ? Carrello.getPrezzoTotale(titolareScuola) : Carrello.getPrezzoTotale(titolareScuola) + view.getSpeseSpedizione()));
		}
    }
    
    class GestionePagamenti implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String pagamento = (((JRadioButton) e.getSource()).getName());
			view.cambiaPagamento(pagamento);
			view.setTipoPagamento(pagamento);
			model.aggiornaGrafica();
			titolareScuola = model.getUtenteGlobale().getTipoCliente().equals("Titolare scuola di musica");
			if(pagamento.equals("Bonifico")) view.setTotaleBonifico(String.format("%.2f \u20ac", (view.isUtenteSpeciale()) ? Carrello.getPrezzoTotale(titolareScuola) : Carrello.getPrezzoTotale(titolareScuola) + view.getSpeseSpedizione()));
		}
    }
    
    class TornaAlMenu implements ActionListener {
		public void actionPerformed(ActionEvent e) {
    	    MenuPrincipale nuovaVista = new MenuPrincipale();
    	    model.cambiaInterfaccia(nuovaVista, "Menu principale");
    	    @SuppressWarnings("unused")
			MenuController controller = new MenuController(model, nuovaVista);
        }
    }
}