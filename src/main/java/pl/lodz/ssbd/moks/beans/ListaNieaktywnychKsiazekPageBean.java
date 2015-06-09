/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moks.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import pl.lodz.ssbd.entities.Ksiazka;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Named(value = "listaNieaktywnychKsiazekPageBean")
@ViewScoped
/**
 * Page Bean dla listy nieaktywnych ksiazek
 */
public class ListaNieaktywnychKsiazekPageBean implements Serializable {

    /**
     * Creates a new instance of listaKsiazekPageBean
     */
    
    @Inject
    private KsiazkaSession ksiazkaSession;
    
    private List<Ksiazka> ksiazki;

    public List<Ksiazka> getKsiazki() {
        return ksiazki;
    }

    public void setKsiazki(List<Ksiazka> ksiazki) {
        this.ksiazki = ksiazki;
    }

    public ListaNieaktywnychKsiazekPageBean() {
        
    }
    
    /**
     * inicjuje liste ksiazek
     */
    @PostConstruct
    public void initModel(){
        System.out.println(ksiazkaSession.pobierzNieaktywneKsiazki().size());
        ksiazki = ksiazkaSession.pobierzNieaktywneKsiazki();
        System.out.println(ksiazki.size());
    }
    
}
