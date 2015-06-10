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
     /**
     * Przeglądanie autorow 
     * @return lista autorow
     */
    public List<Autor> pobierzListeAutorow();
     /**
     * Pobiera autora do edycji
     * @param id id autora
     * @return autor do edycji
     */
    public Autor pobierzAutoraDoEdycji(long id);
    public Autor pobierzAutora(long id);
     /**
     * Dodanie nowego autora
     * @param autor autor do dodania
     * @param wybraneKsiazki ksiazki autora
     */
    public void dodajAutora(Autor autor, List<String> wybraneKsiazki);
     /**
     * Edycja autora
     * @param autor edytowany autor
     */
    public void edytujAutora(Autor autor);
    /**
     * Wyświetlenie listy książek które nie zostały do tej pory ocenione
     * @return ksiazki nieocenione
     */
    public List<Ksiazka> pobierzKsiazkiNieocenione();
    
}
