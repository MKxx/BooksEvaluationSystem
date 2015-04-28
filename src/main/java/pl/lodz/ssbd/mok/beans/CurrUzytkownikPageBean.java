/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.ssbd.entities.Uzytkownik;


/**
 *
 * @author Kuba
 */
@Named(value = "curruzytkownikPageBean")
@RequestScoped
public class CurrUzytkownikPageBean {
    @Inject
    UzytkownikSession uzytkownikSession;
    
    
   // Uzytkownik uzyt= uzytkownikSession.getUzytkownikMenu();
     public CurrUzytkownikPageBean() {
    }
    
    public String getIsAdmin(){
        String login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
                uzytkownikSession.pobierzUzytkownikMenu(login);
                 Uzytkownik user=uzytkownikSession.getUzytkownikMenu();
                 return user.getIpPopZal();
    }
    
}
