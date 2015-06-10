/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moa.beans;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import pl.lodz.ssbd.entities.Autor;
import pl.lodz.ssbd.entities.Ksiazka;

/**
 *
 * @author Marta
 */
@Named(value = "dodajAutoraPageBean")
//@RequestScoped
@ViewScoped
//@SessionScoped
public class DodanieAutoraPageBean implements Serializable{

    @Inject
    AutorSession autorSession;
    private final Map<String, String> ksiazki = new LinkedHashMap<String, String>();
    List<Ksiazka> ksiazkiTemp;

    public Map<String, String> getKsiazki() {
        return ksiazki;
    }
    private List<String> wybraneKsiazki;

    public List<String> getWybraneKsiazki() {
        return wybraneKsiazki;
    }

    public void setWybraneKsiazki(List<String> wybraneKsiazki) {
        this.wybraneKsiazki = wybraneKsiazki;
    }
    
    private Autor autor = new Autor();

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    /**
     * Creates a new instance of DodajAutoraPageBean
     */
    public DodanieAutoraPageBean() {
    }
    
    @PostConstruct
    private void initAutorzy(){
        ksiazkiTemp= autorSession.pobierzKsiazki();
        for(Ksiazka k : ksiazkiTemp){
            ksiazki.put(k.getTytul(), k.getIdKsiazka().toString());
        }
    }
    
    public void stworzAutor(){
        autorSession.dodajAutora(autor, wybraneKsiazki);    
    }
}
