/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo.endpoints;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.entities.Ocena;

import pl.lodz.ssbd.exceptions.OcenaException;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;
import pl.lodz.ssbd.moo.moo.facades.KsiazkaFacadeLocal;
import pl.lodz.ssbd.moo.moo.facades.OcenaFacadeLocal;

/**
 *
 * @author Maciej
 */
@Stateful
@Interceptors({DziennikZdarzenInterceptor.class})
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MOOEndpoint implements MOOEndpointLocal, SessionSynchronization {
    
    @EJB(beanName = "mooKsiazka")
    private KsiazkaFacadeLocal KsiazkaFacade;
    @EJB(beanName = "mooOcena")
    private OcenaFacadeLocal ocenaFacade;
    private long IDTransakcji;
    @Resource
    private SessionContext sessionContext;
    private SimpleDateFormat simpleDateHere = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss (Z)");
    private static final Logger loger = Logger.getLogger(MOOEndpoint.class.getName());
    
    private List<Ksiazka> listaKsiazekDostep;

     /**
     * Metoda zwracająca listę aktywnych książek
     * @return lista aktywnych książek
     */
    @Override
    @PermitAll
    public List<Ksiazka> pobierzKsiazki() {
        listaKsiazekDostep = KsiazkaFacade.findAktywne();
        return listaKsiazekDostep;
    }

    /**
     * Metoda dodająca książkę do ulubionych 
     * Należy zauważyć, że u nas jest to rozwiązane w dośc oryginaly sposób
     * @param ocena która zawiera ksiązkę, którą użytkownik chce dodać do ulubionych.
     * @throws OcenaException 
     */
    @Override
    @RolesAllowed("DodanieDoUlubionych")
    public void dodajDoUlubionych(Ocena ocena) throws OcenaException{
      ocena.setUlubiona(true);
      ocenaFacade.edit(ocena);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    /**
     * Metoda pobierająca wszystkie oceny
     * @return lista wszystkich ocen
     */
    @Override
    public List<Ocena> pobierzOceny() {
        return ocenaFacade.findOcenyInitalized();
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
