package sample;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class ReadMessages {
    private Scanner x;

    String name;
    private int i = 0;
    private String users[] = new String[50];


    public void OpenFile(String file){
        name = file;

        try{
            x = new Scanner(new File(name));
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "File Not Found");
        }
    }

    public void readFiles(){
        while (x.hasNextLine()){
            users[i] = x.nextLine();
            i++;
        }
    }

    public String[] giveuserfiles(){
        return users;
    }





}
