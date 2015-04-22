/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.utils;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author Jakub Kępa 180582
 */
public class mailer {

 private String adresNad = "ssbd05@gmail.com";
 private String hasloNad="ssbdglupcze1";
 
 
 
 public mailer (){
     
 }
 public mailer (String adresNad, String haslo){
     this.adresNad=adresNad;
     this.hasloNad=haslo;
 }

 public void wyslij(String email, String temat, String tresc)
 {
  try{
  Properties ustawienia = System.getProperties();
  ustawienia.put("mail.smtp.host", "smtp.gmail.com");
  ustawienia.put("mail.smtp.auth", "true");
  ustawienia.put("mail.smtp.port", "465");
  ustawienia.put("mail.smtp.socketFactory.port", "465");
  ustawienia.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
  ustawienia.put("mail.smtp.socketFactory.fallback", "false");
  Authenticator autoryzacja = new autoryzacjaSMTP(adresNad, hasloNad);
  Session sesja = Session.getInstance(ustawienia, autoryzacja);

  MimeMessage wiadomosc = new MimeMessage(sesja);
  wiadomosc.setFrom(new InternetAddress(this.adresNad));
  wiadomosc.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
  wiadomosc.setSubject(temat);
  wiadomosc.setText(tresc);
  Transport.send(wiadomosc);
  }
  catch(AddressException a){
      System.out.println("Bład adresu");
  }
  catch(MessagingException a){
      System.out.println("Bład wysyłania");
  }
 }
 public void wyslijPoZarejestrowaniu(String email, String login, String haslo){
     String temat="Potwierdzenie rejestracji w naszym przecudnym serwisie";
     String tresc="Dziekujemy za rejestracje! \nTwoje konto musi zostać potwierdzone przez administratora \nJak na razie twój login to: "+login+"\nA twoje haslo to: "+ haslo+"\nŻyczymy miłego dnia (chociaż, nim administrator potwierdzi twoje konto to warto już życzyć  co najmniej szczęśliwego nowego roku).";
     wyslij(email, temat, tresc);
 }
 public void wyslijPrzykladowegoMaila(){
        wyslijPoZarejestrowaniu("kubakepa@wp.pl","aaa", "aaa");

 }
}

 

