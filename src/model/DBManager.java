package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBManager {
	private static Connection conn = null;
	
	// Implementazione del gestore connessioni con il pattern singleton
    public static Connection getConnessione() {
    	String url = "jdbc:postgresql://ec2-176-34-103-75.eu-west-1.compute.amazonaws.com:5432/";
        String dbName = "d6538kiobf5smu";
        String userName = "irokwufsjhrwnm";
        String password = "J-lTVyRt_YIjz9GJy2pGEnIec5";
		try {
			if(conn == null) conn = DriverManager.getConnection(url + dbName + "?sslmode=require", userName,password);
			//conn = DriverManager.getConnection(url + dbName);
		} catch (SQLException e) {
			System.out.println("Errore nella connessione al DB");
			System.out.println("Errore = " + e.getMessage());
		}
        return conn;
    }
    
    public static void chiudiConnessione(Connection conn) {
        try { conn.close(); } catch (SQLException e) { }
    }
        
    public static List<HashMap<String,Object>> selectQuery(String query, String...parametri) {
    	List<HashMap<String,Object>> listaRisultati = new ArrayList<HashMap<String,Object>>();
    	Connection conn = getConnessione();
    	ResultSet rs = null;
    	ResultSetMetaData metaData = null;
    	
    	try (PreparedStatement pst = conn.prepareStatement(query)) {
    		// Inserimento parametri
	    	for(int i = 0; i < parametri.length; i++){
	    		if(Funzioni.isTimestampCorretto(parametri[i].toString())){
	    			pst.setTimestamp(i+1, Timestamp.valueOf(parametri[i].toString()));
	    		}else if(Funzioni.isBoolean(parametri[i].toString())){
	    			pst.setBoolean(i+1, Boolean.getBoolean(parametri[i].toString()));
	    		}else if(Funzioni.isDouble(parametri[i].toString())){
	    			pst.setDouble(i+1, Double.parseDouble(parametri[i].toString()));
	    	    }else if(Funzioni.isInteger(parametri[i].toString())){
	    			pst.setInt(i+1, Integer.parseInt(parametri[i].toString()));
	    		}else{
	    			pst.setString(i+1, parametri[i].toString());
	    		}
	    	}
	    	
        	rs = pst.executeQuery();
        	
        	metaData = rs.getMetaData();
        	int colonne = metaData.getColumnCount();
        	
            while (rs.next()) {
            	HashMap<String,Object> row = new HashMap<String, Object>(colonne);
                for (int i = 1; i <= colonne; i++) {
                	String isNull = (rs.getObject(i) == null ? "" : rs.getObject(i).toString());
                	row.put(metaData.getColumnName(i),isNull);
                }
                listaRisultati.add(row);
            }
            
        }catch (SQLException e) {
            System.out.println("Errore nell'esecuzione della query");
            System.out.println("Errore = " + e.getMessage());
        }
    	
    	return listaRisultati;
    }
    
    public static int updateQuery(String query, Boolean ritornaIdGenerato, Object...parametri) {
    	Connection conn = getConnessione();
    	try (PreparedStatement pst = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
    		// Inserimento parametri
	    	for(int i = 0; i < parametri.length; i++){
	    		if(Funzioni.isTimestampCorretto(parametri[i].toString())){
	    			pst.setTimestamp(i+1, Timestamp.valueOf(parametri[i].toString()));
	    		}else if(Funzioni.isBoolean(parametri[i].toString())){
	    			pst.setBoolean(i+1, Boolean.getBoolean(parametri[i].toString()));
	    		}else if(Funzioni.isDouble(parametri[i].toString())){
	    			pst.setDouble(i+1, Double.parseDouble(parametri[i].toString()));
	    	    }else if(Funzioni.isInteger(parametri[i].toString())){
	    			pst.setInt(i+1, Integer.parseInt(parametri[i].toString()));
	    		}else{
	    			pst.setString(i+1, parametri[i].toString());
	    		}
	    	}
	    	int ris = pst.executeUpdate();
	    	
	    	if(ritornaIdGenerato) {
			   ResultSet rs = pst.getGeneratedKeys();
			   rs.next();
			   return (rs.getInt(1));
	    	}else{
	    		return ris;
	    	}
        }catch (SQLException e) {
            System.out.println("Errore nell'esecuzione della query");
            System.out.println("Errore = " + e.getMessage());
        }
    	return -1;
    }
    
    public static boolean CheckEsiste(String query, String...parametri){
		try {
			return !(selectQuery(query, parametri).isEmpty());
		} catch (Exception e) {
			return false;
		}
    }
}