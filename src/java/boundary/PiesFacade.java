package boundary;

import entities.Pies;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PiesFacade extends AbstractFacade<Pies> {
    
    @PersistenceContext(unitName = "avisoft.zadaniePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PiesFacade() {
        super(Pies.class);
    }    
    
    public Pies findByIdPsa(Integer idRasy) {
        Query query = em.createNamedQuery("Pies.findById");
        query.setParameter("id", idRasy);
        try {
            return (Pies)query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
