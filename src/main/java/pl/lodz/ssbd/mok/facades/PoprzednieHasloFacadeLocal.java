/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.facades;

import pl.lodz.ssbd.mok.*;
import pl.lodz.ssbd.facades.*;
import java.util.List;
import javax.ejb.Local;
import pl.lodz.ssbd.entities.PoprzednieHaslo;
import pl.lodz.ssbd.exceptions.PoprzednieHasloException;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Local
public interface PoprzednieHasloFacadeLocal {

    void create(PoprzednieHaslo poprzednieHaslo) throws PoprzednieHasloException;

    void edit(PoprzednieHaslo poprzednieHaslo) throws PoprzednieHasloException;


    void remove(PoprzednieHaslo poprzednieHaslo);

    PoprzednieHaslo find(Object id);

    List<PoprzednieHaslo> findAll();

    List<PoprzednieHaslo> findRange(int[] range);

    int count();
    
}
