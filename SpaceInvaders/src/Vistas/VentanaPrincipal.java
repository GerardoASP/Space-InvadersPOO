/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Modelos.EstructuraJuego;
import javax.swing.JFrame;

/**
 *
 * @author GERARDO SANCHEZ
 */
public class VentanaPrincipal{
    //Atributos
    private static JFrame window;
    private static VTableroJuego board;
    
    public static void main(String[] args){
        createFrame();
	createVTableroJuego();
    }
    
    private static void createFrame() {
	System.out.println("[Main]: Creating Frame");
		
	window = new JFrame("Space Invaders");
	window.setVisible(true);
	window.setBounds(100, 100, EstructuraJuego.anchoPantalla, EstructuraJuego.alturaPantalla);
	window.setResizable(false);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private static void createVTableroJuego(){
        System.out.println("[Main]: Creating game board");
		
	board = new VTableroJuego();
	window.add(board);
	board.requestFocusInWindow();
    }
}
