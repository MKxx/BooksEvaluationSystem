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
 *
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
    
    public boolean getIsGosc(){
        if(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser() == null){
            return true;
        }
        return false;
    }
    
    public boolean getIsAdmin(){
        return SprawdzaczRoli.sprawdzRole(rbl.getString("rola.admin"));
    }
    
    public boolean getIsUzytkownik(){
        return SprawdzaczRoli.sprawdzRole(rbl.getString("rola.user"));
    }
    
    public boolean getIsModerator(){
        return SprawdzaczRoli.sprawdzRole(rbl.getString("rola.moderator"));
    }
    
    public String wyloguj(){
        try {
            getRequest().logout();
        } catch (ServletException ex) {
            Logger.getLogger(MenuPageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        getRequest().getSession().invalidate();
        return "wylogowano";
    }
    
     private javax.servlet.http.HttpServletRequest getRequest(){
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
     }
}
