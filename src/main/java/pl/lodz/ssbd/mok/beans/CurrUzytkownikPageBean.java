/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.beans;
 
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.ssbd.entities.Uzytkownik;
 
/**
 * Klasa zawierająca metody potrzebne do wyświetlenia widoku po logowaniu
 * @author Kuba
 */
@Named(value = "curruzytkownikPageBean")
@RequestScoped
public class CurrUzytkownikPageBean {
 
    @Inject
 
    UzytkownikSession uzytkownikSession;
 
    public CurrUzytkownikPageBean() {
    }
    
    /**
     * zwraca ip poprawnego zalogowania
     * @return ip poprawnego zalogowania
     */
    public String pobierzIPPopZal() {
        return uzytkownikSession.pobierzIPOstatniegoPopZalogowania();
 
    }
    
    /**
     * Zwraca czas poprawnego zalogowania
     * @return czas poprawnego zalogowania
     */
    public String pobierzCzasPopZal() {
        if (uzytkownikSession.pobierzCzasOstatniegoPopZalogowania()!= null){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = formatter.format(uzytkownikSession.pobierzCzasOstatniegoPopZalogowania());
        return s;
        }
        return "Brak daty";
    }
 
    /**
     * Zwraca ilosc niepoprawnych zalogowan
     * @return ilosc niepoprawnych zalogowan 
     */
    public int pobierzIloscNPopZal() {
        return uzytkownikSession.pobierzIloscNPopZal();
    }
 
    /**
     * Zwraca czas nieporpawnego zlaogowania
     * @return czas nieporawnego zalogowania
     */
    public String pobierzCzasNPopZal() {
        String login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        uzytkownikSession.pobierzUzytkownikMenu(login);
        Uzytkownik uzyt = uzytkownikSession.getUzytkownikMenu();
        if (uzyt.getCzasNPopZal()!=null){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = formatter.format(uzyt.getCzasNPopZal());
        return s;
        }
        return "Brak daty";
    }
 
}
