package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;
import view.*;

public class DettagliController{
	private NegozioModel model;
	private DettagliStrumento view;

	public DettagliController(NegozioModel model, DettagliStrumento view) {
		this.model = model;
		this.view = view;
		inizializza();
	}

	private void inizializza(){
		view.aggiungiListener(new ApriImg1(),1);
		view.aggiungiListener(new ApriImg2(),2);
		view.aggiungiListener(new ApriImg3(),3);
		view.aggiungiListener(new ApriImg4(),4);
		view.aggiungiListener(new TornaAlMenu(),5);
	}
	
    class ApriImg1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Immagini(view.getImgStrumento(0));
        }
    }
    
    class ApriImg2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Immagini(view.getImgStrumento(1));
        }
    }
    
    class ApriImg3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Immagini(view.getImgStrumento(2));
        }
    }
    
    class ApriImg4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Immagini(view.getImgStrumento(3));
        }
    }
    
    class TornaAlMenu implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(!view.isSpeciale()){
				CatalogoStrumenti view = new CatalogoStrumenti();
				@SuppressWarnings("unused")
				CatalogoController controller = new CatalogoController(model,view);
				model.cambiaInterfaccia(view, "Catalogo");
			}
			else{
/**
				CatalogoStrumentiSpeciali view = new CatalogoStrumenti(model);
				@SuppressWarnings("unused")
				CatalogoController controller = new CatalogoController(model,view);
				model.cambiaInterfaccia(view, "Catalogo");*/
			}
        }
    }
}