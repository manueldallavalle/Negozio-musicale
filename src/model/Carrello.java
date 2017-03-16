package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Carrello {
	private static Carrello carrello = null;
	private ArrayList<Strumento> strumenti;
	
	protected Carrello(){
		strumenti = new ArrayList<Strumento>();
	}
	
	public static Carrello getCarrello() {
		if(carrello == null) {
			carrello = new Carrello();
		}
		return carrello;
	}
	
	public static void setStrumenti(ArrayList<Strumento> arr){
		getCarrello().strumenti = arr;
	}

	public static void aggiungiStrumento(Strumento str){
		getCarrello().strumenti.add(str);
	}
	
	public static void rimuoviStrumento(int indice){
		getCarrello().strumenti.remove(indice);
	}
	
	public static ArrayList<Strumento> getStrumenti(){
		return getCarrello().strumenti;
	}
	
	public static float getPrezzoTotale(boolean titotareMusica){
		float prezzoFinale = 0;
		Iterator<Strumento> itr = getCarrello().strumenti.iterator();
		ArrayList<Strumento> strumentiFatti = new ArrayList<Strumento>();
	    while (itr.hasNext()) {
	    	Strumento tmp = itr.next();
	    	if(!strumentiFatti.contains(tmp)){
	    		strumentiFatti.add(tmp);
		    	int quant = Carrello.getNStrumenti(tmp);
		    	float totProdotti = quant * tmp.getPrezzo();
		    	if(titotareMusica && quant >= tmp.getPezziSconto()) totProdotti = (totProdotti) - (totProdotti * tmp.getSconto())/100;
		    	prezzoFinale += totProdotti;
	    	}
	    }
	    return prezzoFinale;
	}

	public static float getPrezzoTotaleScontato(){
		float prezzoFinale = 0;
		Iterator<Strumento> itr = getCarrello().strumenti.iterator();
	    while (itr.hasNext()){
	    	prezzoFinale += itr.next().getPrezzoScontato();
	    }
	    return prezzoFinale;
	}
	
	public static int getTotStrumenti(){
		return getCarrello().strumenti.size();
	}
	
	public static Strumento getStrumentoByIndex(int indice){
		return getCarrello().strumenti.get(indice);
	}
	
	public static Strumento getStrumentoByCodice(int codiceStrumento){
		Strumento tmp = null;
		Iterator<Strumento> itr = getCarrello().strumenti.iterator();
	    while (itr.hasNext() && tmp == null) {
	    	Strumento curr = itr.next();
	    	if(curr.getCodiceStrumento() == codiceStrumento) tmp = curr;
	    }
	    return tmp;
	}
		
	public static int getNStrumenti(Strumento str){
		int cont = 0;
		Iterator<Strumento> itr = getCarrello().strumenti.iterator();
	    while (itr.hasNext()) {
	    	if(itr.next().equals(str)) cont++;
	    }
	    return cont;
	}
	
	public static void aggiornaQuantita(int quant, int indice){
		Strumento str = getCarrello().strumenti.get(indice);
		Iterator<Strumento> itr = getCarrello().strumenti.iterator();
		int cont = 0;
	    while (itr.hasNext()) {
	    	if(itr.next().equals(str)){
	    		cont++;
	    		if(cont > quant) itr.remove();
	    	}
	    }

	    int daAgg = (quant - cont);
	    if(daAgg > 0) for(int i = 0; i < daAgg; i++) Carrello.aggiungiStrumento(str);
	}
	
	public static void svuotaCarrello(){
		getCarrello().strumenti.clear();
	}
}