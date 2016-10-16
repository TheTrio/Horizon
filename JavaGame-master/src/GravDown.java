import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;


public class GravDown extends Item {

	private String s[] = new String[13];

	public GravDown(int x) {
		super(x);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.GREEN);
		super.paint(g);
	}

	@Override
	public void perform(Ball b, StartingPoint sp) {

		if (b.getGravity() > 3) {
			System.out.println("Grav Up");
			sp.AskQuestion(true);
			b.setGravity(b.getGravity() - 3);
			if (b.getGravity() < 3) {
				b.setGravity(3);
			}


		}
	}
}
