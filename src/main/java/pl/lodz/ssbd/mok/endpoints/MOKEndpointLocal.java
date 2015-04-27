/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.endpoints;

import java.util.List;
import javax.ejb.Local;
import pl.lodz.ssbd.entities.Uzytkownik;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Local
public interface MOKEndpointLocal {

    public void rejestrujUzytkownika(Uzytkownik nowyUzytkownik);
    public List<Uzytkownik> pobierzWszystkichUzytkownikow();
    public void potwierdzUzytkownika(Uzytkownik uzytkownik);
    public void zablokujUzytkownika(Uzytkownik uzytkownik);
    public void odblokujUzytkownika(Uzytkownik uzytkownik);

    public boolean zaloguj(String username, String password, String IP);

    public void zalogujPoprawneUwierzytelnienie(String username, String password, String IP);

    public void zalogujNiepoprawneUwierzytenienie(String username, String password, String IP);

    public Uzytkownik pobierzUzytkownika(String login);
}
