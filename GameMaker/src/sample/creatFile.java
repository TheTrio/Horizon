package sample;

import javax.swing.*;
import java.io.*;
import java.lang.*;
import java.text.Normalizer;
import java.util.*;

        public class creatFile {

            private Formatter x;

            public void makeFile(String name){
                try{
                    x = new Formatter(name);
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Error Making File. Error no. " + e );
                }


    }

    public void addRecords(String values[]){
        x.format("%s\t%s\n", values[0], values[1]);

    }

    public void closeFile(){
        x.close();
    }

}
