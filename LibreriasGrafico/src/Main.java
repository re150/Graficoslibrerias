import java.awt.*;

public class Main {
    public static void main(String[] args){
        Pixel g = new Pixel();
       g.lineaMejorada(50,400,400,50,Color.red );
       /// g.AlgoritmoElipce(250, 300, 200, 100,Color.black);
        g.drawElipse(250, 200, 50, 150,Color.black);
        g.AlgoritmoBresenhamLine(200,300,380,50, Color.black);
        g.AlgoritmoBresenhamLine(50,270,360,50, Color.black);
        //g.ModeloPolarCirculo(200,200,100,Color.black);
        //g.Rectangulo(100,50,100,50, Color.red);
        //g.algortmolineaYm(0,0,250,250, Color.red);
        g.AlgoritmoMPLinea(100,50,300,50,Color.orange);
    //   g.ModeloBresenhamCirculo(200,200,50,Color.black);
        g.drawCircleSymmetry8(250,250,200,Color.black);
        g.drawCircleSymmetry8(250,250,100,Color.black);
    }
}
