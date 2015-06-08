/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo2.endpoints;

import javax.ejb.Local;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.exceptions.KsiazkaException;
import pl.lodz.ssbd.exceptions.OcenaException;
import pl.lodz.ssbd.exceptions.UzytkownikException;

/**
 *
 * @author Maciej
 */
@Local
public interface MOO2EndpointLocal {
    public void zmienOcene(long id_ksiazka, int ocena, String login) throws OcenaException, KsiazkaException, UzytkownikException;

    public void ocenKsiazke(long id_ksiazka, int ocena, String login) throws OcenaException, UzytkownikException, KsiazkaException;
}
