package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import model.Catalogo;
import model.CatalogoDecorator;
import model.DBManager;
import model.EnumQuery;

public class CatalogoStrumentiSpeciali extends CatalogoDecorator{
	private static final long serialVersionUID = 1L;
	private int numStrumenti;
	Random random = new Random();
	
	public CatalogoStrumentiSpeciali(Catalogo cat) {
		super(cat);
		add((CatalogoStrumenti)cat);		
	}

	@Override
	public boolean isSpeciale() {
		return true;
	}
	
	@Override	
	public void creaTabStrumentiSPeciali(){
		List<HashMap<String,Object>> risultatoQuery = DBManager.selectQuery(EnumQuery.GET_STRUMENTO.getValore());
		numStrumenti = risultatoQuery.size();
		ArrayList<String> listaFatti = new ArrayList<String>();
		
		int i = 0;
		DBManager.updateQuery(EnumQuery.ELIMINA_STRUMENTISPECIALI.getValore(), false);

		int records = random.nextInt(numStrumenti-1)+1;

		while(i < records){
			String data;
			int riga;
			do{
				riga = random.nextInt(numStrumenti-1)+1;
				data = String.valueOf(riga-1);
			}while(listaFatti.contains(data));

			DBManager.updateQuery(EnumQuery.CREA_STRUMENTOSPECIALI.getValore(), true, data);
			listaFatti.add(data);
			i++;
		}
	}
}