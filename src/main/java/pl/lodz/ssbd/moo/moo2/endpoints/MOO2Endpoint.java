/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo2.endpoints;

import java.math.BigDecimal;
import java.math.MathContext;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import pl.lodz.ssbd.entities.Ocena;
import pl.lodz.ssbd.exceptions.AutorException;
import pl.lodz.ssbd.exceptions.KsiazkaException;
import pl.lodz.ssbd.exceptions.OcenaException;
import pl.lodz.ssbd.exceptions.UzytkownikException;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;
import pl.lodz.ssbd.moo.moo2.facades.AutorFacadeLocal;
import pl.lodz.ssbd.moo.moo2.facades.KsiazkaFacadeLocal;
import pl.lodz.ssbd.moo.moo2.facades.OcenaFacadeLocal;
import pl.lodz.ssbd.moo.moo2.facades.UzytkownikFacadeLocal;

/**
 * Endpoint transakcyjny o poziomie izolacji: Serializable
 * @author Maciej
 */
@Stateful
@Interceptors({DziennikZdarzenInterceptor.class})
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MOO2Endpoint implements MOO2EndpointLocal, SessionSynchronization {

    @EJB(beanName = "moo2Ksiazka")
    private KsiazkaFacadeLocal ksiazkaFacade;
    @EJB(beanName = "moo2Ocena")
    private OcenaFacadeLocal ocenaFacade;
    @EJB(beanName = "moo2Autor")
    private AutorFacadeLocal autorFacade;
    @EJB(beanName = "moo2Uzytkownik")
    private UzytkownikFacadeLocal uzytkownikFacade;
    private long IDTransakcji;
    @Resource
    private SessionContext sessionContext;
    private SimpleDateFormat simpleDateHere = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss (Z)");
    private static final Logger loger = Logger.getLogger(MOO2Endpoint.class.getName());

    /**
     * Metoda dodająca ocenę
     * @param id_ksiazka id książki wskazanej przez użytkownika
     * @param wartosc wartość oceny jaką wskazał użytkownia (1-5)
     * @param login login użytkownika który ocenia książkę.
     * @throws UzytkownikException jeśli użytkownik nie istnieje
     * @throws OcenaException jeśli ocena JUŻ istnieje
     * @throws KsiazkaException  jeśli książka została zmodyfikowana podczas oceny (OptimistickLock).
     */
    @RolesAllowed("DodanieOceny")
    @Override
    public void ocenKsiazke(long id_ksiazka, int wartosc, String login) throws UzytkownikException, OcenaException, KsiazkaException, AutorException {
        Ocena ocena = new Ocena();
        ocena.setIdKsiazka(ksiazkaFacade.find(id_ksiazka));
        ocena.setIdUzytkownik(uzytkownikFacade.findByLogin(login));
        ocena.setOcena(wartosc);
        ocena.setUlubiona(false);
        ocenaFacade.create(ocena);

        long suma = 0;

        for (Ocena o : ocena.getIdKsiazka().getOcenaList()) {
            suma = suma + o.getOcena();
        }
        ocena.getIdKsiazka().setSredniaOcen(new BigDecimal((double) suma / ocena.getIdKsiazka().getOcenaList().size()));
        ksiazkaFacade.edit(ocena.getIdKsiazka());
        for (Autor a : ocena.getIdKsiazka().getAutorList()) {
            BigDecimal licznik = BigDecimal.ZERO;
            BigDecimal mianownik = BigDecimal.ZERO;
            for (Ksiazka k : a.getKsiazkaList()) {
                if (k.getSredniaOcen() != null) {
                    mianownik = mianownik.add(BigDecimal.valueOf(1.0/(k.getIloscAutorow())));
                    licznik=licznik.add(k.getSredniaOcen().multiply(BigDecimal.valueOf(1.0/k.getIloscAutorow())));
                }
            }
            a.setSrOcena(licznik.divide(mianownik, new MathContext(10)));
            autorFacade.edit(a);
        }
    }

    /**
     * Metoda zmieniająca ocenę dla danej ksiązki 
     * @param id_ksiazka id ksiązki wskazanej przez użytkownika
     * @param ocena wartośc oceny jaką wskazał użytkownik (1-5)
     * @param login login użytkownika który dokonuje zmiany oceny
     * @throws OcenaException wyjątek przy jednoczesnej modyfikacji, bądź w przypadku braku oceny.
     * @throws KsiazkaException wyjątek w przypadku modyfikacji książki
     * @throws UzytkownikException wyjątek rzucany w przypaku braku użytkownika
     * @throws pl.lodz.ssbd.exceptions.AutorException w przypadku modyfikacji autora
     */
    @Override
    @RolesAllowed("ZmianaOceny")
    public void zmienOcene(long id_ksiazka, int ocena, String login) throws OcenaException, KsiazkaException, UzytkownikException, AutorException {
        Ocena newOcena = ocenaFacade.findByKsiazkaAndLogin(ksiazkaFacade.find(id_ksiazka), uzytkownikFacade.findByLogin(login));
        if (newOcena == null) {
            throw new OcenaException("exceptions.ocenanieistnieje");
        }
        newOcena.setOcena(ocena);
        ocenaFacade.edit(newOcena);
        long suma = 0;
        for (Ocena o : newOcena.getIdKsiazka().getOcenaList()) {
            suma = suma + o.getOcena();
        }
        newOcena.getIdKsiazka().setSredniaOcen(new BigDecimal((double) suma / newOcena.getIdKsiazka().getOcenaList().size()));
        ksiazkaFacade.edit(newOcena.getIdKsiazka());
        for (Autor a : newOcena.getIdKsiazka().getAutorList()) {
            BigDecimal licznik = BigDecimal.ZERO;
            BigDecimal mianownik = BigDecimal.ZERO;
            for (Ksiazka k : a.getKsiazkaList()) {
                if (k.getSredniaOcen() != null) {
                     mianownik = mianownik.add(BigDecimal.valueOf(1.0/(k.getIloscAutorow())));
                     licznik=licznik.add(k.getSredniaOcen().multiply(BigDecimal.valueOf(1.0/k.getIloscAutorow())));
                }
            }
            a.setSrOcena(licznik.divide(mianownik, new MathContext(10)));
            autorFacade.edit(a);
        }
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
