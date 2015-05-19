package pl.lodz.ssbd.moo.moo.beans;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.moo.moo.endpoints.MOOEndpointLocal;

/**
 *
 * @author Kuba
 */
@SessionScoped
public class UlubioneSession implements Serializable {

    /**
     * Creates a new instance of UzytkownikSession
     */
    public UlubioneSession() {
    }

    @EJB
    private MOOEndpointLocal MOOEndpoint;

    @RolesAllowed("WyswietlanieListyUlubionych")
    public List<Ksiazka> pobierzUlubione(String login) {
        return MOOEndpoint.pobierzUlubione(login);
    }

}
