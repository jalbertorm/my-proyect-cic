package escom.libreria.carrito.compras.jsf;

import escom.libreria.administrado.roles.jpa.Usuario;
import escom.libreria.carrito.compras.jpa.Detallecarritocompra;
import escom.libreria.carrito.compras.jsf.util.JsfUtil;
import escom.libreria.carrito.compras.jsf.util.PaginationHelper;
import escom.libreria.carrito.compras.ejb.DetallecarritocompraFacade;
import escom.libreria.carrito.compras.jpa.Carritocompra;
import escom.libreria.catalogo.libros.jpa.Catalogolibro;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.persistence.metamodel.ListAttribute;

@ManagedBean (name="detallecarritocompraController")
@SessionScoped
public class DetallecarritocompraController {

    private Detallecarritocompra current;
    private DataModel items = null;
    @EJB private escom.libreria.carrito.compras.ejb.DetallecarritocompraFacade ejbFacade;
    @EJB private escom.libreria.carrito.compras.ejb.CarritocompraFacade carritocompraFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private int contador=1;

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public DetallecarritocompraController() {
    }

    public Detallecarritocompra getSelected() {
        if (current == null) {
            current = new Detallecarritocompra();
            selectedItemIndex = -1;
        }
        return current;
    }

    private DetallecarritocompraFacade getFacade() {
        return ejbFacade;
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

    public List<Detallecarritocompra> getListdetalleCarrito() {
        return listdetalleCarrito;
    }

    public void setListdetalleCarrito(List<Detallecarritocompra> listdetalleCarrito) {
        this.listdetalleCarrito = listdetalleCarrito;
    }


    private List<Detallecarritocompra> listdetalleCarrito;
    
    public String preparaLista(Carritocompra carrito){

       listdetalleCarrito=ejbFacade.buscarCarrito(carrito);

       return "/detallecarritocompra/List";
    }
   



    public String prepareView() {
        current = (Detallecarritocompra)getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Detallecarritocompra();
        selectedItemIndex = -1;
        return "/carritocompra/CatalogoLibro";
    }

    public String create(Catalogolibro libro,Carritocompra carrito) {

        try {
            current=current==null?new Detallecarritocompra():current;
            current.setIdLibro(libro);
            current.setIdCarrito(carrito);
            current.setContador(contador+"");
            BigDecimal total=libro.getPrecio().multiply(new BigDecimal(contador));
            carrito.setTotal(carrito.getTotal().add(total));
            getFacade().create(current);
            //carritocompraFacade.edit(carrito);
            JsfUtil.addSuccessMessage(("Libro Agregado correctamente"));
            return prepareCreate();
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit(Detallecarritocompra  detalle) {
        current = detalle;///(Detallecarritocompra)getItems().getRowData();
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/detallecarritocompra/Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetallecarritocompraUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy(Detallecarritocompra detalle,Carritocompra carrito) {
        current = detalle;//(Detallecarritocompra)getItems().getRowData();
       // selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        //performDestroy();
       // recreateModel();
        if(listdetalleCarrito.contains(detalle)){
           listdetalleCarrito.remove(detalle);

           BigDecimal total=detalle.getIdLibro().getPrecio().multiply(new BigDecimal(Integer.parseInt(detalle.getContador())));
           carrito.setTotal(carrito.getTotal().subtract(total));
           carritocompraFacade.edit(carrito);
           ejbFacade.remove(detalle);
           JsfUtil.addSuccessMessage("Libro Eliminado");
        }

        return "/detallecarritocompra/List";
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
            JsfUtil.addSuccessMessage(("DetallecarritocompraDeleted"));
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

    @FacesConverter(forClass=Detallecarritocompra.class)
    public static class DetallecarritocompraControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetallecarritocompraController controller = (DetallecarritocompraController)facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detallecarritocompraController");
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
            if (object instanceof Detallecarritocompra) {
                Detallecarritocompra o = (Detallecarritocompra) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: "+DetallecarritocompraController.class.getName());
            }
        }

    }

}
