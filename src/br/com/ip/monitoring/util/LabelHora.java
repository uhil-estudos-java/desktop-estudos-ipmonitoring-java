/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ip.monitoring.util;

/**
 *
 * @author uhitlei.barbosa
 */
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LabelHora extends JPanel {
    // Local onde atualizaremos a hora
    private JLabel lblHora;

    // Formatador da hora
    private static final DateFormat FORMATO = new SimpleDateFormat("HH:mm:ss");

    public LabelHora() {
        // Construímos nosso frame
       // super("Exemplo");
        setLayout(new FlowLayout());
        this.add(getLblHora());
      //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 75);
        setVisible(true);

        // Iniciamos a thread do relógio. Tornei uma deamon thread para que seja
        // automaticamente finalizada caso a aplicação feche.
        Thread clockThread = new Thread(new ClockRunnable(), "Clock thread");
        clockThread.setDaemon(true);
        clockThread.start();
    }

    private JLabel getLblHora() {
        if (lblHora == null) {
            lblHora = new JLabel();
        }
        return lblHora;
    }

    /**
     * Método para atualizar a hora no formulário. Não é thread-safe, portanto,
     * se o utilizado fora da thread da AWT, deve-se utilizar um dos comandos da
     * EventQueue (invokeLater ou invokeAndWait).
     */
    public void setHora(Date date) {
        getLblHora().setText(FORMATO.format(date));
    }

    /**
     * Runnable que contém o código que atuará na nossa thread. Basicamente, ele
     * chama o método setHora de segundo em segundo, passando a data atual.
     */
    private class ClockRunnable implements Runnable {
        public void run() {
            try {
                while (true) {
                    // Aqui chamamos o setHora através da EventQueue da AWT.
                    // Conforme dito, isso garante Thread safety para o Swing.
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            // Só podemos chamar setHora diretamente dessa
                            // forma, pois esse Runnable é uma InnerClass não
                            // estática.
                            setHora(new Date());
                        }
                    });
                    // Fazemos nossa thread dormir por um segundo, liberando o
                    // processador para outras threads processarem.
                    Thread.sleep(1000);
                }
            }
            catch (InterruptedException e) {
            }
        }
    }

 //   public static void main(String args[]) {
  //      new LabelHora();
  //  }
}
