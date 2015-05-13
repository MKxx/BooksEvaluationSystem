/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.endpoints;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import pl.lodz.ssbd.entities.PoziomDostepu;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.exceptions.PoprzednieHasloException;
import pl.lodz.ssbd.exceptions.PoziomDostepuException;
import pl.lodz.ssbd.exceptions.UzytkownikException;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Local
public interface MOKEndpointLocal {
    public Uzytkownik pobierzSiebieDoEdycji();

    public void rejestrujUzytkownika(Uzytkownik nowyUzytkownik) throws UzytkownikException;
    public List<Uzytkownik> pobierzUzytkownikow(String wartosc);
    public void potwierdzUzytkownika(Uzytkownik uzytkownik);
    public void zablokujUzytkownika(Uzytkownik uzytkownik)throws UzytkownikException;
    public void odblokujUzytkownika(Uzytkownik uzytkownik)throws UzytkownikException;

    public void zalogujPoprawneUwierzytelnienie(String username, String IP);

    public void zalogujNiepoprawneUwierzytenienie(String username,String IP);

    public void zapiszKontoPoEdycji(Uzytkownik uzytkownikEdycja, boolean zmianaHasla) throws UzytkownikException, PoprzednieHasloException;
    
    public String pobierzIPOstatniegoPopZalogowania();
    public Date pobierzCzasOstatniegoPopZalogowania();
    public int pobierzIloscNPopZal();
    public Uzytkownik pobierzUzytkownikaDoEdycji(String login);

    public void nadajPoziom(PoziomDostepu poziom) throws PoziomDostepuException;

    public void odbierzPoziom(PoziomDostepu poziom) throws PoziomDostepuException;
    public Uzytkownik pobierzUzytkownika(String login);

}
