package pl.lodz.ssbd.mok.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.utils.MD5;

/**
 *
 * @author Maciej
 */
@Named(value = "edycjaCudzegoPageBean")
@RequestScoped
public class EdycjaCudzegoPageBean {
    
    @Inject
    private UzytkownikSession uzytkownikSession;
    private String noweHaslo;
    private String powtorzHaslo;

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

    public String edytujUzytkownika() {
        boolean zmianaHasla = false;
        if(!noweHaslo.equals(powtorzHaslo)){
            FacesContext fctx = FacesContext.getCurrentInstance();
            FacesMessage fmsg = new FacesMessage("Hasła się nie zgadzają");
            fctx.addMessage(null, fmsg);
            return null;
        }
        if(!(noweHaslo.equals("") || noweHaslo == null)){
            uzytkownikSession.getUzytkownikEdycja().setHasloMd5(MD5.hash(noweHaslo));
            zmianaHasla = true;
        }
        uzytkownikSession.zapiszUzytkownikaPoEdycji(zmianaHasla);
        return "panelAdm";
    }
}
