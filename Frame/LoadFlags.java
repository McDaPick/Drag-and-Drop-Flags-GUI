package Frame;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.JProgressBar;

/**
 *
 * @author Mikey
 */
public class LoadFlags extends SwingWorker<HashMap<String, ImageIcon>, String> {

    File[] listOfFiles;
    HashMap <String, ImageIcon> flags;
    String loadingMessage;
    JProgressBar loader;
    JLabel loading;
    int n;
    JFrame splash;

    public LoadFlags(JProgressBar bar, JLabel label, JFrame splasher) {
        loader = bar;
        loading = label;
        splash = splasher;
    }

    public LoadFlags() {

    }

    @Override
    public HashMap<String, ImageIcon> doInBackground() throws FileNotFoundException {
        File fileFolder = new File("C:\\Users\\Mikey\\Documents\\NetBeansProjects\\CSCHW05\\src\\Pictures");
        listOfFiles = fileFolder.listFiles();
        flags = new HashMap<>();
        n = 0;
        for (File i : listOfFiles) {
            n++;
            loader.setValue(n);
            String holder = i.getName();
            loadingMessage = "Loading " + holder;
            loading.setText(loadingMessage);
            flags.put(holder.substring(0, holder.lastIndexOf(".")), new ImageIcon(i.getAbsolutePath()));
        }
//        splash.dispose();
        return flags;
    }


    public HashMap<String, ImageIcon> getFlags() {
        File fileFolder = new File("C:\\Users\\Mikey\\Documents\\NetBeansProjects\\CSCHW05\\src\\Pictures");
        listOfFiles = fileFolder.listFiles();
        flags = new HashMap<>();
        n = 0;
        for (File i : listOfFiles) {
            String holder = i.getName();
            flags.put(holder.substring(0, holder.lastIndexOf(".")), new ImageIcon(i.getAbsolutePath()));
        }
        return flags;
    }
    


    
    public int updateBar() throws IOException {
        int update = 0;
        File folder = new File("C:\\Users\\Mikey\\Documents\\NetBeansProjects\\CSCHW05\\src\\Pictures");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            update = i;
        }

        return update;
    }
}