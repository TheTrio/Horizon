package sample;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class ReadFile {
    private Scanner x;

    String name;
    String values[];
    private int i = 0;
    private String users[] = new String[10];
    private String passwords[] = new String[10];


    public void OpenFile(String file){
        name = file;

        try{
            x = new Scanner(new File(file));
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "File Not Found");
        }
    }

    public void readFiles(){
        while (x.hasNext()){
            users[i] = x.next();
            passwords[i] = x.next();
            i++;
        }
    }

    public String[] giveuserfiles(){
        return users;
    }


    public String[] givepasswordfiles(){
        return passwords;
    }


}
