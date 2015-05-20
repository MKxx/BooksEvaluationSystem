/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo.beans;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.entities.Ocena;
import pl.lodz.ssbd.exceptions.OcenaException;
import pl.lodz.ssbd.mok.endpoints.MOKEndpoint;
import pl.lodz.ssbd.utils.SprawdzaczRoli;

/**
 *
 * @author Maciej
 */
@Named(value = "listaKsiazekPageBeanMOO")
@ViewScoped
public class ListaKsiazekPageBean implements Serializable {


    /**
     * Creates a new instance of listaKsiazekPageBean
     */
    
    @Inject
    OcenaSession ocenaSession;
    
    List<Ksiazka> ksiazki;
    List<Ocena> ocenyList;
    DataModel<Ksiazka> ksiazkiDataModel;
    ResourceBundle rbl;
    
    private int ocena;
    private static Map<String, Integer> oceny;
    
    static{
        oceny = new LinkedHashMap<String, Integer>();
        for(int i=1;i<=5;i++){
            oceny.put(String.valueOf(i), i);
        }
    }
    
    public Map<String, Integer> getOceny() {
        return oceny;
    }

    public void setOceny(Map<String, Integer> oceny) {
        ListaKsiazekPageBean.oceny = oceny;
    }
    
    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
    
    public int getOcena() {
        return ocena;
    }

    public DataModel<Ksiazka> getKsiazkiDataModel() {
        return ksiazkiDataModel;
    }

    public void setKsiazkiDataModel(DataModel<Ksiazka> ksiazkiDataModel) {
        this.ksiazkiDataModel = ksiazkiDataModel;
    }
    public ListaKsiazekPageBean() {
         rbl = ResourceBundle.getBundle("nazwy_rol.role");
    }
    
    @PostConstruct
    @PermitAll
    private void initModel(){
        ksiazki = ocenaSession.pobierzKsiazki();
        ocenyList = ocenaSession.pobierzOceny();
        ksiazkiDataModel = new ListDataModel<>(ksiazki);
    }
    
    public boolean sprawdzCzyOceniona(long idKsiazki){
        String login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        for(Ocena ocena : ocenyList){
            if(ocena.getIdKsiazka().getIdKsiazka()==idKsiazki&&ocena.getIdUzytkownik().getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }
    
    public boolean isUzytkownik(){
        return SprawdzaczRoli.sprawdzRole(rbl.getString("rola.user"));
    }
    
    public void ocen(){
        throw new UnsupportedOperationException();
    }
    
    @RolesAllowed("DodanieDoUlubionych")
    public String dodajDoUlub(long idKsiazki) {
        if (sprawdzCzyOceniona(ksiazkiDataModel.getRowData().getIdKsiazka()) == true) {
            try {
                String login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
               for(Ocena ocena : ocenyList){
            if(ocena.getIdKsiazka().getIdKsiazka()==idKsiazki&&ocena.getIdUzytkownik().getLogin().equals(login)){
                ocenaSession.dodajDoUlub(ocena);
                return null;
            }

                }

            } catch (OcenaException o) {
                return null;
            }
        }
        return null;

    }
    public void zmienOcene(){
        throw new UnsupportedOperationException();
    }
    
}
