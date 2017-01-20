import java.util.*;

public class Player {
    private String playerName;
    private ArrayList<Card> hand;
    private boolean dealer;
    
    public Player(String name, boolean isDealer) {
        
        playerName = name;
        hand = new ArrayList<Card>();
        dealer = isDealer;
        
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
        for(int i = 0; i < hand.size(); i++) {
            Card temp = hand.get(i);
            tempSum += temp.getValue();
        }
        return tempSum;
    }
    
    public int getSumAce() { 
        return getSum()-10;
    }
    
    public void addToHand(Card a){
		hand.add(a);
        }
    
    public void removeFromHand(int a){
    	hand.remove(a);
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
    
    public boolean checkAce(){
    	for(int i = 0; i < hand.size(); i++){
    		Card temp = hand.get(i);
    		if(temp.getCard().equals("ACE"))
    			return true;
    	}
    	return false;
    }
    
    public Card getCard(int a){
    	return hand.get(a);
    }
    
    public void printDeck(){
    	System.out.println(playerName + ": ");
    	for(int i = 0; i < hand.size(); i++){
    		Card temp = hand.get(i);
    		System.out.println(temp.getSuit() + " " + temp.getCard());
    	}
    	System.out.println();
    }
    
}

