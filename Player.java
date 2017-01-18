package school.work;

import java.util.*;

public class Player {
	private String playerName;
	private ArrayList<Card> hand;
	private int chips;
	
	public Player(String name, int currency) {
		
		playerName = name;
		hand = new ArrayList<Card>();
		chips = currency;
		
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public int getChips() {
		return chips;
	}

}
