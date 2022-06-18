import java.awt.Image;

import javax.swing.ImageIcon;

public class Player {
	ImageIcon imgPlayer, newPlayer, RRTankUp, RRTankDown, RRTankRight, RRTankLeft;
	Image img;
	private int xPos, yPos;
	private boolean red, green, blue, MORTY, RICK;
	private final int UP = 0, DOWN = 1, RIGHT = 2, LEFT = 3;

	public Player() {
		imgPlayer = new ImageIcon ("");
        Image NewImage2 = imgPlayer.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		newPlayer = new ImageIcon(NewImage2);
		
		xPos = 0;
		yPos = 0;
		red = false;
		green = false;
		blue = false;
		RRTankUp = new ImageIcon("RRTankUp.png");
		RRTankDown = new ImageIcon("RRTankDown.png");
		RRTankRight = new ImageIcon("RRTankRight.png");
		RRTankLeft = new ImageIcon("RRTankLeft.png");
		
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
	public void setImage (int choice)
	{
		if (choice == 0)
		{
			RICK = true;
		}
		else if (choice == 1)
		{
			MORTY = true;
		}

	}
	public void setColor (int color)
	{
		if (color == 1)
		{
			green = true;
		}

		if (color == 2)
		{
			blue = true;
		}

		if (color == 3)
		{
			red = true;
		}
	}

	public ImageIcon TankColor()
	{
		if (red == true)
		{
			
		}
		return RRTankDown;
	}
	
	public void setDirection(int dir)
	{
		if (dir == 0)
		{
			if (RICK == true)
			{
				imgPlayer = new ImageIcon ("RRTankUp.png");
			}
			else if (MORTY == true)
			{
				imgPlayer = new ImageIcon ("MRTankUp.png");
			}
		}
		else if (dir == 1)
		{
			if (RICK == true)
			{
				imgPlayer = new ImageIcon ("RRTankDown.png");
			}
			else if (MORTY == true)
			{
				imgPlayer = new ImageIcon ("MRTankDown.png");
			}
		}
		else if (dir == 2)
		{
			if (RICK == true)
			{
				imgPlayer = new ImageIcon ("RRTankRight.png");
			}
			else if (MORTY == true)
			{
				imgPlayer = new ImageIcon ("MRTankRight.png");
			}
		}
		else if (dir == 3)
		{
			if (RICK == true)
			{
				imgPlayer = new ImageIcon ("RRTankLeft.png");
			}
			else if (MORTY == true)
			{
				imgPlayer = new ImageIcon ("MRTankLeft.png");
			}
		}
	}

	public ImageIcon getImage()
	{
		return imgPlayer;
	}

	public ImageIcon getNode()
	{
		return newPlayer;
	}
}
