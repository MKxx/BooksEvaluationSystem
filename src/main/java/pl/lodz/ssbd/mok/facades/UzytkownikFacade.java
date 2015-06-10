/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.facades;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.exceptions.SSBD05Exception;
import pl.lodz.ssbd.exceptions.UzytkownikException;
import pl.lodz.ssbd.facades.AbstractFacade;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;
import pl.lodz.ssbd.moa.facades.AutorFacade;

/**
 *
 * @author Robert Mielczarek 
 */
@Stateless(name = "mokU")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors({DziennikZdarzenInterceptor.class})
public class UzytkownikFacade extends AbstractFacade<Uzytkownik> implements UzytkownikFacadeLocal {

    @Override
    public void create(Uzytkownik entity) throws UzytkownikException {
        try {
            super.create(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
            if (ex.getMessage() != null && ex.getMessage().contains("Klucz (email)")) {
                throw new UzytkownikException("exceptions.uzytkownik.email");
            }
            if (ex.getMessage() != null && ex.getMessage().contains("Klucz (login)")) {
                throw new UzytkownikException("exceptions.uzytkownik.login");
            } else {
                throw new UzytkownikException("exceptions.uzytkownik.unknown");
            }
        }
    }

    @Override
    public List<Uzytkownik> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @DenyAll
    public Uzytkownik find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed({"ModyfikowanieDanychSwojegoKonta", "ModyfikowanieDanychCudzegoKonta", "BlokowanieOdblokowanieUzytkownia", "AutoryzacjaKonta"})
    public void edit(Uzytkownik entity) throws UzytkownikException {
        try {
            super.edit(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
            throw new UzytkownikException("exceptions.uzytkownik.concurrent");
        }
    }

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
    @PermitAll
    public Uzytkownik findByLogin(String login) {
        Query q = em.createNamedQuery("Uzytkownik.findByLogin");
        q.setParameter("login", login);
        try {
            return (Uzytkownik) q.getSingleResult();
        } catch (NoResultException ex) {
            Logger.getLogger(UzytkownikFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
    }

    @Override
    @RolesAllowed("WyswietlaniePaneluAdmina")
    public List<Uzytkownik> findByImieiNazwisko(String wartosc) {
        if (wartosc == null || "".equals(wartosc)) {
            Query q = em.createNamedQuery("Uzytkownik.findAll", Uzytkownik.class);
            return q.getResultList();
        }
        Query q = em.createQuery("SELECT DISTINCT u FROM Uzytkownik u JOIN FETCH u.poziomDostepuList WHERE LOWER(u.imie) LIKE :wartosc OR LOWER(u.nazwisko) LIKE :wartosc ");
        q.setParameter("wartosc", "%" + wartosc.toLowerCase() + "%");
        return q.getResultList();
    }
}
