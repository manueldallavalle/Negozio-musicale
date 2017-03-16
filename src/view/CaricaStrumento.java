package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CaricaStrumento extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JButton btnImg1;
	private JButton btnImg2;
	private JButton btnImg3;
	private JButton btnImg4;
	private JTextField txtCategoria;
	private JTextField txtNome;
	private JTextField txtDescrizione;
	private JTextField txtPeso;
	private JTextField txtPrezzoInt;
	private JTextField txtPrezzoDec;
	private JButton btnConferma;
	private JButton btnAnnulla;
	
	private final File[] arrImmagini = new File[4];
	private JLabel icoCategoria;
	private JLabel icoNome;
	private JLabel icoPrezzo;
	private JLabel icoPeso;
	private JLabel icoDescrizione;
	private boolean btn1V = true;
	private boolean btn2V = true;
	private boolean btn3V = true;
	private boolean btn4V = true;
	private boolean notificato = false;

	public CaricaStrumento() {
		this.setPreferredSize(new Dimension(393, 393));
		setLayout(null);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(20, 26, 93, 16);
		add(lblCategoria);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 54, 93, 16);
		add(lblNome);
		
		JLabel lblPrezzo = new JLabel("Prezzo:");
		lblPrezzo.setBounds(20, 82, 61, 16);
		add(lblPrezzo);
		
		JLabel lblVirgola = new JLabel(".");
		lblVirgola.setBounds(243, 82, 61, 16);
		add(lblVirgola);
		
		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setBounds(20, 110, 61, 16);
		add(lblPeso);
		
		JLabel lblInfoPeso = new JLabel("* Il peso deve essere espresso in grammi");
		lblInfoPeso.setFont(new Font("Arial", Font.ITALIC, 11)); 
		lblInfoPeso.setBounds(20, 136, 271, 16);
		add(lblInfoPeso);
		
		JLabel lblDescrizione = new JLabel("Descrizione:");
		lblDescrizione.setBounds(20, 160, 93, 16);
		add(lblDescrizione);
		
		JLabel lblImmagini = new JLabel("Immagini:");
		lblImmagini.setBounds(20, 193, 93, 16);
		add(lblImmagini);
		
		icoCategoria = new JLabel();
		icoCategoria.setBounds(353, 26, 61, 16);
		add(icoCategoria);
		
		icoNome = new JLabel();
		icoNome.setBounds(353, 54, 61, 16);
		add(icoNome);
		
		icoPrezzo = new JLabel();
		icoPrezzo.setBounds(353, 82, 61, 16);
		add(icoPrezzo);
		
		icoPeso = new JLabel();
		icoPeso.setBounds(353, 110, 61, 16);
		add(icoPeso);
		
		icoDescrizione = new JLabel();
		icoDescrizione.setBounds(353, 160, 61, 16);
		add(icoDescrizione);
		
		txtCategoria = new JTextField();
		txtCategoria.setBounds(125, 21, 216, 26);
		add(txtCategoria);
		txtCategoria.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setBounds(125, 49, 216, 26);
		add(txtNome);
		txtNome.setColumns(10);
		
		txtPrezzoInt = new JTextField();
		txtPrezzoInt.setBounds(125, 77, 110, 26);
		add(txtPrezzoInt);
		txtPrezzoInt.setColumns(10);
		
		txtPrezzoDec = new JTextField();
		txtPrezzoDec.setBounds(253, 77, 88, 26);
		txtPrezzoDec.setDocument(new JTextFieldLimit(2));
		add(txtPrezzoDec);
		txtPrezzoDec.setColumns(10);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(125, 105, 216, 26);
		add(txtPeso);
		txtPeso.setColumns(10);
		
		txtDescrizione = new JTextField();
		txtDescrizione.setBounds(125, 155, 216, 26);
		add(txtDescrizione);
		txtDescrizione.setColumns(10);
				
		btnImg1 = new JButton();
		btnImg1.setName("btnImg1");
		btnImg1.setIcon(new ImageIcon("img/aggiungi.png"));
		btnImg1.setBounds(125, 188, 98, 68);
		add(btnImg1);
		
		btnImg2 = new JButton();
		btnImg2.setName("btnImg2");
		btnImg2.setIcon(new ImageIcon("img/aggiungi.png"));
		btnImg2.setBounds(243, 188, 98, 68);
		add(btnImg2);
		
		btnImg3 = new JButton();
		btnImg3.setName("btnImg3");
		btnImg3.setIcon(new ImageIcon("img/aggiungi.png"));
		btnImg3.setBounds(125, 268, 98, 68);
		add(btnImg3);
		
		btnImg4 = new JButton();
		btnImg4.setName("btnImg4");
		btnImg4.setIcon(new ImageIcon("img/aggiungi.png"));
		btnImg4.setBounds(243, 268, 98, 68);
		add(btnImg4);
		
		btnConferma = new JButton("Conferma");
		btnConferma.setBounds(20, 348, 117, 29);
		add(btnConferma);
		
		btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBounds(224, 348, 117, 29);
		add(btnAnnulla);
	}
	
    public void aggiungiListener(ActionListener lst, int tipo){
    	if(tipo == 1) btnImg1.addActionListener(lst);
    	else if(tipo == 2) btnImg2.addActionListener(lst);
    	else if(tipo == 3) btnImg3.addActionListener(lst);
    	else if(tipo == 4) btnImg4.addActionListener(lst);
    	else if(tipo == 5) btnConferma.addActionListener(lst);
    	else if(tipo == 6) btnAnnulla.addActionListener(lst);
    }
    
    public void impostaBtn1(File f, ImageIcon ico){
    	arrImmagini[0] = f;
    	btnImg1.setIcon(ico);
    }
    
    public void impostaBtn2(File f, ImageIcon ico){
    	arrImmagini[1] = f;
    	btnImg2.setIcon(ico);
    }
    
    public void impostaBtn3(File f, ImageIcon ico){
    	arrImmagini[2] = f;
    	btnImg3.setIcon(ico);
    }
    
    public void impostaBtn4(File f, ImageIcon ico){
    	arrImmagini[3] = f;
    	btnImg4.setIcon(ico);
    }
    
    public String getPrezzoDec(){
    	return txtPrezzoDec.getText();
    }
    
    public void setPrezzoDec(String prezzo){
    	txtPrezzoDec.setText(prezzo);
    }

    public String getPrezzoInt(){
    	return txtPrezzoInt.getText();
    }    
		
	public boolean isProcessoFinito(){
		if(!notificato && btn1V && btn2V && btn3V && btn4V){
			notificato = true;
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isProcessoInCorso(){
		return (btn1V && btn2V && btn3V && btn4V);
	}
	
	public void disabilitaTutto(){
		txtCategoria.setEnabled(false);
		txtNome.setEnabled(false);
		txtPrezzoInt.setEnabled(false);
		txtPrezzoDec.setEnabled(false);
		txtPeso.setEnabled(false);
		txtDescrizione.setEnabled(false);
		btnConferma.setEnabled(false);
		btnAnnulla.setEnabled(false);
	}

	public String getCategoria() {
		return txtCategoria.getText();
	}

	public String getNome() {
		return txtNome.getText();
	}

	public String getPrezzo() {
		return txtPrezzoInt.getText() + "." + txtPrezzoDec.getText();
	}

	public String getPeso() {
		return txtPeso.getText();
	}

	public String getDescrizione() {
		return txtDescrizione.getText();
	}

	public File getFileCaricare(int pos) {
		return arrImmagini[pos];
	} 
	
	public void setIcoCaricamento(int tipo, ImageIcon ico){
		switch(tipo){
			case 1:
				icoCategoria.setIcon(ico);
				break;
			case 2:
				icoNome.setIcon(ico);
				break;
			case 3:
				icoPrezzo.setIcon(ico);
				break;
			case 4:
				icoPeso.setIcon(ico);
				break;
			case 5:
				icoDescrizione.setIcon(ico);
				break;
		}
	}
	
	public void impostaIcone(int tipo, ImageIcon ico){
		switch(tipo){
		case 1: btnImg1.setIcon(ico);
				break;
		case 2: btnImg2.setIcon(ico);
				break;
		case 3: btnImg3.setIcon(ico);
				break;
		case 4: btnImg4.setIcon(ico);
				break;
		}
	}
		
	public void impostaStatoThread(int btn, boolean stato){
		if(btn == 1) btn1V = stato;
		else if(btn == 2) btn2V = stato;
		else if(btn == 3) btn3V = stato;
		else if(btn == 4) btn4V = stato;
	}
	
}