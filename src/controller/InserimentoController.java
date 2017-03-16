package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import model.*;
import view.*;

public class InserimentoController {
	private NegozioModel model;
	private InserimentoPersona view;
	
	public InserimentoController(NegozioModel model, InserimentoPersona view) {
		this.model = model;
		this.view = view;
		inizializza();
	}
	
	private void inizializza(){
		view.aggiungiListener(new Inserisci(),1);
		view.aggiungiListener(new Annulla(),2);
	}
	
	public boolean checkCampi(){
		boolean userV = view.getUsername().isEmpty();
		boolean pass1V = view.getPsw().length() < 8;
		boolean pass2V = !view.getPsw().equals(view.getPswConfirmed());
		boolean emailV = !(Funzioni.isEmailCorretta(view.getEmail()));
		boolean nomeV = view.getNome().isEmpty();
		boolean cognomeV = view.getCognome().isEmpty();
		boolean cfV =view.getCf().length() < 16;
		boolean telefonoV = !Funzioni.isInteger(view.getTelefono()) || view.getTelefono().isEmpty();

		view.setIcoRegistrazione(1, userV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
		view.setIcoRegistrazione(2, pass1V ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
		view.setIcoRegistrazione(3, pass2V ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
		view.setIcoRegistrazione(4, emailV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
		view.setIcoRegistrazione(5, nomeV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
		view.setIcoRegistrazione(6, cognomeV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
		view.setIcoRegistrazione(7, cfV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
		view.setIcoRegistrazione(8, telefonoV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
		
		if(!userV){
			   userV = DBManager.CheckEsiste(EnumQuery.CHECK_USERNAME.getValore(), view.getUsername());
			   view.setIcoRegistrazione(1, userV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
			  }
		
		return !(userV || pass1V || pass2V || emailV || nomeV || cognomeV || cfV || telefonoV);
	}
	
	class Inserisci implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(checkCampi()){
				int inserisciDB = DBManager.updateQuery(EnumQuery.INSERISCI_CLIENTE.getValore(), false,
						view.getCf().toUpperCase(),
						view.getUsername(),
						Funzioni.strToMD5(view.getPsw()),
						view.getNome(),
						view.getCognome(),
						view.getEmail(),
						view.getTelefono() + " ",
						view.getCellulare() + " ",
						view.getTipoCliente()
						);
				if(inserisciDB > 0){
					JOptionPane.showMessageDialog(null, "Registrazione Effettuata!");
					Login nuovaVista = new Login();
	        	    model.cambiaInterfaccia(nuovaVista, "Login");
	        	    @SuppressWarnings("unused")
					LoginController controller = new LoginController(model, nuovaVista);
				}else{
					JOptionPane.showMessageDialog(null, "Errore nella registrazione del cliente!");
				}
			}
		}		
	}
	
	class Annulla implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Login nuovaVista = new Login();
	        model.cambiaInterfaccia(nuovaVista, "Login");
	        @SuppressWarnings("unused")
			LoginController controller = new LoginController(model, nuovaVista);			
		}
	}		
}