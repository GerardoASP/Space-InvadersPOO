/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Modelos.Alien;
import Modelos.Cannon;
import Modelos.EstructuraJuego;
import Modelos.GeneradorMapaJ;
import Modelos.GestorRecurso;
import Modelos.GestorTareas;
import Modelos.PresentacionJuego;
import Modelos.Proyectil;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author GERARDO SANCHEZ
 */
public class VTableroJuego extends JPanel implements ActionListener,KeyListener{
    //Atributos
    private PresentacionJuego presentacion;
    private int tiempoRetraso = 20;
    private int tiempoMuerteJugador=200;
    private static int puntuacion=0;
    private static int cantAliensEliminados=0;
    
    //Constructor VTableroJuego
    public VTableroJuego(){
        addKeyListener(this);
        this.setFocusable(true);
        EstructuraJuego.cano = new Cannon();
        GestorTareas.alistarAliens();
        EstructuraJuego.projectiles = new ArrayList<>();
        
        EstructuraJuego.map = new GeneradorMapaJ();
        presentacion = PresentacionJuego.MAIN_MENU;
    }
    
    @Override
    public void paintComponent(Graphics g){
       super.paintComponent(g);
       
       repaint(); revalidate();
       
       //Fondo
       g.setColor(Color.BLACK);
       g.fillRect(0,0,EstructuraJuego.anchoPantalla,EstructuraJuego.alturaPantalla);
       
       if(presentacion == PresentacionJuego.RUNNING){
           graphicRunning(g);
       }else if(presentacion == PresentacionJuego.MAIN_MENU){
           graphicMainMenu(g);
       }else if(presentacion == PresentacionJuego.GAME_OVER){
           graphicGameOver(g);
       }else if(presentacion == PresentacionJuego.HIGHSCORES){
           graphicHighscores(g);
       }
    }
    
    public static void aumentarPuntuacion(int i) {
        puntuacion += i;
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
       if(presentacion == PresentacionJuego.RUNNING){
           if(!EstructuraJuego.cano.verificarMuerte()){
               GestorTareas.cambiarTiemposR();
               GestorTareas.moverA_TodosA();
               GestorTareas.permitirDisparoAlien();
               GestorTareas.moverProyectiles();
               
               //
               if(GestorTareas.detectarColisionAlienP()){
                   cantAliensEliminados++;
               }
               if(GestorTareas.detectarColisionPP()){
                   System.out.println("[Tablero Juego]: Se dectecta muerte jugador");
                   EstructuraJuego.cano.setEstadoVida(true);
               }
               if(GestorTareas.verificarCercaniaAli()){
                   presentacion = PresentacionJuego.GAME_OVER;
               }
               GestorTareas.generarNaveM();
               GestorTareas.detectarColisionNMP();
           }
           
           if(EstructuraJuego.cano.verificarMuerte()){
               tiempoMuerteJugador--;
               
               if(tiempoMuerteJugador==0){
                   tiempoMuerteJugador=200;
                   if(EstructuraJuego.cano.getCantidadVidas()>0){
                       EstructuraJuego.cano.setEstadoVida(false);
                   }else{
                       presentacion = PresentacionJuego.GAME_OVER;
                   }
                   System.out.println("[Tablero Juego:]: Animacion de Muerte finalizada");
               }
           }
           if(cantAliensEliminados == EstructuraJuego.columnasAliens*EstructuraJuego.filasAliens){
               cantAliensEliminados=0;
               GestorTareas.alistarAliens();
               Alien.disminuirTiempoD();
               EstructuraJuego.cano.anadirVidas();
           }
       }
       repaint(); revalidate();
    }
    

    @Override
    public void keyTyped(KeyEvent ke) {
       
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        if(presentacion == PresentacionJuego.RUNNING){
            if(!EstructuraJuego.cano.verificarMuerte()){
                switch(arg0.getKeyCode()){
                    case KeyEvent.VK_LEFT:
                       if(EstructuraJuego.cano.getPosX()>10){
                           EstructuraJuego.cano.mover(-5);
                       } 
                    break;
                        
                    case KeyEvent.VK_RIGHT:
                       if(EstructuraJuego.cano.getPosX() < 810){
                           EstructuraJuego.cano.mover(5);
                       } 
                    break;
                        
                    case KeyEvent.VK_SPACE:
                       if(GestorTareas.getTiempoRetrasoJugadorC() > EstructuraJuego.cano.tiempoDisparo || EstructuraJuego.cano.isEstadoDisparo()){
                           EstructuraJuego.projectiles.add(new Proyectil(EstructuraJuego.cano.getPosX()+19,EstructuraJuego.cano.getPosY()-10,-10));
                           GestorTareas.reiniciarTiempoJC();
                       } 
                    break;    
                }
            }
        }else if(presentacion == PresentacionJuego.MAIN_MENU){
            switch(arg0.getKeyCode()){
                case KeyEvent.VK_ENTER:
                   presentacion = PresentacionJuego.RUNNING; 
                break;
                    
                case KeyEvent.VK_SPACE:
                   presentacion = PresentacionJuego.HIGHSCORES; 
                break;    
            }
        }else if(presentacion == PresentacionJuego.GAME_OVER){
            presentacion = PresentacionJuego.MAIN_MENU;
            
            if(puntuacion > EstructuraJuego.puntajes[9]){
                EstructuraJuego.puntajes[9] = puntuacion;
                GestorTareas.ordenarPuntuaciones();
                GestorRecurso.anotarPuntuacionesA();
            }
            puntuacion=0;
            GestorTareas.reiniciarJuego();
        }else if(presentacion == PresentacionJuego.HIGHSCORES){
            presentacion = PresentacionJuego.MAIN_MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
    private static void graphicRunning(Graphics g){
        if(!EstructuraJuego.cano.verificarMuerte() && !EstructuraJuego.cano.isEstadoDisparo()){
            g.drawImage(GestorRecurso.player,EstructuraJuego.cano.getPosX(),EstructuraJuego.cano.getPosY(),GestorRecurso.player.getWidth()*3,GestorRecurso.player.getHeight()*3,null);
        }else if(!EstructuraJuego.cano.verificarMuerte() && EstructuraJuego.cano.isEstadoDisparo()){
            g.drawImage(GestorRecurso.redPlayer,EstructuraJuego.cano.getPosX(),EstructuraJuego.cano.getPosY(),GestorRecurso.deadPlayer.getWidth()*3,GestorRecurso.deadPlayer.getHeight()*3,null);
        }else{
            g.drawImage(GestorRecurso.deadPlayer,EstructuraJuego.cano.getPosX(),EstructuraJuego.cano.getPosY(),GestorRecurso.redPlayer.getWidth()*3,GestorRecurso.redPlayer.getHeight()*3,null);
        }
        
        g.setFont(new Font("Arial",Font.BOLD,20));
        g.setColor(Color.WHITE);
        g.drawString("Puntuacion: ",500,560);
        g.drawString(""+puntuacion,700,560);
        g.drawString("Cantidad Vidas: ",40,560);
        
        for(int i=0;i<EstructuraJuego.cano.getCantidadVidas();i++){
            g.drawImage(GestorRecurso.player,i*40+110,550,GestorRecurso.player.getWidth()*2,GestorRecurso.player.getHeight()*2,null);
        }
        
        g.setColor(Color.GREEN);
        g.fillRect(5,530,880,5);
        
        g.setColor(Color.WHITE);
        for(int i=0;i<EstructuraJuego.projectiles.size();i++){
            g.fillRect(EstructuraJuego.projectiles.get(i).getPosX(),EstructuraJuego.projectiles.get(i).getPosY(), 2,10);
        }
        
        EstructuraJuego.map.dibujar((Graphics2D)g);
        
        if(EstructuraJuego.redShip != null){
           g.drawImage(GestorRecurso.redShip,EstructuraJuego.redShip.getPosX(),EstructuraJuego.redShip.getPosY(),GestorRecurso.redShip.getWidth()*3,GestorRecurso.redShip.getHeight()*3,null);
        }
    }
    
    private static void graphicMainMenu(Graphics g) {
	//title
	g.setColor(Color.WHITE);
	g.setFont(new Font("Arial", Font.BOLD, 90));
	g.drawString("SPACE", 50, 150);
	g.setColor(Color.GREEN);
	g.drawString("INVADERS", 400, 150);
	g.setColor(Color.WHITE);
		
	//
	g.drawImage(GestorRecurso.alien0, 350, 200, GestorRecurso.alien0.getWidth()*3,GestorRecurso.alien0.getHeight()*3, null);
	g.drawImage(GestorRecurso.alien1, 350, 240,GestorRecurso.alien1.getWidth()*3,GestorRecurso.alien1.getHeight()*3, null);
	g.drawImage(GestorRecurso.alien2, 350, 280,GestorRecurso.alien2.getWidth()*3,GestorRecurso.alien2.getHeight()*3, null);
	g.drawImage(GestorRecurso.redShip, 340, 320,GestorRecurso.redShip.getWidth()*3,GestorRecurso.redShip.getHeight()*3, null);
	
	g.setFont(new Font("Arial", Font.PLAIN, 20));
	g.drawString("= 10 pts", 400, 220);
	g.drawString("= 20 pts", 400, 260);
	g.drawString("= 30 pts", 400, 300);
	g.drawString("= ??? :)", 410, 340);
		
	//
	g.drawString("[Enter] Start Game", 330, 400);
	g.drawString("[Space] Highscores", 330, 430);
    }
    
    private static void graphicGameOver(Graphics g) {
	g.setColor(Color.RED);
	g.setFont(new Font("Arial", Font.BOLD, 50));
	g.drawString("GAME OVER", 300, 150);
		
	g.setColor(Color.WHITE);
	g.setFont(new Font("Arial", Font.PLAIN, 20));
	g.drawString("Your score: "+puntuacion, 350, 200);
	g.drawString("Press any button to return to main menu", 300, 250);
    }
    
    private static void graphicHighscores(Graphics g) {
	g.setColor(Color.WHITE);
	g.setFont(new Font("Arial", Font.BOLD, 40));
	g.drawString("HIGHSCORES", 300, 100);
		
	g.setFont(new Font("Arial", Font.PLAIN, 20));
	for(int i=0; i<EstructuraJuego.puntajes.length; i++) {
	    g.drawString(""+(i+1)+" -", 320, 140+i*30);
            g.drawString(""+EstructuraJuego.puntajes[i], 540, 140+i*30);
        }
		
	g.drawString("Press any button to return to main menu", 300, 500);
    }
    
}
