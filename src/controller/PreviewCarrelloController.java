package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import model.*;
import view.*;

public class PreviewCarrelloController {
	private PreviewCarrello view;
	
	public PreviewCarrelloController(PreviewCarrello view) {
		this.view = view;
		aggiornaPreview();
	}
	
	public void notificaCarrello(){
		view.pulisciCarrello();
		aggiornaPreview();
		view.ricaricaCarrello();
	}
	
	private void aggiornaPreview(){
		int yElemento = 6;
		int cont = 0;
		Iterator<Strumento> itr = Carrello.getStrumenti().iterator();
		ArrayList<Strumento> list = new ArrayList<Strumento>();
		
		boolean haElementi = itr.hasNext();
		
	    while (itr.hasNext()) {
	    	Strumento tmp = itr.next();
	    	
	    	if(!list.contains(tmp)){
	    		list.add(tmp);
	    		
	        	JButton tBtn = new JButton();
	        	tBtn.setIcon(new ImageIcon("img/togliCarrello.png"));
	        	tBtn.setBounds(6, yElemento, 38, 29);
	        	tBtn.setBorder(null);
	        	tBtn.setBackground(Color.WHITE);
	        	
	    		JLabel lblNome = new JLabel(tmp.getNome());
	    		lblNome.setFont(new Font("Arial", Font.ITALIC, 11)); 
	    		lblNome.setBounds(56, yElemento+5, 90, 16);
	    		
	    		JLabel lblQuant = new JLabel();
				lblQuant.setText(String.format("%d x %.2f \u20ac", Carrello.getNStrumenti(tmp), tmp.getPrezzo()));
	    		lblQuant.setFont(new Font("Arial", Font.BOLD, 11)); 
	    		lblQuant.setBounds(160, yElemento+5, 90, 16);
	    		
	            JSeparator separatore = new JSeparator();
	            separatore.setBounds(6, yElemento + 29, 233, 12);
	
	    		tBtn.addActionListener(new GestioneCarrello());
	    		tBtn.setName("btn" + cont);
	
	    		view.aggiungiBtn(tBtn);
	    		view.aggiungiLabel(lblNome);
	    		view.aggiungiLabel(lblQuant);
	    		view.aggiungiSeparator(separatore);

	    		yElemento += 41;
	    	}
	    	cont++;
	    }

	    if(haElementi){
	        JLabel lblTotale = new JLabel("Totale:");
	        lblTotale.setBounds(100, yElemento + 16, 50, 16);
	        lblTotale.setFont(new Font("Arial", Font.BOLD, 15)); 
	
	        JLabel lblTotPrezzo = new JLabel(String.format("%.2f \u20ac", Carrello.getPrezzoTotale(false)));
	        lblTotPrezzo.setBounds(160, yElemento + 16, 90, 16);
	        lblTotPrezzo.setFont(new Font("Arial", Font.BOLD, 15)); 
	        lblTotPrezzo.setForeground(Color.decode("#990000"));
	        
	        view.aggiungiLabel(lblTotale);
	        view.aggiungiLabel(lblTotPrezzo);
	        
	        view.cambiaVisuale(1, yElemento);
	    }else{
	    	view.mostraLblVuoto();
	    	view.cambiaVisuale(2, yElemento);
	    }
	}
	
    class GestioneCarrello implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int indexArray = Integer.parseInt(((JButton) e.getSource()).getName().split("btn")[1]);
			Carrello.rimuoviStrumento(indexArray);
			notificaCarrello();
        }
    }
}