import com.sun.media.jfxmedia.events.PlayerStateEvent;
import com.sun.org.apache.xpath.internal.SourceTree;

import javax.swing.*;
import java.applet.Applet;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.Random;

import static com.sun.javaws.BrowserSupport.showDocument;

public class StartingPoint extends Applet implements Runnable, KeyListener,
		MouseMotionListener, MouseListener {


	int ij = 1;

	Ball b;
	private Image i;
	private Graphics gang;
	Platform p[];
	Item ito[] = new Item[3];
	private int score;
	double cityX = 0;
	double cityDx = .5;
	private int levelcheck = 0;
	boolean mousein = false;
	boolean gameOver = false;
	private boolean x = false;
	protected String pics[] = {"Images\\Taj.png", "Images\\Wall.png", "Images\\Chic.png", "Images\\Macho.png", "Images\\Rio.png", "Images\\Rome.png",  "Images\\Petra.png"};
	Random rw = new Random();
	public int sw;
	StartingPoint sp;
	URL url;
	Image city;

	GiveSettings gr;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void init() {

		sp = this;
		gr = new GiveSettings();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		url = sp.getDocumentBase();
		double width = 850;
		double height = 700;


		gr.OpenFile(url);
		gr.ReadFile();
		gr.CloseFile();
		if(gr.GiveSound().endsWith("On")){
		}else {

		}

		if(gr.GiveScreen().endsWith("On")){
		    p = new Platform[13];
			width = screenSize.getWidth();
			height = screenSize.getHeight();
		} else {

            p = new Platform[7];
		}

		setSize((int)width, (int)height);
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		try {
			url = getDocumentBase();
			System.out.println(url);
		} catch (Exception e) {
	}
	sw = rw.nextInt(7);
	city = getImage(url, pics[sw]);
	Pictures P = new Pictures(this);
	}


	public void start() {

		Pictures.back.loop();

		if(gr.GiveSound().endsWith("Off")){
			Pictures.back.stop();
		}

		score = 0;
		b = new Ball();
		for (int i = 0; i < p.length; i++) {
			Random r = new Random();

			p[i] = new Platform(i * 120, 300);
		}

		for (int i = 0; i < ito.length; i++) {
			Random r = new Random();
			switch (r.nextInt(5)) {
				case 0:
					ito[i] = new GravUp(getWidth() + 2000 * i);

					break;
				case 1:
					ito[i] = new GravDown(getWidth() + 2000 * i);
					break;
				case 2:
					ito[i] = new AgilUp(getWidth() + 2000 * i);
					break;
				case 3:
					ito[i] = new AgilDown(getWidth() + 2000 * i);
					break;
				case 4:
					ito[i] = new Score(getWidth() + 2000 * i, this);
			}

		}
		Thread t1 = new Thread(this);
		t1.start();
	}


	public void run() {

		while (true) {

			for (int i = 0; i < p.length; i++) {
				int testx = p[i].getX();
				if (testx < 0 - p[i].getWidth()) {
					Random r = new Random();
					int fakei = i;
					if (i == 0) {
						fakei = p.length;
					}
					p[i].setX(p[fakei - 1].getX() + p[i].getWidth()
							+ Pictures.level * 10 + r.nextInt(25));
				}
			}
			gameOver = b.getGameOver();

			AskQuestion(false);


			if (cityX > getWidth() * -1) {
				cityX -= cityDx;
			} else {
				cityX = 0;
			}
			if (gameOver != true) {
				score++;
			}

			Random r = new Random();
			for (int i = 0; i < ito.length; i++) {
				if (ito[i].isCreateNew()) {
					ito[i] = null;
					switch (r.nextInt(5)) {
						case 0:
							ito[i] = new GravUp(getWidth() + 10 * r.nextInt(500));
							break;

						case 1:
							ito[i] = new GravDown(getWidth() + 10 * r.nextInt(500));
							break;
						case 2:
							ito[i] = new AgilUp(getWidth() + 10 * r.nextInt(500));
							break;
						case 3:
							ito[i] = new AgilDown(getWidth() + 10 * r.nextInt(500));
							break;
						case 4:
							ito[i] = new Score(getWidth() + 10 * r.nextInt(500),
									this);
					}
					ito[i].setCreateNew(false);
				}
			}
			b.update(this);

			for (int i = 0; i < p.length; i++) {
				p[i].update(this, b);
			}
			for (int i = 0; i < ito.length; i++) {
				ito[i].update(this, b);
			}

			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void AskQuestion(Boolean xy) {


		if(xy) {
			String ans;
			if (pics[sw].equals("Images\\Taj.png")) {
				Random r = new Random();
				int x = r.nextInt(5);
				switch (x) {
					case 0:
						JOptionPane.showMessageDialog(null, "DID YOU KNOW ?\n The construction of the Taj Mahal was begun in 1632, a year after the death of Empress Mumtaz.\n The construction was completed in the year 1653,\n which means that it took approximately 22 years to complete this astounding piece of architecture.");
						break;
					case 1:
						JOptionPane.showMessageDialog(null, "DID YOU KNOW ?\n At that time, the estimated construction cost was a whopping sum of Rs. 32 million,\n which, when considered in terms of today’s value of money,\n would be something way above $1 billion.");

						break;
					case 2:
						JOptionPane.showMessageDialog(null, "More than 20,000 people were put into building the Taj\n, including labourers, stonecutters, painters, embroidery artists, calligraphers, and many others.");

						break;
					case 3:

						JOptionPane.showMessageDialog(null, "The magnificent edifice is bounded on three sides by red stone walls.\n It is constructed completely of white marble.\n The emperor had marbles of the best quality brought from Rajasthan, Afghanistan, Tibet and China.");
						break;
					case 4:
						JOptionPane.showMessageDialog(null, "It is believed that more than 28 different types of precious and semi-precious stones,\n including the striking lapis lazuli, were inlaid into the marble.");

						break;

				}


			} else if (pics[sw].equals("Images\\Wall.png")) {
				ans = JOptionPane.showInputDialog("When was the great Wall of China built ?");
				if (ans.equalsIgnoreCase("200BC") || ans.equalsIgnoreCase("200BCE")) {
					score += 500;
					JOptionPane.showMessageDialog(null, "Good, 500 Points gained");
				} else {
					score -= 500;

					JOptionPane.showMessageDialog(null, "Incorrect Answer, 500 points lost.");
				}

			} else if (pics[sw].equals("Images\\Rome.png")) {
				ans = JOptionPane.showInputDialog("In which Country is it located");
				if (ans.equalsIgnoreCase("Rome") || ans.equalsIgnoreCase("Rome")) {
					score += 500;
					JOptionPane.showMessageDialog(null, "Good, 500 Points gained");
				} else {
					score -= 500;

					JOptionPane.showMessageDialog(null, "Incorrect Answer, 500 points lost.");
				}

			} else if (pics[sw].equals("Images\\Rio.png")) {
				ans = JOptionPane.showInputDialog("In which Country is Rio?");
				if (ans.equalsIgnoreCase("Brazil")) {
					score += 500;
					JOptionPane.showMessageDialog(null, "Good, 500 Points gained");
				} else {
					score -= 500;

					JOptionPane.showMessageDialog(null, "Incorrect Answer, 500 points lost.");
				}

			} else if (pics[sw].equals("Images\\Chic.png")) {
				ans = JOptionPane.showInputDialog("In which Country is Chichen Itza located");
				if (ans.equalsIgnoreCase("Mexico") || ans.equalsIgnoreCase("|Mexico")) {
					score += 500;
					JOptionPane.showMessageDialog(null, "Good, 500 Points gained");
				} else {
					score -= 500;

					JOptionPane.showMessageDialog(null, "Incorrect Answer, 500 points lost.");
				}

			}
		}
		if (levelcheck > 1000) {
			Pictures.level++;
			sw = rw.nextInt(7);
			levelcheck = 0;

			city = getImage(url, pics[sw]);
			System.out.println(pics[sw]);
			String ans;
			if(pics[sw].equals("Images\\Taj.png")){
				Random r = new Random();
				int x = r.nextInt(5);

				switch (x){
					case 0:
						ans = JOptionPane.showInputDialog("Where is TAJ MAHAL Located ? ");
						if(ans.equalsIgnoreCase("Agra")){
							score+=300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						}else {
							score-= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}
						break;
					case 1:
						ans = JOptionPane.showInputDialog("Who built the TAJ MAHAL");
						if(ans.equalsIgnoreCase("Shah Jahan") || ans.equalsIgnoreCase("ShahJahan")){
							score+=300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						}else {
							score-= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}
						break;
					case 2:
						ans = JOptionPane.showInputDialog("What is TAJ MAHAL made of ? ");
						if(ans.contains("Marble")){
							score+=300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						}else {
							score-= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}
						break;
					case 3:
						ans = JOptionPane.showInputDialog("Which Dynasty was TAJ MAHAL built in ? ");
						if(ans.contains("Mughal")){
							score+=300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						}else {
							score-= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}
						break;
					case 4 :
						ans = JOptionPane.showInputDialog("For Whom was TAJ MAHAL made ? ");
						if(ans.contains("Mumtaz")){
							score+=300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						}else {
							score-= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}
						break;

				}


			}else if(pics[sw].equals("Images\\Wall.png")){
				ans = JOptionPane.showInputDialog("When was the great Wall of China built ?");
				if(ans.equalsIgnoreCase("200BC") || ans.equalsIgnoreCase("200BCE")){
					score+=500;
					JOptionPane.showMessageDialog(null, "Good, 500 Points gained");
				}else {
					score-= 500;

					JOptionPane.showMessageDialog(null, "Incorrect Answer, 500 points lost.");
				}

			}else if(pics[sw].equals("Images\\Rome.png")){
				ans = JOptionPane.showInputDialog("In which Country is it located");
				if(ans.equalsIgnoreCase("Rome") || ans.equalsIgnoreCase("Rome")){
					score+=500;
					JOptionPane.showMessageDialog(null, "Good, 500 Points gained");
				}else {
					score-= 500;

					JOptionPane.showMessageDialog(null, "Incorrect Answer, 500 points lost.");
				}

			}else if(pics[sw].equals("Images\\Rio.png")){
				ans = JOptionPane.showInputDialog("In which Country is Rio?");
				if(ans.equalsIgnoreCase("Brazil")){
					score+=500;
					JOptionPane.showMessageDialog(null, "Good, 500 Points gained");
				}else {
					score-= 500;

					JOptionPane.showMessageDialog(null, "Incorrect Answer, 500 points lost.");
				}

			}else if(pics[sw].equals("Images\\Chic.png")){
				ans = JOptionPane.showInputDialog("In which Country is Chichen Itza located");
				if(ans.equalsIgnoreCase("Mexico") || ans.equalsIgnoreCase("|Mexico")){
					score+=500;
					JOptionPane.showMessageDialog(null, "Good, 500 Points gained");
				}else {
					score-= 500;

					JOptionPane.showMessageDialog(null, "Incorrect Answer, 500 points lost.");
				}

			}

		}
		levelcheck++;
	}

	@Override
	public void stop() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void update(Graphics g) {
		if (i == null) {
			i = createImage(this.getSize().width, this.getSize().height);
			gang = i.getGraphics();
		}

		gang.setColor(getBackground());
		gang.fillRect(0, 0, this.getSize().width, this.getSize().width);

		gang.setColor(getForeground());
		paint(gang);

		g.drawImage(i, 0, 0, this);

	}

	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(15, 77, 147));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(city, (int) cityX, 0, this);
		g.drawImage(city, (int) cityX + getWidth(), 0, this);
		b.paint(g);
		for (int i = 0; i < p.length; i++) {
			p[i].paint(g);
		}

		for (int i = 0; i < ito.length; i++) {
			ito[i].paint(g);
		}

		String s = "Score " + Integer.toString(score);
		Font f = new Font("Serif", Font.BOLD, 32);
		g.setFont(f);
		g.setColor(Color.BLACK);
		g.drawString(s, getWidth() - 150 + 2, 50 + 2);
		g.setColor(new Color(198, 226, 255));
		g.drawString(s, getWidth() - 150, 50);
		String sr = "Level " + Pictures.level;
		//g.drawString(sr, getWidth() - 150 + 2, 50 + 2);
		g.setFont(f);
		g.setColor(Color.RED);

		if(gameOver!=true && Platform.godMode!=true){
			if(sp.getWidth() > 850)
				g.drawString("Level " + Pictures.level, getWidth() - 150, getHeight() - 100 * 7);
			else
				g.drawString("Level " + Pictures.level, getWidth() - 150, getHeight() - 100 * 6);
		}else if(gameOver!=true && Platform.godMode==true){
			g.clearRect(750,100,20,20);
			g.setColor(Color.magenta);
			g.drawRect(750,100,20,20);
			g.setColor(Color.RED);
			g.drawString("God Mode:On", 650,100);
			score = 0;
		}


		if (gameOver == true) {
			g.drawString("Level " + Pictures.level + "- Game Over!", getWidth()/2,getHeight()/2);

			if(ij==1){
				ij++;
			}
			g.setColor(Color.WHITE);
			// mouse check g.drawRect(280, 320, 200, 40);
			g.setColor(Color.WHITE);
			g.drawString("PLAY AGAIN",  getWidth()/2,getHeight()/2 + 100);
			Pictures.back.stop();

			if (mousein == true) {
				g.setColor(Color.RED);
				g.drawString("PLAY AGAIN", getWidth()/2,getHeight()/2 + 100);

			}

		}
	}


	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				b.moveLeft();
				break;
			case KeyEvent.VK_RIGHT:
				b.moveRight();
				break;
			case KeyEvent.VK_OPEN_BRACKET:
				Platform.godMode = true;
				break;

		}

	}


	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}


	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}


	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	public void mouseMoved(MouseEvent e) {
		if (gameOver == true) {

			/*if (e.getX() > 280 && e.getX() < 480) {
				if (e.getY() > 320 && e.getY() < 360) {
					mousein = true;
				}
			}
			if (e.getX() < 280 || e.getX() > 480) {
				mousein = false;

			}
			if (e.getY() < 320 || e.getY() > 360) {
				mousein = false;

			}
*/

			if (e.getX() > getWidth()/2 && e.getX() < getWidth()/2 + 150) {
				System.out.println("Centre Found");
				if (e.getY() > getHeight()/2 + 65 && e.getY() < getHeight()/2 + 100) {
					mousein = true;
				}else {
					mousein = false;
				}
			}else
				mousein = false;




		}
	}


	public void mouseClicked(MouseEvent e) {
		if (mousein == true) {
			// New Game
			b = null;
			b = new Ball();
			score = 0;
			Pictures.level = 1;
			if(gr.GiveSound().endsWith("On")){
				Pictures.back.loop();
			}
			levelcheck = 0;
			for (int i = 0; i < p.length; i++) {
				Random r = new Random();

				p[i] = new Platform(i * 120, 300);
			}

			for (int i = 0; i < ito.length; i++) {
				Random r = new Random();
				switch (r.nextInt(5)) {
					case 0:
						ito[i] = new GravUp(getWidth() + 2000 * i);
						break;
					case 1:
						ito[i] = new GravDown(getWidth() + 2000 * i);
						break;
					case 2:
						ito[i] = new AgilUp(getWidth() + 2000 * i);
						break;
					case 3:
						ito[i] = new AgilDown(getWidth() + 2000 * i);
						break;
					case 4:
						ito[i] = new Score(getWidth() + 2000 * i, this);
				}

			}
			mousein = false;
		}
	}


	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
