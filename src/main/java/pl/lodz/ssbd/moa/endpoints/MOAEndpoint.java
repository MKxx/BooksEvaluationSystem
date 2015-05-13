/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.moa.endpoints;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import pl.lodz.ssbd.entities.Autor;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;
import pl.lodz.ssbd.moa.facades.AutorFacadeLocal;

/**
 *
 * @author Maciej
 */
@Stateful
@Interceptors({DziennikZdarzenInterceptor.class})
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MOAEndpoint implements MOAEndpointLocal {
    
    @EJB(beanName = "moaAutor")
    private AutorFacadeLocal AutorFacade;

    @Override
    @PermitAll
    public List<Autor> pobierzListeAutorow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed("ModyfikacjaAutora")
    public Autor pobierzAutoraDoEdycji(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed("DodanieAutora")
    public void dodajAutora(Autor autor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed("ModyfikacjaAutora")
    public void edytujAutora(Autor autor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    @PermitAll
    public Autor pobierzAutora(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
