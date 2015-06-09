/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo.facades;

import java.util.List;
import javax.annotation.security.DenyAll;
import pl.lodz.ssbd.facades.*;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.exceptions.KsiazkaException;
import pl.lodz.ssbd.exceptions.SSBD05Exception;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Stateless(name="mooKsiazka")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors({DziennikZdarzenInterceptor.class})
public class KsiazkaFacade extends AbstractFacade<Ksiazka> implements KsiazkaFacadeLocal {
    @PersistenceContext(unitName = "ssbd05moo")
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
    public void edit(Ksiazka entity) throws KsiazkaException {
        try {
            super.edit(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
            throw new KsiazkaException();
        }
    }

    @Override
    @DenyAll
    public void create(Ksiazka entity) throws KsiazkaException {
        try {
            super.create(entity); //To change body v generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
            throw new KsiazkaException();
        }
    }

    @Override
    public List<Ksiazka> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @DenyAll
    public Ksiazka find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metoda zwracające aktywne książki
     * @return lista aktywnych książek
     */
    @Override
    public List<Ksiazka> findAktywne() {
        TypedQuery<Ksiazka> query = em.createNamedQuery("Ksiazka.findByAktywne", Ksiazka.class);
        query.setParameter("aktywne", true);
        return query.getResultList();
    }
    
    
}
