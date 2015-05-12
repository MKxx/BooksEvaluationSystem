/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo.facades;

import pl.lodz.ssbd.moo.moo.*;
import pl.lodz.ssbd.moo.*;
import pl.lodz.ssbd.facades.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.exceptions.SSBD05Exception;
import pl.lodz.ssbd.exceptions.UzytkownikException;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
//@Stateless
public class UzytkownikFacade extends AbstractFacade<Uzytkownik> implements UzytkownikFacadeLocal {
    @PersistenceContext(unitName = "ssbd05moo")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UzytkownikFacade() {
        super(Uzytkownik.class);
    }
    
       @Override
    public void edit(Uzytkownik entity) throws UzytkownikException {
        try {
            super.edit(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
            throw new UzytkownikException(ex.getMessage());
        }
    }

    @Override
    public void create(Uzytkownik entity) throws UzytkownikException {
        try{
        super.create(entity); //To change body of generated methods, choose Tools | Templates.
        } catch(SSBD05Exception ex){
            throw new UzytkownikException(ex.getMessage());
        }
    }
    
}
