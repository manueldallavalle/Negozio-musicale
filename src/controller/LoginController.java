package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import model.*;
import view.*;

public class LoginController{
	private NegozioModel model;
	private Login view;
	
	public LoginController(NegozioModel model, Login view) {
		this.model = model;
		this.view = view;
		this.view.aggiungiListener(new LoginListener(),1);
		this.view.aggiungiListener(new RegistraListener(),2);
	} 
	
    class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
            if (DBManager.CheckEsiste(
            		EnumQuery.ACCESSO_LOGIN.getValore(),
            		view.getNomeUtente(),
            		Funzioni.strToMD5(view.getPassword())
            	)){
        		List<HashMap<String,Object>> getInfoCliente = DBManager.selectQuery(
        				EnumQuery.GET_CLIENTE.getValore(), view.getNomeUtente());
        		
        		Iterator<HashMap<String,Object>> itr = getInfoCliente.iterator();

        	    while (itr.hasNext()) {
        	    	HashMap<String,Object> riga = itr.next();
        	    	String nomeUtente = riga.get("nomeutente").toString();
        	    	String cf = riga.get("cf").toString();
        	    	String nome = riga.get("nome").toString();
        	    	String cognome = riga.get("cognome").toString();
        	    	String email = riga.get("email").toString();
        	    	String ntelefono = riga.get("ntelefono").toString();
        	    	String ncellulare = riga.get("ncellulare").toString();
        	    	String ip = Funzioni.getIndirizzoIP();
        	    	
        	    	if(riga.get("tipo").toString().equals("Occasionale")){
        	    		model.setUtenteGlobale(new UtenteOccasionale(new UtenteSemplice(nomeUtente, cf, nome, cognome, email, ntelefono, ncellulare, ip)));
        	    	}else if(riga.get("tipo").toString().equals("Musicista professionista")){
        	    		model.setUtenteGlobale(new UtenteProfessionale(new UtenteSemplice(nomeUtente, cf, nome, cognome, email, ntelefono, ncellulare, ip)));
        	    	}else if(riga.get("tipo").toString().equals("Titolare scuola di musica")){
        	    		model.setUtenteGlobale(new UtenteScuola(new UtenteSemplice(nomeUtente, cf, nome, cognome, email, ntelefono, ncellulare, ip)));
        	    	}
        		}
        	    MenuPrincipale nuovaVista = new MenuPrincipale();
        	    model.cambiaInterfaccia(nuovaVista, "Menu principale");
        	    @SuppressWarnings("unused")
				MenuController controller = new MenuController(model, nuovaVista);
            } else {
                JOptionPane.showMessageDialog(null, "Nome Utente o Password errato!");
            }
        }
    }
    
    class RegistraListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	InserimentoPersona nuovaVista = new InserimentoPersona();
    	    model.cambiaInterfaccia(nuovaVista, "Registrazione");
    	    @SuppressWarnings("unused")
			InserimentoController controller = new InserimentoController(model, nuovaVista);
        }
    }	 
}