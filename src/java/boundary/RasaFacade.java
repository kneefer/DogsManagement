package boundary;

import entities.Rasa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class RasaFacade extends AbstractFacade<Rasa> {
    
    @PersistenceContext(unitName = "avisoft.zadaniePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RasaFacade() {
        super(Rasa.class);
    }
    
    @Override
    public List<Rasa> findAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Rasa> root = cq.from(Rasa.class);
        
        cq.select(root);
        cq.orderBy(cb.asc(root.get("nazwaRasy")));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public Rasa findByNazwaRasy(String nazwaRasy) {
        Query query = em.createNamedQuery("Rasa.findByNazwaRasy");
        query.setParameter("nazwaRasy", nazwaRasy);
        try {
            return (Rasa)query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
    
    public Rasa findByIdRasy(Integer idRasy) {
        Query query = em.createNamedQuery("Rasa.findById");
        query.setParameter("id", idRasy);
        try {
            return (Rasa)query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
