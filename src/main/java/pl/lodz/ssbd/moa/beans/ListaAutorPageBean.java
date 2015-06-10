/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moa.beans;

import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import pl.lodz.ssbd.entities.Autor;

/**
 *
 * @author Marta
 */
@Named(value="listaAutorowPageBean")
@ViewScoped

/**
 * PageBean dla listy autorow
 */
public class ListaAutorPageBean implements Serializable{
    
    @Inject
    AutorSession autorSession;
    
    List<Autor> autor;
    DataModel<Autor> autorDataModel;
    public DataModel<Autor> getAutorDataModel() {
        return autorDataModel;
    }
    public void setAutorDataModel(DataModel<Autor> autorDataModel) {
        this.autorDataModel = autorDataModel;
    }
    
    /**
     * Inicjuje liste autorow
     */
    @PostConstruct
    @RolesAllowed("PrzegladanieAutorow")
    private void initModel(){
        autor = autorSession.pobierzListeAutorow();
        autorDataModel = new ListDataModel<>(autor);
    }
    
     /**
     * Edytuj autora
     * @return String z wartoscia przekierowania
     */
    public String edytuj(){
        autorSession.pobierzAutoraDoEdycji(autorDataModel.getRowData().getIdAutor());
        return "edytujautora";
    }
    
     /**
     * Informacja o autorze
     * @return String z wartoscia przekierowania
     */
    public String info(){
        autorSession.pobierzAutoraDoEdycji(autorDataModel.getRowData().getIdAutor());
        return "informacjeoautorze";
    }
}