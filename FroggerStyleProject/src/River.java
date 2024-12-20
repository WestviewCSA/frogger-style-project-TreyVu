import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class River{
	private Image forward; 	
	
	private AffineTransform tx;
	
	// attributes of a Monkey object
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;				// used for hit boxes and collisions
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	
	double scaleFactor = 0.10;
	double scaleWidth = scaleFactor;		//change to scale image
	double scaleHeight = scaleFactor; 		//change to scale image

	// default constructor
	public River() {
		
		forward 	= getImage("/imgs/"+"bg-water1.png"); //load the image for Tree

		//alter these
		width = 100;		// for the hit box
		height = 80;		// for the hit box
		
		// top-left location of your image
		x = Frame.width/2 - width*2;	
		y = Frame.height/2 - height*2;
		
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	public River(int x, int y) {
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
	
			public void showHitBoxes(Graphics g) {
				g.drawRect(getX(), getY(), getWidth(), getHeight());
			}
			
	public boolean collided(Monkey mainChar) {
		// represent each object as a rectangle
		Rectangle rectMain = new Rectangle(
			mainChar.getX(),
			mainChar.getY(),
			mainChar.getWidth(),
			mainChar.getHeight()
		);
		
		// represent the obj we're querying for info as a rectangle
		Rectangle rectObj = new Rectangle (
				this.x,
				this.y,
				this.width,
				this.height
		);
		
		return rectMain.intersects(rectObj);
	
	}

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x+=vx;
		y+=vy;	
		
		init(x,y);
		
		g2.drawImage(forward, tx,  null);
		
//		g.setColor(Color.red);
//		g.drawRect(x, y, width, height);
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = River.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
