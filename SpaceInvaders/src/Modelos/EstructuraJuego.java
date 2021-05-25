
package Modelos;

import java.util.ArrayList;

/**
 *
 * @author GERARDO SANCHEZ
 */
public class EstructuraJuego {
    //Atributos de la Clase
    public static final int anchoPantalla = 900;
    public static final int alturaPantalla = 600;
	
    public static Cannon cano;
    public static Alien aliens[][];
    public static GeneradorMapaJ map;
    public static ArrayList<Proyectil> projectiles;
    public static NaveMisteriosa redShip;
	
    public static final int filasAliens = 5;
    public static final int columnasAliens = 11;
	
    public static int[] puntajes = GestorRecurso.leerPuntuacionesA();
}
