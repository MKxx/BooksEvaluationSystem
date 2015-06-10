/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.facades;

import java.util.List;
import javax.ejb.Local;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.exceptions.UzytkownikException;

/**
 *
 * @author Robert Mielczarek 
 */
@Local
public interface UzytkownikFacadeLocal {

    void create(Uzytkownik uzytkownik) throws UzytkownikException;

    void edit(Uzytkownik uzytkownik) throws UzytkownikException;

    Uzytkownik find(Object id);

    List<Uzytkownik> findAll();

    /**
     * Znajdz uzytkownika po loginie
     * @param username login
     * @return encja uzytkownika
     */
    public Uzytkownik findByLogin(String username);

    /**
     * znajdz po imieniu i nazwisku
     * @param wartosc ciag szukany w imieniu lub nazwisku
     * @return lista uzytkownikow
     */
    public List<Uzytkownik> findByImieiNazwisko(String wartosc);
}
