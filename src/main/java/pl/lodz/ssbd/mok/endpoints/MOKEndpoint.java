/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.endpoints;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import pl.lodz.ssbd.entities.PoziomDostepu;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.mok.facades.UzytkownikFacadeLocal;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Stateful
public class MOKEndpoint implements MOKEndpointLocal {

    @EJB(beanName="mokU")
    private UzytkownikFacadeLocal uzytkownikFacade;
    
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
        uzytkownik.setAktywny(true);
        uzytkownik.setIdUzytkownik(nowyUzytkownik);
        uzytkownik.setNazwa("UZYTKOWNIK");
        nowyUzytkownik.getPoziomDostepuList().add(admin);
        nowyUzytkownik.getPoziomDostepuList().add(moderator);
        nowyUzytkownik.getPoziomDostepuList().add(uzytkownik);
        nowyUzytkownik.setAktywny(true);
        uzytkownikFacade.create(nowyUzytkownik);
    }
    
    @Override
    public List<Uzytkownik> pobierzWszystkichUzytkownikow() {
        return uzytkownikFacade.findAll();
    }
    
    @Override
    public void potwierdzUzytkownika(Uzytkownik uzytkownik){
        Uzytkownik u = uzytkownikFacade.find(uzytkownik.getIdUzytkownik());
        u.setPotwierdzony(true);
    }
    
    @Override
    public void zablokujUzytkownika(Uzytkownik uzytkownik){
        Uzytkownik u = uzytkownikFacade.find(uzytkownik.getIdUzytkownik());
        u.setAktywny(false);
    }
    
    @Override
    public void odblokujUzytkownika(Uzytkownik uzytkownik){
        Uzytkownik u = uzytkownikFacade.find(uzytkownik.getIdUzytkownik());
        u.setAktywny(true);
    }

}
