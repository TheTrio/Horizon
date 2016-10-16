package sample;

import javax.swing.*;
import java.io.*;
import java.lang.*;
import java.text.Normalizer;
import java.util.*;

public class ChatData {
    private Formatter x;
    private File f;
    private String name;

    public void makefile(String s){
        try {
            name = s;
            x = new Formatter(s);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error Code " + e.getStackTrace());
        }
    }



    public void addRecords(String s[], String userName){
        String newline = System.getProperty("line.separator");
        for(String xyz : s){

            if(xyz.equals("")){

            }else {
                x.format("%s:%s", userName, xyz);
            }
        }

    }

    public void closeFile(){
        x.close();
    }



}
