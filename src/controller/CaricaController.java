package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.*;
import view.*;

public class CaricaController{
	private NegozioModel model;
	private CaricaStrumento view;
	private final JFileChooser fc = new JFileChooser();
	
	public CaricaController(NegozioModel model, CaricaStrumento view) {
		this.model = model;
		this.view = view;
		inizializza();
	}

	private void inizializza(){
		view.aggiungiListener(new CaricaImg1(),1);
		view.aggiungiListener(new CaricaImg2(),2);
		view.aggiungiListener(new CaricaImg3(),3);
		view.aggiungiListener(new CaricaImg4(),4);
		view.aggiungiListener(new InserisciStrumenti(),5);
		view.aggiungiListener(new TornaAlMenu(),6);
	}
	
    class CaricaImg1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(view.isProcessoInCorso()){
				FileFilter filtroImg = new FileNameExtensionFilter(
					    "File Immagine", ImageIO.getReaderFileSuffixes());
				fc.setFileFilter(filtroImg);
				fc.setAcceptAllFileFilterUsed(false);
				
		        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		        int result = fc.showOpenDialog(view);
		        
		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fc.getSelectedFile();
		            try {
						URL imgURL = new File(selectedFile.getAbsolutePath()).toURI().toURL();
						view.impostaBtn1(selectedFile, Funzioni.ridimensionaImg(new ImageIcon(imgURL),98,68));
					} catch (MalformedURLException err) {
						System.out.println("Errore nel caricamento dell'immagine!");
						System.out.println("ERRORE = " + err.getMessage());
					}
		        }
			}
        }
    }
    
    class CaricaImg2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(view.isProcessoInCorso()){
				FileFilter filtroImg = new FileNameExtensionFilter(
					    "File Immagine", ImageIO.getReaderFileSuffixes());
				fc.setFileFilter(filtroImg);
				fc.setAcceptAllFileFilterUsed(false);
				
		        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		        int result = fc.showOpenDialog(view);
		        
		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fc.getSelectedFile();
		            try {
						URL imgURL = new File(selectedFile.getAbsolutePath()).toURI().toURL();
						view.impostaBtn2(selectedFile, Funzioni.ridimensionaImg(new ImageIcon(imgURL),98,68));
					} catch (MalformedURLException err) {
						System.out.println("Errore nel caricamento dell'immagine!");
						System.out.println("ERRORE = " + err.getMessage());
					}
		        }
			}
        }
    }
    
    class CaricaImg3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(view.isProcessoInCorso()){
				FileFilter filtroImg = new FileNameExtensionFilter(
					    "File Immagine", ImageIO.getReaderFileSuffixes());
				fc.setFileFilter(filtroImg);
				fc.setAcceptAllFileFilterUsed(false);
				
		        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		        int result = fc.showOpenDialog(view);
		        
		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fc.getSelectedFile();
		            try {
						URL imgURL = new File(selectedFile.getAbsolutePath()).toURI().toURL();
						view.impostaBtn3(selectedFile, Funzioni.ridimensionaImg(new ImageIcon(imgURL),98,68));
					} catch (MalformedURLException err) {
						System.out.println("Errore nel caricamento dell'immagine!");
						System.out.println("ERRORE = " + err.getMessage());
					}
		        }
			}
        }
    }
    
    class CaricaImg4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(view.isProcessoInCorso()){
				FileFilter filtroImg = new FileNameExtensionFilter(
					    "File Immagine", ImageIO.getReaderFileSuffixes());
				fc.setFileFilter(filtroImg);
				fc.setAcceptAllFileFilterUsed(false);
				
		        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		        int result = fc.showOpenDialog(view);
		        
		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fc.getSelectedFile();
		            try {
						URL imgURL = new File(selectedFile.getAbsolutePath()).toURI().toURL();
						view.impostaBtn4(selectedFile, Funzioni.ridimensionaImg(new ImageIcon(imgURL),98,68));
					} catch (MalformedURLException err) {
						System.out.println("Errore nel caricamento dell'immagine!");
						System.out.println("ERRORE = " + err.getMessage());
					}
		        }
			}
        }
    }
    
    class InserisciStrumenti implements ActionListener {
		public void controllaThread(String urlFile, int idStrumento){
			DBManager.updateQuery(EnumQuery.INSERISCI_IMG_STRUMENTO.getValore(), false,
					idStrumento,
					urlFile
			);
			
			if(view.isProcessoFinito()){
				JOptionPane.showMessageDialog(null, "Strumento caricato correttamente!");
        	    MenuPrincipale nuovaVista = new MenuPrincipale();
        	    model.cambiaInterfaccia(nuovaVista, "Menu principale");
        	    @SuppressWarnings("unused")
				MenuController controller = new MenuController(model, nuovaVista);
			}
		}
		
		public boolean checkCampi(){
			if(view.getPrezzoDec().isEmpty()) view.setPrezzoDec("00");
			boolean categoriaV = view.getCategoria().isEmpty();
			boolean nomeV = view.getNome().isEmpty();
			boolean prezzoV = !Funzioni.isInteger(view.getPrezzoInt()) || !Funzioni.isInteger(view.getPrezzoDec());
			boolean pesoV = !Funzioni.isInteger(view.getPeso());
			boolean descrizioneV = view.getDescrizione().isEmpty();

			view.setIcoCaricamento(1, categoriaV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
			view.setIcoCaricamento(2, nomeV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
			view.setIcoCaricamento(3, prezzoV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
			view.setIcoCaricamento(4, pesoV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
			view.setIcoCaricamento(5, descrizioneV ? new ImageIcon("img/errore.png") : new ImageIcon("img/ok.png"));
			view.setIcoCaricamento(7, new ImageIcon("img/ok.png"));
			
			return !(categoriaV || nomeV || prezzoV || pesoV || descrizioneV);
		}
		
    	
    	public void actionPerformed(ActionEvent e) {
    		if(checkCampi()){
				int idStrumento = DBManager.updateQuery(EnumQuery.INSERISCI_STRUMENTO.getValore(), true,
					view.getCategoria(),
					view.getNome(),
					view.getPrezzo(),
					Funzioni.getCurrTimestamp(),
					view.getPeso(),
					view.getDescrizione()
				);
	
				int inserisciVendita = DBManager.updateQuery(EnumQuery.INSERISCI_VENDITA.getValore(), false,
						model.getUtenteGlobale().getUsername(),
						idStrumento,
						model.getUtenteGlobale().getIndirizzoIP()
				);
	
				if(idStrumento > 0 && inserisciVendita > 0){
					view.disabilitaTutto();
					if(view.getFileCaricare(0) != null){
						view.impostaIcone(1, new ImageIcon("img/caricamento.gif"));
						Thread a = new Thread(() -> {
							String urlFile = CaricaFilePHP.inviaFile(view.getFileCaricare(0));
							view.impostaStatoThread(1, false);
							if(!urlFile.equals("-1")){
								view.impostaStatoThread(1, true);
								controllaThread(urlFile,idStrumento);
								view.impostaIcone(1, new ImageIcon("img/okCircolare.png"));
							}else{
								view.impostaIcone(1, new ImageIcon("img/erroreCircolare.png"));
								System.out.println("Errore nell'upload dell'immagine");
							}
						});			
						a.start();
					}
					
					if(view.getFileCaricare(1) != null){
						view.impostaIcone(2, new ImageIcon("img/caricamento.gif"));
						Thread a = new Thread(() -> {
							String urlFile = CaricaFilePHP.inviaFile(view.getFileCaricare(1));
							view.impostaStatoThread(2, false);
							if(!urlFile.equals("-1")){
								view.impostaStatoThread(2, true);
								controllaThread(urlFile,idStrumento);
								view.impostaIcone(2, new ImageIcon("img/okCircolare.png"));
							}else{
								view.impostaIcone(2, new ImageIcon("img/erroreCircolare.png"));
								System.out.println("Errore nell'upload dell'immagine");
							}
						});			
						a.start();
					}
					
					if(view.getFileCaricare(2) != null){
						view.impostaIcone(3, new ImageIcon("img/caricamento.gif"));
						Thread a = new Thread(() -> {
							String urlFile = CaricaFilePHP.inviaFile(view.getFileCaricare(2));
							view.impostaStatoThread(3, false);
							if(!urlFile.equals("-1")){
								view.impostaStatoThread(3, true);
								controllaThread(urlFile,idStrumento);
								view.impostaIcone(3, new ImageIcon("img/okCircolare.png"));
							}else{
								view.impostaIcone(3, new ImageIcon("img/erroreCircolare.png"));
								System.out.println("Errore nell'upload dell'immagine");
							}
						});			
						a.start();
					}
	
					if(view.getFileCaricare(3) != null){
						view.impostaIcone(4, new ImageIcon("img/caricamento.gif"));
						Thread a = new Thread(() -> {
							String urlFile = CaricaFilePHP.inviaFile(view.getFileCaricare(3));
							view.impostaStatoThread(4, false);
							if(!urlFile.equals("-1")){
								view.impostaStatoThread(4, true);
								controllaThread(urlFile,idStrumento);
								view.impostaIcone(4, new ImageIcon("img/okCircolare.png"));
							}else{
								view.impostaIcone(4, new ImageIcon("img/erroreCircolare.png"));
								System.out.println("Errore nell'upload dell'immagine");
							}
						});			
						a.start();
					}
					
			}else{
				JOptionPane.showMessageDialog(null, "Errore nell'inserimento dello strumento");
			}
	    }
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