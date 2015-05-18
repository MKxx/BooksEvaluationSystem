/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moks.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import pl.lodz.ssbd.entities.Autor;
import pl.lodz.ssbd.entities.Ksiazka;

/**
 *
 * @author Maciej
 */
@Named(value = "dodaniePageBean")
@ViewScoped
public class DodaniePageBean implements Serializable {

    @Inject
    KsiazkaSession ksiazkaSession;
    
    private final Ksiazka ksiazka = new Ksiazka();
    private final Map<String, String> autorzy = new LinkedHashMap<String, String>();
    List<Autor> autorzyTemp;

    public Map<String, String> getAutorzy() {
        return autorzy;
    }
    private List<String> wybraniAutorzy;

    public List<String> getWybraniAutorzy() {
        return wybraniAutorzy;
    }

    public void setWybraniAutorzy(List<String> wybraniAutorzy) {
        this.wybraniAutorzy = wybraniAutorzy;
    }
    
    /**
     * Creates a new instance of DodaniePageBean
     */
    public DodaniePageBean() {
        
    }
    
    @PostConstruct
    private void initAutorzy(){
        autorzyTemp= ksiazkaSession.pobierzAutorow();
        for(Autor a : autorzyTemp){
            autorzy.put((a.getImie() + " " + a.getNazwisko()), a.getIdAutor().toString());
        }
    }

    public Ksiazka getKsiazka() {
        return ksiazka;
    }
    
    public void stworzKsiazke(){
        ksiazkaSession.stworzKsiazke(ksiazka, wybraniAutorzy);
    }
    
}
