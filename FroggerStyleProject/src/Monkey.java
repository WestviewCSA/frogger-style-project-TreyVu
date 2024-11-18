import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
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
	
	int space = Frame.width/10;

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
	
	// mehthods for setting vx/vy
	public void setVx(int vx) {
		this.vx = vx; 
	}
	public void setVy(int vy) {
		this.vy = vy;
	}
	// methods for getting vx/vy
	public int getVx() {
		return vx;
	}
	public int getVy() {
		return vy;
	}
	
	
	public boolean checkX() {
		boolean result = true;
		if ((getX()-15)% space != 0) {
        	result = false;
        }
		return result;
	}
	public void fixX() {
		if (!checkX()) {
			int a = (getX()/space);
			if ((getX()) % space > (space/2)) {
				a++;
				setX(space*a+15);
			} else if ((getX()) % space <= (space/2)) {
				setX(space*a+15);
			}
        }
	}
	int deathCount = 0;
	public int getDeaths() {
		return deathCount;
	}
	
	int winCount = 0;
	public int getWins() {
		return winCount;
	}
	public void addWin() {
		winCount++;
	}
	
	public String getWinRate() {
		double winRate = (double) (getWins())/(getWins()+getDeaths());
		if (getDeaths() == 0) {
			winRate = 0.0000;
		}
		winRate *= 100;
		String str = "" + winRate;		
		return "" + str.substring(0, 3) + "%";
	}
	
	
	public void reset() {
		int finalX = space*4+15;
		int finalY = space*8+15;
		int t = 1; // time of travel
		
		setX((int) finalX);
		setY((int) finalY);

		System.out.println("RESET");
		deathCount++;
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
	
	public boolean collided(Duck mainChar) {
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
		
		g.setColor(Color.red);
		g.drawRect(x, y, width, height);
		//g.drawRect(x, y-space*3, width, space*3);

		int space = Frame.width/10; 
		if (x > Frame.width) { 
			x = 15;
		} else if (x < 0) {
			x = space*9 +15;
		}
		if (y < 0) {
			reset();
			addWin();
		}
		
	}
	public void showHitBoxes(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
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
