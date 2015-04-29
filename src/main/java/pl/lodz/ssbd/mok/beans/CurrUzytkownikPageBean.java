/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.beans;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.ssbd.entities.Uzytkownik;


/**
 *
 * @author Kuba
 */
@Named(value = "curruzytkownikPageBean")
@RequestScoped
public class CurrUzytkownikPageBean {
    @Inject
 
    UzytkownikSession uzytkownikSession;
   

     public CurrUzytkownikPageBean() {
    }
    
      public String pobierzIPPopZal(){
        //  List <Uzytkownik> uzytkownicy=uzytkownikSession.pobierzWszystkichUzytkownikow();
        //  String login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
          //Uzytkownik user=uzytkownikSession.getUzytkownikMenu();
          return uzytkownikSession.pobierzIPOstatniegoPopZalogowania();
            
    }
      public String pobierzCzasPopZal(){
          Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String s = formatter.format(uzytkownikSession.pobierzCzasOstatniegoPopZalogowania());
           return s;
      }
      
      public int pobierzIloscNPopZal(){
          return uzytkownikSession.pobierzIloscNPopZal();
      }
      public String pobierzCzasNPopZal(){
           String login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    uzytkownikSession.pobierzUzytkownikMenu(login);
    Uzytkownik uzyt=uzytkownikSession.getUzytkownikMenu();
     Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String s = formatter.format(uzyt.getCzasNPopZal());
    return s;
      }
    
}
