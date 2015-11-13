

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ip.monitoring.teste;

/**
 *
 * @author uhitlei.barbosa
 */
import java.awt.BorderLayout;  
import java.awt.Dimension;  
import java.text.ParseException;  
  
import javax.swing.JFormattedTextField;  
import javax.swing.JFrame;  
import javax.swing.JPanel;  
import javax.swing.text.MaskFormatter;  
  







public class fMask extends JFrame{  
  
  
  String Test = "STRING TESTE";
      
    private JPanel painel = null;  
    private JFormattedTextField txtIp = null;  
  
    public static void main(String[] args) throws ParseException {  
        new fMask();  
    }  
      
    public fMask() throws ParseException {  
        inicializar();  
    }  
  
  
    public void inicializar() throws ParseException{  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.getContentPane().add(getPainel());  
        this.setVisible(true);  
        this.setTitle("Exemplo Mask");  
        this.setSize(200,100);  
        this.setLocationRelativeTo(null);  
    }  
  
    public JPanel getPainel() throws ParseException {  
        if(painel == null){  
            painel = new JPanel(new BorderLayout());  
            painel.add(getTxtIp(), BorderLayout.CENTER);  
        }  
        return painel;  
    }  
  
    public JFormattedTextField getTxtIp() throws ParseException {  
        if(txtIp == null){  
            txtIp = new JFormattedTextField(new MaskFormatter("###.###.###.###"));  
            txtIp.setPreferredSize(new Dimension(100,100));  
            txtIp.setHorizontalAlignment(JFormattedTextField.CENTER);  
        }  
        return txtIp;  
    }  
} 
