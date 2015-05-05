/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.exceptions;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
public class BrakUzytkownikaException extends SSBD05Exception {

    /**
     * Creates a new instance of <code>BrakUzytkownikaException</code> without
     * detail message.
     */
    public BrakUzytkownikaException() {
    }

    /**
     * Constructs an instance of <code>BrakUzytkownikaException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public BrakUzytkownikaException(String msg) {
        super(msg);
    }
}
