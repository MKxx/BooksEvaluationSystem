/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.beans;

import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.exceptions.PoprzednieHasloException;
import pl.lodz.ssbd.exceptions.UzytkownikException;
import pl.lodz.ssbd.utils.Bundle;

/**
 * Klasa obslugujaca widok edycji wlasnego konta
 * @author Marta Chmielecka
 */
@Named(value = "edycjaWlasnegoPageBean")
@ViewScoped
public class EdycjaWlasnegoPageBean implements Serializable {

    @Inject
    private UzytkownikSession uzytkownikSession;
    private String noweHaslo;
    private String powtorzHaslo;

    public EdycjaWlasnegoPageBean() {
    }

    /**
     * Pobranie siebie do edycji
     */
    @PostConstruct
    private void initPage() {
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
    /**
     * Edycja swojego konta
     * @return String przekierowujacy na strone po edycji
     */
    public String edytujSiebie() {
        boolean zmianaHasla = false;
        if (!noweHaslo.equals(powtorzHaslo)) {
            FacesContext fctx = FacesContext.getCurrentInstance();
            FacesMessage fmsg = new FacesMessage("Hasła się nie zgadzają");
            fctx.addMessage(null, fmsg);
            return null;
        }
        if (!(noweHaslo.equals("") || noweHaslo == null)) {
            zmianaHasla = true;
        }
        try {
            uzytkownikSession.zapiszUzytkownikaPoEdycji(zmianaHasla, noweHaslo);
        } catch (UzytkownikException ex) {
            return "nieaktualnedane";
        } catch (PoprzednieHasloException ex) {
            FacesContext fctx = FacesContext.getCurrentInstance();
            Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            FacesMessage fmsg = new FacesMessage(Bundle.internalizuj(ex.getMessage(), locale));
            fctx.addMessage(null, fmsg);
            return null;
        }
        return "sukcesEdycja";
    }
}
