/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.beans;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import pl.lodz.ssbd.utils.SprawdzaczRoli;

/**
 * Klasa obslugujaca widok menu
 * @author Maciej
 */
@Named(value = "menuPageBean")
@RequestScoped
public class MenuPageBean {
    
    ResourceBundle rbl;
    
    /**
     * Creates a new instance of MenuPageBean
     */
    public MenuPageBean() {
        rbl = ResourceBundle.getBundle("nazwy_rol.role");
    }
    /**
     * sprawdzenie czy uzytkownik jest gosciem
     * @return prawda lub falsz
     */
    public boolean getIsGosc(){
        if(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser() == null){
            return true;
        }
        return false;
    }
    
    /**
     * sprawdzenie czy uzytkownik jest administratorem
     * @return prawda lub falsz
     */
    public boolean getIsAdmin(){
        return SprawdzaczRoli.sprawdzRole(rbl.getString("rola.admin"));
    }
    
    /**
     * sprawdzenie czy uzytkownik jest uzytkownikiem
     * @return prawda lub falsz
     */
    public boolean getIsUzytkownik(){
        return SprawdzaczRoli.sprawdzRole(rbl.getString("rola.user"));
    }
    
    /**
     * sprawdzenie czy uzytkownik jest moderatorem
     * @return prawda lub falsz
     */
    public boolean getIsModerator(){
        return SprawdzaczRoli.sprawdzRole(rbl.getString("rola.moderator"));
    }
    
    /**
     * wylogowanie sie
     * @return String przekierowujacy do strony
     */
    public String wyloguj(){
        try {
            getRequest().logout();
        } catch (ServletException ex) {
            Logger.getLogger(MenuPageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        getRequest().getSession().invalidate();
        return "wylogowano";
    }
    /**
     * metoda zwracajaca Request
     * @return http servlet request
     */
     private javax.servlet.http.HttpServletRequest getRequest(){
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
     }
}
