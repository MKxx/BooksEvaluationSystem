/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.exceptions;

/**
 *
 * Wyjątek rzucany w przypadku problemów z edycją encji książka.
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
public class KsiazkaException extends SSBD05Exception {

    /**
     * Creates a new instance of <code>KsiazkaException</code> without detail
     * message.
     */
    public KsiazkaException() {
    }

    /**
     * Constructs an instance of <code>KsiazkaException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public KsiazkaException(String msg) {
        super(msg);
    }
}
