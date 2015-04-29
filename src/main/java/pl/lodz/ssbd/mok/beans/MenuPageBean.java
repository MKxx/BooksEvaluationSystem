/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.beans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import pl.lodz.ssbd.entities.PoziomDostepu;

/**
 *
 * @author Maciej
 */
@Named(value = "menuPageBean")
@RequestScoped
public class MenuPageBean {

    @Inject
    UzytkownikSession uzytkownikSession;
    
    /**
     * Creates a new instance of MenuPageBean
     */
    public MenuPageBean() {
    }
    
    public boolean getIsAdmin(){
        return isRole("ADMINISTRATOR");
    }
    
    public boolean getIsUzytkownik(){
        return isRole("UZYTKOWNIK");
    }
    
    public boolean getIsModerator(){
        return isRole("MODERATOR");
    }
    
    
    private boolean isRole(String role){
        String login;
        login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        if(login == null) return false;
        if(uzytkownikSession.getUzytkownikMenu() == null){
            uzytkownikSession.pobierzUzytkownikMenu(login);
        }
        int index = 0;
        for(PoziomDostepu pd : uzytkownikSession.getUzytkownikMenu().getPoziomDostepuList()){
            if(pd.getNazwa().equals(role)){
                index = uzytkownikSession.getUzytkownikMenu().getPoziomDostepuList().indexOf(pd);
            }
        }

        return uzytkownikSession.getUzytkownikMenu().getPoziomDostepuList().get(index).getAktywny();
    }
}
