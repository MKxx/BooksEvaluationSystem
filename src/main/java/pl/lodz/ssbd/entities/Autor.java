/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "autor")
@TableGenerator(name="AutorIdGen", table="generator", pkColumnName="class_name", valueColumnName="id_range", pkColumnValue="Autor")
@NamedQueries({
    @NamedQuery(name = "Autor.findAll", query = "SELECT a FROM Autor a"),
    @NamedQuery(name = "Autor.findByIdAutor", query = "SELECT a FROM Autor a WHERE a.idAutor = :idAutor"),
    @NamedQuery(name = "Autor.findByImie", query = "SELECT a FROM Autor a WHERE a.imie = :imie"),
    @NamedQuery(name = "Autor.findByNazwisko", query = "SELECT a FROM Autor a WHERE a.nazwisko = :nazwisko"),
    @NamedQuery(name = "Autor.findByRokUr", query = "SELECT a FROM Autor a WHERE a.rokUr = :rokUr"),
    @NamedQuery(name = "Autor.findBySrOcena", query = "SELECT a FROM Autor a WHERE a.srOcena = :srOcena"),
    @NamedQuery(name = "Autor.findByWersjaEncji", query = "SELECT a FROM Autor a WHERE a.wersjaEncji = :wersjaEncji")})
public class Autor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_autor", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy= GenerationType.TABLE, generator="AutorIdGen")
    private Long idAutor;
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
    @Column(name = "rok_ur")
    private Integer rokUr;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sr_ocena", insertable = false, precision = 5, scale = 4)
    private BigDecimal srOcena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "wersja_encji", nullable = false)
    @Version
    private long wersjaEncji;
    @ManyToMany(mappedBy = "autorList", cascade = CascadeType.ALL)
    private List<Ksiazka> ksiazkaList;

    public Autor() {
    }

    public Autor(Long idAutor) {
        this.idAutor = idAutor;
    }

    public Autor(Long idAutor, String imie, String nazwisko, long wersjaEncji) {
        this.idAutor = idAutor;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wersjaEncji = wersjaEncji;
    }

    public Long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Long idAutor) {
        this.idAutor = idAutor;
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

    public Integer getRokUr() {
        return rokUr;
    }

    public void setRokUr(Integer rokUr) {
        this.rokUr = rokUr;
    }

    public BigDecimal getSrOcena() {
        return srOcena;
    }

    public void setSrOcena(BigDecimal srOcena) {
        this.srOcena = srOcena;
    }

    public long getWersjaEncji() {
        return wersjaEncji;
    }

    public void setWersjaEncji(long wersjaEncji) {
        this.wersjaEncji = wersjaEncji;
    }

    public List<Ksiazka> getKsiazkaList() {
        return ksiazkaList;
    }

    public void setKsiazkaList(List<Ksiazka> ksiazkaList) {
        this.ksiazkaList = ksiazkaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAutor != null ? idAutor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autor)) {
            return false;
        }
        Autor other = (Autor) object;
        if ((this.idAutor == null && other.idAutor != null) || (this.idAutor != null && !this.idAutor.equals(other.idAutor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.lodz.ssbd.entities.Autor[ idAutor=" + idAutor + " ]";
    }
    
}
