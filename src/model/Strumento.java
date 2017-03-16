package model;

import java.net.URL;
import java.util.ArrayList;

public class Strumento {
	private int codice;
	private String categoria;
	private String nome;
	private float prezzo;
	private String dataPresenzaSito;
	private float peso;
	private String descrizione;
	private int tipo;
	private float sconto;
	private String livello;
	private boolean usato;
	private ArrayList<URL> immagini;
	private int nPezziSconto;
	
	public Strumento(int codice,String categoria, String nome,Float prezzo, String dataPresenzaSito, Float peso, String descrizione, int tipo, float sconto, String livello, boolean usato, int nPezziSconto){
		this.codice = codice;
		this.categoria = categoria;
		this.nome = nome;
		this.prezzo = prezzo;
		this.dataPresenzaSito = dataPresenzaSito;
		this.peso = peso;
		this.descrizione = descrizione;
		this.tipo = tipo;
		this.sconto = sconto;
		this.livello = livello;
		this.usato = usato;
		this.immagini = new ArrayList<URL>();
		this.nPezziSconto = nPezziSconto;
	}
	
	public void aggiungiImg(URL img){
		immagini.add(img);
	}
	
	public ArrayList<URL> getImmagini(){
		return immagini;
	}

	public int getCodiceStrumento(){
		return codice;
	}
	
	public String getCategoria(){
		return categoria;
	}
	
	public String getNome(){
		return nome;
	}
	
	public Float getPrezzo(){
		return prezzo;
	}
	
	public String getDataPresenzaSito(){
		return dataPresenzaSito;
	}
	
	public float getPeso(){
		return peso;
	}
	
	public String getDescrizione(){
		return descrizione;
	}
	
	public int getTipo(){
		return tipo;
	}
	
	public float getSconto(){
		return sconto;
	}
	
	public float getPrezzoScontato(){
		return (float) ((float)prezzo-(prezzo*(sconto/100)));
	}
	
	public String getLivello(){
		return livello;
	}
	
	public boolean getUsato(){
		return usato;
	}
	
	public int getPezziSconto(){
		return nPezziSconto;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codice;
		result = prime * result + Float.floatToIntBits(prezzo);
		result = prime * result + Float.floatToIntBits(sconto);
		result = prime * result + (usato ? 1231 : 1237);
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Strumento other = (Strumento) obj;
		if (codice != other.codice)
			return false;
		if (Float.floatToIntBits(prezzo) != Float.floatToIntBits(other.prezzo))
			return false;
		if (Float.floatToIntBits(sconto) != Float.floatToIntBits(other.sconto))
			return false;
		if (usato != other.usato)
			return false;
		return true;
	}
	
}