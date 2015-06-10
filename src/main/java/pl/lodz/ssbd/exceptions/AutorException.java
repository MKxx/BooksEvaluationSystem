/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.exceptions;

    /**
 
     * Wyjątek rzucany w przypadku problemów z edycją encji autor.
     *
     * @author Robert Mielczarek <180640@edu.p.lodz.pl>
    */
public class AutorException extends SSBD05Exception {

    /**
     * Creates a new instance of <code>AutorException</code> without detail
     * message.
     */
    public AutorException() {
    }

     /**
     * Constructs an instance of <code>AutorException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public AutorException(String msg) {
        super(msg);
    }
}
