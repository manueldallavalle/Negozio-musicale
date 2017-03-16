package view;

import javax.swing.JPanel;

import controller.PreviewCarrelloController;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MenuPrincipale extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JLabel lblTipoCliente;
	private JLabel lblCf;
	private JLabel lblNomeCognome;
	private JButton btnVisualizzaCatalogo;
	private JButton btnVendi;
	private PreviewCarrello pannelloCarrello;
	private JLabel lblTotArticoli;
	private JButton btnCheckout;
	
	
	public MenuPrincipale() {	
		this.setLayout(null);
		this.setPreferredSize(new Dimension(506, 303));
		
		JLabel txtNomeCognome = new JLabel("Benvenuto, ");
		txtNomeCognome.setFont(new Font("Arial", Font.BOLD, 12));
		txtNomeCognome.setBounds(20, 9, 97, 16);
		add(txtNomeCognome);
		
		lblNomeCognome = new JLabel("");
		lblNomeCognome.setFont(new Font("Arial", Font.ITALIC, 11)); 
		lblNomeCognome.setBounds(91, 9, 145, 16);
		add(lblNomeCognome);
		
		JLabel txtCf = new JLabel("Codice fiscale:");
		txtCf.setFont(new Font("Arial", Font.BOLD, 12)); 
		txtCf.setBounds(20, 37, 97, 16);
		add(txtCf);
		
		lblCf = new JLabel("");
		lblCf.setFont(new Font("Arial", Font.ITALIC, 11)); 
		lblCf.setBounds(131, 37, 145, 16);
		add(lblCf);
		
		JLabel txtTipoCliente = new JLabel("Tipo cliente:");
		txtTipoCliente.setFont(new Font("Arial", Font.BOLD, 12)); 
		txtTipoCliente.setBounds(20, 65, 134, 16);
		add(txtTipoCliente);
		
		lblTipoCliente = new JLabel("");
		lblTipoCliente.setFont(new Font("Arial", Font.ITALIC, 11)); 
		lblTipoCliente.setBounds(131, 65, 134, 16);
		add(lblTipoCliente);
		
		btnVisualizzaCatalogo = new JButton("Visualizza catalogo");
		btnVisualizzaCatalogo.setBounds(20, 93, 188, 29);
		add(btnVisualizzaCatalogo);
		
		btnCheckout = new JButton("Checkout");
		btnCheckout.setBounds(20, 248, 188, 40);
		add(btnCheckout);
	
		JLabel icoCarrello = new JLabel();
		icoCarrello.setIcon(new ImageIcon("img/carrelloHome.png"));
		icoCarrello.setBounds(321, 9, 45, 34);
		add(icoCarrello);
		
		JLabel lblCarrelloSpesa = new JLabel("CARRELLO SPESA");
		lblCarrelloSpesa.setFont(new Font("Arial", Font.BOLD, 10)); 
		lblCarrelloSpesa.setBounds(370, 9, 100, 16);
		add(lblCarrelloSpesa);

		lblTotArticoli = new JLabel("");
		lblTotArticoli.setForeground(Color.decode("#990000"));
		lblTotArticoli.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblTotArticoli.setBounds(370, 26, 133, 16);
		add(lblTotArticoli);
		
		pannelloCarrello = new PreviewCarrello();
		pannelloCarrello.setBounds(242, 43, 258, 245);
		pannelloCarrello.setVisible(false);
		@SuppressWarnings("unused")
		PreviewCarrelloController controllerCarrello = new PreviewCarrelloController(pannelloCarrello);
		add(pannelloCarrello);
		
		btnVendi = new JButton("Vendi strumento");
		btnVendi.setBounds(20, 134, 188, 29);
		btnVendi.setVisible(false);
		add(btnVendi);
	}
		
	
    public void aggiungiListener(ActionListener lst, int tipo){
    	if(tipo == 1) btnVisualizzaCatalogo.addActionListener(lst);
    	else if(tipo == 2) btnCheckout.addActionListener(lst);
    	else btnVendi.addActionListener(lst);
    }
    
    public void aggiungiMouseListener(MouseListener lst){
    	lblTotArticoli.addMouseListener(lst);
    }

	public void impostaNomeCognome(String str) {
		lblNomeCognome.setText(str);
	}

	public void impostaCf(String str) {
		lblCf.setText(str);
	}

	public void impostaTipoCliente(String str) {
		lblTipoCliente.setText(str);
	}
	
	public void mostraVendi(){
		btnVendi.setVisible(true);
	}

	public void cambiaStatoPreviewCarrello(boolean val){
		pannelloCarrello.setVisible(val);
	}
	
	public void impostTotCarrello(String str) {
		lblTotArticoli.setText(str);
	}
	
	public boolean getStatoPreview(){
		return (lblTotArticoli.getText().indexOf("\u25b8") > -1);
	}
    		
}