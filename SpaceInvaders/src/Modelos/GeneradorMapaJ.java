
package Modelos;

import java.awt.Graphics2D;

/**
 *
 * @author GERARDO SANCHEZ
 */
public class GeneradorMapaJ {
    //Atributos de la Clase
    private int mapaJ[][];
    
    //Constructor
    public GeneradorMapaJ(){
        mapaJ = new int[][]{{2,1,1,0,0},{2,1,1,0,0},{2,1,1,0,0},{2,1,1,0,0},{2,1,1,0,0}
        ,{2,1,1,0,0},{2,1,1,0,0},{2,1,1,0,0},{2,1,1,0,0},{2,1,1,0,0},{2,1,1,0,0}};
    }
    
    //Metodos de la Clase
    public void dibujar(Graphics2D g2d){
        for(int i=0;i<mapaJ.length;i++){
            for(int j=0;j<mapaJ[i].length;j++){
                if(!EstructuraJuego.aliens[i][j].verificarMuerte()){
                    switch(mapaJ[i][j]){
                        case 0:
                           if(Alien.getFotograma()== 0){
                               g2d.drawImage(GestorRecurso.alien0,EstructuraJuego.aliens[i][j].getPosX(),EstructuraJuego.aliens[i][j].getPosY(),GestorRecurso.alien0.getWidth()*3,GestorRecurso.alien0.getHeight()*3, null);
                            }else{
                               g2d.drawImage(GestorRecurso.alien0_1,EstructuraJuego.aliens[i][j].getPosX(),EstructuraJuego.aliens[i][j].getPosY(),GestorRecurso.alien0_1.getWidth()*3,GestorRecurso.alien0_1.getHeight()*3, null);
                           }
                        break;
                            
                        case 1:
                          if(Alien.getFotograma() == 0){
                              g2d.drawImage(GestorRecurso.alien1,EstructuraJuego.aliens[i][j].getPosX(),EstructuraJuego.aliens[i][j].getPosY(),GestorRecurso.alien1.getWidth()*3,GestorRecurso.alien1.getHeight()*3, null);
                          }else{
                              g2d.drawImage(GestorRecurso.alien1_1,EstructuraJuego.aliens[i][j].getPosX(),EstructuraJuego.aliens[i][j].getPosY(),GestorRecurso.alien1_1.getWidth()*3,GestorRecurso.alien1_1.getHeight()*3, null);
                          }  
                        break;
                            
                        case 2:
                          g2d.drawImage(GestorRecurso.alien2,EstructuraJuego.aliens[i][j].getPosX(),EstructuraJuego.aliens[i][j].getPosY(),GestorRecurso.alien2.getWidth()*3,GestorRecurso.alien2.getHeight()*3, null);  
                        break;    
                    }
                }else{
                    if(EstructuraJuego.aliens[i][j].getTiempoDesaparicion() > 0){
                        g2d.drawImage(GestorRecurso.explosion,EstructuraJuego.aliens[i][j].getPosX(),EstructuraJuego.aliens[i][j].getPosY(),GestorRecurso.explosion.getWidth()*3,GestorRecurso.explosion.getHeight()*3, null);
                        EstructuraJuego.aliens[i][j].disminuirTiempoD();
                    }
                }
            }
        }
    }
    
    public int retornarTipoAlien(int x,int y){
        return mapaJ[x][y];
    }
}

