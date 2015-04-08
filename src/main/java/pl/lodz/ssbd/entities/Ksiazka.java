/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Entity
@Table(name = "ksiazka")
@TableGenerator(name="KsiazkaIdGen", table="generator", pkColumnName="class_name", valueColumnName="id_range", pkColumnValue="ksiazka")
@NamedQueries({
    @NamedQuery(name = "Ksiazka.findAll", query = "SELECT k FROM Ksiazka k"),
    @NamedQuery(name = "Ksiazka.findByIdKsiazka", query = "SELECT k FROM Ksiazka k WHERE k.idKsiazka = :idKsiazka"),
    @NamedQuery(name = "Ksiazka.findByTytul", query = "SELECT k FROM Ksiazka k WHERE k.tytul = :tytul"),
    @NamedQuery(name = "Ksiazka.findByRokPierwszegoWydania", query = "SELECT k FROM Ksiazka k WHERE k.rokPierwszegoWydania = :rokPierwszegoWydania"),
    @NamedQuery(name = "Ksiazka.findBySredniaOcen", query = "SELECT k FROM Ksiazka k WHERE k.sredniaOcen = :sredniaOcen"),
    @NamedQuery(name = "Ksiazka.findByAktywne", query = "SELECT k FROM Ksiazka k WHERE k.aktywne = :aktywne"),
    @NamedQuery(name = "Ksiazka.findByWersjaEncji", query = "SELECT k FROM Ksiazka k WHERE k.wersjaEncji = :wersjaEncji"),
    @NamedQuery(name = "Ksiazka.findByIloscAutorow", query = "SELECT k FROM Ksiazka k WHERE k.iloscAutorow = :iloscAutorow")})
public class Ksiazka implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_ksiazka", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy= GenerationType.TABLE, generator="KsiazkaIdGen")
    private Long idKsiazka;
    @Basic(optional = false)
    @NotNull
    @Size(min = 2, max = 100)
    @Column(nullable = false, length = 100)
    private String tytul;
    @Column(name = "rok_pierwszego_wydania")
    private Integer rokPierwszegoWydania;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "srednia_ocen",insertable = false, precision = 5, scale = 4)
    private BigDecimal sredniaOcen;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean aktywne;
    @Basic(optional = false)
    @NotNull
    @Column(name = "wersja_encji", nullable = false)
    @Version
    private long wersjaEncji;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ilosc_autorow", updatable = false, nullable = false)
    private Integer iloscAutorow;
    @JoinTable(name = "ksiazka_autor", joinColumns = {
    @JoinColumn(name = "id_ksiazka", referencedColumnName = "id_ksiazka")}, inverseJoinColumns = {
    @JoinColumn(name = "id_autor", referencedColumnName = "id_autor")})
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Autor> autorList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKsiazka")
    private List<Ocena> ocenaList = new ArrayList<>();

    public Ksiazka() {
    }

    public Ksiazka(Long idKsiazka) {
        this.idKsiazka = idKsiazka;
    }

    public Ksiazka(Long idKsiazka, String tytul, boolean aktywne, long wersjaEncji, int iloscAutorow) {
        this.idKsiazka = idKsiazka;
        this.tytul = tytul;
        this.aktywne = aktywne;
        this.wersjaEncji = wersjaEncji;
        this.iloscAutorow = iloscAutorow;
    }

    public Long getIdKsiazka() {
        return idKsiazka;
    }

    public void setIdKsiazka(Long idKsiazka) {
        this.idKsiazka = idKsiazka;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public Integer getRokPierwszegoWydania() {
        return rokPierwszegoWydania;
    }

    public void setRokPierwszegoWydania(Integer rokPierwszegoWydania) {
        this.rokPierwszegoWydania = rokPierwszegoWydania;
    }

    public BigDecimal getSredniaOcen() {
        return sredniaOcen;
    }

    public void setSredniaOcen(BigDecimal sredniaOcen) {
        this.sredniaOcen = sredniaOcen;
    }

    public boolean getAktywne() {
        return aktywne;
    }

    public void setAktywne(boolean aktywne) {
        this.aktywne = aktywne;
    }

    private void setWersjaEncji(long wersjaEncji) {
        this.wersjaEncji = wersjaEncji;
    }

    public int getIloscAutorow() {
        return iloscAutorow;
    }

    public void setIloscAutorow(int iloscAutorow) {
        this.iloscAutorow = iloscAutorow;
    }

    public List<Autor> getAutorList() {
        return autorList;
    }

    public void setAutorList(List<Autor> autorList) {
        this.autorList = autorList;
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
        hash += (idKsiazka != null ? idKsiazka.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ksiazka)) {
            return false;
        }
        Ksiazka other = (Ksiazka) object;
        if ((this.idKsiazka == null && other.idKsiazka != null) || (this.idKsiazka != null && !this.idKsiazka.equals(other.idKsiazka))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.lodz.ssbd.entities.Ksiazka[ idKsiazka=" + idKsiazka + " ]";
    }
    
}
