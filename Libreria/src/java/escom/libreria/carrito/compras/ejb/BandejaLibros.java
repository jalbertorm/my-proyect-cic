/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.libreria.carrito.compras.ejb;

import escom.libreria.carrito.compras.jpa.Carritocompra;
import escom.libreria.catalogo.libros.jpa.Catalogolibro;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author yamil
 */
@Local
public interface  BandejaLibros {

    void addLibro(Catalogolibro libro);
    void removeLibro(Catalogolibro libro);
    BigDecimal doCalculoPrecioGlobal();
    void doConfirmacionLibros();
    List<Catalogolibro> getListLibro();
}
