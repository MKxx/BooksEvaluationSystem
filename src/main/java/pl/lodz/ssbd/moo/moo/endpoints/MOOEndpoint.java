/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo.endpoints;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.entities.Ocena;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;
import pl.lodz.ssbd.moo.moo.facades.KsiazkaFacadeLocal;
import pl.lodz.ssbd.moo.moo.facades.OcenaFacadeLocal;

/**
 *
 * @author Maciej
 */
@Stateless
@Interceptors({DziennikZdarzenInterceptor.class})
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MOOEndpoint implements MOOEndpointLocal {
    
    @EJB(beanName = "mooKsiazka")
    private KsiazkaFacadeLocal KsiazkaFacade;
    @EJB(beanName = "mooOcena")
    private OcenaFacadeLocal OcenaFacade;

    @Override
    @RolesAllowed("WyswietlanieListyUlubionych")
    public List<Ksiazka> pobierzUlubione(String login) {
        return KsiazkaFacade.findUlubione(login);
    }

    @Override
    @RolesAllowed("DodanieDoUlubionych")
    public void dodajDoUlubionych(Ksiazka ksiazka) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
