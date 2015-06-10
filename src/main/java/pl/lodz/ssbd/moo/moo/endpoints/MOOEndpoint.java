/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo.endpoints;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.entities.Ocena;
import pl.lodz.ssbd.exceptions.OcenaException;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;
import pl.lodz.ssbd.moo.moo.facades.KsiazkaFacadeLocal;
import pl.lodz.ssbd.moo.moo.facades.OcenaFacadeLocal;

/**
 *
 * @author Maciej
 */
@Stateful
@Interceptors({DziennikZdarzenInterceptor.class})
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MOOEndpoint implements MOOEndpointLocal {
    
    @EJB(beanName = "mooKsiazka")
    private KsiazkaFacadeLocal KsiazkaFacade;
    @EJB(beanName = "mooOcena")
    private OcenaFacadeLocal ocenaFacade;
    
    private List<Ksiazka> listaKsiazekDostep;

     /**
     * Metoda zwracająca listę aktywnych książek
     * @return lista aktywnych książek
     */
    @Override
    @PermitAll
    public List<Ksiazka> pobierzKsiazki() {
        listaKsiazekDostep = KsiazkaFacade.findAktywne();
        return listaKsiazekDostep;
    }

    /**
     * Metoda dodająca książkę do ulubionych 
     * Należy zauważyć, że u nas jest to rozwiązane w dośc oryginaly sposób
     * @param ocena która zawiera ksiązkę, którą użytkownik chce dodać do ulubionych.
     * @throws OcenaException 
     */
    @Override
    @RolesAllowed("DodanieDoUlubionych")
    public void dodajDoUlubionych(Ocena ocena) throws OcenaException{
      ocena.setUlubiona(true);
      ocenaFacade.edit(ocena);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    /**
     * Metoda pobierająca wszystkie oceny
     * @return lista wszystkich ocen
     */
    @Override
    public List<Ocena> pobierzOceny() {
        return ocenaFacade.findOcenyInitalized();
    }
}
