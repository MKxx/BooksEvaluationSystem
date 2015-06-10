/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moa.beans;
import java.io.Serializable;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import pl.lodz.ssbd.entities.Autor;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.exceptions.AutorException;
import pl.lodz.ssbd.moa.endpoints.MOAEndpointLocal;
/**
 *
 * @author Marta
 */
@SessionScoped

/**
 * Bean sesyjny dla autora
 */
public class AutorSession implements Serializable {
    @EJB
    MOAEndpointLocal MOAEndpoint;
    
    private Autor edytowanyAutor;
    
    public void setEdytowanyAutor(Autor EdytowanyAutor){
        this.edytowanyAutor = EdytowanyAutor;
    }
    
    public Autor getEdytowanyAutor(){
        return this.edytowanyAutor;
    }
    
    public AutorSession() {
    }
 
    /**
     * Pobiera liste autorow 
     * @return lista autorow 
     */
    @RolesAllowed("PrzegladanieAutorow")
    public List<Autor> pobierzListeAutorow(){
        return MOAEndpoint.pobierzListeAutorow(); 
    }

    /**
     * dodaje autora
     * @param autor nowy autor
     */
    void dodajAutora(Autor autor, List<String> wybraneKsiazki) {
         MOAEndpoint.dodajAutora(autor, wybraneKsiazki);
    }
    
    /**
     * edytuje autora
     * @throws AutorException wyjatek w przypadku niepowodzenia
     */
    void zapiszAutoraDoEdycji() throws AutorException {
        MOAEndpoint.edytujAutora(edytowanyAutor);
    }

    /**
     * pobiera autora do edycji i zapisuje w polu edytowanyAutor
     * @param id id autora do edycji
     */
    void pobierzAutoraDoEdycji(long id) {
        edytowanyAutor = MOAEndpoint.pobierzAutoraDoEdycji(id);
    }

     /**
     * Pobiera ksiazki 
     * @return ksiazki nieocenione
     */
    List<Ksiazka> pobierzKsiazki() {
        return MOAEndpoint.pobierzKsiazkiNieocenione();
    }
    
}
