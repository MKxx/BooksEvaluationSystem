/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.endpoints;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import pl.lodz.ssbd.entities.PoziomDostepu;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.mok.facades.PoziomDostepuFacadeLocal;
import pl.lodz.ssbd.mok.facades.UzytkownikFacadeLocal;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Stateful
public class MOKEndpoint implements MOKEndpointLocal {

    @EJB
    private UzytkownikFacadeLocal uzytkownikFacade;
    @EJB
    private PoziomDostepuFacadeLocal poziomDostepuFacade;
    
    @Override
    public void rejestrujUzytkownika(Uzytkownik nowyUzytkownik) {
        PoziomDostepu admin = new PoziomDostepu();
        admin.setAktywny(false);
        admin.setIdUzytkownik(nowyUzytkownik);
        admin.setNazwa("ADMINISTRATOR");
        PoziomDostepu moderator = new PoziomDostepu();
        moderator.setAktywny(false);
        moderator.setIdUzytkownik(nowyUzytkownik);
        moderator.setNazwa("MODERATOR");
        PoziomDostepu uzytkownik = new PoziomDostepu();
        uzytkownik.setAktywny(false);
        uzytkownik.setIdUzytkownik(nowyUzytkownik);
        uzytkownik.setNazwa("UZYTKOWNIK");
        nowyUzytkownik.getPoziomDostepuList().add(admin);
        nowyUzytkownik.getPoziomDostepuList().add(moderator);
        nowyUzytkownik.getPoziomDostepuList().add(uzytkownik);
        poziomDostepuFacade.create(admin);
        poziomDostepuFacade.create(moderator);
        poziomDostepuFacade.create(uzytkownik);
        uzytkownikFacade.create(nowyUzytkownik);
    }

}
