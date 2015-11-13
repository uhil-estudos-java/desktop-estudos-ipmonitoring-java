/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ip.monitoring.util;

import java.util.regex.Pattern;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author uhitlei.barbosa
 */
public class MascaraIP extends PlainDocument {

  int Tamnho;
  String Expression;

  public MascaraIP() {
    this.Expression = "^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})$";
    this.Tamnho = 15;
  }

  
  private boolean validaIpdigit(){
    return true;
  }
  
  
  @Override
  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
    int ilen = (getLength() + str.length());
    String fullString = str ;
    
//    if(getText(0, ilen-1)!=null){
//      fullString = getText(0, ilen-1);
//    }else{
//      fullString=str;
//    }
    boolean valid = Pattern.matches(Expression,fullString);
    String arrayCount[] = (fullString.replace(".", ";")).split(";");
    
    if(valid){
      super.insertString(offset, str.replaceAll("[^0-9|.]", ""), attr);
      return;
    }
    
    if (ilen <= this.Tamnho) {
      
      if(arrayCount.length == 4 && arrayCount[3].length()==3){
        return;
      }else if(arrayCount.length == 4 && arrayCount[3].length()<3 && str.endsWith(".")){//Não Aceitar ponto na ultima casa
        return;
      }else if(arrayCount.length == 3 && arrayCount[2].length()== 3 && !str.endsWith(".") && !fullString.endsWith(".")){//Acrescenta '.' quando a 3 casa chega a 3 digitos
        str = (".")+str;
      }else if(arrayCount.length == 2 && arrayCount[1].length()== 3 && !str.endsWith(".") && !fullString.endsWith(".")){//Acrescenta '.' quando a 2 casa chega a 3 digitos
        str = (".")+str;
      }
      
     if(fullString.endsWith(".") && str.endsWith(".") ){// Não aceita Ponto se já tiver digitado ponto
       return;
     }else{
       
     }
     
      if (ilen == 3) {
        
        if (!str.equalsIgnoreCase(".")&& !fullString.contains(".") ){
          str += (".");
        }
      }


      super.insertString(offset, str.replaceAll("[^0-9|.]", ""), attr);
    } else {
      return;
    }

    // super.insertString(offset, str.replaceAll("[^0-9|.]", ""), attr);
  }

  public void replace(int offset, String str, AttributeSet attr) throws BadLocationException {

    int ilen = (getLength() + str.length());
    String fullString = str ;
    
//    if(getText(0, ilen-1)!=null){
//      fullString = getText(0, ilen-1);
//    }else{
//      fullString=str;
//    }
    boolean valid = Pattern.matches("[^0-9|.]",fullString);
    String arrayCount[] = (fullString.replace(".", ";")).split(";");
    
    
    if(valid){
      super.insertString(offset, str.replaceAll("[^0-9|.]", ""), attr);
      return;
    }
    
    if (ilen <= this.Tamnho) {
      
      if(arrayCount.length == 4 && arrayCount[3].length()==3){
        return;
      }else if(arrayCount.length == 4 && arrayCount[3].length()<3 && str.endsWith(".")){//Não Aceitar ponto na ultima casa
        return;
      }else if(arrayCount.length == 3 && arrayCount[2].length()== 3 && !str.endsWith(".") && !fullString.endsWith(".")){//Acrescenta '.' quando a 3 casa chega a 3 digitos
        str = (".")+str;
      }else if(arrayCount.length == 2 && arrayCount[1].length()== 3 && !str.endsWith(".") && !fullString.endsWith(".")){//Acrescenta '.' quando a 2 casa chega a 3 digitos
        str = (".")+str;
      }
      
     if(fullString.endsWith(".") && str.endsWith(".") ){// Não aceita Ponto se já tiver digitado ponto
       return;
     }else{
       
     }
     
      if (ilen == 3) {
        
        if (!str.equalsIgnoreCase(".")&& !fullString.contains(".") ){
          str += (".");
        }
      }


      super.insertString(offset, str.replaceAll("[^0-9|.]", ""), attr);
    } else {
      return;
    }
  }
}
