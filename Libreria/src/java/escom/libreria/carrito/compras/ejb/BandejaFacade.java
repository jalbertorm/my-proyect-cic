/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.libreria.carrito.compras.ejb;

import escom.libreria.carrito.compras.jpa.Carritocompra;
import escom.libreria.catalogo.libros.jpa.Catalogolibro;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;

/**
 *
 * @author yamil
 */
@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 30)
public class BandejaFacade implements BandejaLibros{



    List <Catalogolibro> librosToComprar= new ArrayList();;

    public BandejaFacade() {
       
    }

    public void addLibro(Catalogolibro libro) {

       librosToComprar.add(libro);
       System.out.println("el tamanio de la lista es"+librosToComprar.size());
    }

    public void removeLibro(Catalogolibro libro) {
       if(librosToComprar.contains(libro)){
           librosToComprar.remove(libro);
       }else
            throw new UnsupportedOperationException("El libro no se elimino porque no se encuentra en el canasto.");
    }

    public BigDecimal doCalculoPrecioGlobal() {
        
        BigDecimal total=new BigDecimal("0.0");
        for(int i=0;i<librosToComprar.size();i++){
            Catalogolibro libro=librosToComprar.get(i);
            total=total.add(libro.getPrecio());
        }
        return total;
        
    }

    public void doConfirmacionLibros() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Catalogolibro> getListLibro() {
       return librosToComprar;
    }

    

}
