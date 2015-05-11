package pl.lodz.ssbd.mok.beans;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import pl.lodz.ssbd.entities.PoziomDostepu;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.mok.endpoints.MOKEndpointLocal;
import pl.lodz.ssbd.utils.MD5;
import pl.lodz.ssbd.utils.Mailer;


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
    private Uzytkownik uzytkownikMenu;

    public Uzytkownik getUzytkownikMenu() {
        return uzytkownikMenu;
    }

    public Uzytkownik getUzytkownikEdycja() {
        return uzytkownikEdycja;
    }

    public void rejestrujUzytkownika(Uzytkownik uzytkownik, String haslo) {
                Uzytkownik nowyUzytkownik = new Uzytkownik();
        nowyUzytkownik.setLogin(uzytkownik.getLogin());
        nowyUzytkownik.setHasloMd5(MD5.hash(haslo));
        nowyUzytkownik.setImie(uzytkownik.getImie());
        nowyUzytkownik.setNazwisko(uzytkownik.getNazwisko());
        nowyUzytkownik.setEmail(uzytkownik.getEmail());
        MOKEndpoint.rejestrujUzytkownika(nowyUzytkownik);
        Mailer.wyslijPoZarejestrowaniu(uzytkownik.getEmail(),uzytkownik.getLogin(),uzytkownik.getHasloMd5());
    }

    public List<Uzytkownik> pobierzUzytkownikow(String wartosc) {
        return MOKEndpoint.pobierzUzytkownikow(wartosc);
    }

    public void potwierdzUzytkownika(Uzytkownik uzytkownik) {
        MOKEndpoint.potwierdzUzytkownika(uzytkownik);
        Mailer.wyslijPoAktywacji(uzytkownik.getEmail(),uzytkownik.getLogin());
    }

    public void zablokujUzytkownika(Uzytkownik uzytkownik) {
        MOKEndpoint.zablokujUzytkownika(uzytkownik);
        Mailer.wyslijPoZablokowaniu(uzytkownik.getEmail(),uzytkownik.getLogin());
    }

    public void odblokujUzytkownika(Uzytkownik uzytkownik) {
        MOKEndpoint.odblokujUzytkownika(uzytkownik);
    }

    public void zalogujPoprawneUwierzytelnienie(String username, String password, String IP) {
        MOKEndpoint.zalogujPoprawneUwierzytelnienie(username, IP);
    }

    public void zalogujNiepoprawneUwierzytenienie(String username, String password, String IP) {
         MOKEndpoint.zalogujNiepoprawneUwierzytenienie(username, IP);
    }

    public String pobierzIPOstatniegoPopZalogowania() {
        return MOKEndpoint.pobierzIPOstatniegoPopZalogowania();
    }

    public Date pobierzCzasOstatniegoPopZalogowania() {
        return MOKEndpoint.pobierzCzasOstatniegoPopZalogowania();
    }

    public int pobierzIloscNPopZal() {
        return MOKEndpoint.pobierzIloscNPopZal();
    }

    public void pobierzUzytkownikMenu(String login) {
        uzytkownikMenu = MOKEndpoint.pobierzUzytkownika(login);
    }

    public void zapiszUzytkownikaPoEdycji(boolean zmianaHasla) {
        MOKEndpoint.zapiszKontoPoEdycji(uzytkownikEdycja, zmianaHasla);
    }

    public void pobierzUzytkownikaDoEdycji(Uzytkownik uzytkownik) {
        uzytkownikEdycja = MOKEndpoint.pobierzUzytkownikaDoEdycji(uzytkownik.getLogin());
    }

    public void nadajPoziom(PoziomDostepu poziom) {
        MOKEndpoint.nadajPoziom(poziom);
    }

    public void odbierzPoziom(PoziomDostepu poziom) {
        MOKEndpoint.odbierzPoziom(poziom);
    }
    
    void pobierzSiebieDoEdycji() {
        uzytkownikEdycja = MOKEndpoint.pobierzSiebieDoEdycji();
    }

}
