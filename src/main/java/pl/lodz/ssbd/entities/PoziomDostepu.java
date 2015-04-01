/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Entity
@Table(name = "poziom_dostepu")
@NamedQueries({
    @NamedQuery(name = "PoziomDostepu.findAll", query = "SELECT p FROM PoziomDostepu p"),
    @NamedQuery(name = "PoziomDostepu.findByIdPoziomDostepu", query = "SELECT p FROM PoziomDostepu p WHERE p.idPoziomDostepu = :idPoziomDostepu"),
    @NamedQuery(name = "PoziomDostepu.findByNazwa", query = "SELECT p FROM PoziomDostepu p WHERE p.nazwa = :nazwa"),
    @NamedQuery(name = "PoziomDostepu.findByAktywny", query = "SELECT p FROM PoziomDostepu p WHERE p.aktywny = :aktywny"),
    @NamedQuery(name = "PoziomDostepu.findByWersjaEncji", query = "SELECT p FROM PoziomDostepu p WHERE p.wersjaEncji = :wersjaEncji")})
public class PoziomDostepu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_poziom_dostepu")
    private Long idPoziomDostepu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String nazwa;
    @Basic(optional = false)
    @NotNull
    private boolean aktywny;
    @Basic(optional = false)
    @NotNull
    @Column(name = "wersja_encji")
    private long wersjaEncji;
    @JoinColumn(name = "id_uzytkownik", referencedColumnName = "id_uzytkownik", nullable = false)
    @ManyToOne(optional = false)
    private Uzytkownik idUzytkownik;

    public PoziomDostepu() {
    }

    public PoziomDostepu(Long idPoziomDostepu) {
        this.idPoziomDostepu = idPoziomDostepu;
    }

    public PoziomDostepu(Long idPoziomDostepu, String nazwa, boolean aktywny, long wersjaEncji) {
        this.idPoziomDostepu = idPoziomDostepu;
        this.nazwa = nazwa;
        this.aktywny = aktywny;
        this.wersjaEncji = wersjaEncji;
    }

    public Long getIdPoziomDostepu() {
        return idPoziomDostepu;
    }

    public void setIdPoziomDostepu(Long idPoziomDostepu) {
        this.idPoziomDostepu = idPoziomDostepu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public boolean getAktywny() {
        return aktywny;
    }

    public void setAktywny(boolean aktywny) {
        this.aktywny = aktywny;
    }

    public long getWersjaEncji() {
        return wersjaEncji;
    }

    public void setWersjaEncji(long wersjaEncji) {
        this.wersjaEncji = wersjaEncji;
    }

    public Uzytkownik getIdUzytkownik() {
        return idUzytkownik;
    }

    public void setIdUzytkownik(Uzytkownik idUzytkownik) {
        this.idUzytkownik = idUzytkownik;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPoziomDostepu != null ? idPoziomDostepu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PoziomDostepu)) {
            return false;
        }
        PoziomDostepu other = (PoziomDostepu) object;
        if ((this.idPoziomDostepu == null && other.idPoziomDostepu != null) || (this.idPoziomDostepu != null && !this.idPoziomDostepu.equals(other.idPoziomDostepu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.lodz.ssbd.entities.PoziomDostepu[ idPoziomDostepu=" + idPoziomDostepu + " ]";
    }
    
}
