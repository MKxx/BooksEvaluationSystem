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
    /**
     * Przeglądanie książek w trybie moderatorskim, to znaczy że jest to lista która służy tylko do wykonywania akcji moderatorskich, takich jak edycja itp.
     * @return lista ksiazek
     */
    public List<Ksiazka> pobierzKsiazki();
    /**
     * Pobiera książkę do Edycji
     * @param id id ksiazki
     * @return ksiazka do edycji
     */
    public Ksiazka pobierzKsiazkeDoEdycji(long id);
    /**
     * Edycja ksiazki
     * @param ksiazka edytowana ksiazka
     * @throws KsiazkaException Rzucony gdy posiadamy nieaktualne dane
     */
    public void edytujKsiazke(Ksiazka ksiazka) throws KsiazkaException ;
    /**
     * Oznaczenie książki jako niekatywnej, przez co użytkownicy nie mogą już oceniać i oglądać danej książki
     * @param ksiazka ksiazka ktora zostanie oznaczona jako nieaktywna
     * @throws KsiazkaException rzucany gdy posiadamy nieaktualne dane
     */
    public void oznaczKsiazkeJakoNieaktywna(Ksiazka ksiazka) throws KsiazkaException;
    /**
     * Dodanie nowej książki
     * @param ksiazka ksiazka do dodania
     * @param wybraniAutorzy autorzy ksiazki
     */
    public void dodajKsiazke(Ksiazka ksiazka, List<String> wybraniAutorzy);
    /**
     * Wyświetlenie listy książek które zostały oznaczone jako nieaktywne
     * @return lista ksiazek nieaktywnych
     */
    public List<Ksiazka> pobierzKsiazkiNieaktywne();
    /**
     * Wyświetlenie listy książek które dany użytkownik oznaczył jako ulubione
     * @param login login uzytkownika
     * @return ksiazki oznaczone jako ulubione
     * @throws KsiazkaException Wyjatek rzucany gdy wystapi blad
     */
    public List<Ksiazka> pobierzKsiazkiUlubione(String login) throws KsiazkaException;
    /**
     * Pobranie listy autorow
     * @return lista autorow
     */
    public List<Autor> pobierzAutorow();
}
