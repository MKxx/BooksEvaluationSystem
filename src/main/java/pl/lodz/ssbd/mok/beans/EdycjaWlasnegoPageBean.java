/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.utils.MD5;

/**
 *
 * @author Marta Chmielecka
 */
@Named(value = "edycjaWlasnegoPageBean")
@RequestScoped
public class EdycjaWlasnegoPageBean {

    @Inject
    private UzytkownikSession uzytkownikSession;
    private String noweHaslo;
    private String powtorzHaslo;
    
    
    public EdycjaWlasnegoPageBean(){
    }
    
    @PostConstruct
    private void initPage(){
        uzytkownikSession.pobierzSiebieDoEdycji();
    }

    public String getNoweHaslo() {
        return noweHaslo;
    }

    public void setNoweHaslo(String noweHaslo) {
        this.noweHaslo = noweHaslo;
    }

    public String getPowtorzHaslo() {
        return powtorzHaslo;
    }

    public void setPowtorzHaslo(String powtorzHaslo) {
        this.powtorzHaslo = powtorzHaslo;
    }

     public void setUzytkownikSession(UzytkownikSession uzytkownikSession) {
        this.uzytkownikSession = uzytkownikSession;
    }
    
    public Uzytkownik getUzytkownikEdycja() {
        return uzytkownikSession.getUzytkownikEdycja();
    }

    public String edytujSiebie() {
        if(!noweHaslo.equals(powtorzHaslo)){
            FacesContext fctx = FacesContext.getCurrentInstance();
            FacesMessage fmsg = new FacesMessage("Hasła się nie zgadzają");
            fctx.addMessage(null, fmsg);
            return null;
        }
        if(!(noweHaslo.equals("") || noweHaslo == null)){
            uzytkownikSession.getUzytkownikEdycja().setHasloMd5(MD5.hash(noweHaslo));
        }
        uzytkownikSession.zapiszUzytkownikaPoEdycji();
        return "index";
    }
}
