/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.utils.UzytkownikComparator;

/**
 *
 * @author Maciej
 */
@Named
@RequestScoped
public class ListPageBean {
    public ListPageBean() {
    }
    
    @Inject  
    private UzytkownikSession uzytkownikSession;

    // DataModel jest reprezentacja listy obiektow potrzebna do pelnego dzialania
    // tabeli (DataTable)
    private List<Uzytkownik> uzytkownicy;
    private DataModel<Uzytkownik> uzytkownikDataModel;

    public DataModel<Uzytkownik> getUzytkownikDataModel() {
        return uzytkownikDataModel;
    }

    
    // Metoda wczytuje liste wszystkich kont
    // Dzieki adnotacji @PostConstruct jest wykonywana automatycznie po zaladowaniu strony,
    // ponadto odwoluja sie do niej inne metody tego ziarna
    @PostConstruct
    private void initModel() {
        uzytkownicy = uzytkownikSession.pobierzWszystkichUzytkownikow();
        uzytkownicy.sort(new UzytkownikComparator());
        uzytkownikDataModel = new ListDataModel<Uzytkownik>(uzytkownicy);
    }
    
    public void potwierdzUzytkownika(){
        uzytkownikSession.potwierdzUzytkownika(uzytkownikDataModel.getRowData());
        initModel();
    }
    
    public void zablokujUzytkownika(){
        uzytkownikSession.zablokujUzytkownika(uzytkownikDataModel.getRowData());
        initModel();
    }
    
    public void odblokujUzytkownika(){
        uzytkownikSession.odblokujUzytkownika(uzytkownikDataModel.getRowData());
        initModel();
    }
    
    public String edytujUzytkownika(){
        uzytkownikSession.pobierzUzytkownikaDoEdycji(uzytkownikDataModel.getRowData());
        return "edycja";
    }
    
    public String edytujPoziomyDostepu(){
        uzytkownikSession.pobierzUzytkownikaDoEdycji(uzytkownikDataModel.getRowData());
        return "edycjaPoziomow";
    }
    public void odswiez(){
        initModel();
    }
}
