package sample;

import java.io.File;
import java.util.Scanner;

public class GiveSetting {
    private Scanner x;
    private String val[] = new String[2];
    private int i;

    public void OpenFile(){
        try {
            i = 0;
            x = new Scanner(new File("Setting.data"));
        }catch (Exception e){

        }

    }

    public void ReadFile(){
        while(x.hasNextLine()){
            val[i] = x.nextLine();
            i++;
        }
        i = 0;
    }

    public String GiveSound(){
        System.out.println(val[0]);
        return val[0];
    }

    public String GiveScreen(){
        System.out.println(val[1]);
        return val[1];
    }

    public void CloseFile(){
        x.close();
    }


}
