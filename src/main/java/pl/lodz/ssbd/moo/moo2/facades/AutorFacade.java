/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo2.facades;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import pl.lodz.ssbd.facades.*;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.lodz.ssbd.entities.Autor;
import pl.lodz.ssbd.exceptions.AutorException;
import pl.lodz.ssbd.exceptions.SSBD05Exception;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;

/**
 *
 * @author Robert Mielczarek 
 */
@Stateless(name="moo2Autor")
@Interceptors({DziennikZdarzenInterceptor.class})
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AutorFacade extends AbstractFacade<Autor> implements AutorFacadeLocal {
    @PersistenceContext(unitName = "ssbd05moo2")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AutorFacade() {
        super(Autor.class);
    }

    @Override
    @RolesAllowed("ModyfikacjaAutora")
    public void edit(Autor entity) throws AutorException  {
        try {
            super.edit(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
        }
    }

    @Override
    @PermitAll
    public List<Autor> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @PermitAll
    public Autor find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed("DodanieAutora")
    public void create(Autor entity) throws AutorException  {
        try {
            super.create(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
        }
    }
    
    
}
