import java.applet.AudioClip;
import java.awt.Image;
import java.net.URL;
import java.util.Random;


public class Pictures {
	static Image platform;
	static Image earth;
	static URL url;
	static StartingPoint sp;
	static AudioClip Bounce;
	static AudioClip back;
	static int level = 1;
	public Pictures(StartingPoint sp){
		try{
			url = sp.getDocumentBase();
		}catch(Exception e){

		}

		Bounce = sp.getAudioClip(url, "Music\\bounce.au");
		back = sp.getAudioClip(url, "Music\\Alan.au");
		platform = sp.getImage(url, "Images\\Tiles.png");
		earth = sp.getImage(url, "Images\\Earth.gif");
		
		Pictures.sp = sp;
	}
}
