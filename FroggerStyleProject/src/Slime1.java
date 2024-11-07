import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Slime1{
	private Image forward; 	
	
	private AffineTransform tx;
	
	// attributes of a Monkey object
	int dir = 0; 					//0-forward
	int width, height;				// used for hit boxes and collisions
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	
	double scaleFactor = 0.5;
	double scaleWidth = scaleFactor;		//change to scale image
	double scaleHeight = scaleFactor; 		//change to scale image

	// default constructor
	public Slime1() {
		
		forward 	= getImage("/imgs/"+"slime1-pixilart.png"); //load the image for Tree

		//alter these
		width = (int) (100*scaleFactor);		// for the hit box
		height = (int) (100*scaleFactor);		// for the hit box
		
		// top-left location of your image
		x = Frame.width/2 - width*2;	
		y = Frame.height - height*2;
		
		vx = -4;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		// if the object leaves the left side, wrap it around the other side for 
		
	}

	// Overload the constructor
	// translation: add another constructor
	public Slime1(int x, int y) {
		// sometimes you still need the work done by another constructor, you can call it
		this(); // invoke the default constructor
		
		// set this object's attributes
		// this.x --> specifies the x attribute, NOT the parameter x
		this.x = x;
		this.y = y;
		
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x+=vx;
		y+=vy;	
		
		// if the object leaves the right side, wrap it around the other side for 
		// infinite scroll	
		if (x > Frame.width) { 
			x = -width;
		}
		
		init(x,y);
		
		g2.drawImage(forward, tx,  null);
		
		// infinite scroll	
		if (x < 0) { 
			x = Frame.width;
		}

	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Slime1.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
