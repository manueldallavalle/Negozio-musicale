package model;

public class UtenteDecorator implements Utente{
	protected Utente utente;

	public UtenteDecorator(Utente ut){
		this.utente = ut;
	}

	@Override
	public String getUsername() {
		return this.utente.getUsername();
	}

	@Override
	public String getCf() {
		return this.utente.getCf();
	}

	@Override
	public String getNome() {
		return this.utente.getNome();
	}

	@Override
	public String getCognome() {
		return this.utente.getCognome();
	}

	@Override
	public String getNomeCognome() {
		return this.utente.getNomeCognome();
	}

	@Override
	public String getTipoCliente() {
		return this.utente.getTipoCliente();
	}

	@Override
	public String getIndirizzoIP() {
		return this.utente.getIndirizzoIP();
	}

	@Override
	public String getEmail() {
		return this.utente.getEmail();
	}

	@Override
	public String getTelefono() {
		return this.utente.getTelefono();
	}

	@Override
	public String getCellulare() {
		return this.utente.getCellulare();
	}
}