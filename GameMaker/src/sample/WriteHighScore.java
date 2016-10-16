package sample;

import java.util.Formatter;

public class WriteHighScore {
    private int oldScore;
    private Formatter x;
    private String filename = "HighScore.txt";
    public static boolean isTrue = false;

    public void SetScore(int score, int oldScore){
        this.oldScore = oldScore;


        if(score > oldScore){
            isTrue = true;
            WriteData(score);


        }else {
            isTrue = false;
        }
    }

    private void WriteData(int score) {
        try{
            String name = String.valueOf(score);
            x = new Formatter(filename);
            x.format("%s", name);
            x.close();
        }catch (Exception er){

        }
    }


}
