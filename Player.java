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
    
    	public void addToHand(Card a){
		hand.add(a);
        }
    
}
