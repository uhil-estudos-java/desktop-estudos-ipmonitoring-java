/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ip.monitoring.teste;

/**
 *
 * @author uhitlei.barbosa
 */
import java.awt.*;
import java.util.regex.*;
import javax.swing.*;
 
import java.awt.*;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
 
public class RegExpIP {
 
    static final Pattern pat = Pattern.compile("\\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b");
    
    static boolean checkString(String s) {
        Matcher m = pat.matcher(s);
        return m.matches();
    }
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Test IP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        JTextField ipField = new JFormattedTextField();
        final JButton button = new JButton("Button");
        button.setEnabled(false);
        
        ipField.getDocument().addDocumentListener(new DocumentListener() {
            void checkDocument(DocumentEvent e) {
                try {
                    String text = e.getDocument().getText(0, e.getDocument().getLength());
                    button.setEnabled(checkString(text));
                } catch (BadLocationException ex) {
                    //Do something, OK?
                }
            }
            public void insertUpdate(DocumentEvent e) {
                checkDocument(e);
            }
            public void removeUpdate(DocumentEvent e) {
                checkDocument(e);
            }
            public void changedUpdate(DocumentEvent e) {
                checkDocument(e);
            }
        });
 
        ipField.setInputVerifier(new InputVerifier() {
            public boolean shouldYieldFocus(JComponent input) {
                boolean inputOK = verify(input);
                if (inputOK) {
                    return true;
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    return false;
                }
            }
            public boolean verify(JComponent input) {
                JTextField field = (JTextField) input;
                return checkString(field.getText());
            }
        });
        frame.add(ipField, BorderLayout.NORTH);
        frame.add(button, BorderLayout.CENTER);
        frame.add(new JButton("Another Button"), BorderLayout.SOUTH);        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
    }
}