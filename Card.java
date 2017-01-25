/**
 * Write a description of class Card here.
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
import java.awt.event.*;
public class Card
{
    private String suit;
    private int value;
    private String card;
    private BufferedImage image;
    private final String[] CARDS ={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    /**
     * Constructor for objects of class Card
     */
    public Card(int suitInt, int valueA)
    {
        
        if( suitInt == 1)
            suit = "SPADE";
        if(suitInt == 2)
            suit = "CLUB";
        if(suitInt == 3)
            suit = "HEART";
        if(suitInt == 4)
            suit = "DIAMOND";
            
        card = CARDS[valueA-1];
        try{
            image = ImageIO.read(new File(card+"_"+suit+".jpg"));
        }
        catch(Exception e){}
        
        if(valueA >= 11)
            value = 10;
        else
            value = valueA;
        if(valueA == 1)
            value = 11;
    }

    public String getSuit()
    {
        return suit;
    }
    
     public String getCard()
    {
        return card;
    }

    public int getValue()
    {
        return value;
    }
    
    public BufferedImage getImage(){
        return image;
    }
}
