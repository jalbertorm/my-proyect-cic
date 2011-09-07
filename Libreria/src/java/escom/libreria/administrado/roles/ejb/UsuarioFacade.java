/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.libreria.administrado.roles.ejb;

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
public class UsuarioFacade {
    @PersistenceContext(unitName = "LibreriaPU")
    private EntityManager em;

    public void create(Usuario usuario) {
        em.persist(usuario);
    }

    public void edit(Usuario usuario) {
        em.merge(usuario);
    }

    public void remove(Usuario usuario) {
        em.remove(em.merge(usuario));
    }

    public Usuario find(Object id) {
        return em.find(Usuario.class, id);
    }

    public List<Usuario> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Usuario.class));
        return em.createQuery(cq).getResultList();
    }

    public List<Usuario> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Usuario.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Usuario> rt = cq.from(Usuario.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public Usuario validar(String user, String password) {
                try{
                TypedQuery<Usuario> tq=em.createQuery("SELECT u FROM Usuario u WHERE u.usuario=:u and u.password=:p",Usuario.class).setParameter("u", user).
                setParameter("p", password);

               return  tq.getResultList().get(0);
                }catch(Exception e){e.printStackTrace();}

                return null;
    }

}
