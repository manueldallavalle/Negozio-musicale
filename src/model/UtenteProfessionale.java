package model;

public class UtenteProfessionale extends UtenteDecorator{
	public UtenteProfessionale(Utente ut) {
		super(ut);
	}

	@Override
	public String getTipoCliente(){
		return "Musicista professionista";
	}
}