/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moa.endpoints;

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
import pl.lodz.ssbd.entities.Autor;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.exceptions.AutorException;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;
import pl.lodz.ssbd.moa.facades.AutorFacadeLocal;
import pl.lodz.ssbd.moa.facades.KsiazkaFacadeLocal;

/**
 *
 * @author Maciej
 */
@Stateful
@Interceptors({DziennikZdarzenInterceptor.class})
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MOAEndpoint implements MOAEndpointLocal, SessionSynchronization  {
    
    @EJB(beanName = "moaAutor")
    private AutorFacadeLocal AutorFacade;
    @EJB(beanName = "moaKsiazka")
    private KsiazkaFacadeLocal ksiazkaFacade;
    private long IDTransakcji;
    @Resource
    private SessionContext sessionContext;
    private SimpleDateFormat simpleDateHere = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss (Z)");
    private static final Logger loger = Logger.getLogger(MOAEndpoint.class.getName());
    
    @Override
    @PermitAll
    public List<Autor> pobierzListeAutorow() {
      return AutorFacade.findAll();
    }

    @Override
    @RolesAllowed("ModyfikacjaAutora")
    public Autor pobierzAutoraDoEdycji(long id) {
        return AutorFacade.find(id);
    }

    @Override
    @RolesAllowed("DodanieAutora")
    public void dodajAutora(Autor autor, List<String> wybraneKsiazki) {
        try {
            if(wybraneKsiazki.size() != 0){
                for(String ks : wybraneKsiazki){
                    Ksiazka k = ksiazkaFacade.find(Long.parseLong(ks));
                    k.getAutorList().add(autor);
                    autor.getKsiazkaList().add(k);
                }
            }
            AutorFacade.create(autor);
            
        } catch (AutorException ex) {
            Logger.getLogger(MOAEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    @RolesAllowed("ModyfikacjaAutora")
    public void edytujAutora(Autor autor) {
        try{
            AutorFacade.edit(autor);
        }
        catch(AutorException ex){
            Logger.getLogger(MOAEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    @PermitAll
    public Autor pobierzAutora(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ksiazka> pobierzKsiazkiNieocenione() {
        return ksiazkaFacade.findNieocenione();
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
