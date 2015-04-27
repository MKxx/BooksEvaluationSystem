/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import pl.lodz.ssbd.entities.PoziomDostepu;
import pl.lodz.ssbd.entities.Uzytkownik;

/**
 *
 * @author Maciej
 */
@Named(value = "edycjaPoziomowPageBean")
@RequestScoped
public class EdycjaPoziomowPageBean {
    
    @Inject
    private UzytkownikSession uzytkownikSession;
    
    private DataModel<PoziomDostepu> poziomyDataModel;

    public DataModel<PoziomDostepu> getPoziomyDataModel() {
        return poziomyDataModel;
    }
    
    @PostConstruct
    private void initModel(){
        List<PoziomDostepu> poziomy = getUzytkownikEdycja().getPoziomDostepuList();
        poziomyDataModel = new ListDataModel<PoziomDostepu>(poziomy);
    }

     public void setUzytkownikSession(UzytkownikSession uzytkownikSession) {
        this.uzytkownikSession = uzytkownikSession;
    }
    
    public Uzytkownik getUzytkownikEdycja() {
        return uzytkownikSession.getUzytkownikEdycja();
    }
    
    public void nadajPoziom(){
        uzytkownikSession.nadajPoziom(poziomyDataModel.getRowData());
        uzytkownikSession.pobierzUzytkownikaDoEdycji(uzytkownikSession.getUzytkownikEdycja());
        initModel();
    }
    
    public void odbierzPoziom(){
        uzytkownikSession.odbierzPoziom(poziomyDataModel.getRowData());
        uzytkownikSession.pobierzUzytkownikaDoEdycji(uzytkownikSession.getUzytkownikEdycja());
        initModel();
    }
}
