/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.facades;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.facades.AbstractFacade;
/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Stateless(name = "mokU")
@TransactionAttribute(TransactionAttributeType.MANDATORY)        
public class UzytkownikFacade extends AbstractFacade<Uzytkownik> implements UzytkownikFacadeLocal {

    @PersistenceContext(unitName = "ssbd05mok")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UzytkownikFacade() {
        super(Uzytkownik.class);
    }

    @Override
    public Uzytkownik findByLogin(String login) {
        Query q = em.createNamedQuery("Uzytkownik.findByLogin");
        q.setParameter("login", login);
        try {
            return (Uzytkownik) q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
