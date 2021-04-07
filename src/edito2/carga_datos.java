package edito2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;

public class carga_datos {

   private FileOutputStream guardar_;
   private FileInputStream abrir_;
   private JFileChooser selecionar_archivo = new JFileChooser();
   private File ruta;
   private String aux_ruta;
   
    public carga_datos(){}
    
    public String abrir() {
        String aux="";
       selecionar_archivo.showDialog(null,"abrir");
       ruta=selecionar_archivo.getSelectedFile();
       aux_ruta=ruta.getAbsolutePath();
        try {
          abrir_=new FileInputStream(ruta);
        } catch (FileNotFoundException e) {
        }
     
        try {
       int j;
        for (int i = 0; i < 1000; i++) {
            j=abrir_.read();
            if(j>1){
            aux+=(char)j; 
            }
        }
        } catch (IOException e) {
        }
        return aux;
    }
    
    public void guardar(String texto,String ruta_){
        boolean estado=false;
        byte[] convercion=texto.getBytes();
        try {
           
            guardar_=new FileOutputStream(new File(ruta_));
            
        } catch (FileNotFoundException e) {
        }
        try {
            guardar_.write(convercion);
        } catch (IOException e) {
        }
   
    }
    
    public void guardad_como(String texto){
        selecionar_archivo.showSaveDialog(null);
        ruta=selecionar_archivo.getSelectedFile();
        String ruta_=ruta.getName();
        char j;
        boolean punto=false;
        for (int i = 0; i < ruta_.length(); i++) {
            j=ruta_.charAt(i);
            if(j=='.'){
                punto=true;
                System.out.println("si");
                break;
            }else{
                punto=false;
            }
        }
        ruta_=ruta.getAbsolutePath();
        aux_ruta=ruta_;
        if(punto){
            System.out.println("xd "+ruta_);
            guardar(texto,ruta_);
        }else{
            ruta_+=".txt";
            guardar(texto,ruta_);
        }
        
        
        
    }  
    
    
    
    public String getAux_ruta() {
        return aux_ruta;
    }
    
    

}
