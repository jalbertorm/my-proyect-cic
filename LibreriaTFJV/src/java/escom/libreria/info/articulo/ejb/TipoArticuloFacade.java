/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.libreria.info.articulo.ejb;

import escom.libreria.info.articulo.jpa.TipoArticulo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xxx
 */
@Stateless
public class TipoArticuloFacade extends AbstractFacade<TipoArticulo> {
    @PersistenceContext(unitName = "LibreriaTFJVPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoArticuloFacade() {
        super(TipoArticulo.class);
    }

}
