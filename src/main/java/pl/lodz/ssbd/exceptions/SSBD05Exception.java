/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@ApplicationException(rollback = true)
public class SSBD05Exception extends Exception {

    /**
     * Creates a new instance of <code>SSBD05Exception</code> without detail
     * message.
     */
    public SSBD05Exception() {
    }

    /**
     * Constructs an instance of <code>SSBD05Exception</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public SSBD05Exception(String msg) {
        super(msg);
    }
}
