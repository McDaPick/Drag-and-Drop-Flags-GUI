package Frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Mikey
 */
public class Frame extends JFrame implements DropTargetListener {

    public JList list;
    public DrawingPanel screen;
    public DropTarget dropTarget;
    public JSlider northSlide = new JSlider(0, 330, 165);
    public JSlider southSlide = new JSlider(0, 330, 165);
    public JSlider eastSlide = new JSlider(0, 330, 165);
    public JSlider westSlide = new JSlider(0, 330, 165);
    HashMap<String, ImageIcon> flags;


    public Frame(String title) throws Exception {
        super(title);
        setLocationRelativeTo(null);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponents(getContentPane());
        addListeners();
        setVisible(true);
         try {
            flags = new LoadFlags().doInBackground();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addComponents(Container contentPane) throws FileNotFoundException, Exception {
        JList list = new JList(getFlagNames());
        list.setDragEnabled(true);

        DrawingPanel screen = new DrawingPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                ArrayList<ImageIcon> images = null;
                try {
                    images = new ArrayList<ImageIcon>(new LoadFlags().doInBackground());
                } catch (Exception ex) {
                    Logger.getLogger(DrawingPanel.class.getName()).log(Level.SEVERE, null, ex);
                }

                super.paintComponent(g);
                for (int i = 0; i < images.size(); i++) {
                    images.get(0).paintIcon(this, g, northSlide.getValue(), westSlide.getValue());
                }
            }

        };
        screen.setLayout(new BorderLayout());

        dropTarget = new DropTarget(screen, this);

            
        
        eastSlide.setOrientation(JSlider.VERTICAL);

        westSlide.setOrientation(JSlider.VERTICAL);

        screen.add(northSlide, BorderLayout.NORTH);

        screen.add(southSlide, BorderLayout.SOUTH);

        screen.add(eastSlide, BorderLayout.EAST);

        screen.add(westSlide, BorderLayout.WEST);

        contentPane.setLayout(
                new BorderLayout());
        contentPane.add(screen, BorderLayout.CENTER);

        contentPane.add(list, BorderLayout.WEST);

    }

    public void addListeners() {

        northSlide.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if (northSlide.getValue() != southSlide.getValue()) {
                    southSlide.setValue(northSlide.getValue());
                    System.out.println(northSlide.getValue());
                }
            }
        });

        southSlide.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (southSlide.getValue() != northSlide.getValue()) {
                    northSlide.setValue(southSlide.getValue());
                }
            }
        });
        eastSlide.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (eastSlide.getValue() != westSlide.getValue()) {
                    westSlide.setValue(eastSlide.getValue());
                }
            }
        }
        );
        westSlide.addChangeListener(
                new ChangeListener() {

                    @Override
                    public void stateChanged(ChangeEvent e) {
                        if (westSlide.getValue() != eastSlide.getValue()) {
                            eastSlide.setValue(westSlide.getValue());
                        }
                    }
                }
        );
    }

    public String[] getFlagNames() throws Exception {
        File folder = new File("C:\\Users\\Mikey\\Documents\\NetBeansProjects\\CSCHW05\\src\\Pictures");
        File[] listOfFiles = folder.listFiles();
        String[] fileNames = new String[197];

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                fileNames[i] = listOfFiles[i].getName();
            } else if (listOfFiles[i].isDirectory()) {
            }
        }
        return fileNames;
    }

//    private boolean dropComponent(Transferable tf) {
//       currentFlag = (String) tf.getTransferData(DataFlavor.stringFlavor);
//        
//        return true;
//
//    }
    @Override
    public void drop(DropTargetDropEvent dtde) {
        if ((dtde.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0) {
            dtde.acceptDrop(dtde.getDropAction());
            Transferable tf = dtde.getTransferable();

            try {
                boolean result = dropComponent(tf);
                dtde.dropComplete(result);

            } catch (Exception e) {
                dtde.dropComplete(false);
            }

        }
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}