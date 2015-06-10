/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.facades;

import pl.lodz.ssbd.mok.*;
import pl.lodz.ssbd.facades.*;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import pl.lodz.ssbd.entities.PoziomDostepu;
import pl.lodz.ssbd.exceptions.PoziomDostepuException;

/**
 *
 * @author Robert Mielczarek 
 */
@Local
public interface PoziomDostepuFacadeLocal {

    void create(PoziomDostepu poziomDostepu) throws PoziomDostepuException;
    
    void edit(PoziomDostepu poziomDostepu) throws PoziomDostepuException;

    PoziomDostepu find(Object id);

    List<PoziomDostepu> findAll();
    
}
