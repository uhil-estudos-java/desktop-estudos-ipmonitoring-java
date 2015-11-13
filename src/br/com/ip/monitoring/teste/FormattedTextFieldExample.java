/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ip.monitoring.teste;

/**
 *
 * @author uhitlei.barbosa
 */
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class FormattedTextFieldExample extends JFrame {
    public FormattedTextFieldExample() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(200, 200));
        getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));

        MaskFormatter mask = null;
        try {
            //
            // Create a MaskFormatter for accepting phone number, the # symbol accept
            // only a number. We can also set the empty value with a place holder
            // character.
            //
            mask = new MaskFormatter("(###) ###-####");
            mask.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //
        // Create a formatted text field that accept a valid phone number.
        //
        JFormattedTextField phoneField = new JFormattedTextField(mask);
        phoneField.setPreferredSize(new Dimension(100, 20));

        //
        // Here we create a formatted text field that accept a date value. We
        // create an instance of SimpleDateFormat and use it to create a
        // DateFormatter instance which will be passed to the JFormattedTextField.
        //
        DateFormat format = new SimpleDateFormat("dd-MMMM-yyyy");
        DateFormatter df = new DateFormatter(format);
        JFormattedTextField dateField = new JFormattedTextField(df);
        dateField.setPreferredSize(new Dimension(100, 20));
        dateField.setValue(new Date());

        getContentPane().add(phoneField);
        getContentPane().add(dateField);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FormattedTextFieldExample().setVisible(true);
            }
        });
    }
}