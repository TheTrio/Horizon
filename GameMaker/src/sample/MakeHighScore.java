package sample;

import javax.swing.*;
import java.io.File;
import java.util.Formatter;

public class MakeHighScore {
    private File f;
    private Formatter format;

    public void OpenFile(String name, String user){
        f = new File(name);

        if(f.exists()){
            JOptionPane.showMessageDialog(null, "Error in File(Error Code 272)", "Error", JFrame.ERROR);
        }else {
            MakeFile(user);
        }
    }

    private void MakeFile(String user) {
        try {
            format = new Formatter(f);
            format.format("%s %s", 0);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error in " + e, "Error", JFrame.ERROR);
        }
    }

}
