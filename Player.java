import java.util.*;

public class Player {
    private String playerName;
    private ArrayList<Card> hand;
    private int chips;
    private boolean dealer;
    
    public Player(String name, int currency, boolean isDealer) {
        
        playerName = name;
        hand = new ArrayList<Card>();
        chips = currency;
        dealer = isDealer;
        
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public int getChips() {
        return chips;
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
    
}
