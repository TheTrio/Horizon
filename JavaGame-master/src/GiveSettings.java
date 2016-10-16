import java.io.File;
import java.net.URL;
import java.util.Scanner;

public class GiveSettings {
    private Scanner x;
    private String val[] = new String[2];
    private int i;
    private File f;

    public void OpenFile(URL url){
        try {
            i = 0;
            String s = url.toString();
            s = s.substring(6, s.length());
            s = s.replaceAll("/p.html", "");
            f = new File(s + "/Setting.data");
            x = new Scanner(f);
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
