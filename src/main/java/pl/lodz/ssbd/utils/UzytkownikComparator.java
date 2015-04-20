/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.utils;

import java.util.Comparator;
import pl.lodz.ssbd.entities.Uzytkownik;

/**
 *
 * @author Maciej
 */
public class UzytkownikComparator implements Comparator<Uzytkownik>{

    @Override
    public int compare(Uzytkownik u1, Uzytkownik u2) {
        return u1.getLogin().compareTo(u2.getLogin());
    }
    
}
