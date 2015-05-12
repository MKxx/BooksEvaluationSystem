/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo2.facades;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import pl.lodz.ssbd.facades.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.lodz.ssbd.entities.Ocena;
import pl.lodz.ssbd.exceptions.OcenaException;
import pl.lodz.ssbd.exceptions.SSBD05Exception;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Stateless
public class OcenaFacade extends AbstractFacade<Ocena> implements OcenaFacadeLocal {

    @PersistenceContext(unitName = "ssbd05moo2")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OcenaFacade() {
        super(Ocena.class);
    }

    @Override
    @RolesAllowed("ZmianaOceny")
    public void edit(Ocena entity) throws OcenaException {
        try {
            super.edit(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
            Logger.getLogger(pl.lodz.ssbd.moo.moo2.facades.OcenaFacade.class.getName()).log(Level.SEVERE, null, ex);
            throw new OcenaException();
        }
    }

    @Override
    @RolesAllowed("DodanieOceny")
    public void create(Ocena entity) throws OcenaException {
        try {
            super.create(entity); //To change body v generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
            Logger.getLogger(pl.lodz.ssbd.moo.moo2.facades.OcenaFacade.class.getName()).log(Level.SEVERE, null, ex);
            throw new OcenaException();
        }
    }

    @Override
    @DenyAll
    public int count() {
        return super.count(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @DenyAll
    public List<Ocena> findRange(int[] range) {
        return super.findRange(range); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @DenyAll
    public List<Ocena> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @DenyAll
    public Ocena find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @DenyAll
    public void remove(Ocena entity) {
        super.remove(entity); //To change body of generated methods, choose Tools | Templates.
    }

}
