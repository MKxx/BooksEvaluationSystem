/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moks.facades;


import java.util.List;
import javax.ejb.Local;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.exceptions.KsiazkaException;

/**
 *
 * @author Robert Mielczarek 
 */
@Local
public interface KsiazkaFacadeLocal {

    void create(Ksiazka ksiazka) throws KsiazkaException;

    void edit(Ksiazka ksiazka) throws KsiazkaException;

    Ksiazka find(Object id);

    List<Ksiazka> findAll();
    /**
     * Pobiera liste ksiazek ulubionych dla danego uzytkownika
     * @param login login uzytkownika
     * @return lista ksiazek ulubionych
     * @throws KsiazkaException Rzucany jest wyjatek gdy wystapi blad
     */
    List<Ksiazka> findUlubione(String login) throws KsiazkaException;
    /**
     * Pobiera liste ksiazek nieaktywnych
     * @return lista ksiazek nieaktywnych
     */
    List<Ksiazka> findNieaktywne();
    /**
     * Pobiera liste ksiazek aktywnych
     * @return Liste ksiazek aktywnych
     */
    public List<Ksiazka> findAktywne();
    
}
