/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moks.beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.exceptions.KsiazkaException;

/**
 *
 * @author Maciej
 */
@Named(value = "edycjaPageBean")
@RequestScoped
public class edycjaPageBean {

    /**
     * Creates a new instance of edycjaPageBean
     */
    
    @Inject 
    private KsiazkaSession ksiazkaSession;
    
    public Ksiazka getKsiazkaEdycja(){
        return ksiazkaSession.getEdytowanaKsiazka();
    }
    
    public String edytujKsiazke(){
        try {
            ksiazkaSession.zapiszKsiazkePoEdycji();
        } catch (KsiazkaException ex) {
            Logger.getLogger(edycjaPageBean.class.getName()).log(Level.SEVERE, null, ex);
            return "nieaktualnedane";
        }
        return "listaksiazek";   
    }
    
}
