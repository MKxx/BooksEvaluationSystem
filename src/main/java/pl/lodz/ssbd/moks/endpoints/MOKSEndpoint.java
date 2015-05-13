/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moks.endpoints;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;
import pl.lodz.ssbd.moks.facades.KsiazkaFacadeLocal;
import pl.lodz.ssbd.moks.facades.UzytkownikFacadeLocal;

/**
 *
 * @author Maciej
 */
@Stateful
@Interceptors({DziennikZdarzenInterceptor.class})
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MOKSEndpoint implements MOKSEndpointLocal {
    
    @EJB(beanName = "moksKsiazka")
    private KsiazkaFacadeLocal KsiazkaFacade;
    @EJB(beanName = "moksUzytkownik")
    private UzytkownikFacadeLocal UzytkownikFacade;
    

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
