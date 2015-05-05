/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.facades;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.facades.AbstractFacade;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;
/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Stateless(name = "mokU")
@TransactionAttribute(TransactionAttributeType.MANDATORY)        
@Interceptors({DziennikZdarzenInterceptor.class})
public class UzytkownikFacade extends AbstractFacade<Uzytkownik> implements UzytkownikFacadeLocal {

    @Override
    public int count() {
        return super.count(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Uzytkownik> findRange(int[] range) {
        return super.findRange(range); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Uzytkownik> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed({"AutoryzacjaKonta","BlokowanieOdblokowanieUzytkownia"})
    public Uzytkownik find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Uzytkownik entity) {
        super.remove(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed("ModyfikowanieDanychCudzegoKonta")
    public void edit(Uzytkownik entity) {
        super.edit(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @PermitAll
    public void create(Uzytkownik entity) {
        super.create(entity); //To change body of generated methods, choose Tools | Templates.
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
            return null;
        }
    }

    @Override
    @RolesAllowed("WyswietlaniePaneluAdmina")
    public List<Uzytkownik> findByImieiNazwisko(String wartosc) {
        if (wartosc == null || "".equals(wartosc)) {
            return findAll();
        }
        Query q = em.createQuery("SELECT u FROM Uzytkownik u WHERE LOWER(u.imie) LIKE :wartosc OR LOWER(u.nazwisko) LIKE :wartosc ");
        q.setParameter("wartosc", "%"+wartosc.toLowerCase()+"%");
        return q.getResultList();
    }
}
