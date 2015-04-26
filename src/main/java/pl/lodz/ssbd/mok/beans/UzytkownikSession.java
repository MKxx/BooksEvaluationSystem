/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.beans;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.mok.endpoints.MOKEndpointLocal;
import pl.lodz.ssbd.utils.MD5;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@SessionScoped
public class UzytkownikSession implements Serializable {

    /**
     * Creates a new instance of UzytkownikSession
     */
    public UzytkownikSession() {
    }
    
    @EJB
    private MOKEndpointLocal MOKEndpoint;

    private Uzytkownik uzytkownikEdycja;

    public Uzytkownik getUzytkownikEdycja() {
        return uzytkownikEdycja;
    }
    public void rejestrujUzytkownika(Uzytkownik uzytkownik) {
        Uzytkownik nowyUzytkownik = new Uzytkownik();
        nowyUzytkownik.setLogin(uzytkownik.getLogin());
        nowyUzytkownik.setHasloMd5(MD5.hash(uzytkownik.getHasloMd5()));
        nowyUzytkownik.setImie(uzytkownik.getImie());
        nowyUzytkownik.setNazwisko(uzytkownik.getNazwisko());
        nowyUzytkownik.setEmail(uzytkownik.getEmail());
        mailer mail=new mailer();
        mail.wyslijPoZarejestrowaniu(uzytkownik.getEmail(), uzytkownik.getLogin(), uzytkownik.getHasloMd5());
        MOKEndpoint.rejestrujUzytkownika(nowyUzytkownik);
    }
    
    public List<Uzytkownik> pobierzWszystkichUzytkownikow(){
        return MOKEndpoint.pobierzWszystkichUzytkownikow();
    }
    
    public void potwierdzUzytkownika(Uzytkownik uzytkownik){
        MOKEndpoint.potwierdzUzytkownika(uzytkownik);
    }
    
    public void zablokujUzytkownika(Uzytkownik uzytkownik){
        MOKEndpoint.zablokujUzytkownika(uzytkownik);
    }
        
    public void odblokujUzytkownika(Uzytkownik uzytkownik){
        MOKEndpoint.odblokujUzytkownika(uzytkownik);
    }

    public boolean zaloguj(String username, String password, String IP) {
        return MOKEndpoint.zaloguj(username,password,IP);
    }

    public void zalogujPoprawneUwierzytelnienie(String username, String password, String IP) {
        MOKEndpoint.zalogujPoprawneUwierzytelnienie(username,password,IP);
    }

    public void zalogujNiepoprawneUwierzytenienie(String username, String password, String IP) {
         MOKEndpoint.zalogujNiepoprawneUwierzytenienie(username,password,IP);
    }

}
