



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.beans;

import java.util.logging.Logger;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author Jakub Kępa 180582
 */
public class autoryzacjaSMTP  extends javax.mail.Authenticator
 {
    private String login;
    private String haslo;
    public autoryzacjaSMTP (String adresNad,String haslo)
    {
        this.login=adresNad;
        this.haslo=haslo;
    }
        
  public PasswordAuthentication getPasswordAuthentication()
  {
   try
   {

    return new PasswordAuthentication(login , haslo);
   }catch (Exception e) {
       Logger.getLogger("Błąd autoryzacji SMTP");
   }
   return null;
  }
 }


