/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo2.endpoints;

import javax.ejb.Local;
import pl.lodz.ssbd.entities.Ksiazka;

/**
 *
 * @author Maciej
 */
@Local
public interface MOO2EndpointLocal {
    public void ocenKsiazke(Ksiazka ksiazka);
    public void zmienOcene(Ksiazka ksiazka);
    
}
