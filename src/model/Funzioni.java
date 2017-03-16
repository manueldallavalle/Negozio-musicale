package model;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.ImageIcon;
import org.apache.commons.codec.binary.Hex;

public class Funzioni {
	
	public static boolean isDataCorretta(String str) {
        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            df.setLenient(false);
            df.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
	}
	
	public static boolean isTimestampCorretto(String str)
	{ 
	    SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
	    try{
	       format.parse(str);
	       return true;
	    } catch(ParseException e) {
	        return false;
	    }
	}
	
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("\\d+");
		return (pattern.matcher(str).matches());
	}
	
	public static boolean isBoolean(String str) {
		return (str.toLowerCase().equals("false") || str.toLowerCase().equals("true"));
	}
	
	public static boolean isDouble(String str) {
		try {  
			Double.parseDouble(str);
		    return (str.indexOf(".") > -1);
		} catch (NumberFormatException e) {  
			return false;  
		} 
	}
	
	public static Timestamp toTimestamp(String str) {    
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = dateFormat.parse(str);
            Timestamp tstamp = new Timestamp(date.getTime());
            return tstamp;
        } 
        catch (ParseException e) {
            return null;
        }
	}
	
	public static String formattaData(String ts)  {   
		return (ts.isEmpty() ? "" : ts.split("-")[2] + "-" + ts.split("-")[1] + "-" +  ts.split("-")[0]);
	}
	
	public static String formattaData(Timestamp ts) {    
		String dataStr = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date(ts.getTime()));
		return dataStr;
	}
	
	public static String getCurrTimestamp(){
		return (new Timestamp(new Date().getTime()).toString());
	}
	
	public static boolean isEmailCorretta(String email){
       String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
       java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
       java.util.regex.Matcher m = p.matcher(email);
       return m.matches();
	}
	
	public static String strToMD5(String pass){
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(pass.getBytes(Charset.forName("UTF8")));
			final byte[] resultByte = messageDigest.digest();
			final String result = new String(Hex.encodeHex(resultByte));
			return result;
		} catch (NoSuchAlgorithmException e) {
			return pass;
		}
	}
	
	public static String getIndirizzoIP(){
		URL url = null;
		BufferedReader in = null;
		String ipAddress = "";
		try {
		    url = new URL("http://musicaunivr.netsons.org/getIpCliente.php");
		    in = new BufferedReader(new InputStreamReader(url.openStream()));
		    ipAddress = in.readLine().trim();
		    if (!(ipAddress.length() > 0)) {
		        try {
		            InetAddress ip = InetAddress.getLocalHost();
		            System.out.println((ip.getHostAddress()).trim());
		            ipAddress = (ip.getHostAddress()).trim();
		        } catch (Exception exp) {}
		    }
		} catch (Exception ex) {}
		return ipAddress;
	}

	public static ImageIcon ridimensionaImg(ImageIcon img, int width, int heigth){
		Image ridimensionata = img.getImage().getScaledInstance(width, heigth,  java.awt.Image.SCALE_SMOOTH);
		return (new ImageIcon(ridimensionata));
	}
	
	
	public static ImageIcon scaricaImg(URL urlImg){
		ImageIcon img;
		try {
			img = new ImageIcon(ImageIO.read(urlImg));
		} catch (IOException e) {
			img = new ImageIcon("img/erroreCircolare.png");
		}
		return img;
	}
	
	public static boolean InviaMailVendita(String emailVenditore, Utente acquirente, ArrayList<Strumento> strVenduti,String pagamento, String corriere, String indirizzo, String cittaCap){
        final String user = "negoziomusicale@outlook.it";
        final String password = "elaborato_Ingegneria1";
        final String oggetto = "Vendita strumenti";

        boolean risultato = false;

        try {
        	Properties props = System.getProperties();
        	props.put("mail.smtp.starttls.enable", "true");
        	props.put("mail.smtp.host", "smtp-mail.outlook.com");

            Session session = Session.getInstance(props, null);
            //session.setDebug(true);

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(user, "NegozioMusicale Univr"));
            msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(emailVenditore, false));
            msg.setSubject(oggetto);


            StringBuffer sb = new StringBuffer();
            sb.append("<HTML>\n<HEAD>\n<TITLE>\n");
            sb.append(oggetto + "\n</TITLE>\n</HEAD>\n<BODY>\n");
            sb.append("<H1>Complimenti, i tuoi strumenti sono stati venduti</H1>\n");

            
            sb.append("<table border='1'\n");
            sb.append("<tr><td>Nome strumento</td><td>Prezzo</td><td>Quantit&agrave;</td></tr>\n");
            
    		Iterator<Strumento> itr = strVenduti.iterator();
    	    while (itr.hasNext()) {
    	    	Strumento strCurr = itr.next();
    	    	sb.append("<tr><td>" + strCurr.getNome() + "</td><td>" + strCurr.getPrezzo() +" &euro;</td><td>" + Carrello.getNStrumenti(strCurr) + "</th></tr>\n");
    	    }
    	    sb.append("</table>\n");

            sb.append("<hr />\n");
            sb.append("Username acquirente: <b>" + acquirente.getUsername() + "</b><br />\n");
            sb.append("Nome acquirente: <b>" + acquirente.getNome() + "</b><br />\n");
            sb.append("Cognome acquirente: <b>" + acquirente.getCognome() + "</b><br />\n");
            sb.append("Tipo pagamento scelto: <b>" + pagamento + "</b><br />\n");
            sb.append("<hr />\n");
            sb.append("Corriere scelto: <b>" + corriere + "</b><br />\n");
            sb.append("Indirizzo: <b>" + indirizzo + "</b><br />\n");
            sb.append("Citt&agrave;: <b>" + cittaCap + "</b><br />\n");
            sb.append("<br /><br />\n");
            sb.append("<i>Grazie per aver utilizzato il nostro negozio per la vendita dei tuoi strumenti</i>");
            
            sb.append("</BODY>\n</HTML>\n");
            msg.setDataHandler(new DataHandler(new ByteArrayDataSource(sb.toString(), "text/html")));            
            msg.setHeader("X-Mailer", "sendhtml");
            msg.setSentDate(new Date());

            Transport.send(msg,user,password);
            risultato = true;
           // System.out.println("\nMail was sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            risultato = false;
        }
        
        return risultato;
	}

}