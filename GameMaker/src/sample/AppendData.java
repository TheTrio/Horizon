package sample;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.Normalizer;
import java.util.Formatter;
import java.util.Scanner;

public class AppendData {

    private String k = "Data.txt";
    private String names;
    private String pass;
    private PrintWriter pr;
    private File f = new File(k);



    public void WriteFile(String a,String b){
        try {
            pr = new PrintWriter(new FileWriter(f, true));
            pr.println();
            pr.print(a + "\t" + b);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error " + e.getStackTrace());
        }
    }

    public void closeFile(){
        pr.close();
    }



}








