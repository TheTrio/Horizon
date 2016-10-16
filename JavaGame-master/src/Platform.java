import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Platform {
	int dx;
	static boolean godMode = false;
	int x, y, width, height;
	Image Plat;
		URL url;
		float frame = 0;
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Platform() {
		
		dx = -1;
		x = 450;
		y = 300;
		width = 120;
		height = 40;
		dx = -10;

	}

	public Platform(int x, int y) {
		this.x = x;
		this.y = y;
		width = 120;
		height = 40;
		dx = -1;
		Plat = Pictures.platform;
		
	}

	public void update(StartingPoint sp, Ball b) {
		int tester = (int)(frame + .1);
		if(tester < 3){
			frame+=.1;
		} else {
			frame = 0;
		}
		x += -(Pictures.level + .5);
		
		check(b);
		if (x < 0 - width) {
			Random r = new Random();
			y = sp.getHeight() - 40 - r.nextInt(400);
		}
	}

	private void check(Ball b) {
        int ballx = b.getX();
        int bally = b.getY();
        int radius = b.getRadius();

        //if (bally + radius > y && bally + radius < y + height) {
		if(b.getDy() < 0) {
			if (bally - radius < y + height && bally - radius > y) {
				if (ballx + radius > x && ballx + radius < x + width + 25) {


					double newDy = b.getGameDy() * -0.5;

					b.setY(y + height + radius);
					b.setDy(newDy);
				}
			}
		}
        if (bally + radius > y && bally + radius < y + height) {
            if (ballx + radius > x && ballx + radius < x + width + 25) {
                Double newDy = b.getGameDy();
                b.setY(y - radius);
                b.setDy(newDy);
                Pictures.Bounce.play();

			}
        }



		if(godMode==true){
			if (bally + radius > 850 && bally + radius < 850 + height) {

					Double newDy = b.getGameDy();
					b.setY(y - radius);
					b.setDy(newDy);
					Pictures.Bounce.play();


		}
	}}

	public void paint(Graphics g) {

		//g.setColor(Color.BLUE);
		
		//g.drawImage(Plat, x, y, Pictures.sp);
		g.drawImage(Plat, x, y, x + width, y + height, 0, 40*(int)frame, 120, 40*(int)frame + 40, Pictures.sp);
	}
}
