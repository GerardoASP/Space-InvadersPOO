
package Modelos;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;

/**
 *
 * @author GERARDO SANCHEZ
 */
public class GestorRecurso {
    //Atributos de la Clase
    public static final BufferedImage player = leerImagen("player");
    public static final BufferedImage alien0 = leerImagen("alien0");
    public static final BufferedImage alien0_1 = leerImagen("alien0_1");
    public static final BufferedImage alien1 = leerImagen("alien1");
    public static final BufferedImage alien1_1 = leerImagen("alien1_1");
    public static final BufferedImage alien2 = leerImagen("alien2");
    public static final BufferedImage deadPlayer = leerImagen("deadPlayer");
    public static final BufferedImage redPlayer = leerImagen("redPlayer");
    
    public static final BufferedImage explosion = leerImagen("explosion");
    public static final BufferedImage redShip = leerImagen("redShip");
    
    private static BufferedReader reader;
    private static PrintWriter writer;
    
    //Constructor Por Defecto
    public GestorRecurso(){
        
    }
    
    //Metodos de la CLase
    private static BufferedImage leerImagen(String nombreArchivo){
        System.out.println("[Gestor De Recursos ]: Cargando "+nombreArchivo+".png");
        
        BufferedImage img = null;
        
        try{
            img = ImageIO.read(new File("src/Recursos/"+nombreArchivo+".png"));
        }catch(IOException e){
            System.out.println("[Gestor de Recursos]: excepcion al cargar src/Recursos/"+nombreArchivo+".png");
        }
        
        return img;
    } 
    
    public static int[] leerPuntuacionesA(){
        int[] array = new int[10];
        
        try{
            reader = new BufferedReader(new FileReader("src/Recursos/Puntajes.txt"));
        }catch(FileNotFoundException e1){
            System.out.println("[Gestor de Recursos]: archivo Puntajes.txt no encontrado");
        }
        
        try{
            String str = reader.readLine();
            
            while(str != null){
                String[] strarray = str.split("-");
                array[Integer.parseInt(strarray[0])] = Integer.parseInt(strarray[1]);
                
                str = reader.readLine();
            }
        }catch(IOException e){
            System.out.println("[Gestor de Recursos]: IOException ocurrio al leer el archivo");
        }
        
        return array;
    }
    
    public static void anotarPuntuacionesA(){
        
        try{
            writer = new PrintWriter(new File("src/Recursos/Puntajes.txt"));
        }catch(FileNotFoundException e){
            System.out.println("[Gestor de Recursos]: archivo Puntajes.txt no encontrado");
        }
        
        for(int i=0;i<EstructuraJuego.puntajes.length; i++){
            writer.println(i+"-"+EstructuraJuego.puntajes[i]);
        }
        writer.close();
    }
}
