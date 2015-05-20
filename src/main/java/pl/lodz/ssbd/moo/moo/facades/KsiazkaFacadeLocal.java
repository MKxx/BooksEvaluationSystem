/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo.facades;

import pl.lodz.ssbd.moo.moo.*;
import pl.lodz.ssbd.moo.*;
import pl.lodz.ssbd.facades.*;
import java.util.List;
import javax.ejb.Local;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.exceptions.KsiazkaException;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Local
public interface KsiazkaFacadeLocal {

    void create(Ksiazka ksiazka) throws KsiazkaException;

    void edit(Ksiazka ksiazka) throws KsiazkaException;

    void remove(Ksiazka ksiazka);

    Ksiazka find(Object id);

    List<Ksiazka> findAll();

    List<Ksiazka> findRange(int[] range);
    
    List<Ksiazka> findUlubione(String login) throws KsiazkaException;

    int count();
    
}
