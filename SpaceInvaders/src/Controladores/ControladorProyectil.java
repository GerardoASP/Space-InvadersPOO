
package Controladores;

import Modelos.Proyectil;
import java.awt.Rectangle;

/**
 *
 * @author GERARDO SANCHEZ
 */
public class ControladorProyectil {
    //Atributos
    Proyectil proye;
    
    public ControladorProyectil(){
        proye = new Proyectil(0,0,0);
    }
    
    //Metodos
    public void mover(){
        proye.mover();
    }
    
    public Rectangle obtenerE(){
        Rectangle espacio;
        return espacio=proye.obtenerE();
    }
}
