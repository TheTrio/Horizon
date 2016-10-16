package sample;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.Normalizer;
import java.util.Formatter;
import java.util.Scanner;

public class ReadChatData {

    private String k = "Data.txt";
    private String names;
    private String pass;
    private PrintWriter pr;
    private File f;


    public void makeFile(String s){
        k = s;
        f = new File(k);
    }


    public void WriteFile(String a[], String userName){
        try {

            pr = new PrintWriter(new FileWriter(f, true));

            for(String xy : a){
                pr.println("");
                pr.println(userName + ":"+ xy);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error " + e.getStackTrace());
        }
    }

    public void closeFile(){
        pr.close();
    }



}








