package pl.lodz.ssbd.moks.facades;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.lodz.ssbd.entities.Autor;
import pl.lodz.ssbd.exceptions.AutorException;
import pl.lodz.ssbd.exceptions.SSBD05Exception;
import pl.lodz.ssbd.facades.AbstractFacade;
import pl.lodz.ssbd.interceptors.DziennikZdarzenInterceptor;

/**
 *
 * @author Robert Mielczarek 
 */
@Stateless(name="moksAutor")
@Interceptors({DziennikZdarzenInterceptor.class})
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AutorFacade extends AbstractFacade<Autor> implements AutorFacadeLocal {
    @PersistenceContext(unitName = "ssbd05moks")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AutorFacade() {
        super(Autor.class);
    }

    @Override
    @DenyAll
    public void edit(Autor entity) throws AutorException  {
        try {
            super.edit(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
            Logger.getLogger(KsiazkaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    @RolesAllowed("DodanieKsiazki")
    public List<Autor> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @DenyAll
    public Autor find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @DenyAll
    public void create(Autor entity) throws AutorException  {
        try {
            super.create(entity); //To change body of generated methods, choose Tools | Templates.
        } catch (SSBD05Exception ex) {
            Logger.getLogger(KsiazkaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
