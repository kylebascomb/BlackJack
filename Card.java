/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Card
{
    private String suit;
    private int value;
    private String card;
    private final String[] CARDS ={"ACE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","EIGHT","NINE","TEN","JACK","QUEEN","KING"};
    /**
     * Constructor for objects of class Card
     */
    public Card(int suitInt, int valueA)
    {
        
        if( suitInt == 1)
            suit = "Spades";
        if(suitInt == 2)
            suit = "Clubs";
        if(suitInt == 3)
            suit = "Hearts";
        if(suitInt == 4)
            suit = "Diamonds";
        card = CARDS[valueA-1];
        
        if(valueA >= 11)
            value = 10;
        else
            value = valueA;
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
}
