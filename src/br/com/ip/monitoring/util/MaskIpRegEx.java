/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ip.monitoring.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author uhitlei.barbosa
 */
public class MaskIpRegEx extends PlainDocument{
  
  int Tamnho;
  String Expression;

  public MaskIpRegEx() {
    this.Expression = "^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})$";
    this.Tamnho = 15;
  }
   
  private boolean validaIpdigit(){
    return true;
  }
  
@Override
  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
   super.insertString(offset, str.replaceAll(Expression, ""), attr);
  }

  public void replace(int offset, String str, AttributeSet attr) throws BadLocationException {

    super.insertString(offset, str.replaceAll(Expression, ""), attr);
  }
}