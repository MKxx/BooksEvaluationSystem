/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.entities.Ocena;
import pl.lodz.ssbd.exceptions.KsiazkaException;
import pl.lodz.ssbd.exceptions.OcenaException;
import pl.lodz.ssbd.exceptions.UzytkownikException;
import pl.lodz.ssbd.moo.moo.endpoints.MOOEndpointLocal;
import pl.lodz.ssbd.moo.moo2.endpoints.MOO2EndpointLocal;

/**
 *
 * @author Maciej
 */
@Named
@SessionScoped
public class OcenaSession implements Serializable {

    @EJB
    MOOEndpointLocal MOOEndpoint;

    @EJB
    MOO2EndpointLocal MOO2Endpoint;
    
    List<Ksiazka> pobierzKsiazki() {
        return MOOEndpoint.pobierzKsiazki();
    }

    List<Ocena> pobierzOceny() {
        return MOOEndpoint.pobierzOceny();
    }
    
        @RolesAllowed("DodanieDoUlubionych")
    void dodajDoUlub(Ocena ocena) throws OcenaException{
        MOOEndpoint.dodajDoUlubionych(ocena);
    }
    
    
    public void zmienOcene(long id_ksiazki, int ocena, String login) throws OcenaException, KsiazkaException, UzytkownikException {
        MOO2Endpoint.zmienOcene(id_ksiazki, ocena, login);
    }

    void ocen(long id_ksiazka, int ocena, String login) throws UzytkownikException, OcenaException {
        MOO2Endpoint.ocenKsiazke(id_ksiazka, ocena, login);
    }
}
