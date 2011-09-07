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
@Table(name = "roles_paginas")
@NamedQueries({
    @NamedQuery(name = "RolesPaginas.findAll", query = "SELECT r FROM RolesPaginas r"),
    @NamedQuery(name = "RolesPaginas.findById", query = "SELECT r FROM RolesPaginas r WHERE r.id = :id")})
public class RolesPaginas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "ROL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Roles rolId;
    @JoinColumn(name = "PAGINA_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Idpaginas paginaId;

    public RolesPaginas() {
    }

    public RolesPaginas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Roles getRolId() {
        return rolId;
    }

    public void setRolId(Roles rolId) {
        this.rolId = rolId;
    }

    public Idpaginas getPaginaId() {
        return paginaId;
    }

    public void setPaginaId(Idpaginas paginaId) {
        this.paginaId = paginaId;
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
        if (!(object instanceof RolesPaginas)) {
            return false;
        }
        RolesPaginas other = (RolesPaginas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "escom.libreria.administrado.roles.jpa.RolesPaginas[id=" + id + "]";
    }

}
