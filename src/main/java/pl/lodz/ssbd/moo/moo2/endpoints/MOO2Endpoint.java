/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo2.endpoints;

import java.math.BigDecimal;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import pl.lodz.ssbd.entities.Autor;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.entities.Ocena;
import pl.lodz.ssbd.exceptions.KsiazkaException;
import pl.lodz.ssbd.exceptions.OcenaException;
import pl.lodz.ssbd.exceptions.UzytkownikException;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;
import pl.lodz.ssbd.moo.moo2.facades.AutorFacadeLocal;
import pl.lodz.ssbd.moo.moo2.facades.KsiazkaFacadeLocal;
import pl.lodz.ssbd.moo.moo2.facades.OcenaFacadeLocal;
import pl.lodz.ssbd.moo.moo2.facades.UzytkownikFacadeLocal;

/**
 *
 * @author Maciej
 */
@Stateless
@Interceptors({DziennikZdarzenInterceptor.class})
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MOO2Endpoint implements MOO2EndpointLocal {

    @EJB(beanName = "moo2Ksiazka")
    private KsiazkaFacadeLocal ksiazkaFacade;
    @EJB(beanName = "moo2Ocena")
    private OcenaFacadeLocal ocenaFacade;
    @EJB(beanName = "moo2Autor")
    private AutorFacadeLocal autorFacade;
    @EJB(beanName = "moo2Uzytkownik")
    private UzytkownikFacadeLocal uzytkownikFacade;

    @RolesAllowed("DodanieOceny")
    public void ocenKsiazke(long id_ksiazka, int wartosc, String login) throws UzytkownikException, OcenaException{
        Ocena ocena = new Ocena();
        ocena.setIdKsiazka(ksiazkaFacade.find(id_ksiazka));
        ocena.setIdUzytkownik(uzytkownikFacade.findByLogin(login));
        ocena.setOcena(wartosc);
        ocena.setUlubiona(false);
        ocenaFacade.create(ocena);

        long suma = 0;

        for (Ocena o : ocena.getIdKsiazka().getOcenaList()) {
            suma = suma + o.getOcena();
        }
        ocena.getIdKsiazka().setSredniaOcen(new BigDecimal(suma / ocena.getIdKsiazka().getOcenaList().size()));
        for (Autor a : ocena.getIdKsiazka().getAutorList()) {
            BigDecimal licznik = BigDecimal.ZERO;
            double mianownik = 0;
            for (Ksiazka k : a.getKsiazkaList()) {
                mianownik = mianownik + 1 / (k.getIloscAutorow());
                licznik.add(k.getSredniaOcen().multiply(BigDecimal.valueOf(k.getIloscAutorow())));
            }
            a.setSrOcena(licznik.divide(BigDecimal.valueOf(mianownik)));
        }
    }

    @Override
    @RolesAllowed("ZmianaOceny")
    public void zmienOcene(long id_ksiazka, int ocena, String login) throws OcenaException, KsiazkaException, UzytkownikException {
        Ocena newOcena = ocenaFacade.findByKsiazkaAndLogin(ksiazkaFacade.find(id_ksiazka), uzytkownikFacade.findByLogin(login));
        if (newOcena == null) {
            throw new OcenaException("exceptions.ocenanieistnieje");
        }
        newOcena.setOcena(ocena);
        ocenaFacade.edit(newOcena);
        long suma = 0;
        for (Ocena o : newOcena.getIdKsiazka().getOcenaList()) {
            suma = suma + o.getOcena();
        }
        newOcena.getIdKsiazka().setSredniaOcen(new BigDecimal(suma / newOcena.getIdKsiazka().getOcenaList().size()));
        ksiazkaFacade.edit(newOcena.getIdKsiazka());
        for (Autor a : newOcena.getIdKsiazka().getAutorList()) {
            BigDecimal licznik = BigDecimal.ZERO;
            double mianownik = 0;
            for (Ksiazka k : a.getKsiazkaList()) {
                mianownik = mianownik + 1 / (k.getIloscAutorow());
                licznik.add(k.getSredniaOcen().multiply(BigDecimal.valueOf(k.getIloscAutorow())));
            }
            a.setSrOcena(licznik.divide(BigDecimal.valueOf(mianownik)));
        }
    }
}
