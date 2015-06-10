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
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.exceptions.KsiazkaException;

/**
 *
 * @author Maciej
 */
@Named(value = "listaKsiazekPageBean")
@RequestScoped
/**
 * Page Bean dla listy ksiazek moderatorskich
 */
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
    
    /**
     * Inicjuje liste ksiazek
     */
    @PostConstruct
    @RolesAllowed("PrzegladanieKsiazekModeratorski")
    private void initModel(){
        ksiazki = ksiazkaSession.pobierzKsiazki();
        ksiazkiDataModel = new ListDataModel<>(ksiazki);
    }
    
    /**
     * oznacz ksiazke jako nieaktywna
     * @return String z wartoscia przekierowania
     */
    public String oznaczJakoNieaktywna(){
        try {
        ksiazkaSession.oznaczJakoNieaktywna(ksiazkiDataModel.getRowData());
        initModel();
        return null;
        }
        catch (KsiazkaException k)
        {
            return "bladksiazka";
        }
    }
    
    /**
     * Edytuj ksiazke
     * @return String z wartoscia przekierowania
     */
    public String edytuj(){
        ksiazkaSession.pobierzKsiazkeDoEdycji(ksiazkiDataModel.getRowData().getIdKsiazka());
        return "edytujksiazke";
    }
    
}
