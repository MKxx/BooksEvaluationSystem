/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo2.endpoints;

import javax.ejb.Local;
import pl.lodz.ssbd.exceptions.KsiazkaException;
import pl.lodz.ssbd.exceptions.OcenaException;
import pl.lodz.ssbd.exceptions.UzytkownikException;

/**
 *
 * @author Maciej
 */
@Local
public interface MOO2EndpointLocal {
    
    /**
     * Metoda zmieniająca ocenę dla danej ksiązki 
     * @param id_ksiazka id ksiązki wskazanej przez użytkownika
     * @param ocena wartośc oceny jaką wskazał użytkownik (1-5)
     * @param login login użytkownika który dokonuje zmiany oceny
     * @throws OcenaException wyjątek przy jednoczesnej modyfikacji, bądź w przypadku braku oceny.
     * @throws KsiazkaException wyjątek w przypadku modyfikacji książki
     * @throws UzytkownikException wyjątek rzucany w przypaku braku użytkownika
     */
    public void zmienOcene(long id_ksiazka, int ocena, String login) throws OcenaException, KsiazkaException, UzytkownikException;

    
    
    /**
     * Metoda dodająca ocenę
     * @param id_ksiazka id książki wskazanej przez użytkownika
     * @param wartosc wartość oceny jaką wskazał użytkownia (1-5)
     * @param login login użytkownika który ocenia książkę.
     * @throws UzytkownikException jeśli użytkownik nie istnieje
     * @throws OcenaException jeśli ocena JUŻ istnieje
     * @throws KsiazkaException  jeśli książka została zmodyfikowana podczas oceny (OptimistickLock).
     */
    public void ocenKsiazke(long id_ksiazka, int wartosc, String login) throws OcenaException, UzytkownikException, KsiazkaException;
}

