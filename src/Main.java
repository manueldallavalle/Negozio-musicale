import java.awt.EventQueue;

import controller.CatalogoController;
import model.NegozioModel;
import view.CatalogoStrumenti;

public class Main {
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				NegozioModel model = new NegozioModel();
				CatalogoStrumenti view = new CatalogoStrumenti();
				@SuppressWarnings("unused")
				CatalogoController controller = new CatalogoController(model,view);
				model.cambiaInterfaccia(view, "Catalogo");
			}
		});
	}
}