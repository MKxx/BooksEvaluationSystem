/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.facades;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import pl.lodz.ssbd.entities.Uzytkownik;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Local
public interface UzytkownikFacadeLocal {

    void create(Uzytkownik uzytkownik);

    void edit(Uzytkownik uzytkownik);

    void remove(Uzytkownik uzytkownik);

    @RolesAllowed({"AutoryzacjaKonta","BlokowanieOdblokowanieUzytkownia"})
    Uzytkownik find(Object id);

    List<Uzytkownik> findAll();

    List<Uzytkownik> findRange(int[] range);

    int count();

    @PermitAll
    @RolesAllowed({"ModyfikowanieDanychSwojegoKonta", "WyszukiwanieUzytkownika"})
    public Uzytkownik findByLogin(String username);
    
    public List<Uzytkownik> findByImieiNazwisko(String wartosc);
}
