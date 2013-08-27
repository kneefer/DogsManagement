package entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "pies")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pies.findAll", query = "SELECT p FROM Pies p"),
    @NamedQuery(name = "Pies.findById", query = "SELECT p FROM Pies p WHERE p.id = :id"),
    @NamedQuery(name = "Pies.findByImie", query = "SELECT p FROM Pies p WHERE p.imie = :imie")
})
public class Pies implements Serializable {
    
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
    
    @JoinColumn(name = "i_wlasciciel", referencedColumnName = "id")
    @ManyToOne
    private Wlasciciel iWlasciciel;
    
    @JoinColumn(name = "i_rasa", referencedColumnName = "id")
    @ManyToOne
    private Rasa iRasa;

    public Pies() {
    }

    public Pies(String imie) {
        this.imie = imie;
    }
    
    public Pies(String imie, Wlasciciel iWlasciciel, Rasa iRasa) {
        this.imie = imie;
        this.iWlasciciel = iWlasciciel;
        this.iRasa = iRasa;
    }
    
    public Pies(String imie, Wlasciciel iWlasciciel) {
        this.imie = imie;
        this.iWlasciciel = iWlasciciel;
    }
    
    public Pies(String imie, Rasa iRasa) {
        this.imie = imie;
        this.iRasa = iRasa;
    }
    
    public Pies(Integer id, String imie, Rasa iRasa) {
        this.id = id;
        this.imie = imie;
        this.iRasa = iRasa;
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

    public Wlasciciel getIWlasciciel() {
        return iWlasciciel;
    }

    public void setIWlasciciel(Wlasciciel iWlasciciel) {
        this.iWlasciciel = iWlasciciel;
    }

    public Rasa getIRasa() {
        return iRasa;
    }

    public void setIRasa(Rasa iRasa) {
        this.iRasa = iRasa;
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
        if (!(object instanceof Pies)) {
            return false;
        }
        Pies other = (Pies) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pies[ id='" + id + "' imie='" + imie + "' iWlasciciel='" + iWlasciciel + "' iRasa='" + iRasa + "' ]";
    }
    
}
