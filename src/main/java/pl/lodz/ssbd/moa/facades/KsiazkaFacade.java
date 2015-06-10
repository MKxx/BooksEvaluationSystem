/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moa.facades;

import pl.lodz.ssbd.moks.facades.*;
import java.util.List;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import pl.lodz.ssbd.facades.*;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.exceptions.KsiazkaException;
import pl.lodz.ssbd.exceptions.SSBD05Exception;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;

/**
 *
 * @author Marta
 */
@Stateless(name="moaKsiazka")
@Interceptors({DziennikZdarzenInterceptor.class})
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class KsiazkaFacade extends AbstractFacade<Ksiazka> implements KsiazkaFacadeLocal {
    @PersistenceContext(unitName = "ssbd05moa")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KsiazkaFacade() {
        super(Ksiazka.class);
    }

    @Override
    @DenyAll
    public List<Ksiazka> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed("DodanieAutora")
    public Ksiazka find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    @DenyAll
    public void edit(Ksiazka entity) throws KsiazkaException  {
        try {
            super.edit(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
        }
    }

    @Override
    @DenyAll
    public void create(Ksiazka entity) throws KsiazkaException  {
        try {
            super.create(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
        }
    }

    @Override
    public List<Ksiazka> findNieocenione() {
        Query q = em.createQuery("SELECT k FROM Ksiazka k WHERE k.sredniaOcen IS NULL");
        return q.getResultList();
    }

}
