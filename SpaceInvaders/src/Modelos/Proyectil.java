package Modelos;

import java.awt.Rectangle;

/**
 *
 * @author GERARDO SANCHEZ
 */
public class Proyectil extends ElementoPantalla{
    //Atributos de la Clase
    private int dirY;
    
    //Constructor Por Defecto
    public Proyectil(){
        
    }
    //Constructor Parametrizado
    public Proyectil(int posX,int posY, int dirY){
        this.posX = posX;
        this.posY = posY;
        this.dirY = dirY;
    }
    
    public int getDirY() {
        return dirY;
    }

    //Getters And Setters
    public void setDirY(int dirY) {    
        this.dirY = dirY;
    }

    //Metodos de la Clase
    public void mover(){
        this.posY += dirY;
    }
    
    @Override
    public Rectangle obtenerE() {
        return new Rectangle(posX,posY,2,10);
    }
    
}
