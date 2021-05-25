package Modelos;

import java.awt.Rectangle;

/**
 *
 * @author GERARDO SANCHEZ
 */
public class NaveMisteriosa extends ElementoPantalla{
    
    //Constructor Por Defecto
    public NaveMisteriosa(){
        this.posX = 900;
        this.posY = 20;
    }
    
    //Metodos de la clase
    public void mover(){
        this.posX -= 10;
    }

    @Override
    public Rectangle obtenerE() {
        return new Rectangle(posX,posY,GestorRecurso.redShip.getWidth()*3,GestorRecurso.redShip.getHeight()*3);
    }
}
