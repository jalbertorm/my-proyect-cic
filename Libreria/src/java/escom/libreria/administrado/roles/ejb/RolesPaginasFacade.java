/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.libreria.administrado.roles.ejb;

import escom.libreria.administrado.roles.jpa.RolesPaginas;
import escom.libreria.administrado.roles.jpa.RolesUsuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author yamil
 */
@Stateless
public class RolesPaginasFacade {
    @PersistenceContext(unitName = "LibreriaPU")
    private EntityManager em;

    public void create(RolesPaginas rolesPaginas) {
        em.persist(rolesPaginas);
    }

    public void edit(RolesPaginas rolesPaginas) {
        em.merge(rolesPaginas);
    }

    public void remove(RolesPaginas rolesPaginas) {
        em.remove(em.merge(rolesPaginas));
    }

    public RolesPaginas find(Object id) {
        return em.find(RolesPaginas.class, id);
    }

    public List<RolesPaginas> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(RolesPaginas.class));
        return em.createQuery(cq).getResultList();
    }

    public List<RolesPaginas> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(RolesPaginas.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<RolesPaginas> rt = cq.from(RolesPaginas.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
   private List<RolesPaginas> l;
    public List<RolesPaginas> getRolPaginaByRolUsario(RolesUsuario grupo) {
            try{
                TypedQuery<RolesPaginas> query=em.createQuery("SELECT r FROM RolesPaginas r WHERE r.rolId = :id", RolesPaginas.class)
                .setParameter("id", grupo.getRolId());
                l=query.getResultList();
                return l;
        }catch(Exception e){e.printStackTrace();}
         return null;
    }

}
