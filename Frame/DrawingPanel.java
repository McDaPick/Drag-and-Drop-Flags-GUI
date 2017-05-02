package Frame;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Mikey
 */
public class DrawingPanel extends JPanel {

    private BufferedImage image;
    ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();

    public DrawingPanel() throws Exception {

    }

    @Override
    protected void paintComponent(Graphics g) {
        
        ArrayList<ImageIcon> images = null;
        try {
            images = new ArrayList<ImageIcon>(new LoadFlags().doInBackground());
        } catch (Exception ex) {
            Logger.getLogger(DrawingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        super.paintComponent(g);
          if (!flagShown) {
                    line = (Graphics2D) g.create();
                    Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                    line.setStroke(dashed);
                    line.drawLine(0, yLine, 700, yLine);
                    line.drawLine(xLine, 0, xLine, 700);
        for (int i = 0; i < images.size(); i++) {
            images.get(0).paintIcon(this, g, 0, 0);
       }
    }

    public void addCircle(ImageIcon flag, Graphics g) {
        System.out.println("Jack Black");
        images.add(images.get(0));
        repaint();
    }

}
