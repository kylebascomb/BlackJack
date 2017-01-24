import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.util.*;
import java.awt.event.*;

public class ImageDemo extends JPanel {

	private BufferedImage image;
	private BufferedImage image2;
	private BufferedImage image3;
	private BufferedImage showCard;
	private ArrayList<BufferedImage> hand;
	
	public ImageDemo() {
		hand = new ArrayList<BufferedImage>();
		try{
			image = ImageIO.read(new File("2_HEART.jpg"));
			image2 = ImageIO.read(new File("J_DIAMOND.jpg"));
			image3 = ImageIO.read(new File("7_HEART.jpg"));
			hand.add(image);
			hand.add(image2);
			hand.add(image3);	
		}
		catch(Exception e) {
			System.out.println("Hey, get it right.");
		}
	}
	
	public void drawCard() {
		showCard = hand.remove(0);
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(showCard, 0, 100, null);
	}
	
	public static void main(String[] args) { //throws Exception
		JFrame frame = new JFrame();
		frame.setSize(1920, 1080);
		frame.setTitle("BlackJack");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageDemo c = new ImageDemo();
		
		JButton hit = new JButton("HIT");
		c.add(hit);
		JButton stay = new JButton("STAY");
		c.add(stay);
		
		class HitButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				//things to be done when click on Hit Button (Any Methods)
				c.drawCard();
			}
		}
		
		ActionListener hitListener = new HitButtonListener();
		hit.addActionListener(hitListener);
		
		frame.add(c);
		frame.setVisible(true); 
	}
	
}
