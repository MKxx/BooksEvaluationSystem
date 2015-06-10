/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.facades;

import java.util.List;
import java.util.logging.Level;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.lodz.ssbd.entities.PoziomDostepu;
import pl.lodz.ssbd.exceptions.PoziomDostepuException;
import pl.lodz.ssbd.exceptions.SSBD05Exception;
import pl.lodz.ssbd.exceptions.UzytkownikException;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;
import pl.lodz.ssbd.facades.AbstractFacade;

/**
 *
 * @author Robert Mielczarek 
 */
@Stateless(name = "mokPD")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors({DziennikZdarzenInterceptor.class})
public class PoziomDostepuFacade extends AbstractFacade<PoziomDostepu> implements PoziomDostepuFacadeLocal {

    @Override
    public List<PoziomDostepu> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PoziomDostepu find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    @RolesAllowed("NadanieOdebraniePoziomuDostepu")
    public void edit(PoziomDostepu entity) throws PoziomDostepuException{
        try {
            super.edit(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
            throw new PoziomDostepuException("exceptions.poziomdostepu.blad");
        }
    }

    @Override
    public void create(PoziomDostepu entity) {
        try {
            super.create(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
        }
    }
    @PersistenceContext(unitName = "ssbd05mok")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PoziomDostepuFacade() {
        super(PoziomDostepu.class);
    }

}
