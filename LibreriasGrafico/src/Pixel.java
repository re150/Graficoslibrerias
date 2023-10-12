import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Thread.sleep;

public class Pixel  extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;

    public Pixel(){

        setSize(500,500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    @Override
    public void paint(Graphics g) {
        try {
            super.paint(graPixel);
        }catch (Exception e){
            System.err.println();
        }
     //   putPixel(80, 80, Color.BLACK);
    }

    public void clear (){
       try {
           sleep(1000);
           super.paint(this.getGraphics());
           // Redibujar el buffer en blanco (color de fondo)
           buffer.setRGB(0, 0, Color.WHITE.getRGB());
           this.getGraphics().drawImage(buffer, 0, 0, this);
       }catch (Exception e){}
    }

    public void putPixel(int x, int y,Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    //Practica 1
    public void AlgoritmolineaYm(int x0, int y0, int x1, int y1, Color c) {

        float m =  (float) (y1 - y0) / (x1 - x0);
        float b = y0 - m * x0;
        float y;

        for (int i = x0; i < x1; i++) {
            y = m * i + b;
            putPixel(Math.round(i), Math.round(y),c);
        }
    }

    //Practica 2
    public void AlgoritmoLineaMejorada(int x0, int y0, int x1, int y1, Color c) {
        //formual de la pendiente
        float m = ((float) x0 == (float)x1) ? 0 : ((float)y1 - (float)y0 ) / ((float)x1 - (float) x0 ); // Manejar líneas verticales
        float b = (float)y0 - m * x0;
        float y;

        int startX = Math.round(x0);
        int endX = Math.round(x1);

        for (int i = startX; i <= endX; i++) {
            if (x0 == x1) {
                y = Math.min(y0, y1) + (i - startX); // Manejar líneas verticales
            } else {
                y = m * i + b;
            }
            putPixel(i, Math.round(y), c);
        }

    }

    //Practica 3
    public void AlgoritmoDDALine(int x0, int y0, int x1, int y1, Color c) {

        float dx = x1 - x0;
        float dy = y1 - y0;
        float x = x0;
        float y = y0;
        float length;
        float xinc;
        float yinc;

        if (Math.abs(dx) > Math.abs(dy)) {

            length = Math.abs(dx);

        } else length = Math.abs(dy);

        xinc = dx / length;
        yinc = dy / length;

        putPixel(Math.round(x), Math.round(y),c);

        for (int k = 1; k < length; k++) {
            x = x + xinc;
            y = y + yinc;
            putPixel(Math.round(x), Math.round(y),c);
        }
    }

    //Practica 4
    public void AlgoritmoBresenhamLine (int x0, int y0, int x1, int y1, Color c) {

        float dx = Math.abs(x1 - x0);
        float dy = Math.abs(y1 - y0);
        float sx = (x0 < x1) ? 1 : -1;
        float sy = (y0 < y1) ? 1 : -1;
        float errO = dx - dy;
        float erro2;

        while (x0 != x1 || y0 != y1) {
            putPixel(x0, y0, c);
              erro2 = 2 * errO;

            if (erro2 > -dy) {
                errO -= dy;
                x0 += sx;
            }

            if (erro2 < dx) {
                errO += dx;
                y0 += sy;
            }

        }
    }

    //Practica 5
    public void AlgoritmoMPLinea(int x0, int y0, int x1, int y1, Color c) {

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;
        int error = dx - dy;
        int x = x0;
        int y = y0;
        int erro2;

        while (x != x1 || y != y1) {

            putPixel(x, y,c);

            erro2 = 2 * error;

            if (-erro2 < dy) {
                error -= dy;
                x  += sx;
            }

            if (erro2 < dx) {
                error += dx;
                y += sy;
            }

        }

        putPixel(x1, y1, c);

    }

    //Practica 6
    public void Rectangulo(int x, int y, int a, int l, Color c) {

        int x0 = x;
        int x1 = x + l;
        int y0 = y;
        int y1 = y + a;

        AlgoritmoDDALine(x0, y0, x0, y1, c);
        AlgoritmoDDALine(x1, y0, x1, y1, c);
        AlgoritmoDDALine(x0, y1, x1, y1, c);
        AlgoritmoDDALine(x0, y0, x1, y0, c);
    }

    //Practica 7
    public void AlgoritmoParaCirculo(int xc, int yc, int r, Color c) {

        double y;

        for (int x = xc - r; x < xc + r; x++) {

            y = yc + Math.sqrt(r * r - (x - xc) * (x - xc));
            putPixel(x, (int) Math.round(y),c);
            y = yc - Math.sqrt(r * r - (x - xc) * (x - xc));
            putPixel(x, (int) Math.round(y), c);

        }

    }

    //Practica 8
    public void AlgortmoPolarCirculo(int xc, int yc, int r, Color c) {

        double y;
        double x;

        for (double t = 0; t <= Math.PI * 2.05; t += 0.01) {
            x = xc + r * Math.sin(t);
            y = yc + r * Math.cos(t);
            putPixel((int) Math.round(x), (int) Math.round(y), c);
        }


    }

    //Practica9
    public  void AlgoritmoCirculoSimetrico8(int xc, int yc, int r, Color c) {
        int x = r;
        int y = 0;
        int radiusError = 1 - x;

        while (x >= y) {
            putPixel(xc + x, yc + y,c); // Octante 1
            putPixel(xc - x, yc + y,c); // Octante 4
            putPixel(xc + x, yc - y,c); // Octante 8
            putPixel(xc - x, yc - y,c); // Octante 5
            putPixel(xc + y, yc + x,c); // Octante 2
            putPixel(xc - y, yc + x,c); // Octante 3
            putPixel(xc + y, yc - x,c); // Octante 7
            putPixel(xc - y, yc - x,c); // Octante 6

            y++;

            if (radiusError < 0) {
                radiusError += 2 * y + 1;
            } else {
                x--;
                radiusError += 2 * (y - x) + 1;
            }
        }
    }
    //Practica10
    public void AlgoritmoPuntoMedioCirculo(int xc, int yc , int r, Color c) {
        int x = r;
        int y = 0;
        int radiusError = 1 - x;

        while (x >= y ) {
            putPixel(xc + x, yc + y, c);
            putPixel(xc - x, yc + y, c);
            putPixel(xc + x, yc - y, c);
            putPixel(xc - x, yc - y, c);
            putPixel(xc + y, yc + x, c);
            putPixel(xc - y, yc + x, c);
            putPixel(xc + y, yc - x, c);
            putPixel(xc - y, yc - x, c);

            y++;

            if (radiusError < 0) {
                radiusError += 2 * y + 1;
            } else {
                x--;
                radiusError += 2 * (y - x + 1);
            }
        }


    }

    //Practica11
    public  void AlgoritmoCirculoBresenham(int xc, int yc , int r, Color c) {
        int x = r;
        int y = 0;
        int error = 0;

        while (x > y) {

            putPixel(xc + x, yc + y, c);
            putPixel(xc + y, yc + x, c);
            putPixel(xc - x, yc + y, c);
            putPixel(xc - y, yc + x, c);
            putPixel(xc - x, yc - y, c);
            putPixel(xc - y, yc - x, c);
            putPixel(xc + x, yc - y, c);
            putPixel(xc + y, yc - x, c);

            if (error <= 0) {
                y++;
                error = error + 2 * y + 1;
            } else if (error > 0) {
                x--;
                error = error - 2 * x + 1;
            }
        }
    }

    //Practica 12
    // es la formula para el perimetro de un elipse
    public void AlgoritmoElipse(int x1, int y1, int r1, int r2, Color c) {
        int  resolu  = 1000 ;
        int x = 0, y = 0;
        double angulo = 2 * Math.PI/resolu, a= 0;

        for(int i=0; i<=resolu+10; i++) {

            a = i*angulo;
            x = (int)(x1 + r2*Math.cos(a));
            y = (int)(y1 + r1*Math.sin(a));
            putPixel(x, y,c);
        }

    }

}