/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
import config.Manejador;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fernando
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
     private int getTypeJSON(String js_obj) {
        Object objeto;
        try {
            objeto = new JSONObject(js_obj);
            return 1;
        
        } catch(JSONException ex) {
            try {
                objeto = new JSONArray(js_obj);
                return 2;
                
            } catch(JSONException ex2) {
                return 0;
            }
        }  
    }
    public static void main(String[] args) {   
        try {
            Manejador admin = new Manejador();
            admin.setRuta("notebooks.json");
            admin.Conectar();
            admin.setNom_bd("notebooks");
            admin.setNom_coleccion("notebooks");
            
            DateFormat format_date = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = new Date();
            String date_now = format_date.format(fecha);
        
            
            JSONObject param_1 = new JSONObject("{nombre: Libreta}");
            
            JSONObject busqueda = admin.Select(param_1);
            
            if(busqueda.has("id") && busqueda.get("id").equals(0)) {
                System.out.println(busqueda.toString(4));
            
            } else {
                JSONObject into_array = new JSONObject("{ notes: [{id_note: 3}] }");
                JSONObject nota = admin.SelectIntoArray(busqueda, into_array); 
                
                System.out.println(nota.toString(4));
            }
            
            /*JSONObject cambio = new JSONObject("{nombre: Cronicas Rata, fecha_modificacion: \""+ date_now +"\"}");
            boolean exito = admin.update(cambio, new JSONObject("{id_notebook: 2}"));
            
            if(exito) {
                System.out.println("Registro modificado de manera satisfactoria");
            }*/
        } catch(FileNotFoundException fe) {
            fe.printStackTrace();
        
        } catch(IOException ioe) {
            ioe.printStackTrace();
        
        } catch(JSONException je) {
            je.printStackTrace();
        }
        
    }
    
}
