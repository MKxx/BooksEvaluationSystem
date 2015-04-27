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
import pl.lodz.ssbd.entities.PoprzednieHaslo;
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

    @EJB(beanName = "mokU")
    private UzytkownikFacadeLocal uzytkownikFacade;
    @EJB(beanName = "mokPD")
    private PoziomDostepuFacadeLocal poziomDostepuFacade;
    private Uzytkownik uzytkownikEdycja;
    private String hasloPrzedEdycja;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void zalogujPoprawneUwierzytelnienie(String username, String password, String IP) {
        Uzytkownik uzytkownik = uzytkownikFacade.findByLogin(username);
        uzytkownik.setCzasPopZal(new Date());
        uzytkownik.setIpPopZal(IP);
        if (uzytkownik.getAktywny()) {
            uzytkownik.setIloscNPopZal(0);
        }
    }

    @Override
    public void zalogujNiepoprawneUwierzytenienie(String username, String password, String IP) {
        Uzytkownik uzytkownik = uzytkownikFacade.findByLogin(username);
        if (uzytkownik == null) {
            return;
        }
        int ilosc_niepoprawnych_zalogowan = uzytkownik.getIloscNPopZal();
        uzytkownik.setCzasNPopZal(new Date());
        if (ilosc_niepoprawnych_zalogowan == 2) {
            uzytkownik.setIloscNPopZal(ilosc_niepoprawnych_zalogowan + 1);
            uzytkownik.setAktywny(false);
        } else {
            uzytkownik.setIloscNPopZal(ilosc_niepoprawnych_zalogowan + 1);
        }
    }
    
    @Override
    public Uzytkownik pobierzUzytkownikaDoEdycji(String login){
        uzytkownikEdycja = uzytkownikFacade.findByLogin(login);
        hasloPrzedEdycja = uzytkownikEdycja.getHasloMd5();
        System.out.println(uzytkownikEdycja.getImie());
        return uzytkownikEdycja;
    }

    @Override
    public void zapiszKontoPoEdycji(Uzytkownik uzytkownik) {
        if (null == uzytkownikEdycja) {
            throw new IllegalArgumentException("Brak wczytanego uzytkownika do modyfikacji");
        }
        if (!uzytkownikEdycja.equals(uzytkownik)) {
            throw new IllegalArgumentException("Modyfikowany uzytkownik niezgodny z wczytanym");
        }
        if(!hasloPrzedEdycja.equals(uzytkownikEdycja.getHasloMd5())){
            PoprzednieHaslo popHaslo = new PoprzednieHaslo();
            popHaslo.setIdUzytkownik(uzytkownik);
            popHaslo.setStareHasloMd5(hasloPrzedEdycja);
            uzytkownik.getPoprzednieHasloList().add(popHaslo);
        }
        uzytkownikFacade.edit(uzytkownikEdycja);
        uzytkownikEdycja = null;
    }

    @Override
    public void nadajPoziom(PoziomDostepu poziom) {
        poziom.setAktywny(true);
        poziomDostepuFacade.edit(poziom);
        
    }

    @Override
    public void odbierzPoziom(PoziomDostepu poziom) {
        poziom.setAktywny(false);
        poziomDostepuFacade.edit(poziom);
    }

    @Override
    public Uzytkownik pobierzUzytkownika(String login) {
        return uzytkownikFacade.findByLogin(login);
    }
}
