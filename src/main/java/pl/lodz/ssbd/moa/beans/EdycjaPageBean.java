/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moa.beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.ssbd.entities.Autor;
import pl.lodz.ssbd.exceptions.AutorException;

/**
 *
 * @author Marta
 */
@Named(value = "AutorEdycjaPageBean")
@RequestScoped
public class EdycjaPageBean {
        
    @Inject 
    private AutorSession autorSession;
    
    public Autor getAutorEdycja(){
        return autorSession.getEdytowanyAutor();
    }
    
    public String edytujAutora(){
        try {
            autorSession.zapiszAutoraDoEdycji();
        } catch (AutorException ex) {
            Logger.getLogger(EdycjaPageBean.class.getName()).log(Level.SEVERE, null, ex);
            return "nieaktualnedane";
        }
        return "listaautorow";   
    }
}