package pl.lodz.ssbd.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Entity
@Table(name = "ocena",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "id_ksiazka"),
            @UniqueConstraint(columnNames = "id_uzytkownik")})
@TableGenerator(name = "OcenaIdGen", table = "generator", pkColumnName = "nazwa_klasy", valueColumnName = "ost_id", pkColumnValue = "ocena")
@NamedQueries({
    @NamedQuery(name = "Ocena.findAll", query = "SELECT o FROM Ocena o"),
    @NamedQuery(name = "Ocena.findByIdOcena", query = "SELECT o FROM Ocena o WHERE o.idOcena = :idOcena"),
    @NamedQuery(name = "Ocena.findByOcena", query = "SELECT o FROM Ocena o WHERE o.ocena = :ocena"),
    @NamedQuery(name = "Ocena.findByUlubiona", query = "SELECT o FROM Ocena o WHERE o.ulubiona = :ulubiona"),
    @NamedQuery(name = "Ocena.findByWersjaEncji", query = "SELECT o FROM Ocena o WHERE o.wersjaEncji = :wersjaEncji")})
public class Ocena implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_ocena", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "OcenaIdGen")
    private Long idOcena;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int ocena;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean ulubiona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "wersja_encji", nullable = false)
    @Version
    private long wersjaEncji;
    @JoinColumn(name = "id_ksiazka", referencedColumnName = "id_ksiazka", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Ksiazka idKsiazka;
    @JoinColumn(name = "id_uzytkownik", referencedColumnName = "id_uzytkownik", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Uzytkownik idUzytkownik;

    public Ocena() {
    }

    public Ocena(Long idOcena) {
        this.idOcena = idOcena;
    }

    public Ocena(Long idOcena, int ocena, boolean ulubiona, long wersjaEncji) {
        this.idOcena = idOcena;
        this.ocena = ocena;
        this.ulubiona = ulubiona;
        this.wersjaEncji = wersjaEncji;
    }

    public Long getIdOcena() {
        return idOcena;
    }

    public void setIdOcena(Long idOcena) {
        this.idOcena = idOcena;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public boolean getUlubiona() {
        return ulubiona;
    }

    public void setUlubiona(boolean ulubiona) {
        this.ulubiona = ulubiona;
    }

    public Ksiazka getIdKsiazka() {
        return idKsiazka;
    }

    public void setIdKsiazka(Ksiazka idKsiazka) {
        this.idKsiazka = idKsiazka;
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
        hash += (idOcena != null ? idOcena.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ocena)) {
            return false;
        }
        Ocena other = (Ocena) object;
        if ((this.idOcena == null && other.idOcena != null) || (this.idOcena != null && !this.idOcena.equals(other.idOcena))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.lodz.ssbd.entities.Ocena[ idOcena=" + idOcena + ", nr wersji: " + wersjaEncji + " ]";
    }

}
