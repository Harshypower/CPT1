import java.awt.Image;

import javax.swing.ImageIcon;
import java.util.Random;
public class Enemy {
	ImageIcon enemy, enemyNew;
	private int index;
	Image img;
	private int xPos, yPos;
	private Random rand;

		public Enemy() {
			// enemy = new A;
			rand = new Random();
			index = rand.nextInt(3) + 1;
			
			enemy = new ImageIcon("Alien" + index + ".png");
			img = enemy.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
			enemyNew = new ImageIcon(img);
			
			xPos = 0;
			yPos = 0;
			
		}
		public void setLocation(int x, int y)
		{
			xPos = x;
			yPos = y;
		}
		
		public int getX()
		{
			return xPos;
		}
		
		public int getY()
		{
			return yPos;
		}
		
		public void setX(int x)
		{
			xPos = x;
		}
		
		public void setY(int y) 
		{
			xPos = y;
		}
		public void move()
		{
			yPos += 5;
		}
		public ImageIcon getNode()
		{
			return enemyNew;
		}
	}