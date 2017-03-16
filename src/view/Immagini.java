package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.*;

import model.ImageIconProxy;

public class Immagini extends JFrame{
	private static final long serialVersionUID = 1L;
		
	public Immagini(){
		super();
	}
	
	public Immagini(URL img){
		JPanel pannello = new JPanel();
		this.setPreferredSize(new Dimension(450, 450));				
		JLabel imgLbl = new JLabel();
		imgLbl.setIcon(new ImageIconProxy(img, imgLbl, 400, 400));
		imgLbl.setBounds(50,50, 400,400);
		pannello.add(imgLbl);
        this.getContentPane().add(pannello);
		avvia();
	}
	
	public void avvia(){
		this.setTitle("Immagini");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		this.setLocation ( ( screenSize.width / 2 )-270, (
		screenSize.height / 2 )-200);
		this.pack();
	}
}