/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ip.monitoring.teste;

/**
 *
 * @author uhitlei.barbosa
 */
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/*
 * Created on 12/12/2003
 *
 *  PlainDocument que aceita apenas valores numericos
 * e usa o '.' como separador decimal
 */
/**
 * @author hempx
 */
public class DocumentNumber extends PlainDocument {

  private int maxLength;
  private int ponto; // posição do separador decimal, -1 quando ele não foi inserido ainda.

  public DocumentNumber(int maxLength) {
    super();
    this.maxLength = maxLength;
    this.ponto = -1;
  }

  public void insertString(int offset, String str, AttributeSet attr)
          throws BadLocationException {
    if (str == null) {
      return;
    }

    if (maxLength <= 0) {
      super.insertString(offset, str, attr);
    }

    int ilen = getLength() + str.length();

    if (ilen <= maxLength) {
      if (verificaInput(str, offset)) {
        super.insertString(offset, str, attr);
      } else {
        return;
      }
    } else {
      if (getLength() == maxLength) {
        return;
      }
      String newStr = str.substring(0, (maxLength - getLength()));
      if (verificaInput(newStr, offset)) {
        super.insertString(offset, newStr, attr);
      } else {
        return;
      }

    }

  }

  public void remove(int offs, int len) throws BadLocationException {

    if (ponto >= offs && ponto < (offs + len)) {
      ponto = -1;
    } else if (ponto != -1 && (offs + len) <= ponto) {
      ponto = ponto - len;
    }
    super.remove(offs, len);


  }

  private boolean verificaInput(String str, int offset) {
    char[] tmp = str.toCharArray();


    for (int i = 0; i < tmp.length; i++) {
      if (tmp[i] == '.' && ponto != -1) {
        return false;
      }

      switch (tmp[i]) {
        case '1':
          break;
        case '2':
          break;
        case '3':
          break;
        case '4':
          break;
        case '5':
          break;
        case '6':
          break;
        case '7':
          break;
        case '8':
          break;
        case '9':
          break;
        case '0':
          break;
        case '.':
          ponto = offset + i;
          break;
        default:
          return false;

      }
    }

    return true;

  }
}
