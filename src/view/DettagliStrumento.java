package view;

import java.awt.Dimension;
import java.awt.event.*;
import java.net.URL;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.Funzioni;
import model.ImageIconProxy;
import model.Strumento;

import javax.swing.JScrollPane;

public class DettagliStrumento extends JPanel{
	private static final long serialVersionUID = 1L;
	private JButton btnBack;
	private JButton btnImg1;
	private JButton btnImg2;
	private JButton btnImg3;
	private JButton btnImg4;
	private boolean type;
	private Strumento strumento;
	
	public DettagliStrumento(Strumento newStrumento, boolean type){
		this.strumento = newStrumento;
		this.type = type;
		setLayout(null);
		this.setPreferredSize(new Dimension(315, 681));
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(43, 36, 81, 14);
		add(lblCategoria);
		
		JLabel lblCategoriaStrumento = new JLabel(strumento.getCategoria());
		lblCategoriaStrumento.setBounds(130, 36, 138, 14);
		add(lblCategoriaStrumento);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(43, 76, 81, 14);
		add(lblNome);
		
		JLabel lblNomeStrumento = new JLabel(strumento.getNome());
		lblNomeStrumento.setBounds(130, 76, 179, 14);
		add(lblNomeStrumento);
		
		JLabel lblPrezzo = new JLabel("Prezzo:");
		lblPrezzo.setBounds(43, 116, 81, 14);
		add(lblPrezzo);
		
		JLabel lblPrezzoStrumento = new JLabel(""+strumento.getPrezzo()+" \u20ac");
		lblPrezzoStrumento.setBounds(130, 116, 129, 14);
		add(lblPrezzoStrumento);

		
		JLabel lblDataSulSito = new JLabel("Data sul sito:");
		lblDataSulSito.setBounds(43, 156, 116, 14);
		add(lblDataSulSito);
		
		JLabel lblDataSulSitoStrumento = new JLabel(Funzioni.formattaData(strumento.getDataPresenzaSito()));
		lblDataSulSitoStrumento.setBounds(130, 156, 138, 14);
		add(lblDataSulSitoStrumento);
		
		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setBounds(43, 196, 81, 14);
		add(lblPeso);
		
		JLabel lblPesoStrumento = new JLabel(""+strumento.getPeso()+" g");
		lblPesoStrumento.setBounds(130, 196, 81, 14);
		add(lblPesoStrumento);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(43, 236, 225, 14);
		add(lblTipo);
		
		JLabel lblTipoStrumento;

		
		if(strumento.getTipo() == 1){
			JLabel lblSconto = new JLabel("Sconto:");
			lblSconto.setBounds(43, 276, 81, 14);
			add(lblSconto);
			lblTipoStrumento = new JLabel("Professionale");		
			JLabel lblScontoStrumento = new JLabel(String.format("%.2f", strumento.getSconto()) + " %");
			lblScontoStrumento.setBounds(130, 276, 200, 14);
			add(lblScontoStrumento);
		}
		else{
			JLabel lblLivello = new JLabel("Livello:");
			lblLivello.setBounds(43, 276, 81, 14);
			add(lblLivello);
			JLabel lblLvlStrumento = new JLabel(""+strumento.getLivello());
			lblLvlStrumento.setBounds(130, 276, 200, 14);
			add(lblLvlStrumento);
			lblTipoStrumento = new JLabel("Scuole di Musica");
		}
		
		lblTipoStrumento.setBounds(130, 236, 200, 14);
		add(lblTipoStrumento);
		
		JLabel lblUsato = new JLabel("Condizioni:");
		lblUsato.setBounds(43, 316, 81, 14);
		add(lblUsato);
		
		JLabel lblUsatoStrumento;
		if(strumento.getUsato()) lblUsatoStrumento = new JLabel("Usato");
		else lblUsatoStrumento = new JLabel("Nuovo");
		lblUsatoStrumento.setBounds(130, 316, 81, 14);
		add(lblUsatoStrumento);		
		
		JLabel lblDescrizione = new JLabel("Descrizione:");
		lblDescrizione.setBounds(43, 356, 81, 14);
		add(lblDescrizione);
		
		JTextArea lblDescrizioneStrumento = new JTextArea();
		lblDescrizioneStrumento.setEditable(false);
		lblDescrizioneStrumento.setText(diviviStr(strumento.getDescrizione(),"\n",17));
		lblDescrizioneStrumento.setBorder(null);
		
		JScrollPane scrollPane_descrizione = new JScrollPane();
		scrollPane_descrizione.setViewportView(lblDescrizioneStrumento);
		scrollPane_descrizione.setBounds(130, 356, 129, 68);
		scrollPane_descrizione.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_descrizione.setBorder(null);
		add(scrollPane_descrizione);

		JLabel lblImgTitolo = new JLabel("Immagini");
		lblImgTitolo.setBounds(43, 437, 70, 14);
		add(lblImgTitolo);
		
		btnImg1 = new JButton();
		btnImg1.setBounds(43, 462, 98, 68);
		btnImg1.setVisible(false);
		add(btnImg1);
		
		btnImg2 = new JButton();
		btnImg2.setBounds(161, 462, 98, 68);
		btnImg2.setVisible(false);
		add(btnImg2);		
		
		btnImg3 = new JButton();
		btnImg3.setBounds(43, 541, 98, 68);
		btnImg3.setVisible(false);
		add(btnImg3);		

		btnImg4 = new JButton();
		btnImg4.setBounds(161, 541, 98, 68);
		btnImg4.setVisible(false);
		add(btnImg4);
		
		btnImg1.setToolTipText("Clicca per ingrandire");
		btnImg2.setToolTipText("Clicca per ingrandire");
		btnImg3.setToolTipText("Clicca per ingrandire");
		btnImg4.setToolTipText("Clicca per ingrandire");
		
		btnBack = new JButton("Indietro");
		btnBack.setBounds(107, 632, 89, 23);
		add(btnBack);

		caricaImmStrumento();
	}
		
	public String diviviStr(String str, String insert, int period){
	    StringBuilder builder = new StringBuilder(
	    		str.length() + insert.length() * (str.length()/period)+1);

	    int index = 0;
	    String prefix = "";
	    while (index < str.length())
	    {
	        builder.append(prefix);
	        prefix = insert;
	        builder.append(str.substring(index, 
	            Math.min(index + period, str.length())));
	        index += period;
	    }
	    return builder.toString();
	}
	
	
	public void caricaImmStrumento() {		
		Iterator<URL> itr = strumento.getImmagini().iterator();		
		int cont = 1;

	    while (itr.hasNext()) {
			switch(cont){
				case 1: 
						btnImg1.setIcon(new ImageIconProxy(itr.next(), btnImg1, 98, 68));
						btnImg1.setVisible(true);
						break;
				case 2: 
						btnImg2.setIcon(new ImageIconProxy(itr.next(), btnImg2, 98, 68));
						btnImg2.setVisible(true);
						break;
				case 3: 
						btnImg3.setIcon(new ImageIconProxy(itr.next(), btnImg3, 98, 68));
						btnImg3.setVisible(true);
						break;
				case 4: 
						btnImg4.setIcon(new ImageIconProxy(itr.next(), btnImg4, 98, 68));
						btnImg4.setVisible(true);
						break;
			}
			cont++;	
		}
	}
	
	public void aggiungiListener(ActionListener lst, int tipo){
		switch(tipo){
			case 1: btnImg1.addActionListener(lst);
					break;
			case 2: btnImg2.addActionListener(lst);
					break;
			case 3: btnImg3.addActionListener(lst);
					break;
			case 4: btnImg4.addActionListener(lst);
					break;
			case 5: btnBack.addActionListener(lst);
				break;
		}
    } 

	public boolean isSpeciale(){
		return type;
	}
	
	public URL getImgStrumento(int cont) {
		return strumento.getImmagini().get(cont);
	}
}