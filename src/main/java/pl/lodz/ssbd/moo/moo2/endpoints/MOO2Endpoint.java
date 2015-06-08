/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo2.endpoints;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;
import pl.lodz.ssbd.moo.moo2.facades.AutorFacadeLocal;
import pl.lodz.ssbd.moo.moo2.facades.KsiazkaFacadeLocal;
import pl.lodz.ssbd.moo.moo2.facades.OcenaFacadeLocal;
import pl.lodz.ssbd.moo.moo2.facades.UzytkownikFacadeLocal;

/**
 *
 * @author Maciej
 */
@Stateful
@Interceptors({DziennikZdarzenInterceptor.class})
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MOO2Endpoint implements MOO2EndpointLocal {
    
    @EJB(beanName = "moo2Ksiazka")
    private KsiazkaFacadeLocal ksiazkaFacade;
    @EJB(beanName = "moo2Ocena")
    private OcenaFacadeLocal ocenaFacade;
    @EJB(beanName = "moo2Autor")
    private AutorFacadeLocal autorFacade;
    @EJB(beanName = "moo2Uzytkownik")
    private UzytkownikFacadeLocal uzytkownikFacade;

    @Override
    @RolesAllowed("DodanieOceny")
    public void ocenKsiazke(Ksiazka ksiazka) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed("ZmianaOceny")
    public void zmienOcene(Ksiazka ksiazka) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
