/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.beans;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.exceptions.UzytkownikException;
import pl.lodz.ssbd.utils.Bundle;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Named(value = "rejestracjaPageBean")
@RequestScoped
public class RejestracjaPageBean {

    private String powtorzHaslo;
    private String haslo;

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public UzytkownikSession getUzytkownikSession() {
        return uzytkownikSession;
    }

    public void setUzytkownikSession(UzytkownikSession uzytkownikSession) {
        this.uzytkownikSession = uzytkownikSession;
    }

    public void setPowtorzHaslo(String powtorzHaslo) {
        this.powtorzHaslo = powtorzHaslo;
    }

    public String getPowtorzHaslo() {
        return powtorzHaslo;
    }

    /**
     * Creates a new instance of RejestracjaPageBean
     */
    public RejestracjaPageBean() {
    }

    @Inject
    private UzytkownikSession uzytkownikSession;

    private final Uzytkownik uzytkownik = new Uzytkownik();

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public String rejestrujUzytkownika() {
        if (!powtorzHaslo.equals(haslo)) {
            FacesContext fctx = FacesContext.getCurrentInstance();
            FacesMessage fmsg = new FacesMessage("Hasła się nie zgadzają");
            fctx.addMessage(null, fmsg);
            return null;
        }
        try {
            uzytkownikSession.rejestrujUzytkownika(uzytkownik, haslo);
            return "sukces";
        } catch (UzytkownikException ex) {
            FacesContext fctx = FacesContext.getCurrentInstance();
            Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            FacesMessage fmsg = new FacesMessage(Bundle.internalizuj(ex.getMessage(), locale));
            fctx.addMessage(null, fmsg);
            return null;
        }
    }
}
