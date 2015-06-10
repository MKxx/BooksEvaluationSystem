/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moa.facades;

import pl.lodz.ssbd.moa.*;
import java.util.List;
import javax.ejb.Local;
import pl.lodz.ssbd.entities.Autor;
import pl.lodz.ssbd.exceptions.AutorException;

/**
 *
 * @author Robert Mielczarek 
 */
@Local
public interface AutorFacadeLocal {

    void create(Autor autor) throws AutorException;

    void edit(Autor autor) throws AutorException;

    Autor find(Object id);

    List<Autor> findAll();
    
}
