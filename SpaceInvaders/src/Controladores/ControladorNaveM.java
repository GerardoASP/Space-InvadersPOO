
package Controladores;

import Modelos.NaveMisteriosa;
import java.awt.Rectangle;

/**
 *
 * @author GERARDO SANCHEZ
 */
public class ControladorNaveM {
    //Atributos
    NaveMisteriosa naveM;
    
    //Constructor 
    public ControladorNaveM(){
        naveM = new NaveMisteriosa();
    }
    
    //Metodos
    public void mover(){
        naveM.mover();
    }
    
    public Rectangle obtenerE(){
        Rectangle espacio;
        return espacio = naveM.obtenerE();
    }
}
