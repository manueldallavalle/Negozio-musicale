package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

import model.NegozioModel;

public class Pannello extends JFrame {
	private static final long serialVersionUID = 1L;
	public Pannello(NegozioModel model){
		super();
		this.setTitle("Pannello");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		this.setLocation ( ( screenSize.width / 2 )-200, (
		screenSize.height / 2 )-350);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	model.chiudiConnessione();
		    	System.exit(0);
		    }
		});
	}
}