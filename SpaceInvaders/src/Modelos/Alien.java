
package Modelos;

import java.awt.Rectangle;

public class Alien extends ElementoPantalla{

    //Atributos de la Clase
    private int dirX;
    private static int tiempoMoviento;
    private static int fotograma;
    private static int tiempoDesaparicion=20;
    
    //Constructor por Defecto
    public Alien(){
        
    }
    
    //Constructor Parametrizado
    public Alien(int posX,int posY){
        this.posX = posX;
        this.posY = posY;
        this.estadoVida = false;
    }
    
    //Getters And Setters

    public int getDirX() {
        return dirX;
    }

    public void setDirX(int dirX) {
        this.dirX = dirX;
    }

    public static int getTiempoMoviento() {
        return tiempoMoviento;
    }

    public void setTiempoMoviento(int tiempoMoviento) {
        this.tiempoMoviento = tiempoMoviento;
    }

    public static int getFotograma() {
        return fotograma;
    }

    public void setFotograma(int fotograma) {
        this.fotograma = fotograma;
    }

    public int getTiempoDesaparicion() {
        return tiempoDesaparicion;
    }

    public void setTiempoDesaparicion(int tiempoDesaparicion) {
        this.tiempoDesaparicion = tiempoDesaparicion;
    }
    
    //Metodos de la Clase
    public void moverEnLateral(){
        posX += dirX;
    }
    
    public void moverEnVerticalA(){
        posY += 10;
    }
    
    public boolean verificarDir(){
        if(posX < 20){
            dirX = 10;
            return true;
        }else if(posX > 810){
            dirX -= -10;
            return true;
        }
        
        return false;
    }
    
    public static void disminuirTiempoD(){
        tiempoDesaparicion--;
    }
    
    public static void cambiarFotograma(){
        if(fotograma == 0){
            fotograma = 1;
        }else if(fotograma == 1){
            fotograma = 0;
        }
    }

    @Override
    public Rectangle obtenerE() {
        return new Rectangle(posX,posY,GestorRecurso.alien1.getWidth()*3,GestorRecurso.alien1.getHeight()*3);
    }
}
