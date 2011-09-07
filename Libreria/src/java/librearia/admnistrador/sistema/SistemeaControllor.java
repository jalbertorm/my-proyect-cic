/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package librearia.admnistrador.sistema;
import java.util.*;
import escom.libreria.administrado.roles.jpa.Idpaginas;
import escom.libreria.administrado.roles.jpa.RolesPaginas;
import escom.libreria.administrado.roles.jpa.RolesUsuario;
import escom.libreria.administrado.roles.jpa.Usuario;
import escom.libreria.administrado.roles.jsf.util.JsfUtil;
import escom.libreria.carrito.compras.ejb.BandejaLibros;
import escom.libreria.carrito.compras.jsf.CarritocompraController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yamil
 */
@ManagedBean(name="sistemeaControllor",eager=true)
@SessionScoped
public class SistemeaControllor {

    /** Creates a new instance of SistemeaControllor */
    @EJB private escom.libreria.administrado.roles.ejb.UsuarioFacade usuarioFacade;
    @EJB private escom.libreria.administrado.roles.ejb.RolesUsuarioFacade rolesusuarioFacade;
    @EJB private escom.libreria.administrado.roles.ejb.RolesPaginasFacade rolespaginaFacade;
    @ManagedProperty("#{carritocompraController}")
    CarritocompraController carritocompraController;
    private HashMap mapaSession;
    private BandejaLibros bandeja;

    public CarritocompraController getCarritocompraController() {
        return carritocompraController;
    }

    public void setCarritocompraController(CarritocompraController carritocompraController) {
        this.carritocompraController = carritocompraController;
    }


    private Usuario usuario;
    private List<Idpaginas>  listaPaginas;/*Lista la acceso a las paginas del usuario*/


    private ExternalContext getContexto(){
          ExternalContext externalContex = FacesContext.getCurrentInstance().getExternalContext();
          return externalContex;
    }
    public String CerrarSession(){
        carritocompraController.setBandeja(null);
        ExternalContext externalContex = getContexto();
        HttpSession session = ( HttpSession)externalContex.getSession(false);
        session.invalidate();
        return "./../index.xhtml";
    }


    private String user,password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public String getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(String user) {
        this.user = user;
    }

    public Usuario getUsuario() {
        return usuario;
    }



    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public SistemeaControllor() {
    }

    public String validar(){

       
        usuario=usuarioFacade.validar(user,password);
        usuario.setId(usuario.getId());
        usuario.setNombre(usuario.getNombre());
        usuario.setPassword(usuario.getPassword());
        usuario.setMaterno(usuario.getMaterno());
        usuario.setAppaterno(usuario.getAppaterno());
        usuario.setCorreo(usuario.getCorreo());
        usuario.setTelefono(usuario.getTelefono());
        usuario.setUsuario(usuario.getUsuario());



       if(usuario!=null){

         setUsuario(usuario);
         listaPaginas=asignarRoles(usuario);
         bandeja =carritocompraController.getNewBandejaLibros();//Garantizamos que se crea un carrito por Session
         setBandeja(bandeja);
       }
        else{
           JsfUtil.addErrorMessage("Usted no hes usuario");
           return "./../index.xhtml";
       }
        user="";password="";
        return "/templates/index";
     
    }


    private List<Idpaginas> asignarRoles(Usuario user){
         List<Idpaginas> l=new ArrayList<Idpaginas>();
         List<RolesUsuario>listaRolesUsuario= rolesusuarioFacade.getRolesUsuario(user);

           for(RolesUsuario grupo:listaRolesUsuario){
                   List<RolesPaginas> rolpagina=rolespaginaFacade.getRolPaginaByRolUsario(grupo);
                   for(RolesPaginas rolpage:rolpagina)
                    l.add(rolpage.getPaginaId());
          }
           return l;
    }
    public BandejaLibros getBandeja() {
        return bandeja;
    }

    public void setBandeja(BandejaLibros bandeja) {
        this.bandeja = bandeja;
    }


    public boolean paginaAutorizada(int idPagina){

         for(Idpaginas idpaginas:listaPaginas){
             if(idpaginas.getId().equals(idPagina))
              return true;
         }
         return false;
    }
}
