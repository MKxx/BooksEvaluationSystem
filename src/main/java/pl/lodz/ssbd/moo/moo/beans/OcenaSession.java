/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo.beans;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.entities.Ocena;
import pl.lodz.ssbd.moo.moo.endpoints.MOOEndpointLocal;

/**
 *
 * @author Maciej
 */
@Named
@SessionScoped
public class OcenaSession implements Serializable {

    @EJB
    MOOEndpointLocal MOOEndpoint;
    
    List<Ksiazka> pobierzKsiazki() {
        return MOOEndpoint.pobierzKsiazki();
    }

    List<Ocena> pobierzOceny() {
        return MOOEndpoint.pobierzOceny();
    }
}
