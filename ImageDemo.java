
/**
 * Write a description of class ImageDemo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.util.*;
public class ImageDemo extends JPanel
{
    BufferedImage image;
    BufferedImage image2;
    
    public ImageDemo(){ //throws Exception
        try
        {
            image = ImageIO.read(new File("BACK" + ".jpg"));
            image2 = ImageIO.read(new File("J_DIAMOND.jpg"));
        }
        catch(Exception e){
            //System.out.println("Exception handler goes here");
        }
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(image,0,0,null);
        g2.drawImage(image2,500,0,null);
    }
    
       public static void main(String[] args){ // throws Exception
       JFrame frame = new JFrame();
       frame.setSize(750,750);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       ImageDemo c = new ImageDemo();
       frame.add(c);
       frame.setVisible(true);
       
    }
}
