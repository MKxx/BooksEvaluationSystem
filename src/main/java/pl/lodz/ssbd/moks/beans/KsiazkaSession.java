package pl.lodz.ssbd.moks.beans;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.exceptions.KsiazkaException;
import pl.lodz.ssbd.moks.endpoints.MOKSEndpointLocal;

/**
 *
 * @author Kuba
 */
@SessionScoped
public class KsiazkaSession implements Serializable {

    /**
     * Creates a new instance of UzytkownikSession
     */
    public KsiazkaSession() {
    }

    @EJB
    private MOKSEndpointLocal MOOEndpoint;

    @RolesAllowed("WyswietlanieListyUlubionych")
    public List<Ksiazka> pobierzUlubione(String login) {
        try {
        return MOOEndpoint.pobierzKsiazkiUlubione(login);
        }
        catch (KsiazkaException a){
            return null;
        }
    }

}
