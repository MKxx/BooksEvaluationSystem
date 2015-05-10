/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.facades;

import java.util.logging.Level;
import java.util.logging.Logger;
import pl.lodz.ssbd.mok.*;
import pl.lodz.ssbd.facades.*;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.lodz.ssbd.entities.PoprzednieHaslo;
import pl.lodz.ssbd.exceptions.SSBD05Exception;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;
import pl.lodz.ssbd.facades.AbstractFacade;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors({DziennikZdarzenInterceptor.class})
public class PoprzednieHasloFacade extends AbstractFacade<PoprzednieHaslo> implements PoprzednieHasloFacadeLocal {
    @PersistenceContext(unitName = "ssbd05mok")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PoprzednieHasloFacade() {
        super(PoprzednieHaslo.class);
    }

    @Override
    public void edit(PoprzednieHaslo entity) {
        try {
            super.edit(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
            Logger.getLogger(PoprzednieHasloFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void create(PoprzednieHaslo entity) {
        try {
            super.create(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
            Logger.getLogger(PoprzednieHasloFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
