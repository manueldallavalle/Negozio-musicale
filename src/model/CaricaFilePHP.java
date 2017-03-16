package model;

import java.io.File;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;
import java.net.URISyntaxException;

public class CaricaFilePHP {
  public static String inviaFile(File urlFile){
	try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
	    URIBuilder builder = new URIBuilder("http://musicaunivr.netsons.org/imgUpload.php");
	    // LINK FINALE http://musicaunivr.esy.es/imgStrumenti/<NOME FILE>
	    HttpPost httppost = new HttpPost(builder.build());
	    //File fileImg = new File(urlFile);
	    File fileImg = urlFile;

	    MultipartEntityBuilder mpEntity = MultipartEntityBuilder.create();        
	    mpEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
	    FileBody fileBody = new FileBody(fileImg);
	    mpEntity.addPart("userfile", fileBody); 
	    
	    HttpEntity entityFin = mpEntity.build();
	    
	    httppost.setEntity(entityFin);
	    //System.out.println("executing request " + httppost.getRequestLine());
	    HttpResponse response = httpClient.execute(httppost);
	    HttpEntity resEntity = response.getEntity();

	    //System.out.println(response.getStatusLine());
	    String urlFileOk = null;
	    
	    if (resEntity != null){
	    	urlFileOk = EntityUtils.toString(resEntity);
	    	EntityUtils.consume(resEntity);
	    }
	    return urlFileOk;
	} catch (IOException | URISyntaxException e) {
		System.out.println("Errore nel caricamento del file!");
		System.out.println("ERRORE = "  + e.getMessage());
		return "-1";
	}
  }
}