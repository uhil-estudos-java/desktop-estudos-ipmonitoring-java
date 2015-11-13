/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ip.monitoring.teste;

import br.com.ip.monitoring.getIp.PegandoIps;
import br.com.ip.monitoring.mail.SendMail;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author uhitlei.barbosa
 */
public class IpTeste {

  public void testes() {
  }

  public static void main(String[] args) {


    long TEMPO = (1000 * 3); // chama o método a cada 3 segundos 
    Timer timer = null;

    if (timer == null) {
      timer = new Timer();
      TimerTask tarefa = new TimerTask() {
        public void run() {
          try {
            
            //chama o método
            
/*************************************************************************/
            String Teste = "192;168;1;178;TESTE";
            String ArrayTes[] = Teste.split(";");

            for (int i = 0; i < ArrayTes.length; i++) {
              System.out.println(ArrayTes[i]);
            }
/*************************************************************************/
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      };
      timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
    }

  }
  //PegandoIps pegue = new PegandoIps();		 
  ///System.out.println(pegue.ipMaquina());
  // new SendMail().sendMail("sisbiju@gmail.com", "uhitlei@gmail.com", "TESTE DE EMAIL PELO APLIC JAVA", "Email Enviado Pelo prog Java");

}
