/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moa.beans;
import java.io.Serializable;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import pl.lodz.ssbd.entities.Autor;
import pl.lodz.ssbd.moa.endpoints.MOAEndpointLocal;
/**
 *
 * @author Marta
 */
@SessionScoped
public class AutorSession implements Serializable {
    @EJB
    MOAEndpointLocal MOAEndpoint;
    
    
    
    public AutorSession() {
    }
 
    @RolesAllowed("PrzegladanieAutorow")
    public List<Autor> pobierzListeAutorow(){
        return MOAEndpoint.pobierzListeAutorow(); 
    }
    
  /*  void stworzAutor(Autor autor) {
        MOAEndpoint.dodajAutora(autor);
    }
*/

    void dodajAutora(Autor autor) {
         MOAEndpoint.dodajAutora(autor);
    }
    
}