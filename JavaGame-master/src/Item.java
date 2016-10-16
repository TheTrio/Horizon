import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Item {
	private int x, y, dx, radius;
	private StartingPoint sp;
	private boolean createNew = false;
	public boolean isCreateNew() {
		return createNew;
	}
	public void setCreateNew(boolean createNew) {
		this.createNew = createNew;
	}
	public Item(int x) {
		// TODO Auto-generated constructor stub\
		this.x = x;
		Random r = new Random();
		y = r.nextInt(400) + radius;
		radius = 10;
		dx = -2;
		
	}
	public int getY() {
		return y;
	}
	
	public void update(StartingPoint sp, Ball b) {
		this.sp = sp;
		x+=dx;
		check(b);
		
		if(x < 0 - radius){
			createNew = true;
		}
	}
	
	private void check(Ball b) {
		int ballx = b.getX();
		int ballz = b.getY();
		int Ballradius = b.getRadius();
		
		int a = x - ballx;
		int bb = y - ballz;
		int collide = radius + Ballradius;
		double c = Math.sqrt((double) (a*a) + (double) (bb*bb));
		if(c < collide){
			perform(b, sp);
			createNew = true;
		}
	}
	public void perform(Ball b, StartingPoint sp) {

		b.setGravity(b.getGravity() + 3);
	}

	public void paint(Graphics g) {

		//g.setColor(Color.GREEN);
		g.fillOval(x - radius, y - radius, radius*2, radius*2);
		

	}
}
