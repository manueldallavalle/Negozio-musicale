package model;

public class UtenteScuola extends UtenteDecorator{
	public UtenteScuola(Utente ut) {
		super(ut);
	}

	@Override
	public String getTipoCliente(){
		return "Titolare scuola di musica";
	}
}