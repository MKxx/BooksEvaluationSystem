/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.endpoints;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import pl.lodz.ssbd.entities.PoziomDostepu;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.mok.facades.UzytkownikFacadeLocal;
import pl.lodz.ssbd.utils.MD5;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Stateful
public class MOKEndpoint implements MOKEndpointLocal {

    @EJB(beanName = "mokU")
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
    public void potwierdzUzytkownika(Uzytkownik uzytkownik) {
        Uzytkownik u = uzytkownikFacade.find(uzytkownik.getIdUzytkownik());
        u.setPotwierdzony(true);
    }

    @Override
    public void zablokujUzytkownika(Uzytkownik uzytkownik) {
        Uzytkownik u = uzytkownikFacade.find(uzytkownik.getIdUzytkownik());
        u.setAktywny(false);
    }

    @Override
    public void odblokujUzytkownika(Uzytkownik uzytkownik) {
        Uzytkownik u = uzytkownikFacade.find(uzytkownik.getIdUzytkownik());
        u.setAktywny(true);
    }

    @Override
    public boolean zaloguj(String username, String password, String IP) {
        System.out.println(username);
        System.out.println(password);
        System.out.println(IP);
        Uzytkownik uzytkownik = uzytkownikFacade.findByLogin(username);
        if (uzytkownik == null) {
            return false;
        }
        if (!uzytkownik.getHasloMd5().equals(MD5.hash(password))) {
            uzytkownik.setCzasNPopZal(new Date());
            if (uzytkownik.getIloscNPopZal() == 2) {
                uzytkownik.setAktywny(false);
            }
            uzytkownik.setIloscNPopZal(uzytkownik.getIloscNPopZal() + 1);
            return false;
        }
        uzytkownik.setCzasPopZal(new Date());
        uzytkownik.setIpPopZal(IP);
        uzytkownik.setIloscNPopZal(0);
        return true;
    }
}
