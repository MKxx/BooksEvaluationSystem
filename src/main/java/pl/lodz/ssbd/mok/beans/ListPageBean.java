/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.beans;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.ssbd.entities.Uzytkownik;
import pl.lodz.ssbd.exceptions.UzytkownikException;
import pl.lodz.ssbd.utils.UzytkownikComparator;

/**
 * Klasa do obslugi widoku panelu admina
 * @author Maciej
 */
@Named
@ViewScoped
public class ListPageBean implements Serializable {
    public ListPageBean() {
    }
    
    private String wartosc;
    
    public String getWartosc() {
        return wartosc;
    }
    public void setWartosc(String wartosc) {
        this.wartosc = wartosc;
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

    
    /**
     * Inicjacja listy uzytkownikow
     */
    @PostConstruct
    private void initModel() {
        uzytkownicy = uzytkownikSession.pobierzUzytkownikow(wartosc);
        uzytkownicy.sort(new UzytkownikComparator());
        uzytkownikDataModel = new ListDataModel<Uzytkownik>(uzytkownicy);
    }
    
    /**
     * potwierdzenie uzytkownika
     * @return String przkierowujacy do strony
     */
    public String potwierdzUzytkownika(){
        try{
        uzytkownikSession.potwierdzUzytkownika(uzytkownikDataModel.getRowData());
        initModel();
        }
        catch(UzytkownikException ex){
           Logger.getLogger(ListPageBean.class.getName()).log(Level.SEVERE, null, ex);
           return "nieaktualnedane";
        }
        return null;
    }
    /**
     * zablokowanie uzytkownika
     * @return String przkierowujacy do strony 
     */
    public String zablokujUzytkownika(){
        try{
            uzytkownikSession.zablokujUzytkownika(uzytkownikDataModel.getRowData());
            initModel();
        }catch (UzytkownikException ex){
            Logger.getLogger(ListPageBean.class.getName()).log(Level.SEVERE, null, ex);
            return "nieaktualnedane";
        }
        return null;
    }
    
    /**
     * odblokowanie Uzytkownika
     * @return String przkierowujacy do strony
     */
    public String odblokujUzytkownika(){
         try{
            uzytkownikSession.odblokujUzytkownika(uzytkownikDataModel.getRowData());
            initModel();
        }catch (UzytkownikException ex){
            Logger.getLogger(ListPageBean.class.getName()).log(Level.SEVERE, null, ex);
            return "nieaktualnedane";
        }
        return null;
    }
    
    /**
     * Rozpoczecie edycji uzytkowika
     * @return String przkierowujacy do strony
     */
    public String edytujUzytkownika(){
        uzytkownikSession.pobierzUzytkownikaDoEdycji(uzytkownikDataModel.getRowData());
        return "edycja";
    }
    
    /**
     * rozpoczecie edycji poziomow dostepu
     * @return String przkierowujacy do strony
     */
    public String edytujPoziomyDostepu(){
        uzytkownikSession.pobierzUzytkownikaDoEdycji(uzytkownikDataModel.getRowData());
        return "edycjaPoziomow";
    }
    
    /**
     * odswiezenie modelu
     */
    public void odswiez(){
        initModel();
    }
}
