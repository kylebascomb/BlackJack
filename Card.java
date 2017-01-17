
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
    /**
     * Constructor for objects of class Card
     */
    public Card(int suitInt, int valuea)
    {
        if( suitInt == 1)
            suit = "Spades";
        if(suitInt == 2)
            suit = "Clubs";
        if(suitInt == 3)
            suit = "Hearts";
        if(suitInt == 4)
            suit = "Diamonds";
        
        value = valuea;
    }

    public String getSuit()
    {
        return suit;
    }
    
    public int getValue()
    {
        return value;
    }
    }

