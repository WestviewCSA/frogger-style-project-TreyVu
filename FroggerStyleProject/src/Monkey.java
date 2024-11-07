import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Monkey{
	private Image forward; 	
	
	private AffineTransform tx;
	
	// attributes of a Monkey object
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;				// used for hit boxes and collisions
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleFactor = 0.6;
	double scaleWidth = scaleFactor;		//change to scale image
	double scaleHeight = scaleFactor; 		//change to scale image

	// default constructor
	public Monkey() {
		
		forward 	= getImage("/imgs/"+"monkey3.png"); //load the image for Tree

		//alter these
		width = (int) (100*scaleFactor);		// for the hit box
		height = (int) (100*scaleFactor);		// for the hit box
		
		// top-left location of your image
		x = Frame.width/2 - width*2;	
		y = Frame.height/2 - height*2;
		
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}

	// Overload the constructor
	// translation: add another constructor
	public Monkey(int x, int y) {
		// sometimes you still need the work done by another constructor, you can call it
		this(); // invoke the default constructor
		
		// set this object's attributes
		// this.x --> specifies the x attribute, NOT the parameter x
		this.x = x;
		this.y = y;
		
	}
	// methods for setting position
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	// methods for changing position
	public void addX(int x) {
		this.x += x;
	}
	public void addY(int y) {
		this.y += y;
	}
	
	// methods for getting position
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	
	// methods for getting height/width
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	
	// movement helper method
	public void move(int dir) {
		int space = Frame.width/10;
		switch (dir) {
		
		case 0: //up
			y -= space;
			break;
		case 1: //down
			y += space;
			break;
		case 2: //left
			x -= space;
			break;
		case 3: //right
			x += space;
			break;	
		}
	}
	
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x+=vx;
		y+=vy;	
		
		init(x,y);
		
		g2.drawImage(forward, tx,  null);

	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Monkey.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
