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

import pl.lodz.ssbd.exceptions.KsiazkaException;
import pl.lodz.ssbd.exceptions.OcenaException;
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
    private OcenaFacadeLocal ocenaFacade;
    
    private List<Ksiazka> listaKsiazekDostep;

    /**
     *
     * @param login
     * @return
     * @throws KsiazkaException
     */
    @Override
    @PermitAll
    public List<Ksiazka> pobierzKsiazki() {
        listaKsiazekDostep = KsiazkaFacade.findAktywne();
        return listaKsiazekDostep;
    }

    @Override
    @RolesAllowed("DodanieDoUlubionych")
    public void dodajDoUlubionych(Ocena ocena) throws OcenaException{
      ocena.setUlubiona(true);
      ocenaFacade.edit(ocena);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Ocena> pobierzOceny() {
        return ocenaFacade.findOcenyInitalized();
    }
}
