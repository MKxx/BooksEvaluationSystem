/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moks.endpoints;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import pl.lodz.ssbd.entities.Autor;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.exceptions.KsiazkaException;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;
import pl.lodz.ssbd.moks.facades.AutorFacadeLocal;
import pl.lodz.ssbd.moks.facades.KsiazkaFacadeLocal;

/**
 *
 * @author Maciej
 */
@Stateful
@Interceptors({DziennikZdarzenInterceptor.class})
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MOKSEndpoint implements MOKSEndpointLocal, SessionSynchronization {
    
    @EJB(beanName = "moksKsiazka")
    private KsiazkaFacadeLocal ksiazkaFacade;
    @EJB(beanName = "moksAutor")
    private AutorFacadeLocal autorFacade;
    private Ksiazka edytowanaKsiazka;
    
    private static final Logger loger = Logger.getLogger(MOKSEndpoint.class.getName());
    private long IDTransakcji;
    @Resource
    private SessionContext sessionContext;
    private static SimpleDateFormat simpleDateHere = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss (Z)");

    @Override
    @RolesAllowed("PrzegladanieKsiazekModeratorski")
    public List<Ksiazka> pobierzKsiazki() {
        return ksiazkaFacade.findAktywne();
    }

    @Override
    @RolesAllowed("ModyfikacjaKsiazki")
    public Ksiazka pobierzKsiazkeDoEdycji(long id) {
        edytowanaKsiazka = ksiazkaFacade.find(id);
        return edytowanaKsiazka;
    }

    @Override
    @RolesAllowed("ModyfikacjaKsiazki")
    public void edytujKsiazke(Ksiazka ksiazka) throws KsiazkaException {
        if (null == ksiazka) {
            throw new IllegalArgumentException("Wartosc ksiazka rowna null");
        } else if (!ksiazka.equals(edytowanaKsiazka)) {
            throw new IllegalArgumentException("Modyfikowana ksiazka niezgodna z wczytaną");
        }
        
        ksiazkaFacade.edit(edytowanaKsiazka);
        edytowanaKsiazka = null;
    }

    @Override
    @RolesAllowed("DodanieKsiazki")
    public void dodajKsiazke(Ksiazka ksiazka, List<String> wybraniAutorzy) {
        List<Autor> autorzyTemp = autorFacade.findAll();
        List<Autor> wybAutorzy = new ArrayList<>();
        for(String s : wybraniAutorzy){
            for(Autor a : autorzyTemp){
                if(Long.parseLong(s) == a.getIdAutor()){
                    a.getKsiazkaList().add(ksiazka);
                    wybAutorzy.add(a);
                }
            } 
        }
        ksiazka.setAutorList(wybAutorzy);
        ksiazka.setIloscAutorow(wybAutorzy.size());
        ksiazka.setAktywne(true);
        try {
            ksiazkaFacade.create(ksiazka);
        } catch (KsiazkaException ex) {
            Logger.getLogger(MOKSEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    @RolesAllowed("OznaczenieJakoNieaktywna")
    public void oznaczKsiazkeJakoNieaktywna(Ksiazka ksiazka) throws KsiazkaException{
        ksiazka.setAktywne(false);
        ksiazkaFacade.edit(ksiazka);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    @RolesAllowed("WyswietlenieNieaktywnych")
    public List<Ksiazka> pobierzKsiazkiNieaktywne() {
        return ksiazkaFacade.findNieaktywne();
    }


    @Override
    @RolesAllowed("WyswietlanieListyUlubionych")
    public List<Ksiazka> pobierzKsiazkiUlubione(String login) throws KsiazkaException{
        return ksiazkaFacade.findUlubione(login);
    
    }

    @Override
    @RolesAllowed("DodanieKsiazki")
    public List<Autor> pobierzAutorow() {
        return autorFacade.findAll();
    }

    @Override
    public void afterBegin() throws EJBException, RemoteException {
        IDTransakcji = System.currentTimeMillis();
        loger.log(Level.INFO, simpleDateHere.format(new Date()).toString()+" || Transakcja o ID: " 
                + IDTransakcji + " zostala rozpoczeta ,przez użytkownika "
                + sessionContext.getCallerPrincipal().getName());
    }

    @Override
    public void beforeCompletion() throws EJBException, RemoteException {
        loger.log(Level.INFO, simpleDateHere.format(new Date()).toString()+" || Transakcja o ID: " + IDTransakcji 
                + " przed zakonczeniem przez użytownka "
                + sessionContext.getCallerPrincipal().getName());
    }

    @Override
    public void afterCompletion(boolean committed) throws EJBException, RemoteException {
        loger.log(Level.INFO, simpleDateHere.format(new Date()).toString()+" || Transakcja o ID: " + IDTransakcji 
                + " zostala zakonczona przez: "
                + (committed ? "zatwierdzenie" : "wycofanie") + " przez użytkownia"
                + sessionContext.getCallerPrincipal().getName());
    }
}
