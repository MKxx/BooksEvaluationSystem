package pl.lodz.ssbd.moo.moo.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.entities.Ocena;
import pl.lodz.ssbd.exceptions.KsiazkaException;
import pl.lodz.ssbd.exceptions.OcenaException;
import pl.lodz.ssbd.exceptions.UzytkownikException;
import pl.lodz.ssbd.moo.moo.endpoints.MOOEndpointLocal;
import pl.lodz.ssbd.moo.moo2.endpoints.MOO2EndpointLocal;

/**
 * Kontroler do obsługi zdarzeń
 * @author Maciej
 */
@Named
@SessionScoped
public class OcenaSession implements Serializable {

    @EJB
    MOOEndpointLocal MOOEndpoint;

    @EJB
    MOO2EndpointLocal MOO2Endpoint;
    
    /**
     * Metoda pobierająca wszystkie książki
     * @return lista książek
     */
    List<Ksiazka> pobierzKsiazki() {
        return MOOEndpoint.pobierzKsiazki();
    }

    /**
     * Metoda pobierająca wszystkie oceny
     * @return lista ocen
     */
    List<Ocena> pobierzOceny() {
        return MOOEndpoint.pobierzOceny();
    }
    
    /**
     * Metoda dodająca książkę dla której przekazywana jest ocena do ulubionych
     * @param ocena ocena dla książki, którą dodajemy do ulubionych
     * @throws OcenaException przy jednoczesnej modyfikacji
     */
    @RolesAllowed("DodanieDoUlubionych")
    public void dodajDoUlub(Ocena ocena) throws OcenaException{
        MOOEndpoint.dodajDoUlubionych(ocena);
    }
    
    /**
     * Metoda zmieniająca ocenę z widoku
     * @param id_ksiazki id książki dla której użytkownik chce zmienić ocenę
     * @param wartosc wartość nowej oceny
     * @param login login użytkownika, który wykonuje akcje
     * @throws OcenaException wyjątek przy jednoczesnej modyfikacji, bądź w przypadku braku oceny.
     * @throws KsiazkaException wyjątek w przypadku modyfikacji książki
     * @throws UzytkownikException wyjątek rzucany w przypaku braku użytkownika
     */
    public void zmienOcene(long id_ksiazki, int wartosc, String login) throws OcenaException, KsiazkaException, UzytkownikException {
        MOO2Endpoint.zmienOcene(id_ksiazki, wartosc, login);
    }

    /**
     * Metoda dodająca ocenę
     * @param id_ksiazka id książki wskazanej przez użytkownika
     * @param wartosc wartość oceny jaką wskazał użytkownia (1-5)
     * @param login login użytkownika który ocenia książkę.
     * @throws UzytkownikException jeśli użytkownik nie istnieje
     * @throws OcenaException jeśli ocena JUŻ istnieje
     * @throws KsiazkaException  jeśli książka została zmodyfikowana podczas oceny (OptimistickLock).
     */
    public void ocen(long id_ksiazka, int wartosc, String login) throws UzytkownikException, OcenaException, KsiazkaException {
        MOO2Endpoint.ocenKsiazke(id_ksiazka, wartosc, login);
    }
}
