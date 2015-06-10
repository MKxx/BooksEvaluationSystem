/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo2.facades;

import java.util.List;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import pl.lodz.ssbd.facades.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.exceptions.SSBD05Exception;
import pl.lodz.ssbd.exceptions.UzytkownikException;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;

/**
 *
 * @author Robert Mielczarek 
 */
@Stateless(name="moo2Uzytkownik")
@Interceptors({DziennikZdarzenInterceptor.class})
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class UzytkownikFacade extends AbstractFacade<Uzytkownik> implements UzytkownikFacadeLocal {

    @PersistenceContext(unitName = "ssbd05moo2")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UzytkownikFacade() {
        super(Uzytkownik.class);
    }

    @Override
    @DenyAll
    public void edit(Uzytkownik entity) throws UzytkownikException {
        try {
            super.edit(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
            throw new UzytkownikException(ex.getMessage());
        }
    }

    @Override
    @DenyAll
    public void create(Uzytkownik entity) throws UzytkownikException {
        try {
            super.create(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
            throw new UzytkownikException(ex.getMessage());
        }
    }

    @Override
    @DenyAll
    public List<Uzytkownik> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed({"DodanieOceny","ZmianaOceny"})
    public Uzytkownik find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Metoda znajdująca użytkownika o podanym loginie
     * @param username
     * @return uzytkownik
     * @throws UzytkownikException jeśli nie ma takiego użytkownika
     */
    @Override
    @RolesAllowed("ZmianaOceny")
    public Uzytkownik findByLogin(String username) throws UzytkownikException{
        Query q = em.createNamedQuery("Uzytkownik.findByLogin");
        q.setParameter("login", username);
        try {
            return (Uzytkownik) q.getSingleResult();
        } catch (NoResultException ex) {
            throw new UzytkownikException("exceptions.uzytkownik.brakuzytkownika");
       }
    }
}
