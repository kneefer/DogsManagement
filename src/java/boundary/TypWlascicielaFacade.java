package boundary;

import entities.TypWlasciciela;
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
public class TypWlascicielaFacade extends AbstractFacade<TypWlasciciela> {
    
    @PersistenceContext(unitName = "avisoft.zadaniePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypWlascicielaFacade() {
        super(TypWlasciciela.class);
    }

    @Override
    public List<TypWlasciciela> findAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<TypWlasciciela> root = cq.from(TypWlasciciela.class);
        
        cq.select(root);
        cq.orderBy(cb.asc(root.get("nazwaTypuWlasciciela")));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public TypWlasciciela findByIdTypuWlasciciela(int idTypuWlasciciela) {
        Query query = em.createNamedQuery("TypWlasciciela.findById");
        query.setParameter("id", idTypuWlasciciela);
        try {
            return (TypWlasciciela)query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
    
}
