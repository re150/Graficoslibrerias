import java.awt.*;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args){
        Pixel g = new Pixel();
      /* g.lineaMejorada(50,400,400,50,Color.red );
       /// g.AlgoritmoElipce(250, 300, 200, 100,Color.black);
        g.drawElipse(250, 200, 50, 150,Color.black);
        g.AlgoritmoBresenhamLine(200,300,380,50, Color.black);*/
     //   g.AlgoritmoBresenhamLine(50,270,360,50, Color.black);
        //g.ModeloPolarCirculo(200,200,100,Color.black);
       // g.Rectangulo(100,100,100,200, Color.red);
        //g.algortmolineaYm(0,0,250,250, Color.red);
      /*  g.AlgoritmoMPLinea(100,50,300,50,Color.orange);
    //   g.ModeloBresenhamCirculo(200,200,50,Color.black);
        g.drawCircleSymmetry8(250,250,200,Color.black);
        g.drawCircleSymmetry8(250,250,100,Color.black);*/
        try {
            //Practica 1
            g.AlgoritmolineaYm(50,400,400,50,Color.red );
            sleep(3000);
            g.repaint();
            //Practica 2
            g.AlgoritmoLineaMejorada(300,50,50,300,Color.black);
            sleep(3000);
            g.repaint();
            //Practica 3
            g.AlgoritmoDDALine(50,300,300,50,Color.CYAN);
            sleep(3000);
            g.repaint();
            //Practica 4
            g.AlgoritmoBresenhamLine(50,300,300,50,Color.black);
            sleep(3000);
            g.repaint();
            //Practica 5
            g.AlgoritmoMPLinea(50,300,300,50,Color.black);
            sleep(3000);
            g.repaint();
            //Practica 7
            g.AlgoritmoParaCirculo(250,250,125,Color.black);
            sleep(3000);
            g.repaint();
            // Practica 8
            g.AlgortmoPolarCirculo(250,250,125,Color.black);
            sleep(3000);
            g.repaint();
            // Practica 9
            g.AlgoritmoCirculoSimetrico8(250,250,100,Color.black);
            sleep(3000);
            g.repaint();
            // Practica 10
            g.AlgoritmoPuntoMedioCirculo(250,250,100,Color.black);
            sleep(3000);
            g.repaint();
            // Practica 11
            g.AlgoritmoCirculoBresenham(250,250,100,Color.black);
            sleep(3000);
            g.repaint();
            g.AlgoritmoBresenhamLine(50,50,250,200, Color.black);
            sleep(3000);
            g.repaint();
            g.AlgoritmoBresenhamLine(50,50,250,50, Color.black);
            sleep(3000);
            g.repaint();
            g.AlgoritmoBresenhamLine(50,200,250,50, Color.red);
            sleep(3000);
            g.repaint();

            g.AlgoritmoBresenhamLine(50,200,250,200, Color.black);
            //Practica 6
            g.Rectangulo(100, 250, 50, 150, Color.red);
            g.Rectangulo(120, 260, 25, 105, Color.red);
            sleep(3000);
            g.repaint();
            //Circulos
            g.AlgoritmoCirculoSimetrico8(250,250,70,Color.black);
            g.AlgoritmoCirculoSimetrico8(250,250,50,Color.black);
            g.AlgoritmoCirculoSimetrico8(250,250,25,Color.black);
            g.AlgoritmoCirculoSimetrico8(250,250,5,Color.black);

            sleep(3000);
            g.repaint();

            //Elipce
            g.AlgoritmoElipse(250,250,50,100, Color.black);
            g.AlgoritmoElipse(250,250,40,85, Color.black);
            g.AlgoritmoElipse(250,250,30,65, Color.black);
            g.AlgoritmoElipse(250,250,15,45, Color.black);
            g.AlgoritmoElipse(250,250,5,25, Color.black);
            sleep(3000);
            g.repaint();
        }catch (Exception e){
            System.err.println(e);
        }






    }
}
