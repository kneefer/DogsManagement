package boundary;

import entities.Wlasciciel;
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
public class WlascicielFacade extends AbstractFacade<Wlasciciel> {
    @PersistenceContext(unitName = "avisoft.zadaniePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WlascicielFacade() {
        super(Wlasciciel.class);
    }
    
    @Override
    public List<Wlasciciel> findAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Wlasciciel> root = cq.from(Wlasciciel.class);
        
        cq.select(root);
        cq.orderBy(cb.asc(root.get("nazwisko")), cb.asc(root.get("imie")));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public Wlasciciel findByIdWlasciciela(int idWlasciciela) {
        Query query = em.createNamedQuery("Wlasciciel.findById");
        query.setParameter("id", idWlasciciela);
        try {
            return (Wlasciciel)query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
