/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
public class Bundle {

    public static String internalizuj(String key, Locale locale) {
        java.util.ResourceBundle rb = ResourceBundle.getBundle("internacjonalizacja/messages", locale);
        return rb.getString(key);
    }
    
//     public static String internalizuj(String key) {
//        java.util.ResourceBundle rb = ResourceBundle.getBundle("internacjonalizacja/messages", Locale.getDefault());
//        return rb.getString(key);
//    }
}
