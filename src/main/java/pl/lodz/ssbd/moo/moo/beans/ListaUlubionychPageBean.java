/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.entities.Ocena;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.mok.beans.UzytkownikSession;
import pl.lodz.ssbd.utils.UzytkownikComparator;

/**
 *
 * @author Kuba
 */
@Named(value = "listaulubionychPageBean")
@RequestScoped
public class ListaUlubionychPageBean implements Serializable {

    @Inject
    private KsiazkaSession ocenaSession;

    // DataModel jest reprezentacja listy obiektow potrzebna do pelnego dzialania
    // tabeli (DataTable)
    private List<Ksiazka> ksiazki;
    private DataModel<Ksiazka> ulubioneDataModel;

    public DataModel<Ksiazka> getUlubioneDataModel() {
        return ulubioneDataModel;
    }

    // Metoda wczytuje liste wszystkich kont
    // Dzieki adnotacji @PostConstruct jest wykonywana automatycznie po zaladowaniu strony,
    // ponadto odwoluja sie do niej inne metody tego ziarna
    @PostConstruct
    @RolesAllowed("WyswietlanieListyUlubionych")
    private void initModel() {
        String login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        ksiazki = ocenaSession.pobierzUlubione(login);
        ulubioneDataModel = new ListDataModel<Ksiazka>(ksiazki);
    }
}
