/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.libreria.carrito.compras.jpa;

import escom.libreria.administrado.roles.jpa.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author yamil
 */
@Entity
@Table(name = "carritocompra")
@NamedQueries({
    @NamedQuery(name = "Carritocompra.findAll", query = "SELECT c FROM Carritocompra c"),
    @NamedQuery(name = "Carritocompra.findById", query = "SELECT c FROM Carritocompra c WHERE c.id = :id"),
    @NamedQuery(name = "Carritocompra.findByFechaCompra", query = "SELECT c FROM Carritocompra c WHERE c.fechaCompra = :fechaCompra"),
    @NamedQuery(name = "Carritocompra.findByTotal", query = "SELECT c FROM Carritocompra c WHERE c.total = :total"),
    @NamedQuery(name = "Carritocompra.findByEstatus", query = "SELECT c FROM Carritocompra c WHERE c.estatus = :estatus")})
public class Carritocompra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "FECHA_COMPRA")
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;

    @Column(name = "TOTAL")
    private BigDecimal total;
    @Basic(optional = false)
    @Column(name = "ESTATUS")
    private boolean estatus;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
    @ManyToOne
    private Usuario idUsuario;
    @OneToMany(mappedBy = "idCarrito")
    private List<Detallecarritocompra> detallecarritocompraCollection;


    public Carritocompra() {
    }

    public Carritocompra(Integer id) {
        this.id = id;
    }

    public Carritocompra(Integer id, Date fechaCompra, BigDecimal total, boolean estatus) {
        this.id = id;
        this.fechaCompra = fechaCompra;
        this.total = total;
        this.estatus = estatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Detallecarritocompra> getDetallecarritocompraCollection() {
        return detallecarritocompraCollection;
    }

    public void setDetallecarritocompraCollection(List<Detallecarritocompra> detallecarritocompraCollection) {
        this.detallecarritocompraCollection = detallecarritocompraCollection;
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
        if (!(object instanceof Carritocompra)) {
            return false;
        }
        Carritocompra other = (Carritocompra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "escom.libreria.carrito.compras.jpa.Carritocompra[id=" + id + "]";
    }

}
