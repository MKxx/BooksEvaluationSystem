/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.facades;

import pl.lodz.ssbd.mok.*;
import pl.lodz.ssbd.facades.*;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.lodz.ssbd.entities.PoziomDostepu;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Stateless(name="mokPD")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors({DziennikZdarzenInterceptor.class})
public class PoziomDostepuFacade extends AbstractFacade<PoziomDostepu> implements PoziomDostepuFacadeLocal {
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
