/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.libreria.administrado.roles.ejb;

import escom.libreria.administrado.roles.jpa.RolesUsuario;
import escom.libreria.administrado.roles.jpa.Usuario;
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
public class RolesUsuarioFacade {
    @PersistenceContext(unitName = "LibreriaPU")
    private EntityManager em;

    public void create(RolesUsuario rolesUsuario) {
        em.persist(rolesUsuario);
    }

    public void edit(RolesUsuario rolesUsuario) {
        em.merge(rolesUsuario);
    }

    public void remove(RolesUsuario rolesUsuario) {
        em.remove(em.merge(rolesUsuario));
    }

    public RolesUsuario find(Object id) {
        return em.find(RolesUsuario.class, id);
    }

    public List<RolesUsuario> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(RolesUsuario.class));
        return em.createQuery(cq).getResultList();
    }

    public List<RolesUsuario> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(RolesUsuario.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<RolesUsuario> rt = cq.from(RolesUsuario.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
      private List<RolesUsuario> l;
    public List<RolesUsuario> getRolesUsuario(Usuario usuario) {
        try{
        TypedQuery<RolesUsuario> query=em.createQuery("SELECT r FROM RolesUsuario r WHERE r.idUsuario=:usuario", RolesUsuario.class).setParameter("usuario", usuario);
        l=query.getResultList();
        return l;
        }catch(Exception e){e.printStackTrace();}

        return null;
    }

}
