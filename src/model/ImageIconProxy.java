package model;

import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ImageIconProxy extends ImageIcon implements Runnable{
	private static final long serialVersionUID = 1L;
	final static ImageIcon LOADING = new ImageIcon("img/caricamento.gif");
    ImageIcon CURRENT = LOADING;
    
    private URL fileName;
    private Object callBackObj;
    private int width;
    private int height;
        
    public ImageIconProxy(URL fileName, Object callBackObj, int width, int height){
        super(LOADING.getImage());
        this.fileName = fileName;
        this.callBackObj = callBackObj;
        this.width = width;
        this.height = height;
    	load(callBackObj);
    }
    public void load(Object callBackObj){
        this.callBackObj = callBackObj;
        new Thread(this).start();
    }
    public void run(){
        CURRENT = Funzioni.ridimensionaImg(Funzioni.scaricaImg(fileName), width, height);
        if(callBackObj instanceof JButton){
        	((JButton) callBackObj).repaint();
        	((JButton) callBackObj).revalidate();
        }else{
        	((JLabel) callBackObj).repaint();
        	((JLabel) callBackObj).revalidate();
        }
    }
    public int getIconWidth() {
        return CURRENT.getIconWidth();
    }
    public int getIconHeight(){
        return CURRENT.getIconHeight();
    }
    public synchronized void paintIcon(Component c, Graphics g, int x, int y){
        CURRENT.paintIcon(c, g, x, y);
    }
}