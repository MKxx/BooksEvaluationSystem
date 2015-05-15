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
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import pl.lodz.ssbd.entities.Ksiazka;

/**
 *
 * @author Maciej
 */
@Named(value = "listaKsiazekPageBean")
@ViewScoped
public class ListaKsiazekPageBean implements Serializable {

    /**
     * Creates a new instance of listaKsiazekPageBean
     */
    
    @Inject
    KsiazkaSession ksiazkaSession;
    
    List<Ksiazka> ksiazki;
    DataModel<Ksiazka> ksiazkiDataModel;

    public DataModel<Ksiazka> getKsiazkiDataModel() {
        return ksiazkiDataModel;
    }

    public void setKsiazkiDataModel(DataModel<Ksiazka> ksiazkiDataModel) {
        this.ksiazkiDataModel = ksiazkiDataModel;
    }
    
    public ListaKsiazekPageBean() {
        
    }
    
    @PostConstruct
    @RolesAllowed("PrzegladanieKsiazekModeratorski")
    private void initModel(){
        ksiazki = ksiazkaSession.pobierzKsiazki();
        ksiazkiDataModel = new ListDataModel<>(ksiazki);
    }
    
    public void oznaczJakoNieaktywna(){
        throw new UnsupportedOperationException();
    }
    
    public void edytuj(){
        throw new UnsupportedOperationException();
    }
    
}
