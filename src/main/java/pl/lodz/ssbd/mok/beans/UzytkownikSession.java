package pl.lodz.ssbd.mok.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import pl.lodz.ssbd.entities.PoziomDostepu;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.exceptions.PoprzednieHasloException;
import pl.lodz.ssbd.exceptions.PoziomDostepuException;
import pl.lodz.ssbd.exceptions.UzytkownikException;
import pl.lodz.ssbd.mok.endpoints.MOKEndpointLocal;
import pl.lodz.ssbd.utils.Mailer;


/**
 * Bean sesyjyjny obslugujacy wszystkie widoki dla MOK
 * @author Robert Mielczarek 
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
    /**
     * rejestracja uzytkownika
     * @param uzytkownik encja uzytkownika
     * @param haslo haslo uzytkowika
     * @throws UzytkownikException Wyjatek w przypadku powtarzajacego sie loginu badz emaila
     */
    public void rejestrujUzytkownika(Uzytkownik uzytkownik, String haslo) throws UzytkownikException {
        Uzytkownik nowyUzytkownik = new Uzytkownik();
        nowyUzytkownik.setLogin(uzytkownik.getLogin());
        nowyUzytkownik.setImie(uzytkownik.getImie());
        nowyUzytkownik.setNazwisko(uzytkownik.getNazwisko());
        nowyUzytkownik.setEmail(uzytkownik.getEmail());
        MOKEndpoint.rejestrujUzytkownika(nowyUzytkownik, haslo);
        Mailer.wyslijPoZarejestrowaniu(uzytkownik.getEmail(),uzytkownik.getLogin(),uzytkownik.getHasloMd5());
    }

    /**
     * Pobiera lista uzytkownikow
     * @param wartosc Szukany ciag w nazwisku badz imieniu
     * @return liste uzytkownikow
     */
    public List<Uzytkownik> pobierzUzytkownikow(String wartosc) {
        return MOKEndpoint.pobierzUzytkownikow(wartosc);
    }

    /**
     * Potwierdz uzytkownika
     * @param uzytkownik encja uzytkownika
     * @throws UzytkownikException wyjatek w przypadku niespojnosci danych
     */
    public void potwierdzUzytkownika(Uzytkownik uzytkownik) throws UzytkownikException{
        MOKEndpoint.potwierdzUzytkownika(uzytkownik);
        Mailer.wyslijPoAktywacji(uzytkownik.getEmail(),uzytkownik.getLogin());
    }

    /**
     * zablokuj uzytkownika
     * @param uzytkownik encja uzytkowika
     * @throws UzytkownikException wyjatek w przypadku niespojnosci danych
     */
    public void zablokujUzytkownika(Uzytkownik uzytkownik)throws UzytkownikException {
        MOKEndpoint.zablokujUzytkownika(uzytkownik);
        Mailer.wyslijPoZablokowaniu(uzytkownik.getEmail(),uzytkownik.getLogin());
    }

    /**
     * odblokuj uzytkowika
     * @param uzytkownik encja uzytkownika
     * @throws UzytkownikException wyjatek w przypadku niespojnosci danych
     */
    public void odblokujUzytkownika(Uzytkownik uzytkownik) throws UzytkownikException{
        MOKEndpoint.odblokujUzytkownika(uzytkownik);
    }

    /**
     * zapisanie inforamcji o poprawnym uwierzytelnieniu
     * @param username login uzytkowika
     * @param password haslo
     * @param IP adres IP
     */
    public void zalogujPoprawneUwierzytelnienie(String username, String password, String IP) {
        MOKEndpoint.zalogujPoprawneUwierzytelnienie(username, IP);
    }

    /**
     * zapisanie inforamcji o niepoprawnym uwierzytelnieniu
     * @param username login uzytkowika
     * @param password haslo
     * @param IP adres IP
     */
    public void zalogujNiepoprawneUwierzytenienie(String username, String password, String IP) {
         MOKEndpoint.zalogujNiepoprawneUwierzytenienie(username, IP);
    }

    /**
     * pobranie IP ostatniego poprawnego zalogowania
     * @return Adres IP
     */
    public String pobierzIPOstatniegoPopZalogowania() {
        return MOKEndpoint.pobierzIPOstatniegoPopZalogowania();
    }

    /**
     * Pobranie czasu ostatniego poprawnego zalogowania
     * @return Data
     */
    public Date pobierzCzasOstatniegoPopZalogowania() {
        return MOKEndpoint.pobierzCzasOstatniegoPopZalogowania();
    }

    /**
     * pobranie ilosci niepoprawnych zalogowan
     * @return ilosc niepoprawnych zalogowan
     */
    public int pobierzIloscNPopZal() {
        return MOKEndpoint.pobierzIloscNPopZal();
    }

    /**
     * pobranie uzytkowika do skladania menu
     * @param login login uzytkowika
     */
    public void pobierzUzytkownikMenu(String login) {
        uzytkownikMenu = MOKEndpoint.pobierzUzytkownika(login);
    }

    /**
     * Zapisanie encji uzytkowika po edycji
     * @param zmianaHasla czy zmieniono haslo
     * @param haslo nowe haslo
     * @throws UzytkownikException wyjatek przy niespojnosci danych
     * @throws PoprzednieHasloException wyjatek gdy haslo sie powtarza
     */
    public void zapiszUzytkownikaPoEdycji(boolean zmianaHasla, String haslo) throws UzytkownikException, PoprzednieHasloException {
        MOKEndpoint.zapiszKontoPoEdycji(uzytkownikEdycja, zmianaHasla, haslo);
    }

    /**
     * pobrania uzytkowika do edycji
     * @param uzytkownik encja uzytkowika
     */
    public void pobierzUzytkownikaDoEdycji(Uzytkownik uzytkownik) {
        uzytkownikEdycja = MOKEndpoint.pobierzUzytkownikaDoEdycji(uzytkownik.getLogin());
    }
    
    /**
     * Nadanie poziomu dostepu
     * @param poziom encja poziomu
     * @throws PoziomDostepuException wyjatek gdy wystapi niespojnosc danych 
     */
    public void nadajPoziom(PoziomDostepu poziom) throws PoziomDostepuException {
        MOKEndpoint.nadajPoziom(poziom);
    }

    /**
     * odebranie poziomu dostepu
     * @param poziom encja poziomu
     * @throws PoziomDostepuException wyjatek gdy wystapi niespojnosc danych
     */
    public void odbierzPoziom(PoziomDostepu poziom) throws PoziomDostepuException {
        MOKEndpoint.odbierzPoziom(poziom);
    }
    
    /**
     * pobranie siebie do edycji
     */
    void pobierzSiebieDoEdycji() {
        uzytkownikEdycja = MOKEndpoint.pobierzSiebieDoEdycji();
    }

}
