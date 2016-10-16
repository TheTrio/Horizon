import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Score extends Item{
	private StartingPoint appletInfo;
	public Score(int x, StartingPoint appletInfo) {
		super(x);
		this.appletInfo = appletInfo;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		super.paint(g);
	}
	
	@Override
	public void perform(Ball b, StartingPoint sp) {
		// TODO Auto-generated method stub
		System.out.println("Grav Up");
		sp.AskQuestion(true);
		Random r = new Random();
		appletInfo.setScore(appletInfo.getScore() + 500 + r.nextInt(2000));
	}

}
