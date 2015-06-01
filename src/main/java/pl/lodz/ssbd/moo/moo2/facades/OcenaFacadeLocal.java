/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo2.facades;

import pl.lodz.ssbd.moo.moo2.*;
import pl.lodz.ssbd.moo.moo.*;
import pl.lodz.ssbd.moo.*;
import pl.lodz.ssbd.facades.*;
import java.util.List;
import javax.ejb.Local;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.entities.Ocena;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.exceptions.OcenaException;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Local
public interface OcenaFacadeLocal {

    void create(Ocena ocena) throws OcenaException;

    void edit(Ocena ocena) throws OcenaException;

    void remove(Ocena ocena);

    Ocena find(Object id);

    List<Ocena> findAll();

    List<Ocena> findRange(int[] range);

    int count();

    public Ocena findByKsiazkaAndLogin(Ksiazka ksiazka, Uzytkownik uzytkownik);
    
}
