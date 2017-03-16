package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Login extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField nomeUtente;
    private JButton btnLogin;
    private JPasswordField passwordField;
    private JButton btnRegistra;

    public Login() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(288, 170));

        nomeUtente = new JTextField();
        nomeUtente.setBounds(135, 20, 139, 26);
        add(nomeUtente);
        nomeUtente.setColumns(10);

        JLabel lblUser = new JLabel("Nome utente:");
        lblUser.setBounds(21, 25, 111, 16);
        add(lblUser);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(21, 92, 253, 29);
        add(btnLogin);

        JLabel lblPassw = new JLabel("Password:");
        lblPassw.setBounds(21, 58, 88, 16);
        add(lblPassw);

        passwordField = new JPasswordField();
        passwordField.setBounds(135, 53, 139, 26);
        add(passwordField);

        JLabel lblRegistra = new JLabel("Nuovo utente? Clicca");
        lblRegistra.setBounds(21, 135, 132, 16);
        add(lblRegistra);

        btnRegistra = new JButton("Qui");
        btnRegistra.setBounds(153, 129, 62, 26);
        add(btnRegistra);
    }
    
    public void aggiungiListener(ActionListener lst, int tipo){
    	if(tipo == 1) btnLogin.addActionListener(lst);
    	else btnRegistra.addActionListener(lst);
    }

	public String getNomeUtente() {
		return nomeUtente.getText();
	}

	public String getPassword() {
		return String.valueOf(passwordField.getPassword());
	}
}