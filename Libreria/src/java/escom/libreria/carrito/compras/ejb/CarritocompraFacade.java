/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.libreria.carrito.compras.ejb;

import escom.libreria.administrado.roles.jpa.Usuario;
import escom.libreria.carrito.compras.jpa.Carritocompra;
import escom.libreria.carrito.compras.jpa.Detallecarritocompra;
import escom.libreria.carrito.compras.jsf.CarritocompraController;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
public class CarritocompraFacade {
    @PersistenceContext(unitName = "LibreriaPU")
    private EntityManager em;

    public void create(Carritocompra carritocompra) {
        em.persist(carritocompra);
    }

    public void edit(Carritocompra carritocompra) {
        em.merge(carritocompra);
    }

    public void remove(Carritocompra carritocompra) {
        em.remove(em.merge(carritocompra));
    }

    public Carritocompra find(Object id) {
        return em.find(Carritocompra.class, id);
    }

    public List<Carritocompra> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Carritocompra.class));
        return em.createQuery(cq).getResultList();
    }

    public List<Carritocompra> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Carritocompra.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Carritocompra> rt = cq.from(Carritocompra.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }


    public Carritocompra getCarritoActivo(Usuario usuario){
        boolean estauts=true;
        Carritocompra carrito=null;

        try{
            TypedQuery<Carritocompra> tq=em.createQuery("SELECT c FROM Carritocompra c where c.idUsuario=:user and c.estatus=:estatus",Carritocompra.class)
            .setParameter("user", usuario)
            .setParameter("estatus", estauts);
             carrito=tq.getResultList().get(0);
            
        
        }catch(Exception e){e.printStackTrace();}

        return carrito;
    }

     public  BandejaLibros getBandejaLibros(){
        BandejaLibros bandeja=null;
        try {
            InitialContext contex = new InitialContext();
            bandeja = (BandejaLibros) contex.lookup("java:global/Libreria/BandejaFacade!escom.libreria.carrito.compras.ejb.BandejaLibros");
            return bandeja;
        } catch (NamingException ex) {
            Logger.getLogger(CarritocompraController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandeja;
    }

}
