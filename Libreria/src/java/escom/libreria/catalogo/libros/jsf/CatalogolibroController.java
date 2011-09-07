package escom.libreria.catalogo.libros.jsf;

import escom.libreria.catalogo.libros.jpa.Catalogolibro;
import escom.libreria.catalogo.libros.jsf.util.JsfUtil;
import escom.libreria.catalogo.libros.jsf.util.PaginationHelper;
import escom.libreria.catalogo.libros.ejb.CatalogolibroFacade;
import java.math.BigDecimal;
import java.util.List;

import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean (name="catalogolibroController")
@SessionScoped
public class CatalogolibroController {

    private Catalogolibro current;
    private DataModel items = null;
    private boolean bandera=true;//activada;
    @EJB private escom.libreria.catalogo.libros.ejb.CatalogolibroFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    private String autor,titulo,palabraClava;
    private List<Catalogolibro> librosBusqueda;
    private boolean ocultar;

    public boolean isOcultar() {
       
        return ocultar;
    }
    public String getDireccionar(){

        System.out.println("ocultar"+ocultar);
        if(ocultar==false){
         return "Portada";
        }else
            ocultar=false;
        return "id";


    }

    public void setOcultar(boolean ocultar) {
        this.ocultar = ocultar;
    }


    public List<Catalogolibro> getLibrosBusqueda() {
        if(librosBusqueda==null){
            librosBusqueda=ejbFacade.findAll();
        }
        return librosBusqueda;
    }

    public void setLibrosBusqueda(List<Catalogolibro> librosBusqueda) {
        this.librosBusqueda = librosBusqueda;
    }

    public boolean isBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    
    
   

    public String buscarLibro(){

        autor=autor==null?" ":autor;
        titulo=titulo==null?" ":titulo;
        palabraClava=palabraClava==null?" ":palabraClava;

        System.out.println("palabraClave"+palabraClava+"Autor"+autor+"titulo"+titulo);

         librosBusqueda=ejbFacade.getBuscarLibro(autor, titulo, palabraClava);
        return "/carritocompra/CatalogoLibro";
    }


    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPalabraClava() {
        return palabraClava;
    }

    public void setPalabraClava(String palabraClava) {
        this.palabraClava = palabraClava;
    }



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    

    public CatalogolibroController() {
    }

    public Catalogolibro getSelected() {
        if (current == null) {
            current = new Catalogolibro();
            selectedItemIndex = -1;
        }
        return current;
    }

    private CatalogolibroFacade getFacade() {
        return ejbFacade;
    }
    public List<Catalogolibro> getLibros(){
        return ejbFacade.findAll();
    }
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem()+getPageSize()}));
                }
            };
        }
        return pagination;
    }


    public List<String> complete(String query){
        return ejbFacade.getTitleLibros(query);
    }
    public String prepareList() {
        recreateModel();
        return "/catalogolibro/List";
    }

    public String prepareView(Catalogolibro libro) {
        current =libro; //(Catalogolibro)getItems().getRowData();
        System.out.println("entre aki");
       // selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Catalogolibro();
        selectedItemIndex = -1;
        return "/catalogolibro/Create";
    }

    public String create() {
        bandera=isBandera();

        System.out.println(bandera);
        try {
            if(bandera){
                current.setUrl("resources/libros/");
                current.setUrlImagen("puerquito.jpg");
                current.setTipo("default");
                current.setUrlLibros("default");
                current.setUrlResumen("default");
                current.setUrlPortada("default");
                current.setUrlContraportada("default");
                current.setFormato("default");
                bandera=false;
                return "/catalogolibro/Create";//prepareCreate();

            }

            else
            {

                current.setAsunto(current.getAsunto());
                current.setAudiencia( current.getAudiencia());
                current.setCobertura( current.getCobertura());
                current.setColaboracion( current.getColaboracion());
                current.setCreador( current.getCreador());
                current.setDerechos( current.getDerechos());
                current.setEditor( current.getEditor());
                current.setFechaPublicacion(current.getFechaPublicacion());
                current.setFormato(current.getFormato());
                current.setFuente(current.getFuente());
                current.setIdentificador(current.getIdentificador());
                current.setIdioma(current.getIdioma());
                current.setMetodoAcumulacion(current.getMetodoAcumulacion());
                current.setPeriodicidad(current.getPeriodicidad());
                current.setPoliticaPeriodificacion(current.getPoliticaPeriodificacion());
                current.setProcedencia(current.getProcedencia());
                current.setPrecio(current.getPrecio());
                current.setRelacion(current.getRelacion());
                current.setTipo(current.getTipo());
                current.setTitularDerechos(current.getTitularDerechos());
                current.setTitulo(current.getTitulo());
                current.setUrl(current.getUrl());
                current.setUrlImagen(current.getUrlImagen());



            getFacade().create(current);
            bandera=true;
            
            JsfUtil.addSuccessMessage(("Libro Creado Satisfactoriamente"));
            return "/catalogolibro/List";//prepareCreate();
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit(Catalogolibro libro) {
        bandera=true;
        current = libro;//(Catalogolibro)getItems().getRowData();
       // selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/catalogolibro/Edit";
    }

    public String update() {
        try {

                current.setAsunto(current.getAsunto());
                current.setAudiencia( current.getAudiencia());
                current.setCobertura( current.getCobertura());
                current.setColaboracion( current.getColaboracion());
                current.setCreador( current.getCreador());
                current.setDerechos( current.getDerechos());
                current.setEditor( current.getEditor());
                current.setFechaPublicacion(current.getFechaPublicacion());
                current.setFormato(current.getFormato());
                current.setFuente(current.getFuente());
                current.setIdentificador(current.getIdentificador());
                current.setIdioma(current.getIdioma());
                current.setMetodoAcumulacion(current.getMetodoAcumulacion());
                current.setPeriodicidad(current.getPeriodicidad());
                current.setPoliticaPeriodificacion(current.getPoliticaPeriodificacion());
                current.setProcedencia(current.getProcedencia());
                current.setPrecio(current.getPrecio());
                current.setRelacion(current.getRelacion());
                current.setTipo(current.getTipo());
                current.setTitularDerechos(current.getTitularDerechos());
                current.setTitulo(current.getTitulo());
                current.setUrl(current.getUrl());
                current.setUrlImagen(current.getUrlImagen());

                if(bandera==false){

                 bandera=true;
                 getFacade().edit(current);
                 JsfUtil.addSuccessMessage(("CatalogolibroUpdated"));
                 return "/catalogolibro/View";

                }
                 if(bandera==true)bandera=false;
                return "/catalogolibro/Edit";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy(Catalogolibro libro) {
        current = libro;//(Catalogolibro)getItems().getRowData();
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();

        //performDestroy();
        //recreateModel();
        ejbFacade.remove(libro);
        JsfUtil.addSuccessMessage("Libro eliminado");
        return "/catalogolibro/List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CatalogolibroDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count-1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex+1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass=Catalogolibro.class)
    public static class CatalogolibroControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CatalogolibroController controller = (CatalogolibroController)facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "catalogolibroController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Catalogolibro) {
                Catalogolibro o = (Catalogolibro) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: "+CatalogolibroController.class.getName());
            }
        }

    }

}
