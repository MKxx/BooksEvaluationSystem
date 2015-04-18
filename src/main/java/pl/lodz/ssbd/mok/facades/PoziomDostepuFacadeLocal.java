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
import pl.lodz.ssbd.entities.PoziomDostepu;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Local
public interface PoziomDostepuFacadeLocal {

    void create(PoziomDostepu poziomDostepu);

    void edit(PoziomDostepu poziomDostepu);

    void remove(PoziomDostepu poziomDostepu);

    PoziomDostepu find(Object id);

    List<PoziomDostepu> findAll();

    List<PoziomDostepu> findRange(int[] range);

    int count();
    
}
