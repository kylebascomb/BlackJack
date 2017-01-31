import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.util.*;
import java.awt.event.*;

public class Player extends JPanel{
    private String playerName;
    private ArrayList<Card> hand;
    private boolean dealer;
    private int xPos;
    private int yPos;
    private BufferedImage image;
    private boolean split;

    public Player(String name, boolean isDealer, boolean isSplit) {
        playerName = name;
        hand = new ArrayList<Card>();
        dealer = isDealer;
        split = isSplit;
        yPos = 450;
        if(isDealer == true){
            yPos = 100;
        }

    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isDealer(){
        if (dealer == true)
            return true;
        return false;
    }

    public int getSum() {
        int tempSum = 0;
        int aceCount = countAce();
        for(int i = 0; i < hand.size(); i++) {
            Card temp = hand.get(i);
            tempSum += temp.getValue();
            if(tempSum > 21 && aceCount > 0){
                tempSum -= 10;
                aceCount--;
            }
        }
        return tempSum;
    }

    public void addToHand(Card a){
        hand.add(a);
        
    }

    public void removeFromHand(int a){
        hand.remove(a);
    }
    
    public int getYPos(){
        return yPos;
    }

    public void eraseHand() {
        for(int i = 0; i < hand.size(); i++) {
            hand.remove(i);
            i--;
        }
    }

    public boolean checkSplit(){
        if(hand.size() > 2)
            return false;
        Card card1 = hand.get(0);
        Card card2 = hand.get(1);
        if(card1.getCard().equals(card2.getCard()))
            return true;
        else
            return false;
    }

    public boolean isSplitPlayer() {
    	return split;
    }
    
    public int countAce(){
        int count = 0;
        for(int i = 0; i < hand.size(); i++){
            Card temp = hand.get(i);
            if(temp.getCard().equals("A"))
                count ++;
        }
        return count;
    }

    public boolean checkFace(){
        for(int i = 0; i < hand.size(); i++){
            Card temp = hand.get(i);
            if(temp.getCard().equals("10") || temp.getCard().equals("J") || temp.getCard().equals("Q") || temp.getCard().equals("K") )
                return true;
        }
        return false;
    }

    public Card getCard(int a){
        return hand.get(a);
    }

    public void printDeck(){
        System.out.print(playerName + ": ");
        for(int i = 0; i < hand.size(); i++){
            Card temp = hand.get(i);
            
        }
        System.out.println(getSum());
        System.out.println();
    }
    
    public ArrayList<Card> getHand(){
        return hand;
    }
}
