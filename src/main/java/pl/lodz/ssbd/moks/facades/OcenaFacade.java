/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moks.facades;

import java.util.logging.Level;
import java.util.logging.Logger;
import pl.lodz.ssbd.moks.*;
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
    @PersistenceContext(unitName = "ssbd05moks")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OcenaFacade() {
        super(Ocena.class);
    }

    @Override
    public void edit(Ocena entity) throws OcenaException {
        try {
            super.edit(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
            Logger.getLogger(OcenaFacade.class.getName()).log(Level.SEVERE, null, ex);
            throw new OcenaException();
        }
    }

    @Override
    public void create(Ocena entity) throws OcenaException {
        try {
            super.create(entity); //To change body v generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
            Logger.getLogger(OcenaFacade.class.getName()).log(Level.SEVERE, null, ex);
            throw new OcenaException();
        }
    }
    
    
}
