/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.libreria.administrado.roles.jpa;

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
@Table(name = "roles_usuario")
@NamedQueries({
    @NamedQuery(name = "RolesUsuario.findAll", query = "SELECT r FROM RolesUsuario r"),
    @NamedQuery(name = "RolesUsuario.findByUsuario", query = "SELECT r FROM RolesUsuario r WHERE r.usuario = :usuario"),
    @NamedQuery(name = "RolesUsuario.findById", query = "SELECT r FROM RolesUsuario r WHERE r.id = :id"),
    @NamedQuery(name = "RolesUsuario.findByRol", query = "SELECT r FROM RolesUsuario r WHERE r.rol = :rol")})
public class RolesUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "USUARIO")
    private String usuario;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ROL")
    private String rol;
    @JoinColumn(name = "ROL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Roles rolId;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public RolesUsuario() {
    }

    public RolesUsuario(Integer id) {
        this.id = id;
    }

    public RolesUsuario(Integer id, String usuario, String rol) {
        this.id = id;
        this.usuario = usuario;
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Roles getRolId() {
        return rolId;
    }

    public void setRolId(Roles rolId) {
        this.rolId = rolId;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
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
        if (!(object instanceof RolesUsuario)) {
            return false;
        }
        RolesUsuario other = (RolesUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "escom.libreria.administrado.roles.jpa.RolesUsuario[id=" + id + "]";
    }

}
