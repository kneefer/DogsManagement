package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "wlasciciel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wlasciciel.findAll", query = "SELECT w FROM Wlasciciel w"),
    @NamedQuery(name = "Wlasciciel.findById", query = "SELECT w FROM Wlasciciel w WHERE w.id = :id"),
    @NamedQuery(name = "Wlasciciel.findByImie", query = "SELECT w FROM Wlasciciel w WHERE w.imie = :imie"),
    @NamedQuery(name = "Wlasciciel.findByNazwisko", query = "SELECT w FROM Wlasciciel w WHERE w.nazwisko = :nazwisko")})
public class Wlasciciel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "imie")
    private String imie;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nazwisko")
    private String nazwisko;
    
    @JoinColumn(name = "i_typ_wlasciciela", referencedColumnName = "id")
    @ManyToOne
    private TypWlasciciela iTypWlasciciela;
    
    @OneToMany(mappedBy = "iWlasciciel")
    private Collection<Pies> piesCollection;

    public Wlasciciel() {
    }

    public Wlasciciel(Integer id) {
        this.id = id;
    }

    public Wlasciciel(Integer id, String imie, String nazwisko) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }
    
    public Wlasciciel(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public TypWlasciciela getITypWlasciciela() {
        return iTypWlasciciela;
    }

    public void setITypWlasciciela(TypWlasciciela iTypWlasciciela) {
        this.iTypWlasciciela = iTypWlasciciela;
    }

    @XmlTransient
    public Collection<Pies> getPiesCollection() {
        return piesCollection;
    }

    public void setPiesCollection(Collection<Pies> piesCollection) {
        this.piesCollection = piesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wlasciciel)) {
            return false;
        }
        Wlasciciel other = (Wlasciciel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Wlasciciel[ id='" + id + "' imie='" + imie + "' nazwisko='" + nazwisko + "' iTypWlasciciela='" + iTypWlasciciela + "' ]";
    }
    
}
