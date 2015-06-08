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
public class KsiazkaException extends SSBD05Exception {

    /**
     * Creates a new instance of <code>KsiazkaException</code> without detail
     * message.
     */
    public KsiazkaException() {
    }

    /**
     * Wyjątek rzucay w przypadku problemów z edycją encji książka.
     *
     * @param msg the detail message.
     */
    public KsiazkaException(String msg) {
        super(msg);
    }
}
