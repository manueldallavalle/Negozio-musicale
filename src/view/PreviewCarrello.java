package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;


public class PreviewCarrello extends JPanel{
	private static final long serialVersionUID = 1L;
	private JPanel pannelloElementi;
	private JScrollPane scrollPane;
	
	public PreviewCarrello() {
		setLayout(null);
		setPreferredSize(new Dimension(258, 242));
		setOpaque(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 245, 230);

		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);

		pannelloElementi = new JPanel();
		pannelloElementi.setBorder(null);
		pannelloElementi.setLayout(null);
		pannelloElementi.setBackground(Color.WHITE); 
		scrollPane.setViewportView(pannelloElementi);
	}
	
	public void mostraLblVuoto(){
    	JLabel lblVuoto = new JLabel("Il tuo carrello \u00E8 vuoto!");
    	lblVuoto.setFont(new Font("Arial", Font.ITALIC, 15)); 
    	lblVuoto.setBounds(54, 22, 150, 16);
    	pannelloElementi.add(lblVuoto);
	}
	
	public void aggiungiLabel(JLabel lbl){
		pannelloElementi.add(lbl);
	}
	
	public void aggiungiTextField(JTextField txt){
		pannelloElementi.add(txt);
	}
		
	public void aggiungiBtn(JButton btn){
		pannelloElementi.add(btn);
	}

	public void aggiungiSeparator(JSeparator separator) {
		pannelloElementi.add(separator);
	}
	
	public void cambiaVisuale(int tipo, int yElemento){
		// TIPO == 1 - CI SONO ELEMENTI NEL CARRELLO
		// TIPO == 2 - NON CI SONO ELEMENTI NEL CARRELLO
		if(tipo == 1){
	        scrollPane.setBounds(6, 6, 245, (yElemento + 45 <= 242) ? yElemento + 45 : 240);
	        pannelloElementi.setPreferredSize(new Dimension(240, yElemento + 50));
	        setPreferredSize(new Dimension(258, (yElemento + 50 <= 242) ? yElemento + 50 : 242));
	        scrollPane.setPreferredSize(new Dimension(245, yElemento + 50));
		}else{
	    	scrollPane.setBounds(6, 6, 245, 75);
	    	pannelloElementi.setPreferredSize(new Dimension(240, 70));
	        setPreferredSize(new Dimension(258, 70));
	        scrollPane.setPreferredSize(new Dimension(245, 75));
		}
	}
	
	public void pulisciCarrello(){
		pannelloElementi.removeAll();
	}
	
	public void ricaricaCarrello(){
		pannelloElementi.revalidate();
		pannelloElementi.repaint();
		scrollPane.revalidate();
		scrollPane.repaint();
		repaint();
		revalidate();
	}
		
}