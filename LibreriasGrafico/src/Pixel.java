import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;


import static java.lang.Thread.getAllStackTraces;
import static java.lang.Thread.sleep;

public class Pixel  extends JFrame {
    private BufferedImage buffer;
    private BufferedImage fondo;

    private Graphics fondoG;
    private int size1 = 1;

    public int getSize1() {
        return size1;
    }

    public void setSize1(int size1) {
        this.size1 = size1;
    }

    public Pixel() {

        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        fondo = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        fondoG = fondo.getGraphics();
        fondoG.setColor(Color.WHITE);
        fondoG.fillRect(0, 0, getWidth(), getWidth());


    }
    @Override
    public void update(Graphics g) {

        g.drawImage(fondo, 0, 0, this);

    }
    @Override
    public void paint(Graphics g) {
        update(g);
        /*try {
            super.paint(graPixel);
        } catch (Exception e) {
            System.err.println();
        }*/
        //   putPixel(80, 80, Color.BLACK);
    }

    public void clear() {
        try {
            sleep(1000);
            // Redibujar el buffer en blanco (color de fondo)
            fondoG.setColor(Color.WHITE);
            fondoG.fillRect(0, 0, getWidth(), getHeight());
            this.getGraphics().drawImage(fondo, 0, 0, this);
            putPixel(0, 0, 1, Color.WHITE);
        } catch (Exception e) {
        }
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        fondo.getGraphics().drawImage(buffer, x, y, this);
        repaint();
    }

    //Pixel del Grosor de linea
    public void putPixel(int x, int y, int size, Color c) {
        setSize1(size);
        buffer.setRGB(0, 0, c.getRGB());
        buffer = new BufferedImage(getSize1(), getSize1(), BufferedImage.TYPE_INT_RGB);
        fondo.getGraphics().drawImage(buffer, x, y, this);
        putPixel(0,0,Color.WHITE);
    }

    //Practica 1
    public void AlgoritmolineaYm(int x0, int y0, int x1, int y1, Color c) {

        float m = (float) (y1 - y0) / (x1 - x0);
        float b = y0 - m * x0;
        float y;

        for (int i = x0; i < x1; i++) {
            y = m * i + b;
            putPixel(Math.round(i), Math.round(y), c);
        }
    }

    //Practica 2
    public void AlgoritmoLineaMejorada(int x0, int y0, int x1, int y1, Color c) {
        if (x0 == x1) {
            // Caso especial: la línea es vertical
            int startY = Math.min(y0, y1);
            int endY = Math.max(y0, y1);
            for (int y = startY; y <= endY; y++) {
                putPixel(x0, y, c);
            }
        } else {
            float m = (float) (y1 - y0) / (x1 - x0); // calcula la pendiente de la línea
            float b = y0 - m * x0; // calcula la intersección con el eje y
            float y;

            int startX = Math.min(x0, x1);
            int endX = Math.max(x0, x1);

            for (int i = startX; i <= endX; i++) {
                y = m * i + b;
                putPixel(Math.round(i), Math.round(y), c);
            }
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

        putPixel(Math.round(x), Math.round(y), c);

        for (int k = 1; k < length; k++) {
            x = x + xinc;
            y = y + yinc;
            putPixel(Math.round(x), Math.round(y), c);
        }
    }

    //Practica 4
    public void AlgoritmoBresenhamLine(int x0, int y0, int x1, int y1, Color c) {

        float dx = Math.abs(x1 - x0);
        float dy = Math.abs(y1 - y0);
        float sx = (x0 < x1) ? 1 : -1;
        float sy = (y0 < y1) ? 1 : -1;
        float errO = dx - dy;
        float erro2;

        while (x0 != x1 || y0 != y1) {
            putPixel(x0, y0, c);
            System.out.println("x0 = " + x0 + " " + y0);
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

            putPixel(x, y, c);

            erro2 = 2 * error;

            if (-erro2 < dy) {
                error -= dy;
                x += sx;
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
            putPixel(x, (int) Math.round(y), c);
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
    public void AlgoritmoCirculoSimetrico8(int xc, int yc, int r, Color c) {
        int x = r;
        int y = 0;
        int radiusError = 1 - x;

        while (x >= y) {
            putPixel(xc + x, yc + y, c); // Octante 1
            putPixel(xc - x, yc + y, c); // Octante 4
            putPixel(xc + x, yc - y, c); // Octante 8
            putPixel(xc - x, yc - y, c); // Octante 5
            putPixel(xc + y, yc + x, c); // Octante 2
            putPixel(xc - y, yc + x, c); // Octante 3
            putPixel(xc + y, yc - x, c); // Octante 7
            putPixel(xc - y, yc - x, c); // Octante 6

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
    public void AlgoritmoPuntoMedioCirculo(int xc, int yc, int r, Color c) {
        int x = r;
        int y = 0;
        int radiusError = 1 - x;

        while (x >= y) {
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
    public void AlgoritmoCirculoBresenham(int xc, int yc, int r, Color c) {
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
        int resolu = 1000;
        int x = 0, y = 0;
        double angulo = 2 * Math.PI / resolu, a = 0;

        for (int i = 0; i <= resolu + 10; i++) {

            a = i * angulo;
            x = (int) (x1 + r2 * Math.cos(a));
            y = (int) (y1 + r1 * Math.sin(a));
            putPixel(x, y, c);
        }

    }

    //Practica 13
    public void AlgoritmoTiposDeLinea(int x0, int y0, int x1, int y1, int paints, int unpaints, Color c) {
        boolean estado = true;
        float dx = Math.abs(x1 - x0);
        float dy = Math.abs(y1 - y0);
        float sx = (x0 < x1) ? 1 : -1;
        float sy = (y0 < y1) ? 1 : -1;
        float errO = dx - dy;
        float erro2;
        int paintS = 0;
        int con = 0;
        int unpaint2 = paints;

        while (x0 != x1 || y0 != y1) {
            //los pixel que si se pintan
            System.out.println("paintS = " + paintS);

            if (paintS < unpaint2 && estado) {
                putPixel(x0, y0, c);
                paintS++;
            } else {
                estado = false;
                // paintS--;
                con++;
                if (con >= unpaints) {
                    System.out.println("con = " + con);
                    estado = true;
                    con = 0;
                    paintS = 0;
                }
            }
            //System.out.println("estado = " + estado);
            System.out.println("x0 = " + x0 + " y0 " + y0);
            erro2 = 2 * errO;

            if (erro2 > -dy) {
                errO -= dy;
                x0 += sx;
                //    System.out.println("sx = " + sx);
            }

            if (erro2 < dx) {
                errO += dx;
                y0 += sy;
                //  System.out.println("sy = " + sy);
            }

        }

    }

    public void AlgoritmoTiposDeLinea(int x0, int y0, int x1, int y1, String Mask, Color c) {
        boolean estado = true;
        float dx = Math.abs(x1 - x0);
        float dy = Math.abs(y1 - y0);
        float sx = (x0 < x1) ? 1 : -1;
        float sy = (y0 < y1) ? 1 : -1;
        float errO = dx - dy;
        float erro2;
        int paint1 = 0;
        int con = 0;
        int unpaint1;
        int unpaint0;
        boolean firse1 = false;
        boolean firse0 = false;
        ArrayList<Character> unos = new ArrayList<>();
        ArrayList<Character> ceros = new ArrayList<>();

        for (char caracter : Mask.toCharArray()) {
            if (caracter == '1') {
                unos.add(caracter);
                if (firse0 == false) {
                    firse1 = !firse0;
                    // System.out.println("firse1 = " + firse1);
                }
            } else if (caracter == '0') {
                ceros.add(caracter);
                if (firse1 == false) {
                    firse0 = !firse1;
                    //System.out.println("firse0 = " + firse0);
                }
            }
        }
        unpaint1 = unos.size();
        unpaint0 = ceros.size();
        while (x0 != x1 || y0 != y1) {
            if (firse0) estado = false;

            if (paint1 < unpaint1 && estado == true) {
                putPixel(x0, y0, c);
                paint1++;

            } else {
                estado = false;
                con++;
                if (con > unpaint0) {
                    estado = true;
                    firse0 = false;
                    con = 0;
                    paint1 = 0;
                }
            }

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

    public void AnalisarMascara(String Mask) {
        boolean firse1 = false;
        boolean firse0 = false;
        String unos = "";
        String ceros = "";

        for (char caracter : Mask.toCharArray()) {
            if (caracter == '1') {
                unos += caracter;
                if (firse0 == false) {
                    firse1 = !firse0;
                }
            } else if (caracter == '0') {
                ceros += caracter;
                if (firse1 == false) {
                    firse0 = !firse1;
                }
            }
        }

        //  System.out.println("String con unos: " + unos + " " + firse1);
        //System.out.println("String con ceros: " + ceros);
    }

    //Practica 14
    public void AlgoritmoGrosordeLinea(int x0, int y0, int x1, int y1, int width, Color c) {

        float dx = Math.abs(x1 - x0);
        float dy = Math.abs(y1 - y0);
        float sx = (x0 < x1) ? 1 : -1;
        float sy = (y0 < y1) ? 1 : -1;
        float errO = dx - dy;
        float erro2;

        while (x0 != x1 || y0 != y1) {
            putPixel(x0, y0, width, c);
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

    public void AlgoritmoGrosordeLinea(int x0, int y0, int x1, int y1, Color c, int grosor) {
        if (grosor == 1) {
            // Línea de grosor 1 píxel, simplemente usa el algoritmo de Bresenham original
            AlgoritmoBresenhamLine(x0, y0, x1, y1, c);
        } else {
            float dx = Math.abs(x1 - x0);
            float dy = Math.abs(y1 - y0);

            if (dx > dy) {
                for (int i = -grosor / 2; i < grosor / 2; i++) {
                    int yOffset = i;
                    int xStart = Math.min(x0, x1);
                    int xEnd = Math.max(x0, x1);
                    int y = y0 + yOffset;
                    for (int x = xStart; x <= xEnd; x++) {
                        putPixel(x, y, c);
                    }
                }
            } else {
                for (int i = -grosor / 2; i < grosor / 2; i++) {
                    int xOffset = i;
                    int yStart = Math.min(y0, y1);
                    int yEnd = Math.max(y0, y1);
                    int x = x0 + xOffset;
                    for (int y = yStart; y <= yEnd; y++) {
                        putPixel(x, y, c);
                    }
                }
            }
        }
    }

    //Pracica 15
    public void AlgoritmoTiposECircunferencias(int xc, int yc, int r, String Mask, Color c) {
        boolean estado = true;
        int x = r;
        int y = 0;
        int error = 0;
        int paint1 = 0;
        int con = 0;
        int unpaint1;
        int unpaint0;
        boolean firse1 = false;
        boolean firse0 = false;
        ArrayList<Character> unos = new ArrayList<>();
        ArrayList<Character> ceros = new ArrayList<>();
        for (char caracter : Mask.toCharArray()) {
            if (caracter == '1') {
                unos.add(caracter);
                if (firse0 == false) {
                    firse1 = !firse0;
                    // System.out.println("firse1 = " + firse1);
                }
            } else if (caracter == '0') {
                ceros.add(caracter);
                if (firse1 == false) {
                    firse0 = !firse1;
                    //System.out.println("firse0 = " + firse0);
                }
            }
        }
        unpaint1 = unos.size();
        unpaint0 = ceros.size();


        while (x > y) {

            if (firse0) estado = false;

            if (paint1 < unpaint1 && estado == true) {
                putPixel(xc + x, yc + y, c);
                putPixel(xc + y, yc + x, c);
                putPixel(xc - x, yc + y, c);
                putPixel(xc - y, yc + x, c);
                putPixel(xc - x, yc - y, c);
                putPixel(xc - y, yc - x, c);
                putPixel(xc + x, yc - y, c);
                putPixel(xc + y, yc - x, c);
                paint1++;
            } else {
                estado = false;
                con++;
                if (con > unpaint0) {
                    estado = true;
                    firse0 = false;
                    con = 0;
                    paint1 = 0;
                }
            }

            if (error <= 0) {
                y++;
                error = error + 2 * y + 1;
            } else if (error > 0) {
                x--;
                error = error - 2 * x + 1;
            }
        }
    }

    //Practica 16
    public void AlgoritmoGrosorDeCircunferencias(int x0, int y1, int r, int grosor, Color c) {
       //System.out.println("c = " + c);
        int x = r;
        int y = 0;
        int error = 1 - r;

        while (x >= y) {
            putPixel(x0 + x, y1 + y, grosor, c);
            putPixel(x0 + y, y1 + x, grosor, c);
            putPixel(x0 - x, y1 + y, grosor, c);
            putPixel(x0 - y, y1 + x, grosor, c);
            putPixel(x0 - x, y1 - y, grosor, c);
            putPixel(x0 - y, y1 - x, grosor, c);
            putPixel(x0 + x, y1 - y, grosor, c);
            putPixel(x0 + y, y1 - x, grosor, c);

            if (error <= 0) {
                y++;
                error = error + 2 * y + 1;
            } else if (error > 0) {
                x--;
                error = error - 2 * x + 1;
            }
        }

    }

    //Practica 17
    public void scanLine(int x0, int y0, int x1, int y1, Color c) {

        int x = x0, y = y0;
        Color pixelColor = new Color(fondo.getRGB(x, y));

        while (y<=y1) {
            pixelColor = new Color(fondo.getRGB(x, y));

            if(pixelColor.equals(Color.WHITE)) {
                putPixel(x, y, c);
            }
            if(x<x1) {
                x++;
            }
            else if(x==x1) {
                x=x0;
                y++;
            }


        }
    }
    

    //Practica 18
   public void floodFill(int x, int y, Color c) {
        int IntDelColorOld;
       IntDelColorOld = fondo.getRGB(x, y);
       Color ColorOld = new Color(IntDelColorOld);

        if (ColorOld.equals(Color.WHITE)) {
            putPixel(x, y, c);
            floodFill(x + 1, y, c);
            floodFill(x - 1, y, c);
            floodFill(x, y + 1, c);
            floodFill(x, y - 1, c);
        }
    }
 /* public void floodfill(int x, int y ,Color c){

        FontMetrics color1;
      color1 = graPixel.getFontMetrics();

      Color color2 = Color.black;

       if(color2.equals(color1)){
           putPixel(x,y,c);//no hay pixel
            floodfill(x + 1,y ,c);//norte
        }
      if(color2.equals(color1)){
          floodfill(x -1,y,c);//este
      }
      if(color2.equals(color1)){
          floodfill(x,y,c);//Sur
      }
      if(color2.equals(color1)){
          floodfill(x ,y,c);//Oeste
      }

    }*/

    //Practica 19

    //Practica 20


}