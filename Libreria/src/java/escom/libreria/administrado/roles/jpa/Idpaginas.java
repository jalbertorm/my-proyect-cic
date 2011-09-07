/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.libreria.administrado.roles.jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author yamil
 */
@Entity
@Table(name = "idpaginas")
@NamedQueries({
    @NamedQuery(name = "Idpaginas.findAll", query = "SELECT i FROM Idpaginas i"),
    @NamedQuery(name = "Idpaginas.findById", query = "SELECT i FROM Idpaginas i WHERE i.id = :id"),
    @NamedQuery(name = "Idpaginas.findByNombre", query = "SELECT i FROM Idpaginas i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Idpaginas.findByDescripcion", query = "SELECT i FROM Idpaginas i WHERE i.descripcion = :descripcion")})
public class Idpaginas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paginaId")
    private List<RolesPaginas> rolesPaginasList;

    public Idpaginas() {
    }

    public Idpaginas(Integer id) {
        this.id = id;
    }

    public Idpaginas(Integer id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<RolesPaginas> getRolesPaginasList() {
        return rolesPaginasList;
    }

    public void setRolesPaginasList(List<RolesPaginas> rolesPaginasList) {
        this.rolesPaginasList = rolesPaginasList;
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
        if (!(object instanceof Idpaginas)) {
            return false;
        }
        Idpaginas other = (Idpaginas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "escom.libreria.administrado.roles.jpa.Idpaginas[id=" + id + "]";
    }

}
