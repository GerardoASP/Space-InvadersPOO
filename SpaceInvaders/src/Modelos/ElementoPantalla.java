
package Modelos;

import java.awt.Rectangle;

public abstract class ElementoPantalla {
    //Atributos de la Clase
    protected int posX;
    protected int posY;
    protected boolean estadoVida;
    
    //Getters And Setters
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
    
    public void setEstadoVida(boolean estadoVida) {
        this.estadoVida = estadoVida;
    }
    
    //Metodos de la Clase
    public boolean verificarMuerte(){
        return estadoVida;
    }
    
    
    public abstract Rectangle obtenerE();
}
