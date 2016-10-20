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
		sw = 0;
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




		if(levelcheck!=0 && levelcheck%300==0 && gameOver!=true){
			Random xyz = new Random();
			int w;
			switch (pics[sw]){
				case "Images\\Taj.png" :
					w = xyz.nextInt(9) ;


					switch (w){
						case 0:
							JOptionPane.showMessageDialog(null, "Although the Taj Mahal was built for Mumtaz Mahal, Shah Jahan was also laid to rest in the tomb alongside his late wife.");
						case 1 : JOptionPane.showMessageDialog(null, "The Taj Mahal took approximately 20 years and approximately 20,000 workers to complete.\n There were also about 1000 elephants used to transport the materials needed for construction.");
							break;
						case 2 :
							JOptionPane.showMessageDialog(null, "The magnificent edifice is bounded on three sides by red stone walls.\n It is constructed completely of white marble.\n The emperor had marbles of the best quality brought from Rajasthan, Afghanistan, Tibet and China.");
							break;
						case 3 :
							JOptionPane.showMessageDialog(null, "The Taj Mahal has a large white dome surrounded by four smaller domes.\n The large white dome in the center is approximately 115 feet tall.");
							break;
						case 4 :
							JOptionPane.showMessageDialog(null, "The construction of the Taj Mahal was begun in 1632, a year after the death of Empress Mumtaz.\n The construction was completed in the year 1653,\n which means that it took approximately 22 years to complete this astounding piece of architecture.");
							break;
						case 5 :
							JOptionPane.showMessageDialog(null, "At that time, the estimated construction cost was a whopping sum of Rs. 32 million,\n which, when considered in terms of today’s value of money,\n would be something way above $1 billion.");
							break;
						case 6:
							JOptionPane.showMessageDialog(null, "There were about 28 different types of precious and semi-precious stones inlaid into the burial tomb of the Taj Mahal.\n During the Indian Rebellion in 1857, many of the precious stones were stripped from the walls of the tomb.");
							break;
						case 7:
							JOptionPane.showMessageDialog(null, "It is rumored that Shah Jahan had the hands cut off from the people who worked on the Taj Mahal once it was complete.\n His reasoning was that nobody would ever be able to build such a beautiful building ever again.");
							break;
						case 8:
							JOptionPane.showMessageDialog(null, "There are 99 names of Allah inscribed calligraphically on the side of Mumtaz Mahal's actual tomb.");
							break;
						case 9:
							JOptionPane.showMessageDialog(null, "The color of Taj Mahal appears to change color depending on the time of day.\n The color change also depends on whether there is moonlight at night.");
					}
					break;
				case "Images\\Rome.png":
					w = xyz.nextInt(9);
					switch (w){
						case 0:
							JOptionPane.showMessageDialog(null, "It is also known as the Flavian Amphitheatre because it was built during the Flavian dynasty.\n It got the name Colosseum because of a statue that was located\n alongside the amphitheatre called ‘the colossus of Nero'.");


							break;
						case 1:
							JOptionPane.showMessageDialog(null, "Construction of the Collosseum began in 72 AD under the emperor Vespasian.\n");


							break;
						case 2:
							JOptionPane.showMessageDialog(null, "Titus, Vespasian's son and successor completed the construction of Collosseum in 81 AD.\n It took only 9 years to build.\n Even today, construction of a stadium that size would be difficult to build in only 9 years");


							break;
						case 3:
							JOptionPane.showMessageDialog(null, "It took 60,000 Jewish slaves to build the Colosseum.\n It was built of stone and concrete.");


							break;
						case 4:
							JOptionPane.showMessageDialog(null, "Domitian, Vespasian's youngest son, made modifications to the Collosseum during his reign from 81-96 AD.\n He built underground tunnels to hold slaves and animals.\n He also built a gallery to the top for additional seating.");


							break;
						case 5:
							JOptionPane.showMessageDialog(null, "Several different events were held in the Colosseum\n including gladiator contests, mock battles and animal hunts, and\n dramas that were based on Classical mythology.");



							break;
						case 6:
							JOptionPane.showMessageDialog(null, "There were also re-enactments of famous battles and executions held in the Collosseum.");


							break;
						case 7:
							JOptionPane.showMessageDialog(null, "The Collosseum was used for entertainment for 390 years.\n During this time more than 400,000 people died inside the Colosseum.\n It's also estimated that about 1,000,000 animals died in the Colosseum as well.");


							break;
						case 8:
							JOptionPane.showMessageDialog(null, "After the Colosseum was partially destroyed by the earthquake, some of the fallen pieces were used to build St Peter's Basilica.");


							break;
						case 9:
							JOptionPane.showMessageDialog(null, "In the early medieval era it was no longer used for entertainment purposes.\n Eventually it was used for workshops, housing, religious quarters, a quarry,\n a fortress and even as a Christian shrine.");


							break;
					}

					break;
				case "Images\\Rio.png":
					w=xyz.nextInt(9);
					switch (w){
						case 0:
							JOptionPane.showMessageDialog(null, "Christ the Redeemer is the largest art deco statue in the world.\n It is 98 feet tall (not including the 26 foot pedestal), and the arms stretch to 92 feet wide.");
							break;
						case 1:
							JOptionPane.showMessageDialog(null, "The design of Christ the Redeemer was chosen from several ideas\n and construction began in the 1920s, taking nine years to finish.");
							break;
						case 2:
							JOptionPane.showMessageDialog(null, "Christ the Redeemer is located in the Tijuca Forest National Forest, at the top of the Corcovado Mountain.");
							break;
						case 3:
							JOptionPane.showMessageDialog(null, "The money to build Christ the Redeemer came from Brazil's Catholic community.");
							break;
						case 4:
							JOptionPane.showMessageDialog(null, "The chosen statue is meant to show that Christ loves all and will embrace all that come to him.");
							break;
						case 5:
							JOptionPane.showMessageDialog(null, "The stones that were used to build Christ the Redeemer came from Sweden.\n The statue was completed in 1931. It officially opened on October 12, 1931.");
							break;
						case 6:
							JOptionPane.showMessageDialog(null, "The cost of building Christ the Redeemer in 1931 was approximately $250,000 US. Today that would roughly be the equivalent of Rs. 21 Crore.");
							break;
						case 7:
							JOptionPane.showMessageDialog(null, "The materials used to build Christ the Redeemer were reinforced concrete and soapstone.\n The statue had to be constructed in pieces and carried to the mountain top to be erected.");
							break;
						case 8:
							JOptionPane.showMessageDialog(null, "The right arm of Jesus Christ points to south Rio de Janeiro while the left arm points to north Rio de Janeiro.");
							break;
						case 9:
							JOptionPane.showMessageDialog(null, "There were several designs to choose from.\n One was a depiction of the Christian cross.\n Another was a statue of Jesus holding a globe, while standing over a pedestal that was to symbolize the world.");
							break;

					}
					break;
				case "Images\\Macho.png":
					w=xyz.nextInt(9);
					switch (w){
						case 0:

							break;
						case 1:
							JOptionPane.showMessageDialog(null, "Machu Picchu is an Inca site in Peru that is thought to have been built as an estate for Pachacuti, an Incan emperor.");
							break;
						case 2:
							JOptionPane.showMessageDialog(null, "It was abandoned as a place for Inca rulers in about 1572 and was lost.\n It was discovered by the outside world in 1911 and has since become a tourist attraction.");
							break;
						case 3:
							JOptionPane.showMessageDialog(null, "Machu Picchu is also known as the Lost City of the Incas.\n" + "It is located on the eastern Andes mountain slopes in a tropical mountain forest.\n There are terraces and walls that look as if they were cut from the rock.");
							break;
						case 4:
							JOptionPane.showMessageDialog(null, "Machu Pichu is 7,970 feet above sea level.");
							break;
						case 5:
							JOptionPane.showMessageDialog(null, "Professor Hiram Bingham is thought to be one of the first people to lay eyes on the site and rediscover it in 1911");
							break;
						case 6:
							JOptionPane.showMessageDialog(null, "Machu Pichu is one of the most important historical Latin American sites and covers an area of 32,500 hectares.\n" +
									"Machu Picchu was built with polished dry stone walls.");
							break;
						case 7:
							JOptionPane.showMessageDialog(null, "One of the reasons Machu Picchu may have been abandoned is because of an outbreak of smallpox.");
							break;
						case 8:
							JOptionPane.showMessageDialog(null, "There are three main structures located in the Sacred District of Machu Picchu.\n One is the Temple of the Three Windows which, along with the main temple\n are said to have the most impressive architecture in Machu Picchu.");
							break;
						case 9:
							JOptionPane.showMessageDialog(null, "The second is the Temple of the Sun, a semi-circle shaped temple\n that at once time was thought to have gold and precious jewels inlaid in the door.\n The third, the Intiwatana, is a stone located on the top of a hill.");
							break;

					}
					break;
				case "Images\\Wall.png":
					w=xyz.nextInt(9);
					switch (w){
						case 0:
							JOptionPane.showMessageDialog(null, "The Great Wall of China was built to protect the Chinese Empire from invasion\n as well as to impose border control and control immigration and emigration.");
							break;
						case 1:
							JOptionPane.showMessageDialog(null, "It was built by more than one emperor\n and the majority of its reconstruction occurred during the Ming Dynasty.\n The wall includes watch towers, barracks\n and also allowed signals to be sent by smoke and fire.");
							break;
						case 2:
							JOptionPane.showMessageDialog(null, "Archaeological surveys have estimated the length of the wall to be about 13, 171 miles long.");
							break;
						case 3:
							JOptionPane.showMessageDialog(null, "Originally the Great Wall of China wall was a series of several walls that were eventually joined together. The first were built in the 7th century BC.");
							break;
						case 4:
							JOptionPane.showMessageDialog(null, "While the Great Wall of China was being built it was commonly\n referred to as ‘the longest cemetery on earth'. \nIt is estimated that more than one million people died during construction.");
							break;
						case 5:
							JOptionPane.showMessageDialog(null, "Genghis Khan breached the wall in places where it was not complete and conquered the majority of northern China from 1211 and 1223 AD.");
							break;
						case 6:
							JOptionPane.showMessageDialog(null, "There have been rumors that human bones were used to create\n the mortar to bind the stones of the wall together, but no bones have ever been found within\n the Great Wall of China");
							break;
						case 7:
							JOptionPane.showMessageDialog(null, "Before the 20th century, the Great Wall of China was rarely depicted in any Chinese art.");
							break;
						case 8:
							JOptionPane.showMessageDialog(null, "The wheelbarrow was invented by the Chinese and it was used during the construction of the wall.\n" +
									"Due to erosion, there is a section of the Great Wall that may disappear in the next 20 years.");
							break;
						case 9:
							JOptionPane.showMessageDialog(null, "In the 2008 Summer Olympics, the Great Wall of China was the finish spot for a cycling course.\n");
							break;

					}
					break;
				case "Images\\Petra.png":
					w=xyz.nextInt(9);
					switch (w){
						case 0:
							JOptionPane.showMessageDialog(null, "Petra is an ancient city located between the Dead Sea and the Red Sea.\n The city is half man-made and half carved into the rock");
							break;
						case 1:
							JOptionPane.showMessageDialog(null, " Petra is often referred to as the Rose City because of the rose color of the rock from which the city was carved.");
							break;
						case 2:
							JOptionPane.showMessageDialog(null, "Petra is thought to have been established as early as 312 BC.\n It was the capital city of Nabataens. There is evidence to suggest\n that between 1550 and 1292 BC settlements\n had begun in and around Petra.");
							break;
						case 3:
							JOptionPane.showMessageDialog(null, "Petra was the center of caravan trade, an important junction for the silk trade.\n It was located at a junction that China, India, Egypt, Greece, Rome, Syria\n and southern Arabia used for trading spices and silk, among other products.");
							break;
						case 4:
							JOptionPane.showMessageDialog(null, "There are approximately 800 structures in Petra. The most famous is Pharoah's Treasure, a mausoleum.");
							break;
						case 5:
							JOptionPane.showMessageDialog(null, "The Romans included it in the province of Arabia Petraea in the 2nd and 3rd centuries.\n" +
									"Petra was captured by Muslims in the 7th century and by the Crusaders in the 12th century.");
							break;
						case 6:
							JOptionPane.showMessageDialog(null, "Petra would not have been possible as a place for settlement\n if it had not been for the water conduit system that was built to provide\n storage and supply for its citizens.");
							break;
						case 7:
							JOptionPane.showMessageDialog(null, "There was enough water to support the 30,000 people\n that are believed to have inhabited Petra. There was also\n enough water in this desert region to have lush gardens.");
							break;
						case 8:
							JOptionPane.showMessageDialog(null, "The Arab people of Petra became wealthy and prosperous because of their commercial trade abilities.\n They did not gain the power in the region because of force (war).");
							break;
						case 9:
							JOptionPane.showMessageDialog(null, "An earthquake in 363 destroyed many of the buildings in Petra\n and it also did major damage to the water system.");
							break;

					}

					break;
				case "Images\\Chic.png":
					w=xyz.nextInt(9);
					switch (w){
						case 0:
							JOptionPane.showMessageDialog(null, "Located on the Yucatan Peninsula, Chichen Itza was a large city built by the Maya people. ");
							break;
						case 1:
							JOptionPane.showMessageDialog(null, "Chichen Itza may have been built where it was because of the location of two large natural sink holes\n nearby that would have provided water year-round.");
							break;
						case 2:
							JOptionPane.showMessageDialog(null, "It was one of the largest Maya cities and also had the most diverse population in any Maya population.\n It covered five square kilometers.");
							break;
						case 3:
							JOptionPane.showMessageDialog(null, "Many of the Maya people that lived in Chichen Itza were very skilled craftsmen,\n including sculptors, weavers, jewelers and potters.\n" +
									"There is a wide variety of architectural styles that is attributed to the fact that\n Chichen Itza had such diverse population and culture.");
							break;
						case 4:
							JOptionPane.showMessageDialog(null, "The people of Chichen Itza built strong allies with regional tribes and this helped them thrive for two centuries.");
							break;
						case 5:
							JOptionPane.showMessageDialog(null, "All the buildings of Chichen Itza are made from stone.\n It's also thought that the Maya did no use the wheel\n to build any of their temples, pyramids or palaces.\n");
							break;
						case 6:
							JOptionPane.showMessageDialog(null, "Some of the most famous buildings in Chichen Itza that have survived include:\n The Warrior's Temple, El Castillo, and The Great Ball Court.");
							break;
						case 7:
							JOptionPane.showMessageDialog(null, "One of these sink holes was thought to have been used as a place of human sacrifice.\n These sacrifices were made in times of drought, and men, women and children would be\n thrown in the well as a sacrifice to the Chac God.");
							break;
						case 8:
							JOptionPane.showMessageDialog(null, "In about 600 AD. Chichen Itza had started to gain importance in the region.\n Between 900 and 1050 AD, Chichen Itza had become a powerful capital.\n It also controlled northern and central Yucatan at the time.");
							break;
						case 9:
							JOptionPane.showMessageDialog(null, "Chichen Itza may have been a religious center for a period of time and is believed to have been a pilgrimage place for the Maya.");
							break;

					}
			}



		}

		if (levelcheck > 1000 && gameOver!=true) {
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
				Random r = new Random();
				switch (r.nextInt(5)){
					case 0: ans = JOptionPane.showInputDialog("What is the great Wall of China Made of ?");
						if(ans.contains("Brick") || ans.contains("Stone")){
							score+=300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						}else {
							score-= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}
						break;
					case 1:
						ans = JOptionPane.showInputDialog("Other than China, which other country is the great wall of China Located ?");
						if(ans.contains("Mongolia") || ans.contains("mongolia")){
							score+=300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						}else {
							score-= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
					case 2:
						ans = JOptionPane.showInputDialog("How Wide is the great wall of china(In meters)");
						if(ans.contains("16") || ans.contains("19")  || ans.contains("18") || ans.contains("17") || ans.contains("15")){
							score+=300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						}else {
							score-= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;

					case 3:
						ans = JOptionPane.showInputDialog("How tall is the great Wall of China ?(in Meters)");
						if(ans.equals("5") || ans.equals("6")  || ans.equals("7") || ans.equals("8") || ans.equals("4")){
							score+=300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						}else {
							score-= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
					case 4:
						ans = JOptionPane.showInputDialog("Can you see Great Wall of China From Space(YES/NO)");
						if(ans.equalsIgnoreCase("No")){
							score+=300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						}else {
							score-= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
				}

			}else if(pics[sw].equals("Images\\Rio.png")){
				Random r = new Random();
				switch (r.nextInt(5)){
					case 0: ans = JOptionPane.showInputDialog("How tall is Christ The Redeemer ?");
						if(ans.contains("130") || ans.contains("130")){
							score+=300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						}else {
							score-= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}
						break;
					case 1:
						ans = JOptionPane.showInputDialog("On what is Christ the Redeemer located ?");
						if(ans.equalsIgnoreCase("Corcovado Mountain") || ans.contains("Corcovado")){
							score+=300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						}else {
							score-= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
					case 2:
						ans = JOptionPane.showInputDialog("In Which year was Christ the Redeemer completed ?");
						if(ans.contains("1931")){
							score+=300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						}else {
							score-= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;

					case 3:
						ans = JOptionPane.showInputDialog("What is Christ The Redeemer made of ?");
						if(ans.contains("Concrete") || ans.contains("soapstone")){
							score+=300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						}else {
							score-= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
					case 4:
						ans = JOptionPane.showInputDialog("Near Which City is it located(Full Name)");
						if(ans.equalsIgnoreCase("rio de janeiro")){
							score+=300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						}else {
							score-= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
				}

			}else if(pics[sw].equals("Images\\Rome.png")){
				Random r = new Random();
				switch (r.nextInt(5)) {
					case 0:
						ans = JOptionPane.showInputDialog("What is the meaning of Colosseum?");
						if (ans.contains("Large") || ans.contains("Big")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}
						break;
					case 1:
						ans = JOptionPane.showInputDialog("Today is the Colosseum used for other purposes than tourist attractions(YES/NO) ?");
						if (ans.equalsIgnoreCase("Yes") || ans.equalsIgnoreCase("No")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
					case 2:
						ans = JOptionPane.showInputDialog("How was the Colosseum damaged ?");
						if (ans.contains("Earthquake") || ans.contains("Light") || ans.contains("fire") || ans.contains("Fire") || ans.contains("light")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;

					case 3:
						ans = JOptionPane.showInputDialog("How Many people did it take to Build the Colosseum(Guess upto the nearest Ten Thousand)");
						if (ans.equals("20000") || ans.equals("25000") || ans.equals("30000")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
					case 4:
						ans = JOptionPane.showInputDialog("Can you see Colosseum From Space(YES/NO)");
						if (ans.equalsIgnoreCase("No")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
				}
			}else if(pics[sw].equals("Images\\Chic.png")){
				Random r = new Random();
				switch (r.nextInt(5)) {
					case 0:
						ans = JOptionPane.showInputDialog("In Which Mexican State is Chichen Itza located ?");
						if (ans.contains("Yucatan")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}
						break;
					case 1:
						ans = JOptionPane.showInputDialog("When did the Chichen Itza region come into prominence?");
						if (ans.equalsIgnoreCase("600AD") || ans.equalsIgnoreCase("600")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
					case 2:
						ans = JOptionPane.showInputDialog("Chichen Itza was built by the ?");
						if (ans.equalsIgnoreCase("Mayans")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;

					case 3:
						ans = JOptionPane.showInputDialog("What are the buildings of Chichen Itza made of?");
						if (ans.equalsIgnoreCase("LimeStone") || ans.contains("LimeStone")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
					case 4:
						ans = JOptionPane.showInputDialog("The largest, most prominent step pyramid at the center of Chichen Itza is called:");
						if (ans.equalsIgnoreCase("El Castillo")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
				}

			}else if(pics[sw].equalsIgnoreCase("Images//Macho.png")){
				Random r = new Random();
				switch (r.nextInt(5)) {
					case 0:
						ans = JOptionPane.showInputDialog("In what modern-day country is Machu Picchu located?");
						if (ans.equalsIgnoreCase("Peru")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}
						break;
					case 1:
						ans = JOptionPane.showInputDialog("In what mountain range is Machu Picchu located?");
						if (ans.equalsIgnoreCase("Andes")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
					case 2:
						ans = JOptionPane.showInputDialog("About how many feet above sea level is the town located? (In feet)");
						if (ans.equalsIgnoreCase("9000")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;

					case 3:
						ans = JOptionPane.showInputDialog("What civilization is thought to have built Machu Picchu?");
						if (ans.equalsIgnoreCase("Incan")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
					case 4:
						ans = JOptionPane.showInputDialog("Was The Machu Pichu in the old Wonders of the World");
						if (ans.equalsIgnoreCase("No")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
				}
			}else if(pics[sw].equalsIgnoreCase("Images//Egypt.png")){
				Random r = new Random();
				switch (r.nextInt(5)) {
					case 0:
						ans = JOptionPane.showInputDialog("Who decided to build the Great Pyramid of Giza?");
						if (ans.equalsIgnoreCase("Pharaoh Khufu")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}
						break;
					case 1:
						ans = JOptionPane.showInputDialog("What type of material was used to build the Great Pyramid of Giza?");
						if (ans.equalsIgnoreCase("Limestone")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
					case 2:
						ans = JOptionPane.showInputDialog("Where is the Great Pyramid of Giza located?");
						if (ans.equalsIgnoreCase("Egypt")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;

					case 3:
						ans = JOptionPane.showInputDialog("Was The Pyramid of Giza Made by Slaves ?");
						if (ans.equalsIgnoreCase("No")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
					case 4:
						ans = JOptionPane.showInputDialog("Was The Pyramid Of Giza in the old Wonders of the World");
						if (ans.equalsIgnoreCase("Yes")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
				}
			}else if(pics[sw].equalsIgnoreCase("Images//Petra.png")){
				Random r = new Random();
				switch (r.nextInt(5)) {
					case 0:
						ans = JOptionPane.showInputDialog("Usually, visitors will enter Petra through a long narrow winding gorge. What is this called? ");
						if (ans.contains("Siq")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}
						break;
					case 1:
						ans = JOptionPane.showInputDialog("Who was the first Western explorer to reach Petra?");
						if (ans.equalsIgnoreCase("Johann Ludwig Burckhardt")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
					case 2:
						ans = JOptionPane.showInputDialog("In which modern-day country is Petra?");
						if (ans.equalsIgnoreCase("Jordan")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;

					case 3:
						ans = JOptionPane.showInputDialog("On entering the City the modern-day visitor will be confronted by one \nof the most dramatic of all Petra's buildings. \nWhat is it called? ");
						if (ans.equalsIgnoreCase("The Treasury")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
					case 4:
						ans = JOptionPane.showInputDialog("Who Built the City of Petra ?");
						if (ans.equalsIgnoreCase("Nabatean Arabs")) {
							score += 300;
							JOptionPane.showMessageDialog(null, "Good, 300 Points gained");
						} else {
							score -= 300;

							JOptionPane.showMessageDialog(null, "Incorrect Answer, 300 points lost.");
						}

						break;
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
			if(sp.getWidth() > 850) {
				System.out.println("Hello World");
				g.drawString("Level " + Pictures.level, getWidth() - 150, getHeight() - 100 * 5);
			}else
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
				g.drawString("Level " + Pictures.level + "- Game Over!", getWidth()/2,getHeight()/2);
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
