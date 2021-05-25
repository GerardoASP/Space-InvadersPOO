
package Controladores;

import Modelos.Alien;
import java.awt.Rectangle;

/**
 *
 * @author GERARDO SANCHEZ
 */
public class ControladorAlien {
    //Atributos de la Clase
    Alien alien;
    
    //Constructor Parametrizado
    public ControladorAlien(){
        Alien alien = new Alien(0,0);
    }
    
    //Metodos
    public void moverEnLateral(){
        alien.moverEnLateral();
    }
    
    public void moverEnVerticalA(){
        alien.moverEnVerticalA();
    }
    
    public boolean verificarDir(){
        boolean respuesta = false;
        return respuesta = alien.verificarDir();
    }
    
    public static void disminuirTiempoD(){
        Alien.disminuirTiempoD();
    }
    
    public static void cambiarFotograma(){
        Alien.cambiarFotograma();
    }
    
    public Rectangle obtenerE(){
        Rectangle espacio;
        return espacio=alien.obtenerE();
    }
}
