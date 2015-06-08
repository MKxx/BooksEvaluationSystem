package pl.lodz.ssbd.utils;

import javax.faces.context.FacesContext;

/**
 *
 * @author Maciej
 */
public class SprawdzaczRoli {
     /**
       * Sprawdza czy do danego uzytkownika nalezy dana rola%
       * 
       * @param rola
       *                Nazwa roli.
       */
    public static  boolean sprawdzRole(String rola){
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(rola);
    }
    /**
       * @return true jeśli dana rola nalezy do uzytkownika, w przeciwnym razie false.
       */
}
