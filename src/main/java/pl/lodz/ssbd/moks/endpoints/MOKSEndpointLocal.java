/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moks.endpoints;

import java.util.List;
import javax.ejb.Local;
import pl.lodz.ssbd.entities.Autor;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.exceptions.KsiazkaException;

/**
 *
 * @author Maciej
 */
@Local
public interface MOKSEndpointLocal {
    public List<Ksiazka> pobierzKsiazki();
    public Ksiazka pobierzKsiazkeDoEdycji();
    public void edytujKsiazke(Ksiazka ksiazka);
    public void oznaczKsiazkeJakoNieaktywna(Ksiazka ksiazka) throws KsiazkaException;
    public void dodajKsiazke(Ksiazka ksiazka, List<String> wybraniAutorzy);
    public List<Ksiazka> pobierzKsiazkiNieaktywne();
    public List<Ksiazka> pobierzKsiazkiUlubione(String login) throws KsiazkaException;

    public List<Autor> pobierzAutorow();
}
