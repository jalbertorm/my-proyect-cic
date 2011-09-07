package escom.libreria.carrito.compras.jsf;

import escom.libreria.administrado.roles.jpa.Usuario;
import escom.libreria.carrito.compras.ejb.BandejaLibros;
import escom.libreria.carrito.compras.jpa.Carritocompra;
import escom.libreria.carrito.compras.jsf.util.JsfUtil;
import escom.libreria.carrito.compras.jsf.util.PaginationHelper;
import escom.libreria.carrito.compras.ejb.CarritocompraFacade;
import escom.libreria.carrito.compras.jpa.Detallecarritocompra;
import escom.libreria.catalogo.libros.jpa.Catalogolibro;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.naming.InitialContext;
import javax.naming.NamingException;

@ManagedBean (name="carritocompraController")
@SessionScoped
public class CarritocompraController {

    private Carritocompra current;
    private DataModel items = null;
    
    @EJB private escom.libreria.administrado.roles.ejb.UsuarioFacade carritocompraFacadeUsuario;
    @EJB private escom.libreria.carrito.compras.ejb.CarritocompraFacade carritocompraFacade;
  


    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Carritocompra carrito;
    private boolean ocultar=false;

    public BandejaLibros getBandeja() {
        return bandeja;
    }

    public void setBandeja(BandejaLibros bandeja) {
        this.bandeja = bandeja;
    }
    private BandejaLibros bandeja=null;

    public CarritocompraController() {
    }

    public Carritocompra getSelected() {
        if (current == null) {
            current = new Carritocompra();
            selectedItemIndex = -1;
        }
        return current;
    }

    private CarritocompraFacade getFacade() {
        return carritocompraFacade;
    }

   

    
     private List<Catalogolibro> listaLibros;

    public List<Catalogolibro> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(List<Catalogolibro> listaLibros) {
        this.listaLibros = listaLibros;
    }
  
    public String crearCarritoCompra(Usuario usuario){

        System.out.println("Entre");
        //Carritocompra carrito=carritocompraFacade.getCarritoActivo(usuario);
         carrito= carritocompraFacade.getCarritoActivo(usuario);
        
         System.out.println("Carrito"+carrito);

       if(carrito==null){
          
            carrito=new Carritocompra();
            carrito.setIdUsuario(usuario);
            carrito.setFechaCompra(new Date());
            carrito.setEstatus(true);
            carrito.setTotal(BigDecimal.ZERO);
            ocultar=true;
            carritocompraFacade.create(carrito);
            JsfUtil.addSuccessMessage("Carrito Creado Satisfactoriamente");


        }else{
             System.out.println("Usted tiene un carrito de compra activado");
            JsfUtil.addSuccessMessage("Usted tiene un carrito de compra activado");
          
            listaLibros=bandeja.getListLibro();

            if(carrito.getTotal()==null)
              carrito.setTotal(BigDecimal.ZERO);
              carrito.setTotal(bandeja.doCalculoPrecioGlobal().add(carrito.getTotal()));
            return "/carritocompra/List";
        }
        //current=carrito;/*Copiando la referencia*/
        return "/carritocompra/CatalogoLibro";

    }

   
    public BandejaLibros getNewBandejaLibros(){
        bandeja=carritocompraFacade.getBandejaLibros();
        return bandeja;

    }
    public void agregarToBandeja(Catalogolibro libro){
        //bandeja=getBandejaLibros();
        if(libro!=null){
          bandeja.addLibro(libro);
          JsfUtil.addSuccessMessage("Libro agregado satisfactoriamente");
        }
       // return "/carritocompra/List";

    }

    public void eliminarFromBandeja(Catalogolibro libro){
        //bandeja=getBandejaLibros();
        
        bandeja.removeLibro(libro);
        carrito.setTotal(carrito.getTotal().subtract(libro.getPrecio()));
        JsfUtil.addSuccessMessage("Libro eliminado satisfactoriamente");

    }

   

    public Carritocompra getCarrito() {
        return carrito;
    }

    public void setCarrito(Carritocompra carrito) {
        this.carrito = carrito;
    }




    public boolean isOcultar() {
        return ocultar;
    }

    public void setOcultar(boolean ocultar) {
        this.ocultar = ocultar;
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

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Carritocompra)getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Carritocompra();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CarritocompraCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Carritocompra)getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CarritocompraUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy(Carritocompra item) {
        current = item;//(Carritocompra)getItems().getRowData();
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        //performDestroy();
        //recreateModel();
        JsfUtil.addErrorMessage("El carrito de compra a sido eliminado");
        carritocompraFacade.remove(carrito);
        return "/carritocompra/List";
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CarritocompraDeleted"));
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
        return JsfUtil.getSelectItems(carritocompraFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(carritocompraFacade.findAll(), true);
    }

    @FacesConverter(forClass=Carritocompra.class)
    public static class CarritocompraControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CarritocompraController controller = (CarritocompraController)facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "carritocompraController");
            return controller.carritocompraFacade.find(getKey(value));
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
            if (object instanceof Carritocompra) {
                Carritocompra o = (Carritocompra) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: "+CarritocompraController.class.getName());
            }
        }

    }

}
