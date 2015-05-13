/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo2.facades;

import java.util.List;
import java.util.logging.Level;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import pl.lodz.ssbd.moo.moo2.*;
import pl.lodz.ssbd.moo.moo.*;
import pl.lodz.ssbd.moo.*;
import pl.lodz.ssbd.facades.*;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.exceptions.KsiazkaException;
import pl.lodz.ssbd.exceptions.SSBD05Exception;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Stateless(name="moo2Ksiazka")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class KsiazkaFacade extends AbstractFacade<Ksiazka> implements KsiazkaFacadeLocal {
    @PersistenceContext(unitName = "ssbd05moo2")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KsiazkaFacade() {
        super(Ksiazka.class);
    }
    
    @Override
    @RolesAllowed({"DodanieOceny","ZmianaOceny"})
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
    @DenyAll
    public int count() {
        return super.count(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @DenyAll
    public List<Ksiazka> findRange(int[] range) {
        return super.findRange(range); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @DenyAll
    public List<Ksiazka> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed({"DodanieOceny","ZmianaOceny"})
    public Ksiazka find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @DenyAll
    public void remove(Ksiazka entity) {
        super.remove(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
