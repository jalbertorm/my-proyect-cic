/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escom.libreria.catalogo.libros.jsf;



/**
 *
 * @author yamil
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import escom.libreria.catalogo.libros.jsf.util.JsfUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


/**
 *
 * @author yamil
 */

@ManagedBean(name="subirPDF")
@SessionScoped


public class SubirPDF {


    private String urlFile="C:/Users/yamil/Documents/NetBeansProjects/Libreria/web/resources/libros/";

    private String virtual="resources/libros/";

    private static final int BUFFER_SIZE = 6124;

    private StreamedContent imagemEnviada = new DefaultStreamedContent();
    private String imagemTemporaria;
    private CroppedImage croppedImage;
    private boolean exibeBotao = false;


    @ManagedProperty("#{catalogolibroController}")
    private CatalogolibroController catalogolibroController;

    

    public CatalogolibroController getCatalogolibroController() {
        return catalogolibroController;
    }

    public void setCatalogolibroController(CatalogolibroController catalogolibroController) {
        this.catalogolibroController = catalogolibroController;
    }


    /** Creates a new instance of SubirPDF */
    public SubirPDF() {
    }

public void criaArquivo(byte[] bytes, String arquivo) {
      FileOutputStream fos;
      try {
         fos = new FileOutputStream(arquivo);
         fos.write(bytes);
         fos.close();
      } catch (FileNotFoundException ex) {
         ex.printStackTrace();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
   }

    public void handleFileUpload0(FileUploadEvent event) {

       byte[] img = event.getFile().getContents();
      imagemTemporaria = event.getFile().getFileName();
      FacesContext facesContext = FacesContext.getCurrentInstance();
      ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
      String arquivo = scontext.getRealPath("/resources/libros/" + imagemTemporaria);
      System.out.println("url"+arquivo);
      criaArquivo(img, arquivo);
      



    }


    public boolean descargarArchivo(FileUploadEvent event) {
       // ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
       System.out.println("Ruta a guardar[ " + urlFile + event.getFile().getFileName());
        try {
            FileOutputStream fileOutputStream = null;
            File result = null;
            result = new File(urlFile + event.getFile().getFileName());
           
            fileOutputStream = new FileOutputStream(result);

            byte[] buffer = new byte[BUFFER_SIZE];
            int bulk;
            InputStream inputStream = event.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                 }
             fileOutputStream.write(buffer, 0, bulk);
             fileOutputStream.flush();
            }
            fileOutputStream.close();
            inputStream.close();
           return true;
        } catch (IOException e) {
        System.out.println("Error handleFileUpload" + e);
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Logo 1 no puede ser cargado", ""));
        return false;
    }
}
    public void handleFileUpload(FileUploadEvent event) {

                System.out.println(event.getFile().getFileName());
                
               if(descargarArchivo(event)){

              
                 catalogolibroController.getSelected().setUrlImagen(event.getFile().getFileName());
                 catalogolibroController.setBandera(false);
               }
               
                
    }
     
     public String create(){
         try{

         if(    catalogolibroController.getSelected().geturlLibro().length()<0 ||
                catalogolibroController.getSelected().getUrlContraportada().length()<0 ||
                catalogolibroController.getSelected().getUrlImagen().length()<0 ||
                catalogolibroController.getSelected().getUrlPortada().length()<0 ||
                catalogolibroController.getSelected().getUrlResumen().length()<0
            ){

             JsfUtil.addErrorMessage("Faltan por subir uno o algunos elementos");


              return "/catalogolibro/Create_1";
         }
         }catch(Exception e){

             JsfUtil.addErrorMessage("Faltan por subir uno o algunos elementos");
             return "/catalogolibro/Create_1";
         }
         catalogolibroController.getSelected().setUrl(virtual);
         return "/catalogolibro/Create";
     }


}

