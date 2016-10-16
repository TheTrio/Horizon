import java.awt.Color;
import java.awt.Graphics;

public class AgilUp extends Item {

	public AgilUp(int x) {
		super(x);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.magenta);
		super.paint(g);
	}
	
	@Override
	public void perform(Ball b, StartingPoint sp) {
		// TODO Auto-generated method stub
		if(b.getAgility() <8){
			System.out.println("Grav Up");
			sp.AskQuestion(true);
		b.setAgility(b.getAgility() + 1);
		} 
	}
}
	