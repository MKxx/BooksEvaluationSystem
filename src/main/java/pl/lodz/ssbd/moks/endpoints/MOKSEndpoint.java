/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moks.endpoints;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import pl.lodz.ssbd.entities.Ksiazka;

/**
 *
 * @author Maciej
 */
@Stateful
public class MOKSEndpoint implements MOKSEndpointLocal {

    @Override
    @RolesAllowed("PrzegladanieKsiazekModeratorski")
    public List<Ksiazka> pobierzKsiazki() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed("ModyfikacjaKsiazki")
    public Ksiazka pobierzKsiazkeDoEdycji() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed("ModyfikacjaKsiazki")
    public void edytujKsiazke(Ksiazka ksiazka) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed("DodanieKsiazki")
    public void dodajKsiazke(Ksiazka ksiazka) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed("OznaczenieJakoNieaktywna")
    public void oznaczKsiazkeJakoNieaktywna(Ksiazka ksiazka) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    @RolesAllowed("WyswietlenieNieaktywnych")
    public List<Ksiazka> pobierzKsiazkiNieaktywne() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed("WyswietlenieUlubionych")
    public List<Ksiazka> pobierzKsiazkiUlubione() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
