/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moks.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import pl.lodz.ssbd.entities.Autor;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.exceptions.KsiazkaException;
import pl.lodz.ssbd.moks.endpoints.MOKSEndpointLocal;

/**
 *
 * @author Maciej
 */
@Named(value = "ksiazkaSession")
@SessionScoped
/**
 * Bean sesyjny dla ksiazki
 */
public class KsiazkaSession implements Serializable {

    /**
     * Creates a new instance of KsiazkaSession
     * 
     */
    
    @EJB
    MOKSEndpointLocal MOKSEndpoint;
    private Ksiazka edytowanaKsiazka;

    public Ksiazka getEdytowanaKsiazka() {
        return edytowanaKsiazka;
    }

    public void setEdytowanaKsiazka(Ksiazka edytowanaKsiazka) {
        this.edytowanaKsiazka = edytowanaKsiazka;
    }
    
    public KsiazkaSession() {
    }
    
    /**
     * Pobiera liste ksiazek aktywnych
     * @return ksiazki 
     */
    @RolesAllowed("PrzegladanieKsiazekModeratorski")
    public List<Ksiazka> pobierzKsiazki(){
        return MOKSEndpoint.pobierzKsiazki();
    }

    /**
     * pobiera liste autorow
     * @return autorzy
     */
    List<Autor> pobierzAutorow() {
        return MOKSEndpoint.pobierzAutorow();
    }

    /**
     * Tworzy książkę
     * @param ksiazka ksiazka do stworzenia
     * @param wybraniAutorzy autorzy ksiazki
     */
    void stworzKsiazke(Ksiazka ksiazka, List<String> wybraniAutorzy) {
        MOKSEndpoint.dodajKsiazke(ksiazka, wybraniAutorzy);
    }
    
    /**
     * Pobiera ulubione ksiazki
     * @param login login uzytkownika
     * @return lista uulubionych ksiazek badz null w przypadku błędu
     */
    @RolesAllowed("WyswietlanieListyUlubionych")
    public List<Ksiazka> pobierzUlubione(String login) {
        try {
        return MOKSEndpoint.pobierzKsiazkiUlubione(login);
        }
        catch (KsiazkaException a){
            return null;
        }
    }

    /**
     * Pobiera nieaktywne ksaizki
     * @return lista ksiazek nieaktywnych
     */
    @RolesAllowed("WyswietlenieNieaktywnych")
    public List<Ksiazka> pobierzNieaktywneKsiazki(){
        return MOKSEndpoint.pobierzKsiazkiNieaktywne();
    }
    /**
     * oznacza ksiazke jako nieaktywna
     * @param ksiazka ksiazka do oznaczenia
     * @throws KsiazkaException wyjatek w przypadku niepowodzenia
     */
    public void oznaczJakoNieaktywna(Ksiazka ksiazka) throws KsiazkaException{
        MOKSEndpoint.oznaczKsiazkeJakoNieaktywna(ksiazka);
    }

    /**
     * Edytuje ksiazke
     * @throws KsiazkaException wyjatek w przypadku niepowodzenia
     */
    void zapiszKsiazkePoEdycji() throws KsiazkaException {
        MOKSEndpoint.edytujKsiazke(edytowanaKsiazka);
    }

    /**
     * pobiera ksiazke do edycji i zapisuje w polu edytowanaKsiazka
     * @param id id ksiazki do edycji
     */
    void pobierzKsiazkeDoEdycji(long id) {
        edytowanaKsiazka = MOKSEndpoint.pobierzKsiazkeDoEdycji(id);
    }
}
