import java.applet.Applet;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Random;

public class Ball extends Applet{
	private double Gravity = 15;
	private double energyloss = 1;
	private double xFriction = .9;
	private double dt = .2;
	private int x = 400;
	private int y = 25;
	private double dx = 0;
	private double dy = 0;
	private int radius = 20;
	private double gameDy = -75;
	private int maxSpeed = 20;
	Random r = new Random();
	private int agility = 3;
	private boolean game_over = false;

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public Ball() {
		// TODO Auto-generated constructor stub
	}

	public Ball(int i, int j) {
		x = i;
		y = j;
	}

	public double getGameDy() {
		return gameDy;
	}

	public void setGameDy(double gameDy) {
		this.gameDy = gameDy;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getDx() {
		return dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public double getGravity() {
		return Gravity;
	}

	public void setGravity(double gravity) {
		Gravity = gravity;
	}

	public void setGravity(int more) {
		Gravity-=more;
	}

	public void moveRight() {
		if (dx + agility < maxSpeed) {
			dx += agility;
		}
	}

	public void moveLeft() {
		if (dx - agility > -maxSpeed) {
			dx -= agility;
		}
	}

	public void update(StartingPoint sp) {
		//Physics Formulas

		if (x + dx > sp.getSize().width - radius - 1) {
			x = sp.getSize().width - radius - 1;
			dx = -dx;
		} else if (x + dx < 0 + radius) {
			x = 0 + radius;
			dx = -dx;
		} else {
			x += dx;
		}
        // Managing Friction
		if (y == sp.getHeight() - radius - 1) {
			dx *= xFriction;
			// Finding the location
            if (Math.abs(dx) < 1) {
				dx = 0;
			}
		}
		if (y - 200 > sp.getHeight() - radius - 1) {
			game_over = true;
		} else {
			// Velocity


            // V = u + at
            dy = dy + Gravity * dt;
            // S = ut + 1/2 at^2
			y += dy * dt + .5 * Gravity * dt * dt;
		}

	}

	public void paint(Graphics g) {

		g.fillOval(x - radius, y - radius, radius*2, radius*2);

		g.drawImage(Pictures.earth,x - radius, y - radius,radius*2, radius*2,this);

		

	}

	public int getRadius() {
		// TODO Auto-generated method stub
		return radius;
	}

	public boolean getGameOver() {

		return game_over;
	}
}
