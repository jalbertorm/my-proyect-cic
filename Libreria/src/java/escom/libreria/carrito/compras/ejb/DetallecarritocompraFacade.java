/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.libreria.carrito.compras.ejb;

import escom.libreria.carrito.compras.jpa.Carritocompra;
import escom.libreria.carrito.compras.jpa.Detallecarritocompra;
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
public class DetallecarritocompraFacade {
    @PersistenceContext(unitName = "LibreriaPU")
    private EntityManager em;

    public void create(Detallecarritocompra detallecarritocompra) {
        em.persist(detallecarritocompra);
    }

    public void edit(Detallecarritocompra detallecarritocompra) {
        em.merge(detallecarritocompra);
    }

    public void remove(Detallecarritocompra detallecarritocompra) {
        em.remove(em.merge(detallecarritocompra));
    }

    public Detallecarritocompra find(Object id) {
        return em.find(Detallecarritocompra.class, id);
    }

    public List<Detallecarritocompra> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Detallecarritocompra.class));
        return em.createQuery(cq).getResultList();
    }

    public List<Detallecarritocompra> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Detallecarritocompra.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Detallecarritocompra> rt = cq.from(Detallecarritocompra.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<Detallecarritocompra> buscarCarrito(Carritocompra id) {

        System.out.println("ID CARRITO"+id.getId());
           try{
        TypedQuery<Detallecarritocompra> tq=em.createQuery("SELECT d FROM Detallecarritocompra d WHERE d.idCarrito.id=:id",Detallecarritocompra.class).setParameter("id", id.getId());
        List<Detallecarritocompra> l=tq.getResultList();
        System.out.println("elementos "+l.size());
        return l;
        }catch(Exception e){e.printStackTrace();}

         return null;

        
    }

   
}
