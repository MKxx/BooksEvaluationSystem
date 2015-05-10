/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.exceptions.UzytkownikException;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Named(value = "rejestracjaPageBean")
@RequestScoped
public class RejestracjaPageBean {

    private String powtorzHaslo;

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
        if (!powtorzHaslo.equals(uzytkownik.getHasloMd5())) {
            FacesContext fctx = FacesContext.getCurrentInstance();
            FacesMessage fmsg = new FacesMessage("Hasła się nie zgadzają");
            fctx.addMessage(null, fmsg);
            return null;
        }
        try {
            uzytkownikSession.rejestrujUzytkownika(uzytkownik);
        } catch (UzytkownikException ex) {
            Logger.getLogger(RejestracjaPageBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return "sukces";
    }
}
