package view;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.JTextFieldLimit;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class InserimentoPersona extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JTextField txtUsername;
	private JPasswordField txtPass1;
	private JPasswordField txtPass2;
	private JTextField txtEmail;
	private JTextField txtNome;
	private JTextField txtTelefono;
	private JButton btnConferma;
	private JButton btnAnnulla;
	private JComboBox<String> txtTipoCliente;
	private JTextField txtCf;
	private JTextField txtCognome;
	private JTextField txtCellulare;
	private JLabel icoUsername;
	private JLabel icoPass1;
	private JLabel icoPass2;
	private JLabel icoEmail;
	private JLabel icoNome;
	private JLabel icoCognome;
	private JLabel icoCf;
	private JLabel icoTelefono;
	
	public InserimentoPersona() {
		this.setPreferredSize(new Dimension(388, 392));
		setLayout(null);
		
		JLabel lblUsername = new JLabel("Nome utente:");
		lblUsername.setBounds(16, 26, 137, 16);
		add(lblUsername);
		
		JLabel lblPass1 = new JLabel("Password:");
		lblPass1.setBounds(16, 55, 86, 16);
		add(lblPass1);
		
		JLabel lblSuggPassw = new JLabel("* La password deve essere composta da almeno 8 caratteri");
		lblSuggPassw.setFont(new Font("Arial", Font.ITALIC, 10)); 
		lblSuggPassw.setBounds(16, 78, 320, 16);
		add(lblSuggPassw);
		
		JLabel lblPass2 = new JLabel("Reinserisci la password:");
		lblPass2.setBounds(16, 106, 158, 16);
		add(lblPass2);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(16, 134, 86, 16);
		add(lblEmail);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(16, 162, 99, 16);
		add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome:");
		lblCognome.setBounds(16, 190, 137, 16);
		add(lblCognome);
		
		JLabel lblCf = new JLabel("Codice fiscale:");
		lblCf.setBounds(16, 218, 118, 16);
		add(lblCf);
		
		JLabel lblTelefono = new JLabel("Numero di telefono:");
		lblTelefono.setBounds(16, 246, 158, 16);
		add(lblTelefono);
		
		JLabel lblCellulare = new JLabel("Numero di cellulare:");
		lblCellulare.setBounds(16, 276, 137, 16);
		add(lblCellulare);
		
		JLabel lblTipo = new JLabel("Tipo cliente:");
		lblTipo.setBounds(16, 306, 137, 16);
		add(lblTipo);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(181, 21, 158, 26);
		add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPass1 = new JPasswordField();
		txtPass1.setBounds(181, 50, 158, 26);
		add(txtPass1);
		txtPass1.setColumns(10);
		
		txtPass2 = new JPasswordField();
		txtPass2.setBounds(181, 101, 158, 26);
		add(txtPass2);
		txtPass2.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(181, 129, 158, 26);
		add(txtEmail);
		txtEmail.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setBounds(181, 157, 158, 26);
		add(txtNome);
		txtNome.setColumns(10);
		
		txtCognome = new JTextField();
		txtCognome.setBounds(181, 185, 158, 26);
		add(txtCognome);
		txtCognome.setColumns(10);
		
		txtCf = new JTextField();
		txtCf.setBounds(181, 213, 158, 26);
		txtCf.setColumns(10);
		txtCf.setDocument(new JTextFieldLimit(16));
		add(txtCf);
				
		txtTelefono = new JTextField();
		txtTelefono.setBounds(181, 241, 158, 26);
		add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtCellulare = new JTextField();
		txtCellulare.setBounds(181, 271, 158, 26);
		add(txtCellulare);
		txtCellulare.setColumns(10);
		
		btnConferma = new JButton("Conferma");
		btnConferma.setBounds(16, 348, 117, 29);
		add(btnConferma);
		
		btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBounds(219, 348, 117, 29);
		add(btnAnnulla);

		final String[] tipoCliente = {
			"Occasionale", "Musicista professionista", "Titolare scuola di musica"
		};
		
		txtTipoCliente = new JComboBox<String>();
		txtTipoCliente.setModel(new DefaultComboBoxModel<String>(tipoCliente));
		txtTipoCliente.setBounds(181, 302, 158, 26);
		add(txtTipoCliente);

		icoUsername = new JLabel();
		icoUsername.setBounds(351, 26, 61, 16);
		add(icoUsername);
		
		icoPass1 = new JLabel();
		icoPass1.setBounds(351, 55, 61, 16);
		add(icoPass1);
		
		icoPass2 = new JLabel();
		icoPass2.setBounds(351, 106, 61, 16);
		add(icoPass2);
		
		icoEmail = new JLabel();
		icoEmail.setBounds(351, 134, 61, 16);
		add(icoEmail);
		
		icoNome = new JLabel();
		icoNome.setBounds(351, 162, 61, 16);
		add(icoNome);
		
		icoCognome = new JLabel();
		icoCognome.setBounds(351, 190, 61, 16);
		add(icoCognome);
		
		icoCf = new JLabel();
		icoCf.setBounds(351, 218, 61, 16);
		add(icoCf);
		
		icoTelefono = new JLabel();
		icoTelefono.setBounds(351, 246, 61, 16);
		add(icoTelefono);
	}
	
    public void aggiungiListener(ActionListener lst, int tipo){
    	if(tipo == 1) btnConferma.addActionListener(lst);
    	else if(tipo == 2) btnAnnulla.addActionListener(lst);
    }
	
	public String getCf(){
		return txtCf.getText();
	}
	
	public String getUsername(){
		return txtUsername.getText();
	}
	
	public String getPsw(){
		return String.valueOf(txtPass1.getPassword());
	}
	
	public String getPswConfirmed(){
		return String.valueOf(txtPass2.getPassword());
	}
	
	public String getNome(){
		return txtNome.getText();
	}
	
	public String getCognome(){
		return txtCognome.getText();
	}
	
	public String getEmail(){
		return txtEmail.getText();
	}
	
	public String getTelefono(){
		return txtTelefono.getText();
	}	
	
	public String getCellulare(){
		return txtCellulare.getText();
	}	
	
	public String getTipoCliente(){
		return txtTipoCliente.getSelectedItem().toString();
	}
	
	public void setIcoRegistrazione(int tipo, ImageIcon ico){
		switch(tipo){
			case 1:
				icoUsername.setIcon(ico);
				break;
			case 2:
				icoPass1.setIcon(ico);
				break;
			case 3:
				icoPass2.setIcon(ico);
				break;
			case 4:
				icoEmail.setIcon(ico);
				break;
			case 5:
				icoNome.setIcon(ico);
				break;
			case 6:
				icoCognome.setIcon(ico);
				break;
			case 7:
				icoCf.setIcon(ico);
				break;
			case 8:
				icoTelefono.setIcon(ico);
				break;
		}
	}
 
}
