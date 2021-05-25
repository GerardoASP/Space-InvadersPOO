
package Controladores;

import Modelos.Cannon;
import java.awt.Rectangle;

/**
 *
 * @author GERARDO SANCHEZ
 */
public class ControladorCannon {
    //Atributos de la Clase
    Cannon canoP;
    
    //Constructor Parametrizado
    public ControladorCannon(){
        Cannon canoP = new Cannon(5, true,3);
    }
    
    //Metodos
    public void mover(int xDir){
        canoP.mover(xDir);
    }
    
    public void anadirVidas(){
        canoP.anadirVidas();
    }
    
    public void disminuirCantVidas(){
        canoP.disminuirVida();
    }
    
    public Rectangle obtenerE(){
        Rectangle espacio;
        return espacio = canoP.obtenerE();
    }
}
