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

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Local
public interface PoprzednieHasloFacadeLocal {

    void create(PoprzednieHaslo poprzednieHaslo);

    void edit(PoprzednieHaslo poprzednieHaslo);

    void remove(PoprzednieHaslo poprzednieHaslo);

    PoprzednieHaslo find(Object id);

    List<PoprzednieHaslo> findAll();

    List<PoprzednieHaslo> findRange(int[] range);

    int count();
    
}
