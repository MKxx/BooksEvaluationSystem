/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo.endpoints;

import java.util.List;
import javax.ejb.Stateless;
import pl.lodz.ssbd.entities.Ksiazka;

/**
 *
 * @author Maciej
 */
@Stateless
public class MOOEndpoint implements MOOEndpointLocal {

    @Override
    public List<Ksiazka> pobierzKsiazki() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dodajDoUlubionych(Ksiazka ksiazka) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
