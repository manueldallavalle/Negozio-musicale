package model;

public enum EnumQuery {
    ACCESSO_LOGIN("SELECT 1 FROM cliente WHERE nomeutente = ? AND password = ?;"),
	GET_CLIENTE("SELECT C.nomeutente, C.cf, C.nome, C.cognome, C.email, C.ntelefono, C.ncellulare, C.tipo "
			+ "FROM cliente AS C "
			+ "WHERE C.nomeutente = ?;"),
    CHECK_USERNAME("SELECT 1 FROM cliente WHERE nomeutente = ?;"),
    INSERISCI_CLIENTE("INSERT INTO cliente VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)"),
	RICERCA_STRUMENTO_PREZZO("SELECT * FROM Strumento WHERE prezzo = ?"),
    RICERCA_STRUMENTO_MODELLO("SELECT * FROM Strumento WHERE tipo = ?"),
    RICERCA_STRUMENTO_CATEGORIA("SELECT * FROM Strumento WHERE categoria ilike ?"),
    CREA_STRUMENTOSPECIALI("INSERT INTO strumentospeciale " 
    						+ "SELECT strumento.codice, nome, prezzo/2, datapresenzasito, peso,descrizione, tipo, 0, livello, usato, categoria, pezziSconto "
    						+ "from strumento " 
    						+ "order by codice " 
    						+ "limit 1 offset ?;"),
    ELIMINA_STRUMENTISPECIALI("DELETE FROM strumentospeciale"),
    GET_STRUMENTO("SELECT S.codice,S.nome,S.prezzo FROM Strumento S"),
    GET_STRUMENTOSPECIALE("SELECT S.codice,S.nome,S.prezzo FROM Strumentospeciale S"),
    GET_DETTAGLI_STRUMENTI("SELECT codice, nome, prezzo, datapresenzasito, peso, descrizione, tipo, sconto, livello, usato, categoria, pezzisconto FROM Strumento WHERE codice = ?"),
    GET_DETTAGLI_STRUMENTISPECIALE("SELECT codice, nome, prezzo, datapresenzasito, peso, descrizione, tipo, sconto, livello, usato, categoria, pezzisconto FROM Strumentospeciale WHERE codice = ?"),
	INSERISCI_STRUMENTO("INSERT INTO Strumento (categoria,nome,prezzo,datapresenzasito,peso,descrizione,tipo)  VALUES (?, ?, ?, ?, ?, ?, 1);"),
	INSERISCI_IMG_STRUMENTO("INSERT INTO immstrumento VALUES ( ?, ? );"),
	INSERISCI_VENDITA("INSERT INTO venditastrumento VALUES (DEFAULT, ? , ? , ?);"),
	GET_IMMAGINI_STRUMENTO("SELECT link FROM immstrumento WHERE strumento = ?"),
	INSERISCI_ACQUISTO("INSERT INTO acquisto VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? );"),
	ESTRAI_ID_STRUMENTI("SELECT codice FROM STRUMENTO"),
	GET_STRUMENTI_DA_CLIENTE("SELECT email FROM Strumento S JOIN venditastrumento VS ON S.codice = vs.strumento JOIN cliente c ON vs.cliente = c.nomeutente WHERE vs.strumento = ?"),
	CHECK_CLIENTE_SPESO_3000("SELECT 1 "
							+ "FROM acquisto "
							+ "WHERE (clienteacquisto = ?) AND (EXTRACT(YEAR FROM dataacquisto) = EXTRACT(YEAR FROM current_date)) "
							+ "GROUP BY clienteacquisto,dataacquisto "
							+ "HAVING (SUM(prezzo) >= 3000);"
							);
	
    private String qVal;
    
    private EnumQuery(String qVal) {
        this.qVal = qVal;
    }

    public String getValore() {
        return this.qVal;
    }
    
}