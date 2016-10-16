import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

public class GravUp extends Item {

	public GravUp(int x) {
		super(x);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		super.paint(g);
	}

	@Override
	public void perform(Ball b, StartingPoint sp) {
		// TODO Auto-generated method stub
		if(b.getGravity() > 3){
			System.out.println("Grav Up");
			sp.AskQuestion(true);
			b.setGravity(b.getGravity() + 3);
		}else if(b.getGravity() < 3){
			b.setGravity(3);

		}
	}


	
	
	

}
