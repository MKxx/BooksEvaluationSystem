/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.endpoints;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import pl.lodz.ssbd.entities.PoziomDostepu;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.exceptions.PoprzednieHasloException;
import pl.lodz.ssbd.exceptions.PoziomDostepuException;
import pl.lodz.ssbd.exceptions.UzytkownikException;

/**
 *
 * @author Robert Mielczarek 
 */
@Local
public interface MOKEndpointLocal {
    /**
     * Pobranie siebie do edycji
     * @return encja uzytkownika
     */
    public Uzytkownik pobierzSiebieDoEdycji();
    /**
     * Rejestracja konta
     * @param nowyUzytkownik
     * @param haslo
     * @throws UzytkownikException 
     */
    public void rejestrujUzytkownika(Uzytkownik nowyUzytkownik, String haslo) throws UzytkownikException;
    /**
     * Wyświetlenie panelu administratora oraz raportu z listą użytkowników
     * Wyszukiwanie użytkownika
     * @param wartosc ciag znakow w imieniu lub nazwisku
     * @return lista uzytkownikow
     */
    public List<Uzytkownik> pobierzUzytkownikow(String wartosc);
    /**
     * Autoryzacja konta
     * @param uzytkownik
     * @throws UzytkownikException 
     */
    public void potwierdzUzytkownika(Uzytkownik uzytkownik) throws UzytkownikException;
    /**
     * Zablokowanie możliwości uwierzytelnienia się 
     * @param uzytkownik
     * @throws UzytkownikException 
     */
    public void zablokujUzytkownika(Uzytkownik uzytkownik)throws UzytkownikException;
    /**
     * odblokowanie możliwości uwierzytelnienia się 
     * @param uzytkownik
     * @throws UzytkownikException 
     */
    public void odblokujUzytkownika(Uzytkownik uzytkownik)throws UzytkownikException;
    /**
     * Uwierzytelnianie
     * @param username login uzytkowika
     * @param IP adres IP
     */
    public void zalogujPoprawneUwierzytelnienie(String username, String IP);
    /**
     * Uwierzytelnianie
     * @param username login uzytkowika
     * @param IP adres IP
     */
    public void zalogujNiepoprawneUwierzytenienie(String username,String IP);
    /**
     * Modyfikacja danych cudzego konta
     * Modyfikacja danych konta
     * 
     * @param uzytkownikEdycja
     * @param zmianaHasla
     * @param haslo
     * @throws UzytkownikException
     * @throws PoprzednieHasloException 
     */
    public void zapiszKontoPoEdycji(Uzytkownik uzytkownikEdycja, boolean zmianaHasla, String haslo) throws UzytkownikException, PoprzednieHasloException;
    /**
     * pobranie IP ostaniego poprawnego zalogowania
     * @return adres ip
     */
    public String pobierzIPOstatniegoPopZalogowania();
    /**
     * pobiera date ostatniego poprawnego zalogowania
     * @return data
     */
    public Date pobierzCzasOstatniegoPopZalogowania();
    /**
     * pobiera ilosc nieporpawnych zalogowan
     * @return ilosc niepoprawnych zalogowan
     */
    public int pobierzIloscNPopZal();
    /**
     * Pobranie uzytkowika do edycji
     * @param login login uzytkowika
     * @return encja uzytkowika
     */
    public Uzytkownik pobierzUzytkownikaDoEdycji(String login);
    /**
     * Nadanie poziomu dostępu
     * @param poziom encja poziomu
     * @throws PoziomDostepuException wyjatek w przypadku niespojonsci
     */
    public void nadajPoziom(PoziomDostepu poziom) throws PoziomDostepuException;
    /**
     * odebranie poziomu dostępu
     * @param poziom encja poziomu
     * @throws PoziomDostepuException wyjatek w przypadku niespojonsci
     */
    public void odbierzPoziom(PoziomDostepu poziom) throws PoziomDostepuException;
    /**
     * Pobranie uzytkowika
     * @param login login uzytkownika
     * @return encja uzytkowika
     */
    public Uzytkownik pobierzUzytkownika(String login);

}
