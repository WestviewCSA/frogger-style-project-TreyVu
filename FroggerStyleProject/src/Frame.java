import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//Timer related variables
	int waveTimer = 5; //each wave of enemies is 20s
	long ellapseTime = 0;
	Font timeFont = new Font("Courier", Font.BOLD, 70);
	int level = 0;
	
	
	Font myFont = new Font("Courier", Font.BOLD, 40);
	SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("scifi.wav", false);
//	Music soundBang = new Music("bang.wav", false);
//	Music soundHaha = new Music("haha.wav", false);
	
	int space = Frame.width/10; 
	// create an instance of the Monkey class
	Monkey monkey = new Monkey(space*4+15, space*8+15);
	
	//frame width/height
	static int width = 800;
	static int height = 800;	
	
	// a row of MonkeyScrolling objects
	Duck[] duckRow1 = new Duck[10]; // only creates bookshelf, not books
	Duck[] duckRow2 = new Duck[10];
	//arraylist version of duckRow1
//	ArrayList<Duck> row1 = new ArrayList <Duck();
	
	
	Slime1[] slimeRow1 = new Slime1[6];
	Slime2[] slimeRow2 = new Slime2[6];
	Slime1[] slimeRow3 = new Slime1[6];
	
	Stone[] stoneRow1 = new Stone[10];
	Stone[] stoneRow2 = new Stone[10];
	Stone[] stoneRow3 = new Stone[10];
	
	River[] riverRow1 = new River[10];
	River[] riverRow2 = new River[10];
	
	Grass[] grassRow1 = new Grass[10];
	
	Wood[] woodRow1 = new Wood[10];
	
	Lava[] lavaRow1 = new Lava[10];
	
	Flamingo[] flamingoRow1 = new Flamingo[4];

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		// lines
		int space = Frame.width/10;
		
		for (Lava obj : lavaRow1) {
			obj.paint(g);
		}
		for (Wood obj : woodRow1) {
			obj.paint(g);
		}
		for (Grass obj : grassRow1) {
			obj.paint(g);
		}
		for (River obj : riverRow1) {
			obj.paint(g);
		}
		for (River obj : riverRow2) {
			obj.paint(g);
		}
		for (Stone obj : stoneRow1) {
			obj.paint(g);
		}
		for (Stone obj : stoneRow2) {
			obj.paint(g);
		}
		for (Stone obj : stoneRow3) {
			obj.paint(g);
		}

		// paint the objects that you have
		// for each object in the array
		int duckMin = 3, duckMax = 5;
		int duckSpeed1 = (int) Math.random()*(duckMax-duckMin)+duckMin;
		int duckSpeed2 = (int) Math.random()*(duckMax-duckMin)+duckMin;

		
		for (Duck obj : duckRow1) { // for every Duck object in row1 array
 			obj.paint(g);
 			obj.setVx(duckSpeed1);
		}
		
//		for (int i = 0; i < 10; i++) {
//			// add the object to the list
//			row1.add(new Duck(i*180, 100)); 
//		}
		//traverse the list
//		for (Duck obj : row1) {
//			obj.paint(g);
//		}
		
		
		for (Duck obj : duckRow2) { // for every Duck object in row1 array
 			obj.paint(g);
 			obj.setVx(duckSpeed2);
		}
		for (Slime1 obj : slimeRow1) {
			obj.paint(g);
		}
		for (Slime2 obj : slimeRow2) {
			obj.paint(g);
		}
		for (Slime1 obj : slimeRow3) {
			obj.paint(g);
		}
		
		for (Flamingo obj : flamingoRow1) {
			obj.paint(g);
		}
		
		// last paint monkey
		monkey.paint(g);
		
		// collision detection for each duck object in rrow1
		
		boolean isMounted = false;
		for(Duck obj : duckRow1) {
			// for every object, invoke the collided method
			if (obj.collided(monkey)) {
				monkey.setX(obj.getX());
				monkey.setY(obj.getY());
				System.out.println("Mount Duck");
				isMounted = true;
			} 
		}
		for(Duck obj : duckRow2) {
			if (obj.collided(monkey)) {
				monkey.setX(obj.getX());
				monkey.setY(obj.getY());
				System.out.println("Mount Duck");
				isMounted = true;
			} 
		}
		for(River obj : riverRow1) {
			// for every object, invoke the collided method
			if (obj.collided(monkey) && isMounted == false) {
				monkey.reset();
				System.out.println("wet");
			}
		}
		for(River obj : riverRow2) {
			// for every object, invoke the collided method
			if (obj.collided(monkey) && isMounted == false) {
				monkey.reset();
				System.out.println("wet2");
			}
		}
		
		for(Slime1 obj : slimeRow1) {
			if (obj.collided(monkey)) {
				monkey.reset();
				//deathCount++;
			}
		}
		for(Slime2 obj : slimeRow2) {
			if (obj.collided(monkey)) {
				monkey.reset();
			}
		}
		for(Slime1 obj : slimeRow3) {
			if (obj.collided(monkey)) {
				monkey.reset();
			}
		}
		
		for(Flamingo obj : flamingoRow1) {
			// for every object, invoke the collided method
			if (obj.collided(monkey)) {
				//System.out.println("OUCH");
				monkey.setX(obj.getX());
				monkey.setY(obj.getY());
			}
		}
//		g.setColor(Color.blue);
//		for (int i = space; i < Frame.width; i += space) {
//			g.drawLine(0, i, Frame.width, i); // horizontal
//			g.drawLine(i, 0, i, Frame.height); // vertical
//		}
		
		g.setColor(Color.white);
		g.drawString("Deaths: " + monkey.getDeaths(), 15, space*9+35);
		g.drawString("Wins: " + monkey.getWins(), 15, space*9+15);
		g.drawString("Win Rate: " + monkey.getWinRate(), space*8+15, space*9+35);
	}
//	public void slimeBoxes() {
//		for (Slime1 obj : slimeRow1) {
//			obj.showHitBoxes(getGraphics());
//		}
//		for (Slime2 obj : slimeRow2) {
//			obj.showHitBoxes(getGraphics());
//		}
//		for (Slime1 obj : slimeRow3) {
//			obj.showHitBoxes(getGraphics());
//		}
//	}
//	public void monkeyBox() {
//		monkey.showHitBoxes(getGraphics());
//	}
//	public void duckBoxes() {
//		for (Duck obj : duckRow1) {
//			obj.showHitBoxes(getGraphics());
//		}
//		for (Duck obj : duckRow2) {
//			obj.showHitBoxes(getGraphics());
//		}
//	}
	
	
	
	public static void main(String[] arg) {
		Frame f = new Frame();
		
	}
	
	// constructor
	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);
	
		//backgroundMusic.play();

		int space = Frame.width/10; 
		// set up  any 1D array here - create the objects hat go in them;
			// create the object and put it in the array at position i
		for (int i=0; i < lavaRow1.length; i++) {
			lavaRow1[i] = new Lava(i*space, space*0);
		}
		for (int i=0; i < woodRow1.length; i++) {
			woodRow1[i] = new Wood(i*space, space*1);
		}
		for (int i=0; i < grassRow1.length; i++) {
			grassRow1[i] = new Grass(i*space, space*2);
		}
		
		for (int i=0; i < riverRow1.length; i++) {
			riverRow1[i] = new River(i*space, space*6);
		}
		for (int i=0; i < riverRow2.length; i++) {
			riverRow2[i] = new River(i*space, space*7);
		}
		for (int i=0; i < stoneRow1.length; i++) {
			stoneRow1[i] = new Stone(i*space, space*9);
		}
		for (int i=0; i < stoneRow2.length; i++) {
			stoneRow2[i] = new Stone(i*space, space*8);
		}
		for (int i=0; i < stoneRow3.length; i++) {
			stoneRow3[i] = new Stone(i*space, space*5);
		}
		for (int i=0; i < duckRow1.length; i++) {
			duckRow1[i] = new Duck(i*(Frame.width/(duckRow1.length-4))+30, space*7+12);
		}
		for (int i=0; i < duckRow2.length; i++) {
			duckRow2[i] = new Duck(i*(Frame.width/(duckRow2.length-4)), space*6+12);	
		}
		for (int i=0; i < slimeRow1.length; i++) {
			slimeRow1[i] = new Slime1(i*(Frame.width/(slimeRow1.length-2)), space*4+20);			
		}
		for (int i=0; i < slimeRow2.length; i++) {
			slimeRow2[i] = new Slime2(i*(Frame.width/(slimeRow2.length-0)), space*3+20);			
		}
		for (int i=0; i < slimeRow3.length; i++) {
			slimeRow3[i] = new Slime1(i*(Frame.width/(slimeRow3.length-2))+30, space*2+20);			
		}
		for (int i=0; i < flamingoRow1.length; i++) {
			flamingoRow1[i] = new Flamingo(i*(Frame.width/(flamingoRow1.length-0))+30, space*0+20);			
		}
		
		
		
		//the cursor image must be outside of the src folder
		//you will need to import a couple of classes to make it fully 
		//functional! use eclipse quick-fixes
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	
		
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	
	@Override 
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println(arg0.getKeyCode());
		
//		if (arg0.getKeyCode() == 87 || arg0.getKeyCode() == 38) { // UP
//			monkey.move(0);
//		} else if (arg0.getKeyCode() == 83 || arg0.getKeyCode() == 40) { // DOWN
//			monkey.move(1);
//		} else if (arg0.getKeyCode() == 65 || arg0.getKeyCode() == 37) { // left
//			monkey.move(2);
//		} else if (arg0.getKeyCode() == 68 || arg0.getKeyCode() == 39) { // right
//			monkey.move(3);
//		}
		
		
		int space = Frame.width/10; 

		int key = arg0.getKeyCode();
	       // release the up, down, left right, set the dx  to 0. Link will stop moving.
	        if (key == KeyEvent.VK_LEFT) {
	            monkey.addX(-space);
	            System.out.println("x: " + monkey.getX() + " = space*" + (monkey.getX()/space) + "+" + (monkey.getX()%space));
	            monkey.fixX();
	        } if (key == KeyEvent.VK_RIGHT) {
	        	monkey.addX(space);	     
	            System.out.println("x: " + monkey.getX() + " = space*" + (monkey.getX()/space) + "+" + (monkey.getX()%space));
	        	monkey.fixX();
	        } if (key == KeyEvent.VK_UP) {
	            monkey.addY(-space);
	            System.out.println("y: " + monkey.getY() + " = space*" + (monkey.getY()/space) + "+" + (monkey.getY()%space));
	            monkey.fixX();
	        } if (key == KeyEvent.VK_DOWN) {
	        	if (monkey.getY() != space*9+15) {
	        		monkey.addY(space);
	        	}
	            System.out.println("y: " + monkey.getY() + " = space*" + (monkey.getY()/space) + "+" + (monkey.getY()%space));
	        	monkey.fixX();
	        }
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
