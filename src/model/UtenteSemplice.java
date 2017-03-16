package model;

public class UtenteSemplice implements Utente{
	private String nomeUtente;
	private String cf;
	private String nome;
	private String cognome;
	private String email;
	private String ntelefono;
	private String ncellulare;
	private String indirizzoIp;
	
	public UtenteSemplice(String nomeUtente,String cf,String nome, String cognome, String email, String ntelefono, String ncellulare, String indirizzoIp){
		this.nomeUtente = nomeUtente;
		this.cf = cf;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.ntelefono = ntelefono;
		this.ncellulare = ncellulare;
		this.indirizzoIp = indirizzoIp;
	}

	@Override
	public String getUsername(){
		return nomeUtente;
	}
	
	@Override
	public String getCf(){
		return cf;
	}
	
	@Override
	public String getNome(){
		return nome;
	}
	
	@Override
	public String getCognome(){
		return cognome;
	}
	
	@Override
	public String getNomeCognome(){
		return nome + " " + cognome;
	}
	
	@Override
	public String getTipoCliente(){
		return "Utente semplice";
	}
	
	@Override
	public String getIndirizzoIP(){
		return indirizzoIp;
	}
	
	@Override
	public String getEmail(){
		return email;
	}
	
	@Override
	public String getTelefono(){
		return ntelefono;
	}
	
	@Override
	public String getCellulare(){
		return ncellulare;
	}
}
