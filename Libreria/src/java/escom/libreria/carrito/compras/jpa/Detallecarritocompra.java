/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.libreria.carrito.compras.jpa;

import escom.libreria.catalogo.libros.jpa.Catalogolibro;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author yamil
 */
@Entity
@Table(name = "detallecarritocompra")
@NamedQueries({
    @NamedQuery(name = "Detallecarritocompra.findAll", query = "SELECT d FROM Detallecarritocompra d"),
    @NamedQuery(name = "Detallecarritocompra.findById", query = "SELECT d FROM Detallecarritocompra d WHERE d.id = :id"),
    @NamedQuery(name = "Detallecarritocompra.findByContador", query = "SELECT d FROM Detallecarritocompra d WHERE d.contador = :contador")})
public class Detallecarritocompra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CONTADOR")
    private String contador;
    @JoinColumn(name = "ID_CARRITO", referencedColumnName = "ID")
    @ManyToOne
    private Carritocompra idCarrito;
    @JoinColumn(name = "ID_LIBRO", referencedColumnName = "ID")
    @ManyToOne
    private Catalogolibro idLibro;

    public Detallecarritocompra() {
    }

    public Detallecarritocompra(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContador() {
        return contador;
    }

    public void setContador(String contador) {
        this.contador = contador;
    }

    public Carritocompra getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Carritocompra idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Catalogolibro getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Catalogolibro idLibro) {
        this.idLibro = idLibro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallecarritocompra)) {
            return false;
        }
        Detallecarritocompra other = (Detallecarritocompra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "escom.libreria.carrito.compras.Detallecarritocompra[id=" + id + "]";
    }

}
