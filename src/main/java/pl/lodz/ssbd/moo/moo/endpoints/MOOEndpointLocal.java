/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moo.moo.endpoints;

import java.util.List;
import javax.ejb.Local;
import pl.lodz.ssbd.entities.Ksiazka;
import pl.lodz.ssbd.entities.Ocena;
import pl.lodz.ssbd.exceptions.OcenaException;


/**
 *
 * @author Maciej
 */
@Local
public interface MOOEndpointLocal {
     /**
     * Metoda zwracająca listę aktywnych książek
     * @return lista aktywnych książek
     */
    public List<Ksiazka> pobierzKsiazki();

    /**
     * Metoda dodająca książkę do ulubionych 
     * Należy zauważyć, że u nas jest to rozwiązane w dośc oryginaly sposób
     * @param ocena która zawiera ksiązkę, którą użytkownik chce dodać do ulubionych.
     * @throws OcenaException 
     */
    public void dodajDoUlubionych(Ocena ocena) throws OcenaException;

     /**
     * Metoda pobierająca wszystkie oceny
     * @return lista wszystkich ocen
     */
    public List<Ocena> pobierzOceny();
    
}
