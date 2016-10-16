package sample;

import javax.swing.*;
import java.io.File;
import java.util.*;

public class MakeData {
    File file;
    creatFile cr_File;
    String name;



    public MakeData(String fileName){
        file = new File(fileName);
        name = fileName;

    }

    public boolean check_exists(String values[]){

        if(file.exists()){
            JOptionPane.showMessageDialog(null, "File Exists");
        }else {
            cr_File = new creatFile();
            cr_File.makeFile(name);

            cr_File.addRecords(values);
            cr_File.closeFile();



        }



       return false;
    }

    public boolean onlyCheck(){
        if(file.exists()){
            return true;
        }else {
            return false;
        }
    }
}
