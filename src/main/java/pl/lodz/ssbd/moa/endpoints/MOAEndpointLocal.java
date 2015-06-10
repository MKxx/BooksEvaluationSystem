/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moa.endpoints;

import java.util.List;
import javax.ejb.Local;
import pl.lodz.ssbd.entities.Autor;
import pl.lodz.ssbd.entities.Ksiazka;

/**
 *
 * @author Maciej
 */
@Local
public interface MOAEndpointLocal {
    
    public List<Autor> pobierzListeAutorow();
    public Autor pobierzAutoraDoEdycji(long id);
    public Autor pobierzAutora(long id);
    public void dodajAutora(Autor autor, List<String> wybraneKsiazki);
    public void edytujAutora(Autor autor);

    public List<Ksiazka> pobierzKsiazkiNieocenione();
    
}
