/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Entity
@Table(name="uzytkownik")
@TableGenerator(name="UzytkownikIdGen", table="generator", pkColumnName="nazwa_klasy", valueColumnName="ost_id", pkColumnValue="uzytkownik", initialValue = 3)
@NamedQueries({
    @NamedQuery(name = "Uzytkownik.findAll", query = "SELECT u FROM Uzytkownik u"),
    @NamedQuery(name = "Uzytkownik.findByIdUzytkownik", query = "SELECT u FROM Uzytkownik u WHERE u.idUzytkownik = :idUzytkownik"),
    @NamedQuery(name = "Uzytkownik.findByImie", query = "SELECT u FROM Uzytkownik u WHERE u.imie = :imie"),
    @NamedQuery(name = "Uzytkownik.findByNazwisko", query = "SELECT u FROM Uzytkownik u WHERE u.nazwisko = :nazwisko"),
    @NamedQuery(name = "Uzytkownik.findByLogin", query = "SELECT u FROM Uzytkownik u WHERE u.login = :login"),
    @NamedQuery(name = "Uzytkownik.findByEmail", query = "SELECT u FROM Uzytkownik u WHERE u.email = :email"),
    @NamedQuery(name = "Uzytkownik.findByCzasPopZal", query = "SELECT u FROM Uzytkownik u WHERE u.czasPopZal = :czasPopZal"),
    @NamedQuery(name = "Uzytkownik.findByIpPopZal", query = "SELECT u FROM Uzytkownik u WHERE u.ipPopZal = :ipPopZal"),
    @NamedQuery(name = "Uzytkownik.findByCzasNPopZal", query = "SELECT u FROM Uzytkownik u WHERE u.czasNPopZal = :czasNPopZal"),
    @NamedQuery(name = "Uzytkownik.findByHasloMd5", query = "SELECT u FROM Uzytkownik u WHERE u.hasloMd5 = :hasloMd5"),
    @NamedQuery(name = "Uzytkownik.findByAktywny", query = "SELECT u FROM Uzytkownik u WHERE u.aktywny = :aktywny"),
    @NamedQuery(name = "Uzytkownik.findByWersjaEncji", query = "SELECT u FROM Uzytkownik u WHERE u.wersjaEncji = :wersjaEncji"),
    @NamedQuery(name = "Uzytkownik.findByPotwierdzony", query = "SELECT u FROM Uzytkownik u WHERE u.potwierdzony = :potwierdzony"),
    @NamedQuery(name = "Uzytkownik.findByIloscNPopZal", query = "SELECT u FROM Uzytkownik u WHERE u.iloscNPopZal = :iloscNPopZal")})
public class Uzytkownik implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_uzytkownik", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy= GenerationType.TABLE, generator="UzytkownikIdGen")
    private Long idUzytkownik;
    @Basic(optional = false)
    @NotNull
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String imie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 2, max = 70)
    @Column(nullable = false, length = 70)
    private String nazwisko;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(unique = true, nullable = false, length = 50)
    private String login;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(unique = true, nullable = false, length = 50)
    private String email;
    @Column(name = "czas_pop_zal", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date czasPopZal;
    @Size(max = 15)
    @Column(name = "ip_pop_zal", insertable = false, length = 15)
    private String ipPopZal;
    @Column(name = "czas_n_pop_zal", insertable = false, length = 15)
    @Temporal(TemporalType.TIMESTAMP)
    private Date czasNPopZal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 32, max = 32)
    @Column(name = "haslo_md5", length = 32)
    private String hasloMd5;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean aktywny;
    @Basic(optional = false)
    @NotNull
    @Column(name = "wersja_encji", nullable = false)
    @Version
    private long wersjaEncji;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean potwierdzony;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ilosc_n_pop_zal", nullable = false)
    private int iloscNPopZal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUzytkownik")
    private List<PoprzednieHaslo> poprzednieHasloList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUzytkownik")
    private List<PoziomDostepu> poziomDostepuList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUzytkownik")
    private List<Ocena> ocenaList = new ArrayList<>();

    public Uzytkownik() {
    }

    public Uzytkownik(Long idUzytkownik) {
        this.idUzytkownik = idUzytkownik;
    }

    public Uzytkownik(Long idUzytkownik, String imie, String nazwisko, String login, String email, String hasloMd5, boolean aktywny, long wersjaEncji, boolean potwierdzony, int iloscNPopZal) {
        this.idUzytkownik = idUzytkownik;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.login = login;
        this.email = email;
        this.hasloMd5 = hasloMd5;
        this.aktywny = aktywny;
        this.wersjaEncji = wersjaEncji;
        this.potwierdzony = potwierdzony;
        this.iloscNPopZal = iloscNPopZal;
    }

    public Long getIdUzytkownik() {
        return idUzytkownik;
    }

    public void setIdUzytkownik(Long idUzytkownik) {
        this.idUzytkownik = idUzytkownik;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCzasPopZal() {
        return czasPopZal;
    }

    public void setCzasPopZal(Date czasPopZal) {
        this.czasPopZal = czasPopZal;
    }

    public String getIpPopZal() {
        return ipPopZal;
    }

    public void setIpPopZal(String ipPopZal) {
        this.ipPopZal = ipPopZal;
    }

    public Date getCzasNPopZal() {
        return czasNPopZal;
    }

    public void setCzasNPopZal(Date czasNPopZal) {
        this.czasNPopZal = czasNPopZal;
    }

    public String getHasloMd5() {
        return hasloMd5;
    }

    public void setHasloMd5(String hasloMd5) {
        this.hasloMd5 = hasloMd5;
    }

    public boolean getAktywny() {
        return aktywny;
    }

    public void setAktywny(boolean aktywny) {
        this.aktywny = aktywny;
    }

    private void setWersjaEncji(long wersjaEncji) {
        this.wersjaEncji = wersjaEncji;
    }

    public boolean getPotwierdzony() {
        return potwierdzony;
    }

    public void setPotwierdzony(boolean potwierdzony) {
        this.potwierdzony = potwierdzony;
    }

    public int getIloscNPopZal() {
        return iloscNPopZal;
    }

    public void setIloscNPopZal(int iloscNPopZal) {
        this.iloscNPopZal = iloscNPopZal;
    }

    public List<PoprzednieHaslo> getPoprzednieHasloList() {
        return poprzednieHasloList;
    }

    public void setPoprzednieHasloList(List<PoprzednieHaslo> poprzednieHasloList) {
        this.poprzednieHasloList = poprzednieHasloList;
    }

    public List<PoziomDostepu> getPoziomDostepuList() {
        return poziomDostepuList;
    }

    public void setPoziomDostepuList(List<PoziomDostepu> poziomDostepuList) {
        this.poziomDostepuList = poziomDostepuList;
    }

    public List<Ocena> getOcenaList() {
        return ocenaList;
    }

    public void setOcenaList(List<Ocena> ocenaList) {
        this.ocenaList = ocenaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUzytkownik != null ? idUzytkownik.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uzytkownik)) {
            return false;
        }
        Uzytkownik other = (Uzytkownik) object;
        if ((this.idUzytkownik == null && other.idUzytkownik != null) || (this.idUzytkownik != null && !this.idUzytkownik.equals(other.idUzytkownik))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.lodz.ssbd.entities.Uzytkownik[ idUzytkownik=" + idUzytkownik + " ]";
    }
    
}
