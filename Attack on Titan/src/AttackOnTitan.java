/**
 * Attack on Titan
 * By: Vansh Sabharwal and Harshveer Thind
 * Date: June 17, 2022
 * Class: ICS 3U1 
 * About:
 * Attack on Titan is about a planet taken over, and the only ones that can stop it or hold off the enemies for long enough
 * are Rick and Morty
 * The objective of the game is kill the invading alien species and hold the off for long enough
 * You can use an ultimate ability to send send the enemies back behind their lines and lines them up for you making it easier to kill them
 * You cannot let them past you or hit you otherwise you lose a life which you have 5 of
 * if you lose all your hearts you lose the game
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.Random;

import javax.swing.*;

public class AttackOnTitan extends JPanel implements ActionListener, KeyListener, MouseListener{
	
	private ImageIcon menuBack, imgControls, rickIcon, rickIconnew, sandBack, bullets, heart, mortyIcon, mortyIconNew;
	private boolean up, down, right, left, play, controls, exit, menu, isFired, ult, useUlt, startUlt;
	private int frameX, frameY, choice, playerYpos, playerXpos, xPosEnemy, yPosEnemy,
	bulletXpos, bulletYpos, dir, score = 0, lives = 5, seconds;
	private final int UP = 0, DOWN = 1, RIGHT = 2, LEFT = 3;
	private Rectangle2D forPlayer, enemyMask, bulletMask;
	private Ellipse2D enemyBounds;
	private Image newBack, newControls, morty;
	private String [] character = {"Rick", "Morty"};
	private String c;
	private Player player;
	private Enemy enemy; 
	private Enemy [] alien;
	private JFrame frame;
	private Font f;
	private Color cBlue;
	private Timer game, playerTimer, shootTimer, enTimer, ultTimer;
	private Random rand;

	
	public static void main(String[] args) {
		new AttackOnTitan();
	}

	public AttackOnTitan()
	{
		// uses the method that initializes the images
		imgInitialization();

		// uses the method that initializes the variables
		variableInitialization();

		// initializes the font
		try {
		f = Font.createFont(Font.TRUETYPE_FONT, new File
		("Lovelo-LineBold.ttf")).deriveFont(30f);
		GraphicsEnvironment ge =
		GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new
		File ("images/Rogue Hero Regular.ttf")));
		} catch (Exception e) {}

		// gets the color cobalt blue
		cBlue = Color.decode("#0047ab");
		
		// initializes things from the enemy class
		enemy = new Enemy();
		alien = new Enemy[3];

		// initializes the random class
		rand = new Random();
		
		// spawns the enemy at random places
		for (int i = 0; i < alien.length; i++)
		{
			alien[i] = new Enemy();
			alien[i].setLocation(rand.nextInt(700) , -200 );
			
		}
		

		// Initialize Mouse Listener bindings
		addMouseListener(this);
		setFocusable(true);
		requestFocus();
		
		// Initialize KeyListener bindings
		setLayout(null);
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
				
		// Generate JFrame for the main room of the classroom
		frame = new JFrame();
		frame.setContentPane(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameX, frameY);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(true);
		
		// initializes variable to access the player class
		player = new Player();

		//initializes the timers used in the game
		ultTimer = new Timer (1000, this);


		game = new Timer (25, this);
		game.start();

		enTimer = new Timer (50, this);

		shootTimer = new Timer (10, this);
		playerTimer = new Timer (50, this);
		playerTimer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// changes JFrame sizes to the different images that may be open
		if (e.getSource() == game)
		{
			if (menu == true)
			{
				frame.setSize(900, 600);

			}
		
			if (controls == true) {
				frame.setSize(592, 720);
			}

			if (play == true)			
			{
				frame.setSize(800, 750);

				if (startUlt == false)
				{
					ultTimer.start();
					startUlt = true;
				}
			}
		}

		// timer for the ultimate ability
		if (e.getSource() == ultTimer)
		{
			seconds--;
		}

		// allows user to ult and pauses ultimate timer
		if (seconds == 0)
		{
			ult = true;
			ultTimer.stop();
		}
		// player movement and stops user from going out of bounds
		if (e.getSource() == playerTimer)
		{
			
			if (up == true)
			{
				player.setDirection(UP);
				dir = UP;
				if ((playerYpos - 90) + player.getImage().getIconHeight() <= 
						0)
				{
					playerYpos -= 0;
				}
				else
				{
					playerYpos -= 10;
				}
			}
			
			else if (down == true)
			{
				player.setDirection(DOWN);
				dir = DOWN;
				if ((playerYpos + 40) + player.getImage().getIconHeight() >= 
						750)
				{
					playerYpos += 0;
				}
				else
				{
					playerYpos += 10;
				}
				
			}
			
			else if (left == true)
			{
				player.setDirection(LEFT);
				dir = LEFT;
				if ((playerXpos - 75) + player.getImage().getIconWidth() <= 
						0)
				{
					playerXpos -= 0;
				}
				else
				{
					playerXpos -= 10;
				}
				
			}
			
			else if (right == true)
			{
				player.setDirection(RIGHT);
				dir = RIGHT;
				if ((playerXpos + 20) + player.getImage().getIconWidth() >= 
						800)
				{
					playerXpos += 0;
				}
				
				else
				{
					playerXpos += 10;
				}
			}
			forPlayer = new Rectangle2D.Double(playerXpos, playerYpos, player.getImage().getIconWidth(), player.getImage().getIconHeight());
			
		}
		
		// bullet movement and physics
		if (e.getSource() == shootTimer)
		{
			bulletMask = new Rectangle2D.Double(bulletXpos, bulletYpos, bullets.getIconWidth(), bullets.getIconHeight());
			if (dir == UP && isFired == true)
			{
				bullets = new ImageIcon ("bulletsUp.png");
				bulletYpos -= 10;
				
				
			}
			else if (dir == DOWN && isFired == true)
			{
				bullets = new ImageIcon ("bulletsDown.png");
				bulletYpos += 10;
				
			}
			else if (dir == RIGHT && isFired == true)
			{
				bullets = new ImageIcon ("bulletsRight.png");
				bulletXpos += 10;
				
			}
			else if (dir == LEFT && isFired == true)
			{
				bullets = new ImageIcon ("bulletsLeft.png");
				bulletXpos -= 10;
			}
			if (bulletXpos  > frame.getWidth() || bulletXpos < 0 || bulletYpos > frame.getHeight() || bulletYpos < 0) // gone beyond the frame.
			{
				isFired = false; 
				shootTimer.stop();
			}
			
			System.out.print("SHOOTING");
		}

		// Enemy mask and bullet intersection
		if (e.getSource() == enTimer)
		{
			for (int i = 0; i < alien.length; i++)
			{
				alien[i].move();
				enemyMask = new Rectangle2D.Double(alien[i].getX(), alien[i].getY(),
						enemy.getNode().getIconWidth(), enemy.getNode().getIconHeight());
				
			}
			
			
			//enemyBounds = new Ellipse2D.Double(xPosEnemy-1000 , yPosEnemy-1000,
			//		enemy.getNode().getIconWidth()+2000, enemy.getNode().getIconHeight()+2000);

			if (bulletMask.intersects(enemyMask))
			{
				for (int i = 0; i < alien.length; i++)
				{
					alien[i].setLocation(rand.nextInt(700) , -200 );
				}
				score++;
				isFired = false;
				shootTimer.stop();
				
			}

			// loss of hit point if enemy gets past player
			if (yPosEnemy > frame.getHeight() || forPlayer.intersects(enemyMask))
			{
				playerXpos = frame.getWidth()/2;
				playerYpos = frame.getHeight()- player.getImage().getIconHeight();
				lives--;
			}

			// goes back to menu if user loses
			if (lives == 0)
			{
				frame.setSize(900, 600);
				menu = true;
				JOptionPane.showMessageDialog(null, "Game Over!","Attack on Titan", JOptionPane.OK_OPTION, mortyIconNew);
			}
				
		}
		
		repaint();
	}	

	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		// draws menu page
		if (menu == true) { 
			g2.drawImage(newBack, 0, 0, this);
			
			g2.setFont(f.deriveFont(50f));
			g2.setColor(cBlue);
			g2.drawString("PLAY", 51, 150);
			
			g2.setFont(f.deriveFont(35f));
			g2.drawString("CONTROLS", 51, 190);
			g2.drawString("EXIT", 51, 225);
		}

		// draws images when the game is being played
		if (play == true)
		{
			g2.drawImage(sandBack.getImage(), 0, 0, this);
			g2.drawImage(player.getImage().getImage(), playerXpos, playerYpos, this);

			for (int i = 0; i < alien.length; i++)
			{
				if (useUlt == true)
				{
					alien[i].setLocation(0 - player.getNode().getIconWidth(), 0 - player.getNode().getIconHeight());
				}
				
				g2.drawImage(alien[i].getNode().getImage(), alien[i].getX(),
				alien[i].getY(), this);
				System.out.println(alien[i].getX() + " " + alien[i].getY());
			}
			if (isFired == true)
			{
				g2.drawImage(bullets.getImage(), bulletXpos, bulletYpos, this);
			}

			g2.setColor(cBlue);
			g2.setFont(f.deriveFont(10));
			g2.drawString("Ultimate: " + seconds, 580, 700);
			g2.drawImage(heart.getImage(), 20, 675, this);
			g2.drawString("" + lives, 60, 700);
		}

		if (controls == true)
		{
			g2.drawImage(newControls, 0, 0, this);

		}
		if (exit == true) {
			System.exit(0);
		}

	}	

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getX() > 51 && e.getX() <= 178 && e.getY() >= 110 
				&& e.getY() <= 150 && menu == true) {
			menu = false;
			
			choice = JOptionPane.showOptionDialog(null, "Rick or Morty:", "CHARACTER SELECTION"
					, JOptionPane.DEFAULT_OPTION, 0, rickIconnew, character, character[0]);

			player.setImage(choice);
					
			JOptionPane.showMessageDialog(null, "We need your help! Aliens from outer space have been\nin control of Earth for over 20 years. It is time to\nmake one final push for a miracle. It is up to you to\nprovide time for reinforcements from other countries to arrive.\nDon't let the enemies get past you they will take a hitpoint ", "ATTACK ON TITAN", JOptionPane.OK_OPTION, rickIconnew);
			enTimer.start();
			System.out.println("play");

			play = true;

		}

		if (e.getX() > 51 && e.getX() <= 250 && e.getY() > 160 && e.getY() <= 190 && menu == true)
		{
			menu = false;
			controls = true;
			System.out.println("controls");
		}

		if (e.getX() > 190 && e.getX() <= 420 && e.getY() > 610 && e.getY() < 720 && controls == true )
		{
			controls = false;
			menu = true;
			System.out.println("menu");
		}

		if (e.getX() > 51 && e.getX() < 150 && e.getY() > 198 && e.getY() < 250 && menu == true){
			menu = false; 
			exit = true;

			System.out.println("exit");
		} 
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
			
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// User clicks the left arrow key
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
		{
			left = true;
		}
					
		// User clicks the right arrow key
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
		{
			right = true;
		}
					
		// User clicks the up arrow key
		else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
		{
			up = true;
		}
					
		// User clicks the down arrow key
		else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
		{
			down = true;
		}
		// User clicks the space key
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if (isFired == false)
			{
				bulletXpos = playerXpos + 15; 
				bulletYpos = playerYpos + 15;
				isFired = true; 
				shootTimer.start();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_E && ult == true)
		{
			useUlt = true;
			seconds = 10;
			ultTimer.start();
		}

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		// User clicks the left arrow key
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
		{
			left = false;
		}
					
		// User clicks the right arrow key
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
		{
			right = false;
		}
					
		// User clicks the up arrow key
		else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
		{
			up = false;
		}
					
		// User clicks the down arrow key
		else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
		{
			down = false;
		}

		
	}

	public void imgInitialization()
	{
		
		rickIcon = new ImageIcon ("Rick.png");
		Image rick1 = rickIcon.getImage().getScaledInstance(50, 75, Image.SCALE_DEFAULT);
		rickIconnew = new ImageIcon (rick1);
		
		mortyIcon = new ImageIcon ("Morty.png");
		Image morty = mortyIcon.getImage().getScaledInstance(50, 75, Image.SCALE_DEFAULT);
		mortyIconNew = new ImageIcon (morty);
		
		menuBack = new ImageIcon("mainAOT.jpg");
		newBack = menuBack.getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT);
		
		imgControls = new ImageIcon("ControlsPage.jpg");
		newControls = imgControls.getImage().getScaledInstance(592, 720, Image.SCALE_SMOOTH);
		
		sandBack = new ImageIcon ("playSandbackground.jpg");

		bullets = new ImageIcon ("");
		bulletMask = new Rectangle2D.Double(bulletXpos, bulletYpos, bullets.getIconWidth(), bullets.getIconHeight());

		heart = new ImageIcon("heart.png");

	}
  
	public void variableInitialization()
	{
		up = false;
		down = false;
		right = false;
		left = false;
		isFired = false;
		menu = true;
		play = false;
		controls = false;
		exit = false;
		frameX = 900;
		frameY = 600;
		playerXpos = 400;
		playerYpos = 400;
		ult = false;
		seconds = 10;
		useUlt = false;
		startUlt = false;

	}



}
