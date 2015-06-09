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
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import pl.lodz.ssbd.entities.Ksiazka;


/**
 *
 * @author Kuba
 */
@Named(value = "listaulubionychPageBean")
@ViewScoped
/**
 * Page Bean dla listy ulubionych ksiazek
 */
public class ListaUlubionychPageBean  implements Serializable{

    @Inject
    private KsiazkaSession ksiazkaSession;
    private List<Ksiazka> ksiazki;
    private DataModel<Ksiazka> ksiazkiDataModel;

    public DataModel<Ksiazka> getUlubioneDataModel() {
        return ksiazkiDataModel;
    }

    // Metoda wczytuje liste wszystkich kont
    // Dzieki adnotacji @PostConstruct jest wykonywana automatycznie po zaladowaniu strony,
    // ponadto odwoluja sie do niej inne metody tego ziarna
    
    public ListaUlubionychPageBean () {
        
    }
    
    /**
     * Inicjuje liste ksiazek
     */
    @PostConstruct
    @RolesAllowed("WyswietlanieListyUlubionych")
    public void initModel() {
        String login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        ksiazki = ksiazkaSession.pobierzUlubione(login);
        ksiazkiDataModel = new ListDataModel<Ksiazka>(ksiazki);
    }
}
