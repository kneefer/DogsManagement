package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "typ_wlasciciela")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypWlasciciela.findAll", query = "SELECT t FROM TypWlasciciela t"),
    @NamedQuery(name = "TypWlasciciela.findById", query = "SELECT t FROM TypWlasciciela t WHERE t.id = :id"),
    @NamedQuery(name = "TypWlasciciela.findByNazwaTypuWlasciciela", query = "SELECT t FROM TypWlasciciela t WHERE t.nazwaTypuWlasciciela = :nazwaTypuWlasciciela")})
public class TypWlasciciela implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Size(max = 45)
    @Column(name = "nazwa_typu_wlasciciela")
    private String nazwaTypuWlasciciela;
    
    @OneToMany(mappedBy = "iTypWlasciciela")
    private Collection<Wlasciciel> wlascicielCollection;

    public TypWlasciciela() {
    }

    public TypWlasciciela(Integer id, String nazwaTypuWlasciciela) {
        this.id = id;
        this.nazwaTypuWlasciciela = nazwaTypuWlasciciela;
    }
    
    public TypWlasciciela(String nazwaTypuWlasciciela) {
        this.nazwaTypuWlasciciela = nazwaTypuWlasciciela;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwaTypuWlasciciela() {
        return nazwaTypuWlasciciela;
    }

    public void setNazwaTypuWlasciciela(String nazwaTypuWlasciciela) {
        this.nazwaTypuWlasciciela = nazwaTypuWlasciciela;
    }

    @XmlTransient
    public Collection<Wlasciciel> getWlascicielCollection() {
        return wlascicielCollection;
    }

    public void setWlascicielCollection(Collection<Wlasciciel> wlascicielCollection) {
        this.wlascicielCollection = wlascicielCollection;
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
        if (!(object instanceof TypWlasciciela)) {
            return false;
        }
        TypWlasciciela other = (TypWlasciciela) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TypWlasciciela[ id='" + id + "' nazwaTypuWlasciciela='" + nazwaTypuWlasciciela + "' ]";
    }
    
}
