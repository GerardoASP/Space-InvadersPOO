
package Modelos;

import java.awt.Rectangle;

/**
 *
 * @author GERARDO SANCHEZ
 */
public class Cannon extends ElementoPantalla {
    //Atributos de la Clase
    public int tiempoDisparo;
    private boolean estadoDisparo;
    private int cantidadVidas;
    
    
    //Constructor Parametrizado
    public Cannon(int tiempoDisparo, boolean estadoDisparo, int cantidadVidas) {
        this.tiempoDisparo = tiempoDisparo;
        this.estadoDisparo = estadoDisparo;
        this.cantidadVidas = cantidadVidas;
    }
    
    public Cannon(){
        posX = EstructuraJuego.anchoPantalla/2;
        posY = EstructuraJuego.alturaPantalla-100;
        cantidadVidas = 3;
        estadoVida = false;
        estadoDisparo = false;
    }

    //Getters And Setters
    public boolean isEstadoDisparo() {
        return estadoDisparo;
    }

    public void setEstadoDisparo(boolean estadoDisparo) {
        this.estadoDisparo = estadoDisparo;
    }

    public int getCantidadVidas() {
        return cantidadVidas;
    }
    
    public void setCantidadVidas(int cantidadVidas) {    
        this.cantidadVidas = cantidadVidas;
    }

    //Metodos de la Clase
    public void mover(int xDir){
        posX += xDir;
    }
    
    public void anadirVidas(){
        if(cantidadVidas < 9){
            cantidadVidas++;
        }
    }
    
    public void disminuirVida(){
        cantidadVidas--;
    }
    
    
    @Override
    public Rectangle obtenerE() {
        return new Rectangle(posX,posY,GestorRecurso.player.getWidth()*3,GestorRecurso.player.getHeight()*3);
    }
    
}
