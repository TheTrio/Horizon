package sample;

import java.io.File;
import java.util.Scanner;

public class HighScoreCheck {

    private File f;
    private Scanner x;
    private String file;
    private int score;
    private String temp;

    public void OpenFile(String name) {

        f = new File(name);
        if (f.exists()) {
            try {
                file = name;

                x = new Scanner(f);
            } catch (Exception e) {
                System.out.println("Error is  " + e);
            }
        }else {

        }
    }
    public void TakeFiles(){
        while(x.hasNext()){

            score = Integer.parseInt(x.next());
            System.out.println(score);
            temp = String.valueOf(score);
        }
    }

    public String giveString(){
        return temp;
    }

    public int giveFiles(){
        return score;
    }

    public void CloseFile(){
        x.close();
    }
}
