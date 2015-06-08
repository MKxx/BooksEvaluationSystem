/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo.facades;

import java.util.List;
import java.util.logging.Level;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import pl.lodz.ssbd.moo.moo.*;
import pl.lodz.ssbd.moo.*;
import pl.lodz.ssbd.facades.*;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pl.lodz.ssbd.entities.Ocena;
import pl.lodz.ssbd.exceptions.OcenaException;
import pl.lodz.ssbd.exceptions.SSBD05Exception;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Stateless(name="mooOcena")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class OcenaFacade extends AbstractFacade<Ocena> implements OcenaFacadeLocal {
    @PersistenceContext(unitName = "ssbd05moo")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OcenaFacade() {
        super(Ocena.class);
    }
  
    @Override
    @RolesAllowed("DodanieDoUlubionych")
    public void edit(Ocena entity) throws OcenaException {
        try {
            super.edit(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
            throw new OcenaException();
        }
    }

    @Override
    @DenyAll
    public void create(Ocena entity) throws OcenaException {
        try {
            super.create(entity); //To change body v generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
            throw new OcenaException();
        }
    }

    @Override
    @PermitAll
    public List<Ocena> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @DenyAll
    public Ocena find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ocena> findOcenyInitalized() {
        TypedQuery<Ocena> query = em.createNamedQuery("Ocena.findAll", Ocena.class);
        return query.getResultList();
    }
    
}
