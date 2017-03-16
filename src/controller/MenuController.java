package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.*;
import view.*;

public class MenuController{
	private NegozioModel model;
	private MenuPrincipale view;
	private boolean titolareScuola;
	
	public MenuController(NegozioModel model, MenuPrincipale view) {
		this.model = model;
		this.view = view;
		inizializza();
	} 
	
	private void inizializza(){
		String tipoCliente = model.getUtenteGlobale().getTipoCliente();
		view.impostaNomeCognome(model.getUtenteGlobale().getNomeCognome());
		view.impostaCf(model.getUtenteGlobale().getCf());
		view.impostaTipoCliente(tipoCliente);
		
		view.aggiungiListener(new VisualizzaCatalogo(),1);
		view.aggiungiListener(new VediCheckout(),2);
		view.aggiungiMouseListener(new ClickPreviewCarrello());
		
		if(tipoCliente.equals("Musicista professionista")){
			view.mostraVendi();
			view.aggiungiListener(new Vendi(),3);
		}
			
		titolareScuola = tipoCliente.equals("Titolare scuola di musica");
		view.impostTotCarrello(String.format("<html><span style='font-size:7px'>%d Prodotto(i) - %.2f \u20ac \u25b8</span></html>", Carrello.getTotStrumenti(), Carrello.getPrezzoTotale(titolareScuola)));
	}
	
    class VisualizzaCatalogo implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CatalogoStrumenti view = new CatalogoStrumenti();
			@SuppressWarnings("unused")
			CatalogoController controller = new CatalogoController(model,view);
			model.cambiaInterfaccia(view, "Catalogo");
        }
    }
    
    class Vendi implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CaricaStrumento nuovaVista = new CaricaStrumento();
    	    model.cambiaInterfaccia(nuovaVista, "Carica strumento");
    	    @SuppressWarnings("unused")
			CaricaController controller = new CaricaController(model, nuovaVista);
        }
    }
    
    class VediCheckout implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// GUARDARE CHE CAZZO E' TYPE!!
			// 
			//model.cambiaInterfaccia(new Checkout());
			Checkout nuovaVista = new Checkout();
    	    model.cambiaInterfaccia(nuovaVista, "Checkout");
    	    @SuppressWarnings("unused")
    	    CheckoutController controller = new CheckoutController(model, nuovaVista);
        }
    }
    
    class ClickPreviewCarrello implements MouseListener {
    	public void mouseEntered(MouseEvent e){}
    	public void mouseExited(MouseEvent e) {}
    	public void mouseClicked(MouseEvent e) {
			boolean isPreviewChiusa = view.getStatoPreview();
			view.cambiaStatoPreviewCarrello(isPreviewChiusa);
			if(isPreviewChiusa){
				view.impostTotCarrello(String.format("<html><span style='font-size:7px'>%d Prodotto(i) - %.2f \u20ac \u25be</span></html>", Carrello.getTotStrumenti(), Carrello.getPrezzoTotale(titolareScuola)));
			}else{
				view.impostTotCarrello(String.format("<html><span style='font-size:7px'>%d Prodotto(i) - %.2f \u20ac \u25b8</span></html>", Carrello.getTotStrumenti(), Carrello.getPrezzoTotale(titolareScuola)));
			}
    	}
    	public void mousePressed(MouseEvent e) {}
    	public void mouseReleased(MouseEvent e) {}
    }
 
}