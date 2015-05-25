/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo.endpoints;

import java.util.List;
import javax.ejb.Local;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.entities.Ocena;


/**
 *
 * @author Maciej
 */
@Local
public interface MOOEndpointLocal {
    public void dodajDoUlubionych(Ksiazka ksiazka);
    public List<Ksiazka> pobierzKsiazki();
    public List<Ocena> pobierzOceny();
    
}
