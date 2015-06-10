/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo2.facades;

import java.util.List;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import pl.lodz.ssbd.facades.*;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.entities.Ocena;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.exceptions.OcenaException;
import pl.lodz.ssbd.exceptions.SSBD05Exception;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;

/**
 *
 * @author Robert Mielczarek 
 */
@Stateless(name="moo2Ocena")
@Interceptors({DziennikZdarzenInterceptor.class})
@TransactionAttribute(TransactionAttributeType.MANDATORY)
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
            throw new OcenaException("exceptions.ocena.istnieje");
        }
    }

    @Override
    @RolesAllowed("DodanieOceny")
    public void create(Ocena entity) throws OcenaException {
        try {
            super.create(entity); //To change body v generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
            throw new OcenaException("exceptions.ocena.istnieje");
        }
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

    /**
     * Metoda znajdująca ocenę dla danego użytkownika i podanej książki
     * @param ksiazka książka 
     * @param uzytkownik uzytkownik
     * @return ocena dla podanej ksiazki dla danego uzytkownika
     */
    @Override
    @RolesAllowed("ZmianaOceny")
    public Ocena findByKsiazkaAndLogin(Ksiazka ksiazka, Uzytkownik uzytkownik) {
        Query q = em.createQuery("SELECT o FROM Ocena o WHERE o.idKsiazka = :ksiazka AND o.idUzytkownik = :uzytkownik");
        q.setParameter("ksiazka", ksiazka);
        q.setParameter("uzytkownik", uzytkownik);
        try {
            return (Ocena) q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
