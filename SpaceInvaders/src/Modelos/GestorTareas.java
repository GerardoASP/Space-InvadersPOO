
package Modelos;

import Vistas.VTableroJuego;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author GERARDO SANCHEZ
 */
public class GestorTareas {
    //Atributos de la Clase
    private static int tiempoRetrasoAlien= 0;
    private static int tiempoRetrasoJugadorC =0;
    private static int tiempoRetrasoTiroS = 0;
    
    //Constructor Por Defecto
    public GestorTareas(){
        
    }
    
    //Getters And Setters

    public static int getTiempoRetrasoAlien() {
        return tiempoRetrasoAlien;
    }

    public static void setTiempoRetrasoAlien(int tiempoRetrasoAlien) {
        GestorTareas.tiempoRetrasoAlien = tiempoRetrasoAlien;
    }

    public static int getTiempoRetrasoJugadorC() {
        return tiempoRetrasoJugadorC;
    }

    public static void setTiempoRetrasoJugadorC(int tiempoRetrasoJugadorC) {
        GestorTareas.tiempoRetrasoJugadorC = tiempoRetrasoJugadorC;
    }

    public static int getTiempoRetrasoTiroS() {
        return tiempoRetrasoTiroS;
    }

    public static void setTiempoRetrasoTiroS(int tiempoRetrasoTiroS) {
        GestorTareas.tiempoRetrasoTiroS = tiempoRetrasoTiroS;
    }
    
    //Metodos de la Clase
    public static void cambiarTiemposR(){
        tiempoRetrasoAlien++;
        tiempoRetrasoJugadorC++;
        if(EstructuraJuego.cano.isEstadoDisparo()){
            tiempoRetrasoTiroS++;
        }
    }
    
    public static void reiniciarTiempoJC(){
        tiempoRetrasoJugadorC=0;
    }
    
    public static void alistarAliens(){
        EstructuraJuego.aliens = new Alien[EstructuraJuego.columnasAliens][EstructuraJuego.filasAliens];
        for(int i=0;i<EstructuraJuego.columnasAliens;i++){
            for(int j=0;j<EstructuraJuego.filasAliens;j++){
                EstructuraJuego.aliens[i][j] = new Alien(200+i*45,50+j*45);
            }
        }
        tiempoRetrasoAlien = 0;
    }
    
    public static void moverA_TodosA(){
        
        if(tiempoRetrasoAlien == Alien.getTiempoMoviento()){
            moverAliensHo();
            tiempoRetrasoAlien=0;
        }
        
        if(EstructuraJuego.aliens[0][0].getDirX() > 0){
            if(EstructuraJuego.aliens[EstructuraJuego.columnasAliens-1][EstructuraJuego.filasAliens-1].verificarDir()){
                moverAliensVerA();
            }
        }else{
            if(EstructuraJuego.aliens[0][0].verificarDir()){
                moverAliensVerA();
            }
        }
    }
    
    private static void moverAliensHo(){
        for(int i=0;i<EstructuraJuego.columnasAliens;i++){
            for(int j=0;j<EstructuraJuego.filasAliens;j++){
                try{
                   EstructuraJuego.aliens[i][j].moverEnVerticalA();
                }catch(NullPointerException e){
                    continue;
                }
            }
        }
        Alien.cambiarFotograma();
    }
    
    private static void moverAliensVerA(){
        for(int i=0;i<EstructuraJuego.columnasAliens;i++){
            for(int j=0;j<EstructuraJuego.filasAliens;j++){
                try{
                   EstructuraJuego.aliens[i][j].moverEnVerticalA();
                }catch(NullPointerException e){
                    continue;
                }
            }
        }
    }
    
    public static void moverProyectiles(){
        for(int i=0;i<EstructuraJuego.projectiles.size();i++){
            EstructuraJuego.projectiles.get(i).mover();
            
            if(EstructuraJuego.projectiles.get(i).getPosY() < 0 || EstructuraJuego.projectiles.get(i).getPosY() > EstructuraJuego.alturaPantalla-80){
                EstructuraJuego.projectiles.remove(i);
            }
        }
    }
    
    public static void permitirDisparoAlien(){
        Random rand = new Random();
        int columna = rand.nextInt(EstructuraJuego.columnasAliens*4);
        int fila = rand.nextInt(EstructuraJuego.filasAliens*4);
        
        if(columna >= EstructuraJuego.columnasAliens || columna >= EstructuraJuego.filasAliens) return;
        
        if(EstructuraJuego.aliens[columna][fila].verificarMuerte()) return;
        
        if(fila == 4){
            EstructuraJuego.projectiles.add(new Proyectil(EstructuraJuego.aliens[columna][fila].getPosX()+15,EstructuraJuego.aliens[columna][fila].getPosY()+25,2));
        }
        
        if(EstructuraJuego.aliens[columna][fila-1].verificarMuerte()){
            EstructuraJuego.projectiles.add(new Proyectil(EstructuraJuego.aliens[columna][fila].getPosX()+15,EstructuraJuego.aliens[columna][fila].getPosY()+25,2));
        }
    }
    
    public static boolean detectarColisionPP(){
        for(int i=0;i<EstructuraJuego.projectiles.size();i++){
            if(EstructuraJuego.projectiles.get(i).obtenerE().intersects(EstructuraJuego.cano.obtenerE())){
                EstructuraJuego.projectiles.remove(i);
                EstructuraJuego.cano.disminuirVida();
                return true;
            }
        }
        return false;
    }
    
    public static void generarNaveM(){
        Random rand = new Random();
        int r = rand.nextInt(750);
        
        if(EstructuraJuego.redShip == null && r == 0){
            EstructuraJuego.redShip = new NaveMisteriosa();
            System.out.println("[Gestor de Tareas]: Creando Nave Misteriosa");
        }
        
        if(EstructuraJuego.redShip != null){
            EstructuraJuego.redShip.mover();
            if(EstructuraJuego.redShip.getPosX() < 0){
                EstructuraJuego.redShip = null;
            }
        }
    }
    
    public static void detectarColisionNMP(){
        for(int i=0;i<EstructuraJuego.projectiles.size();i++){
            if(EstructuraJuego.redShip != null){
                if(EstructuraJuego.projectiles.get(i).obtenerE().intersects(EstructuraJuego.redShip.obtenerE())){
                    EstructuraJuego.redShip = null;
                    EstructuraJuego.cano.setEstadoDisparo(true);
                }
            }
        }
        if(tiempoRetrasoTiroS == 350) EstructuraJuego.cano.setEstadoDisparo(false);
    }
    
    public static void reiniciarJuego(){
        EstructuraJuego.cano = new Cannon();
        
        GestorTareas.alistarAliens();
        
        EstructuraJuego.projectiles = new ArrayList<>();
    }
    
    public static void ordenarPuntuaciones(){
        int appo;
        
        for(int i=0;i<EstructuraJuego.puntajes.length-1;i++){
            for(int j=0;j<EstructuraJuego.puntajes.length;j++){
                if(EstructuraJuego.puntajes[j] > EstructuraJuego.puntajes[i]){
                    appo = EstructuraJuego.puntajes[i];
                    EstructuraJuego.puntajes[i] = EstructuraJuego.puntajes[j];
                    EstructuraJuego.puntajes[j] = appo;
                }
            }
        }
    }
    
    public static boolean verificarCercaniaAli(){
        for(int i=0;i<EstructuraJuego.aliens.length;i++){
            for(int j=0;j<EstructuraJuego.aliens[i].length;j++){
                if(EstructuraJuego.aliens[i][j].getPosY() > 350 && !EstructuraJuego.aliens[i][j].verificarMuerte()){
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean detectarColisionAlienP(){
        for(int i=0;i<EstructuraJuego.columnasAliens;i++){
            for(int j=0;j<EstructuraJuego.filasAliens;j++){
                for(int k=0;k<EstructuraJuego.projectiles.size();k++){
                    if(EstructuraJuego.projectiles.get(k).obtenerE().intersects(EstructuraJuego.aliens[i][j].obtenerE()) && !EstructuraJuego.aliens[i][j].verificarMuerte()){
                        EstructuraJuego.aliens[i][j].setEstadoVida(true);
                        EstructuraJuego.projectiles.remove(k);
                        
                        switch(EstructuraJuego.map.retornarTipoAlien(i, j)){
                            case 0:
                                VTableroJuego.aumentarPuntuacion(10);
                            break;
                                
                            case 1:
                               VTableroJuego.aumentarPuntuacion(20); 
                            break;
                                
                            case 2:
                              VTableroJuego.aumentarPuntuacion(30);  
                            break;  
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
