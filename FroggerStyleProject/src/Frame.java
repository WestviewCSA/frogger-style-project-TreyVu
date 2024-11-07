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
	Slime1[] slimeRow1 = new Slime1[6];
	Stone[] stoneRow1 = new Stone[10];
	Stone[] stoneRow2 = new Stone[10];
	River[] riverRow1 = new River[10];
	River[] riverRow2 = new River[10];
	Grass[] grassRow1 = new Grass[10];

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		// lines
		int space = Frame.width/10;
		for (int i = space; i < Frame.width; i += space) {
			g.drawLine(0, i, Frame.width, i); // horizontal
			g.drawLine(i, 0, i, Frame.height); // horizontal
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
		
		
		// paint the objects that you have
		monkey.paint(g);
		
		// paint the objects that you have
		// for each object in the array
		for (Duck obj : duckRow1) { // for every Duck object in row1 array
 			obj.paint(g);
		}
		for (Duck obj : duckRow2) { // for every Duck object in row1 array
 			obj.paint(g);
		}
		for (Slime1 obj : slimeRow1) {
			obj.paint(g);
		}
		
		
		// collision detection for each duck object in rrow1
		for(Duck obj : duckRow1) {
			// for every object, invoke the collided method
			if (obj.collided(monkey)) {
				System.out.println("OUCH");
			}
		}
		for(Duck obj : duckRow2) {
			// for every object, invoke the collided method
			if (obj.collided(monkey)) {
				System.out.println("OUCH2");
			}
		}

	}
	
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
		
		for (int i=0; i < grassRow1.length; i++) {
			grassRow1[i] = new Grass(i*space, space*5);
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
		
		
		// set up  any 1D array here - create the objects hat go in them;
		for (int i=0; i < duckRow1.length; i++) {
			
			// create the object and put it in the array at position i
			duckRow1[i] = new Duck(i*(Frame.width/(duckRow1.length-4)), space*7+12);
			
		}
		for (int i=0; i < duckRow2.length; i++) {
			
			// create the object and put it in the array at position i
			duckRow2[i] = new Duck(i*(Frame.width/(duckRow2.length-4)), space*6+12);
			
		}
		
		for (int i=0; i < slimeRow1.length; i++) {
			slimeRow1[i] = new Slime1(i*(Frame.width/(slimeRow1.length-2)), space*4+20);			
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
		System.out.println(arg0.getKeyCode());
		
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
	        if (key == KeyEvent.VK_LEFT) 
	        {
	            monkey.addX(-space);
	        }
	 
	        if (key == KeyEvent.VK_RIGHT) 
	        {
	        	monkey.addX(space);;	        }
	 
	        if (key == KeyEvent.VK_UP) 
	        {
	            monkey.addY(-space);
	        }
	 
	        if (key == KeyEvent.VK_DOWN) 
	        {
	        	monkey.addY(space);
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
