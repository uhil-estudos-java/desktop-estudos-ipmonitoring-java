/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ip.monitoring.getIp;

/**
 *
 * @author uhitlei.barbosa
 */
import br.com.ip.monitoring.sites.VerificaSite;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PegandoIps {		
	
	String ipLocal = "";
	String ipExterno = "";
	String linhas = null;
	Matcher m;

	public String ipMaquina(){ 					 		 
		 
		 try{			
			 
			InetAddress end = InetAddress.getLocalHost();
		 	ipLocal = end.getHostAddress().toString();		
			
			URL url = new URL(new VerificaSite().verificandoSite());
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

			Pattern pega = Pattern.compile("[0-9]{1,3}[.-]{1}[0-9]{1,3}[.-]{1}[0-9]{1,3}[.-]{1}[0-9]{1,3}");

			while((linhas = buffer.readLine()) != null){

				m = pega.matcher(linhas);

				while(m.find()){

					ipExterno = m.group();
				  }
			  }

			buffer.close();
			conexao.disconnect();

		} catch(MalformedURLException e1){

			    System.exit(1);

		}catch(IOException e2){

			    System.exit(1);
		    }		 
		

		return "Meu IP Local: " + ipLocal + "\nMeu IP Externo: " + ipExterno.replace("-", ".");

	}
        
        public String getIp(){ 					 		 
		 
		 try{			
			 
			InetAddress end = InetAddress.getLocalHost();
		 	ipLocal = end.getHostAddress().toString();		
			
			URL url = new URL(new VerificaSite().verificandoSite());
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

			Pattern pega = Pattern.compile("[0-9]{1,3}[.-]{1}[0-9]{1,3}[.-]{1}[0-9]{1,3}[.-]{1}[0-9]{1,3}");

			while((linhas = buffer.readLine()) != null){

				m = pega.matcher(linhas);

				while(m.find()){

					ipExterno = m.group();
				  }
			  }

			buffer.close();
			conexao.disconnect();

		} catch(MalformedURLException e1){

			    System.exit(1);

		}catch(IOException e2){

			    System.exit(1);
		    }		 
		

		return ipExterno.replace("-", ".");

	}
}

