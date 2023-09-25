import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.List;
public class Graficos extends JFrame {
    private BufferedImage buffer;
    private Graphics pixel;
    private Color c = Color.red;

    public Graficos() {
        this.setResizable(false);
        this.setVisible(true);
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        pixel = (Graphics2D) buffer.createGraphics();
    }

    public void putPixel(int x, int y) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void lineaFormulaYm(int x0, int y0, int x1, int y1) {

        c = Color.blue;
        float m = (float) (y1 - y0) / (x1 - x0);
        float b = y0 - m * x0;
        float y;

        for (int x = x0; x < x1; x++) {

            y = m * x + b;
            putPixel(Math.round(x), Math.round(y));

        }


    }

    public void lineaMejorada(int x0, int y0, int x1, int y1) {

        c = Color.black;

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

                putPixel(x1, Math.round(Y));

            }
        } else if (y0 == y1) {

            for (int X = x0; X < x1; X++) {

                putPixel(X, y1);

            }
        } else {
            for (int x = x0; x < x1; x++) {

                y = m * x + b;
                putPixel(Math.round(x), Math.round(y));

            }
        }


    }


    public void LineaDDA(int x0, int y0, int x1, int y1) {

        c = Color.BLACK;
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

        putPixel(Math.round(x), Math.round(y));

        for (int k = 1; k < length; k++) {

            x = x + xinc;
            y = y + yinc;
            putPixel(Math.round(x), Math.round(y));
        }


    }

    public void lineaBresenham(int x0, int y0, int x1, int y1) {

        c = Color.CYAN;
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;
        int err = dx - dy;
        int e2;


        while (x0 != x1 || y0 != y1) {
            putPixel(x0, y0);

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

        putPixel(x1, y1);

    }

    public void drawLineaPM(int x0, int y0, int x1, int y1) {

        c = Color.BLACK;
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;

        int err = dx - dy;
        int x = x0;
        int y = y0;

        while (x != x1 || y != y1) {

            putPixel(x, y);

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

        putPixel(x1, y1);


    }

    public void drawRectangulo(int x, int y, int a, int l) {

        int x0 = x;
        int x1 = x + l;
        int y0 = y;
        int y1 = y + a;
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<Void>> futures = null;

        try {    //No tengo idea de por que pero no se dibuja bien sin este sleep especifico
            Thread.sleep(47);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            futures = executor.invokeAll(List.of(
                    () -> {
                        LineaDDA(x0, y0, x0, y1);
                        return null;
                    },
                    () -> {
                        LineaDDA(x1, y0, x1, y1);
                        return null;
                    },
                    () -> {
                        LineaDDA(x0, y1, x1, y1);
                        return null;
                    },
                    () -> {
                        LineaDDA(x0, y0, x1, y0);
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

    public void drawCirculoInef(int xc, int yc, int r) {

        double y;

        for (int x = xc - r; x < xc + r; x++) {

            y = yc + Math.sqrt(r * r - (x - xc) * (x - xc));
            putPixel(x, (int) Math.round(y));
            y = yc - Math.sqrt(r * r - (x - xc) * (x - xc));
            putPixel(x, (int) Math.round(y));

        }


    }

    public void drawCirculoPolar(int xc, int yc, int r) {

        double y;
        double x;
        for (double t = 0; t <= Math.PI * 2.05; t += 0.01) {

            x = xc + r * Math.sin(t);
            y = yc + r * Math.cos(t);
            putPixel((int) Math.round(x), (int) Math.round(y));

        }


    }

	/*
	public void drawCirculoSimetria8(int xc, int yc, int r) {

		Color c = Color.BLACK;

		double x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6, x7, y7, x8, y8;

		double piqu = Math.PI/4;
		double pimed = Math.PI/2;
		for(double t = 0; t<= Math.PI/2; t+=0.01) {

			x1 = xc + r*Math.sin(t/2 + pimed);
			y1 = yc + r*Math.cos(t/2 + pimed);
			x2 = xc + r*Math.sin(-t/2 + pimed);
			y2 = yc + r*Math.cos(-t/2 + pimed);
			x3 = xc + r*Math.sin(t/2 - pimed);
			y3 = yc + r*Math.cos(t/2 - pimed);
			x4 = xc + r*Math.sin(-t/2 - pimed);
			y4 = yc + r*Math.cos(-t/2 - pimed);
			x5 = xc + r*Math.sin(t/2 - piqu);
			y5 = yc + r*Math.cos(t/2 - piqu);
			x6 = xc + r*Math.sin(-t/2 - piqu);
			y6 = yc + r*Math.cos(-t/2 - piqu);
			x7 = xc + r*Math.sin(t/2 + piqu);
			y7 = yc + r*Math.cos(t/2 + piqu);
			x8 = xc + r*Math.sin(-t/2 + piqu);
			y8 = yc + r*Math.cos(-t/2 + piqu);



			putPixel((int)Math.round(x1),(int)Math.round(y1), Color.RED);
			putPixel((int)Math.round(x2),(int)Math.round(y2), Color.BLUE);
			putPixel((int)Math.round(x3),(int)Math.round(y3), Color.RED);
			putPixel((int)Math.round(x4),(int)Math.round(y4), Color.BLUE);
			putPixel((int)Math.round(x5),(int)Math.round(y5), Color.RED);
			putPixel((int)Math.round(x6),(int)Math.round(y6), Color.BLUE);
			putPixel((int)Math.round(x7),(int)Math.round(y7), Color.RED);
			putPixel((int)Math.round(x8),(int)Math.round(y8), Color.BLUE);



		}


	}*/
}
