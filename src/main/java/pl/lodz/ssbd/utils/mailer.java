/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.utils;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;



/**
 *
 * @author Jakub Kępa 180582
 */

public final class mailer {

      




 public static void wyslij(String email, String temat, String tresc)
 {   
  try{
   InitialContext ctx = new InitialContext();
   Session sesja;
   sesja = (Session) ctx.lookup("mail/mailer");



  MimeMessage wiadomosc = new MimeMessage(sesja);
  wiadomosc.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
  wiadomosc.setSubject(temat);
  wiadomosc.setText(tresc);
  Transport.send(wiadomosc);
  }
  catch(AddressException a){
      Logger.getLogger("Bład adresu");
  }
  catch(MessagingException a){
      Logger.getLogger("Bład wysyłania");
  }  
  catch (NamingException ex) {
      Logger.getLogger("Bład NamingException");
     }
  Logger.getLogger("Wysłano");
 }
 public static void wyslijPoZarejestrowaniu(String email, String login, String haslo){
     String temat="Potwierdzenie rejestracji w naszym przecudnym serwisie";
     String tresc="Dziekujemy za rejestracje! \nTwoje konto musi zostać potwierdzone przez administratora \nJak na razie twój login to: "+login+"\nA twoje haslo to: "+ haslo+"\nŻyczymy miłego dnia (chociaż, nim administrator potwierdzi twoje konto to warto już życzyć  co najmniej szczęśliwego nowego roku).";
     wyslij(email, temat, tresc);
 }
 public static void wyslijPoAktywacji(String email, String login){
     String temat="Twoje konto jest już aktywne";
     String tresc="Twoje konto zostało własnie aktywowane!\nMożesz już się zalogować za pomocą loginu "+login+" oraz hasla które podałeś podczas rejestracji";
     wyslij(email, temat, tresc);
 }
  public static void wyslijPoZablokowaniu(String email, String login){
     String temat="Twoje konto jest zablokowane";
     String tresc="Twoje konto zostało właśnie zablokowane\nSkontaktuj się z nami jeśli nie wiesz o co chodzi";
     wyslij(email, temat, tresc);
 }
 
}

 

