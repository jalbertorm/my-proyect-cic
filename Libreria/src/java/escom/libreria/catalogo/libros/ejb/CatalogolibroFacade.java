/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.libreria.catalogo.libros.ejb;

import escom.libreria.catalogo.libros.jpa.Catalogolibro;
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
public class CatalogolibroFacade {
    @PersistenceContext(unitName = "LibreriaPU")
    private EntityManager em;

    public void create(Catalogolibro catalogolibro) {
        em.persist(catalogolibro);
    }

    public void edit(Catalogolibro catalogolibro) {
        em.merge(catalogolibro);
    }

    public void remove(Catalogolibro catalogolibro) {
        em.remove(em.merge(catalogolibro));
    }

    public Catalogolibro find(Object id) {
        return em.find(Catalogolibro.class, id);
    }

    public List<Catalogolibro> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Catalogolibro.class));
        return em.createQuery(cq).getResultList();
    }

    public List<Catalogolibro> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Catalogolibro.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Catalogolibro> rt = cq.from(Catalogolibro.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<Catalogolibro> getBuscarLibro(String autor,String titulo,String palabra){

                   try{
                TypedQuery<Catalogolibro> tipo=em.createQuery(
                "SELECT c FROM Catalogolibro c WHERE c.titulo like :titulo or c.cobertura like :palabra or c.creador like :autor"
                ,Catalogolibro.class).setParameter("autor","%"+autor+"%")
                .setParameter("titulo","%"+titulo+"%").setParameter("palabra", "%"+palabra+"%");
                List<Catalogolibro> l =tipo.getResultList();
                return l;
                }catch(Exception e){
                  e.printStackTrace();
                }
                   return null;
    }

     public List<String> getTitleLibros(String titulo){

                   try{
                    TypedQuery<String> tipo=em.createQuery(
                    "SELECT c.titulo FROM Catalogolibro c WHERE c.titulo like :titulo "
                    ,String.class).setParameter("titulo","%"+titulo+"%");
                    List<String> l =tipo.getResultList();
                    return l;
                }catch(Exception e){
                  e.printStackTrace();
                }
                   return null;
    }
     public List<Catalogolibro> getTopTen(){

               TypedQuery<Catalogolibro> tipo=em.createQuery(
                    "SELECT c FROM Catalogolibro c order by c.id DESC"
                    ,Catalogolibro.class);
                  List<Catalogolibro> l=tipo.getResultList();
                  return l;

     }

}
