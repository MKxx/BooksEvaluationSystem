package pl.lodz.ssbd.utils;

import javax.faces.context.FacesContext;

/**
 *
 * @author Maciej
 */
public class SprawdzaczRoli {
    public static  boolean sprawdzRole(String rola){
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(rola);
    }
}
