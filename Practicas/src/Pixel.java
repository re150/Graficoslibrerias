import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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
        super.paint(g);
        putPixel(80, 80, Color.BLACK);
    }
    public void putPixel(int x, int y,Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void lineaFormulaYm(int x0, int y0, int x1, int y1, Color c) {

        float m = (float) (y1 - y0) / (x1 - x0);
        float b = y0 - m * x0;
        float y;

        for (int x = x0; x < x1; x++) {

            y = m * x + b;
            putPixel(Math.round(x), Math.round(y),c);

        }


    }

    public void lineaMejorada(int x0, int y0, int x1, int y1, Color c) {

        float m = (float) (y0 - y1) / (x0 - x1);
        float b = y1 - m * x1;
        float y;
        boolean con1 = (y0 - y1) < 0 ? false : true;
        boolean con2 = (x0 - x1) < 0 ? false : true;


        if (con1) {
            int temp;
            temp = y1;
            y1 = y0;
            y0 = temp;
        }

        if (con2) {
            int temp;
            temp = x1;
            x1 = x0;
            x0 = temp;
        }


        if (x1 == x0) {

            for (int Y = y0; Y < y1; Y++) {

                putPixel(x1, Math.round(Y), c);

            }
        } else if (y0 == y1) {

            for (int X = x0; X < x1; X++) {

                putPixel(X, y1,c);

            }
        } else {
            for (int x = x0; x < x1; x++) {

                y = m * x + b;
                putPixel(Math.round(x), Math.round(y),c);

            }
        }


    }


    public void LineaDDA(int x0, int y0, int x1, int y1, Color c) {

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

    public void lineaBresenham(int x0, int y0, int x1, int y1, Color c ) {


        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;
        int err = dx - dy;
        int e2;


        while (x0 != x1 || y0 != y1) {
            putPixel(x0, y0,c);

            e2 = 2 * err;
            if (e2 > -dy) {
                err = err - dy;
                x0 = x0 + sx;
            }
            if (e2 < dx) {
                err = err + dx;
                y0 = y0 + sy;
            }

        }

        putPixel(x1, y1,c);

    }

    public void LineaPM(int x0, int y0, int x1, int y1, Color c) {

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;

        int err = dx - dy;
        int x = x0;
        int y = y0;

        while (x != x1 || y != y1) {

            putPixel(x, y,c);

            int err2 = 2 * err;
            if (err2 > -dy) {
                err = err - dy;
                x = x + sx;

            }
            if (err2 < dx) {

                err = err + dx;
                y = y + sy;

            }
        }

        putPixel(x1, y1, c);


    }

    public void Rectangulo(int x, int y, int a, int l, Color c) {

        int x0 = x;
        int x1 = x + l;
        int y0 = y;
        int y1 = y + a;
        ExecutorService executor = Executors.newFixedThreadPool(4);
        java.util.List<Future<Void>> futures = null;

        try {
            futures = executor.invokeAll(List.of(
                    () -> {
                        LineaDDA(x0, y0, x0, y1, c);
                        return null;
                    },
                    () -> {
                        LineaDDA(x1, y0, x1, y1,c);
                        return null;
                    },
                    () -> {
                        LineaDDA(x0, y1, x1, y1, c);
                        return null;
                    },
                    () -> {
                        LineaDDA(x0, y0, x1, y0, c);
                        return null;
                    }
            ));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void CirculoInef(int xc, int yc, int r, Color c) {

        double y;

        for (int x = xc - r; x < xc + r; x++) {

            y = yc + Math.sqrt(r * r - (x - xc) * (x - xc));
            putPixel(x, (int) Math.round(y),c);
            y = yc - Math.sqrt(r * r - (x - xc) * (x - xc));
            putPixel(x, (int) Math.round(y), c);

        }


    }

    public void CirculoPolar(int xc, int yc, int r, Color c) {

        double y;
        double x;
        for (double t = 0; t <= Math.PI * 2.05; t += 0.01) {

            x = xc + r * Math.sin(t);
            y = yc + r * Math.cos(t);
            putPixel((int) Math.round(x), (int) Math.round(y), c);

        }


    }
    public static void main(String[] args){
        Pixel g = new Pixel();


        g.putPixel(50,200, Color.BLACK);
       /* g.lineaFormulaYm(0,400,400,0,Color.black );
        g.lineaMejorada(0,250,300,0, Color.blue);
        g.lineaFormulaYm(0,150,200,0, Color.black);
        g.LineaDDA(0,300,300,0, Color.black);
        g.lineaBresenham(0,200,200,150, Color.black);
        g.LineaPM(100,500,400,50, Color.black);
        g.Rectangulo(100,100,100,200, Color.black);
        g.CirculoInef(100,100,100,Color.black);
        g.CirculoPolar(200,200,100,Color.black);*/

    }

}
