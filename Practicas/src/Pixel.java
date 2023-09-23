import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Pixel  extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;
    public Pixel(){
        setSize(500,500);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        putPixel(80,80,Color.BLACK);
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }
    public static void linea (int x0, int y0,int x1, int y1) {
        Graficos g = new Graficos();
     //   g.drawLineaImperfecta(x0,y0,x1,y1);
        g.drawRectangulo(100,100,100,200);
    }
    public static void main(String[] args){
         //   new Pixel();

            linea(0,400,400,0);
    }


}
