import java.awt.*;

import static java.lang.Thread.sleep;

public class Main {

    public  static void Practicas (){
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
            g.clear();
            //Practica 2
            g.AlgoritmoLineaMejorada(100,100,150,100,Color.black);
            g.clear();
            //Practica 3
            g.AlgoritmoDDALine(50,300,300,50,Color.CYAN);
            g.clear();
            //Practica 4
            g.AlgoritmoBresenhamLine(150,300,150,50,Color.black);
            g.clear();
            //Practica 5
            g.AlgoritmoMPLinea(50,300,300,50,Color.black);
            g.clear();
            //Practica 6
            g.Rectangulo(100, 250, 50, 150, Color.black);
            g.clear();
            //Practica 7
            g.AlgoritmoParaCirculo(250,250,125,Color.black);
            g.clear();
            // Practica 8
            g.AlgortmoPolarCirculo(250,250,125,Color.black);
            g.clear();
            // Practica 9
            g.AlgoritmoCirculoSimetrico8(250,250,100,Color.black);
            g.clear();
            // Practica 10
            g.AlgoritmoPuntoMedioCirculo(250,250,100,Color.black);
            g.clear();
            // Practica 11
            g.AlgoritmoCirculoBresenham(250,250,100,Color.black);
            g.clear();
            //Practica 12
            g.AlgoritmoBresenhamLine(50,50,250,200, Color.black);
            g.clear();
            g.AlgoritmoBresenhamLine(50,50,250,50, Color.black);
            g.clear();
            g.AlgoritmoBresenhamLine(50,200,250,50, Color.red);
            g.clear();

            g.AlgoritmoBresenhamLine(50,200,250,200, Color.black);
            g.Rectangulo(100, 250, 50, 150, Color.red);
            g.Rectangulo(120, 260, 25, 105, Color.red);

            g.clear();
            //Circulos
            g.AlgoritmoCirculoSimetrico8(250,250,70,Color.black);
            g.AlgoritmoCirculoSimetrico8(250,250,50,Color.black);
            g.AlgoritmoCirculoSimetrico8(250,250,25,Color.black);
            g.AlgoritmoCirculoSimetrico8(250,250,5,Color.black);

            g.clear();

            //Elipce
            g.AlgoritmoElipse(250,250,50,100, Color.black);
            g.AlgoritmoElipse(250,250,40,85, Color.black);
            g.AlgoritmoElipse(250,250,30,65, Color.black);
            g.AlgoritmoElipse(250,250,15,45, Color.black);
            g.AlgoritmoElipse(250,250,5,25, Color.black);
           g.clear();
            //Practica 13
           g.AlgoritmoTiposDeLinea(150,50,150,300 ,4,1,Color.red);
            g.clear();
            //g.AlgoritmoTiposDeLinea2(50,100,50,300 ,"00011",Color.black);
            g.AlgoritmoTiposDeLinea(100,100,100,300 ,"00000001111",Color.red);
            g.AlgoritmoTiposDeLinea(110,100,110,300 ,"111111100",Color.black);
            g.clear();
            //Practica 14
            g.AlgoritmoGrosordeLinea(50,150,50,300,2,Color.red);
            g.AlgoritmoGrosordeLinea(110,100,110,310,Color.red,5);
            g.AlgoritmoGrosordeLinea(125,100,125,300,3,Color.red);
            g.clear();
            //Practica 15
            //g.AlgoritmoTiposECircunferencias(250,250,100,"111110",Color.red);
            //g.AlgoritmoTiposECircunferencias(200,200,50,"0001111",Color.red);
            g.clear();
            //Practica 16
            g.AlgoritmoGrosorDeCircunferencias(200,250,100,5,Color.red);
            g.AlgoritmoGrosorDeCircunferencias(200,200,50,2,Color.red);
           // g.clear();
            //Practica 17
           /*g.Rectangulo(100, 100, 100, 100, Color.black);
           g.scanLine(100,100, 200, 200, Color.red);
            //Pracica 18
            g.clear();
           g.AlgoritmoCirculoBresenham(200, 250, 50,  Color.BLACK);
           g.floodFill(200,250,Color.red);
*/
            //Practica 19

        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void main(String[] args){
         Practicas();
    }
}
