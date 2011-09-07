/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.libreria.catalogo.libros.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author yamil
 */
@Entity
@Table(name = "catalogolibro")
@NamedQueries({
    @NamedQuery(name = "Catalogolibro.findAll", query = "SELECT c FROM Catalogolibro c"),
    @NamedQuery(name = "Catalogolibro.findById", query = "SELECT c FROM Catalogolibro c WHERE c.id = :id"),
    @NamedQuery(name = "Catalogolibro.findByAsunto", query = "SELECT c FROM Catalogolibro c WHERE c.asunto = :asunto"),
    @NamedQuery(name = "Catalogolibro.findByAudiencia", query = "SELECT c FROM Catalogolibro c WHERE c.audiencia = :audiencia"),
    @NamedQuery(name = "Catalogolibro.findByCobertura", query = "SELECT c FROM Catalogolibro c WHERE c.cobertura = :cobertura"),
    @NamedQuery(name = "Catalogolibro.findByColaboracion", query = "SELECT c FROM Catalogolibro c WHERE c.colaboracion = :colaboracion"),
    @NamedQuery(name = "Catalogolibro.findByCreador", query = "SELECT c FROM Catalogolibro c WHERE c.creador = :creador"),
    @NamedQuery(name = "Catalogolibro.findByDerechos", query = "SELECT c FROM Catalogolibro c WHERE c.derechos = :derechos"),
    @NamedQuery(name = "Catalogolibro.findByEditor", query = "SELECT c FROM Catalogolibro c WHERE c.editor = :editor"),
    @NamedQuery(name = "Catalogolibro.findByFechaPublicacion", query = "SELECT c FROM Catalogolibro c WHERE c.fechaPublicacion = :fechaPublicacion"),
    @NamedQuery(name = "Catalogolibro.findByFormato", query = "SELECT c FROM Catalogolibro c WHERE c.formato = :formato"),
    @NamedQuery(name = "Catalogolibro.findByFuente", query = "SELECT c FROM Catalogolibro c WHERE c.fuente = :fuente"),
    @NamedQuery(name = "Catalogolibro.findByIdentificador", query = "SELECT c FROM Catalogolibro c WHERE c.identificador = :identificador"),
    @NamedQuery(name = "Catalogolibro.findByIdioma", query = "SELECT c FROM Catalogolibro c WHERE c.idioma = :idioma"),
    @NamedQuery(name = "Catalogolibro.findByMetodoAcumulacion", query = "SELECT c FROM Catalogolibro c WHERE c.metodoAcumulacion = :metodoAcumulacion"),
    @NamedQuery(name = "Catalogolibro.findByMetodoInstruccion", query = "SELECT c FROM Catalogolibro c WHERE c.metodoInstruccion = :metodoInstruccion"),
    @NamedQuery(name = "Catalogolibro.findByPeriodicidad", query = "SELECT c FROM Catalogolibro c WHERE c.periodicidad = :periodicidad"),
    @NamedQuery(name = "Catalogolibro.findByPoliticaPeriodificacion", query = "SELECT c FROM Catalogolibro c WHERE c.politicaPeriodificacion = :politicaPeriodificacion"),
    @NamedQuery(name = "Catalogolibro.findByProcedencia", query = "SELECT c FROM Catalogolibro c WHERE c.procedencia = :procedencia"),
    @NamedQuery(name = "Catalogolibro.findByRelacion", query = "SELECT c FROM Catalogolibro c WHERE c.relacion = :relacion"),
    @NamedQuery(name = "Catalogolibro.findByTitulo", query = "SELECT c FROM Catalogolibro c WHERE c.titulo = :titulo"),
    @NamedQuery(name = "Catalogolibro.findByTipo", query = "SELECT c FROM Catalogolibro c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "Catalogolibro.findByTitularDerechos", query = "SELECT c FROM Catalogolibro c WHERE c.titularDerechos = :titularDerechos"),
    @NamedQuery(name = "Catalogolibro.findByUrlImagen", query = "SELECT c FROM Catalogolibro c WHERE c.urlImagen = :urlImagen"),
    @NamedQuery(name = "Catalogolibro.findByurlLibro", query = "SELECT c FROM Catalogolibro c WHERE c.urlLibro = :urlLibro")})
public class Catalogolibro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ASUNTO")
    private String asunto;
    @Column(name = "AUDIENCIA")
    private String audiencia;
    @Basic(optional = false)
    @Column(name = "COBERTURA")
    private String cobertura;
    @Column(name = "COLABORACION")
    private String colaboracion;
    @Basic(optional = false)
    @Column(name = "CREADOR")
    private String creador;
    @Basic(optional = false)
    @Column(name = "DERECHOS")
    private String derechos;
    @Basic(optional = false)
    @Column(name = "EDITOR")
    private String editor;
    @Basic(optional = false)
    @Column(name = "FECHA_PUBLICACION")
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;
    @Column(name = "FORMATO")
    private String formato;
    @Basic(optional = false)
    @Column(name = "FUENTE")
    private String fuente;
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private String identificador;
    @Basic(optional = false)
    @Column(name = "IDIOMA")
    private String idioma;
    @Column(name = "METODO_ACUMULACION")
    private String metodoAcumulacion;
    @Column(name = "METODO_INSTRUCCION")
    private String metodoInstruccion;
    @Column(name = "PERIODICIDAD")
    private String periodicidad;
    @Column(name = "POLITICA_PERIODIFICACION")
    private String politicaPeriodificacion;
    @Basic(optional = false)
    @Column(name = "PROCEDENCIA")
    private String procedencia;
    @Basic(optional = false)
    @Column(name = "RELACION")
    private String relacion;
    @Basic(optional = false)
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "TIPO")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "TITULAR_DERECHOS")
    private String titularDerechos;
    @Column(name = "URL_IMAGEN")
    private String urlImagen;
    @Column(name = "URL_LIBRO")
    private String urlLibro;
    @Column(name = "URL_RESUMEN")
    private String urlResumen;
    @Column(name = "URL_PORTADA")
    private String urlPortada;
    @Column(name = "URL_CONTRAPORTADA")
    private String urlContraportada;
    @Column(name = "URL")
    private String url;
    @Basic(optional = true)
    @Column(name = "PRECIO")
    private BigDecimal precio;

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getUrlLibros() {
        return urlLibro;
    }

    public void setUrlLibros(String urlLibros) {
        this.urlLibro = urlLibros;
    }
    

    


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlContraportada() {
        return urlContraportada;
    }

    public void setUrlContraportada(String urlContraportada) {
        this.urlContraportada = urlContraportada;
    }

    public String getUrlPortada() {
        return urlPortada;
    }

    public void setUrlPortada(String urlPortada) {
        this.urlPortada = urlPortada;
    }

    public String getUrlResumen() {
        return urlResumen;
    }

    public void setUrlResumen(String urlResumen) {
        this.urlResumen = urlResumen;
    }



    public Catalogolibro() {
    }

    public Catalogolibro(Integer id) {
        this.id = id;
    }

    public Catalogolibro(Integer id, String asunto, String cobertura, String creador, String derechos, String editor, Date fechaPublicacion, String fuente, String identificador, String idioma, String procedencia, String relacion, String titulo, String titularDerechos) {
        this.id = id;
        this.asunto = asunto;
        this.cobertura = cobertura;
        this.creador = creador;
        this.derechos = derechos;
        this.editor = editor;
        this.fechaPublicacion = fechaPublicacion;
        this.fuente = fuente;
        this.identificador = identificador;
        this.idioma = idioma;
        this.procedencia = procedencia;
        this.relacion = relacion;
        this.titulo = titulo;
        this.titularDerechos = titularDerechos;
    }

    public Catalogolibro(Integer id, String asunto, String audiencia, String cobertura, String colaboracion, String creador, String derechos, String editor, Date fechaPublicacion, String formato, String fuente, String identificador, String idioma, String metodoAcumulacion, String metodoInstruccion, String periodicidad, String politicaPeriodificacion, String procedencia, String relacion, String titulo, String tipo, String titularDerechos, String urlImagen, String urlLibro, String urlResumen, String urlPortada, String urlContraportada) {
        this.id = id;
        this.asunto = asunto;
        this.audiencia = audiencia;
        this.cobertura = cobertura;
        this.colaboracion = colaboracion;
        this.creador = creador;
        this.derechos = derechos;
        this.editor = editor;
        this.fechaPublicacion = fechaPublicacion;
        this.formato = formato;
        this.fuente = fuente;
        this.identificador = identificador;
        this.idioma = idioma;
        this.metodoAcumulacion = metodoAcumulacion;
        this.metodoInstruccion = metodoInstruccion;
        this.periodicidad = periodicidad;
        this.politicaPeriodificacion = politicaPeriodificacion;
        this.procedencia = procedencia;
        this.relacion = relacion;
        this.titulo = titulo;
        this.tipo = tipo;
        this.titularDerechos = titularDerechos;
        this.urlImagen = urlImagen;
        this.urlLibro = urlLibro;
        this.urlResumen = urlResumen;
        this.urlPortada = urlPortada;
        this.urlContraportada = urlContraportada;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getAudiencia() {
        return audiencia;
    }

    public void setAudiencia(String audiencia) {
        this.audiencia = audiencia;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public String getColaboracion() {
        return colaboracion;
    }

    public void setColaboracion(String colaboracion) {
        this.colaboracion = colaboracion;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getDerechos() {
        return derechos;
    }

    public void setDerechos(String derechos) {
        this.derechos = derechos;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getMetodoAcumulacion() {
        return metodoAcumulacion;
    }

    public void setMetodoAcumulacion(String metodoAcumulacion) {
        this.metodoAcumulacion = metodoAcumulacion;
    }

    public String getMetodoInstruccion() {
        return metodoInstruccion;
    }

    public void setMetodoInstruccion(String metodoInstruccion) {
        this.metodoInstruccion = metodoInstruccion;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    public String getPoliticaPeriodificacion() {
        return politicaPeriodificacion;
    }

    public void setPoliticaPeriodificacion(String politicaPeriodificacion) {
        this.politicaPeriodificacion = politicaPeriodificacion;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrlLibro() {
        return urlLibro;
    }

    public void setUrlLibro(String urlLibro) {
        this.urlLibro = urlLibro;
    }

    public String getTitularDerechos() {
        return titularDerechos;
    }

    public void setTitularDerechos(String titularDerechos) {
        this.titularDerechos = titularDerechos;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String geturlLibro() {
        return urlLibro;
    }

    public void seturlLibro(String urlLibro) {
        this.urlLibro = urlLibro;
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
        if (!(object instanceof Catalogolibro)) {
            return false;
        }
        Catalogolibro other = (Catalogolibro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "escom.libreria.catalogo.libros.Catalogolibro[id=" + id + "]";
    }

}
