/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.beans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.ssbd.entities.Uzytkownik;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Named(value = "rejestracjaPageBean")
@RequestScoped
public class RejestracjaPageBean {

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
        System.out.println(uzytkownik);
        uzytkownikSession.rejestrujUzytkownika(uzytkownik);
        return "success";
    }
}
