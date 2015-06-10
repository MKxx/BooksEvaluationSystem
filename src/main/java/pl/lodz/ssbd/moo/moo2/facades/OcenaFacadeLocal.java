/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo2.facades;

import java.util.List;
import javax.ejb.Local;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.entities.Ocena;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.exceptions.OcenaException;

/**
 *
 * @author Robert Mielczarek 
 */
@Local
public interface OcenaFacadeLocal {

    void create(Ocena ocena) throws OcenaException;

    void edit(Ocena ocena) throws OcenaException;

    Ocena find(Object id);

    List<Ocena> findAll();

     /**
     * Metoda znajdująca ocenę dla danego użytkownika i podanej książki
     * @param ksiazka książka 
     * @param uzytkownik uzytkownik
     * @return ocena dla podanej ksiazki dla danego uzytkownika
     */
    public Ocena findByKsiazkaAndLogin(Ksiazka ksiazka, Uzytkownik uzytkownik);
    
}
