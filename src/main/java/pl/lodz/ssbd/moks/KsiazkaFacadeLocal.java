/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moks;

import pl.lodz.ssbd.facades.*;
import java.util.List;
import javax.ejb.Local;
import pl.lodz.ssbd.entities.Ksiazka;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Local
public interface KsiazkaFacadeLocal {

    void create(Ksiazka ksiazka);

    void edit(Ksiazka ksiazka);

    void remove(Ksiazka ksiazka);

    Ksiazka find(Object id);

    List<Ksiazka> findAll();

    List<Ksiazka> findRange(int[] range);

    int count();
    
}
