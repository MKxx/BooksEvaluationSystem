/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moks.facades;

import java.util.List;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import pl.lodz.ssbd.facades.*;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.exceptions.KsiazkaException;
import pl.lodz.ssbd.exceptions.SSBD05Exception;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Stateless(name="moksKsiazka")
@Interceptors({DziennikZdarzenInterceptor.class})
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class KsiazkaFacade extends AbstractFacade<Ksiazka> implements KsiazkaFacadeLocal {
    @PersistenceContext(unitName = "ssbd05moks")
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
    public List<Ksiazka> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed("ModyfikacjaKsiazki")
    public Ksiazka find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed("WyswietlanieListyUlubionych")
    public List<Ksiazka> findUlubione(String login) throws KsiazkaException {
        if (login == null || "".equals(login)) {
            throw new KsiazkaException();
        }
        Query q = em.createQuery("SELECT DISTINCT k FROM Ocena o JOIN FETCH o.idKsiazka k JOIN FETCH o.idUzytkownik u WHERE u.login = :login AND o.ulubiona = true");
        q.setParameter("login", login);

        return q.getResultList();
    }

    @Override
    @RolesAllowed("WyswietlenieNieaktywnych")
    public List<Ksiazka> findNieaktywne() {
        TypedQuery<Ksiazka> query = em.createNamedQuery("Ksiazka.findByAktywne", Ksiazka.class);
        query.setParameter("aktywne", false);
        return query.getResultList();
    }
    
    @Override
    @RolesAllowed({"ModyfikacjaKsiazki", "OznaczenieJakoNieaktywna"})
    public void edit(Ksiazka entity) throws KsiazkaException  {
        try {
            super.edit(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
        }
    }

    @Override
    @RolesAllowed("DodanieKsiazki")
    public void create(Ksiazka entity) throws KsiazkaException  {
        try {
            super.create(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
        }
    }

    @Override
    @RolesAllowed("PrzegladanieKsiazekModeratorski")
    public List<Ksiazka> findAktywne() {                 
        TypedQuery<Ksiazka> query = em.createNamedQuery("Ksiazka.findByAktywne", Ksiazka.class);
        query.setParameter("aktywne", true);
        return query.getResultList();
    }

}
