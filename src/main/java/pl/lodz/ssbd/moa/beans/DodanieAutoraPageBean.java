/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moa.beans;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import pl.lodz.ssbd.entities.Autor;

/**
 *
 * @author Marta
 */
@Named(value = "dodajAutoraPageBean")
//@RequestScoped
//@ViewScoped
@SessionScoped
public class DodanieAutoraPageBean implements Serializable{

    @Inject
    AutorSession autorSession;
    
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
    
    public void stworzAutor(){
        autorSession.dodajAutora(autor);    
    }
}
