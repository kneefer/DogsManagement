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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "rasa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rasa.findAll", query = "SELECT r FROM Rasa r"),
    @NamedQuery(name = "Rasa.findById", query = "SELECT r FROM Rasa r WHERE r.id = :id"),
    @NamedQuery(name = "Rasa.findByNazwaRasy", query = "SELECT r FROM Rasa r WHERE r.nazwaRasy = :nazwaRasy")})
public class Rasa implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nazwa_rasy")
    private String nazwaRasy;
    
    @OneToMany(mappedBy = "iRasa")
    private Collection<Pies> piesCollection;

    public Rasa() {
    }

    public Rasa(Integer id) {
        this.id = id;
    }

    public Rasa(Integer id, String nazwaRasy) {
        this.id = id;
        this.nazwaRasy = nazwaRasy;
    }
    
    public Rasa(String nazwaRasy) {
        this.nazwaRasy = nazwaRasy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwaRasy() {
        return nazwaRasy;
    }

    public void setNazwaRasy(String nazwaRasy) {
        this.nazwaRasy = nazwaRasy;
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
        if (!(object instanceof Rasa)) {
            return false;
        }
        Rasa other = (Rasa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        
        if(this.nazwaRasy.equals(other.nazwaRasy) == false) {
            return false;
        }  
        
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rasa[ id='" + id + "' nazwaRasy='" + nazwaRasy + "' ]";
    }
    
}
