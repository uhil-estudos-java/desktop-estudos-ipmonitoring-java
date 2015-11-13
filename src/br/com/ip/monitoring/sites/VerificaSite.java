/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ip.monitoring.sites;

/**
 *
 * @author uhitlei.barbosa
 */
import java.net.HttpURLConnection;
import java.net.URL;

public class VerificaSite {
	
	public String verificandoSite(){

		String[] site = {"http://www.meuenderecoip.com", "http://meuip.datahouse.com.br", 
				         "http://www.hlera.com.br/meu_ip/", "http://www.abusar.org.br",
				         "http://www.mostrarip.com.br"};

		String siteValido = "";

		for(int i = 0; i < site.length; i++){

			try {

				URL url = new URL(site[i]);
				HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

				int codigo = conexao.getResponseCode();

				if(codigo == 200){

					siteValido = site[i];
				  }

			} catch (Exception e) {
				
			       }
		}

		return siteValido;
	}
}

