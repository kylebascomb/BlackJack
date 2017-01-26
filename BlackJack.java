import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.util.*;
import java.awt.event.*;

public class BlackJack extends JPanel{
    private Deck deck;
    private ArrayList<Player> playerList;
    private Player dealer;
    private int xPos;
    private int yPos;
    private boolean dealerDone;
    private boolean hasSplit;
    private int selection;
    private boolean blackJack,push,bust,win,lose,cont = false;
    JButton hit = new JButton("HIT");
    JButton stay = new JButton("STAY");
    JButton doubleDown = new JButton("DOUBLE DOWN");
    JButton split = new JButton("SPLIT");
    JButton yes = new JButton("YES");
    JButton no = new JButton("NO");
    private ArrayList<BufferedImage> deckBack;
    private BufferedImage imageBack;
    private BufferedImage removeBack;
    private int deckSize = 53;
    public BlackJack(){
        deck = new Deck();
        playerList = new ArrayList<Player>();
        dealer = new Player("Dealer",true);
        xPos = 0;
        yPos = 0;
        selection = 0;
        add(hit);
        hit.setBounds(1700, 100, 100, 50);
        add(stay);
        stay.setBounds(1700, 150, 100, 50);
        
        deckBack = new ArrayList<BufferedImage>();
		try{
			for(int i = 0; i < 52; i++) {
				imageBack = ImageIO.read(new File("BACK.jpg"));
				deckBack.add(imageBack);
			}
			
		}
		catch(Exception e) {
			//System.out.println("Hey, get it right.");
		}
        
        class HitButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                setSelection1();
            }
        }

        class StayButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                setSelection2();
            }
        }

        class DoubleDownButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                setSelection3();
            }
        }

        class SplitButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent event) {
                setSelection4();
            }
        }
        
        class YesButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent event) {
                setSelection5();
            }
        }
        
        class NoButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent event) {
                setSelection6();
            }
        }

        ActionListener hitListener = new HitButtonListener();
        hit.addActionListener(hitListener);
        ActionListener stayListener = new StayButtonListener();
        stay.addActionListener(stayListener);
        ActionListener doubleDownListener = new DoubleDownButtonListener();
        doubleDown.addActionListener(doubleDownListener);  
        ActionListener splitListener = new SplitButtonListener();
        split.addActionListener(splitListener);
        ActionListener yesListener = new YesButtonListener();
        yes.addActionListener(yesListener);  
        ActionListener noListener = new NoButtonListener();
        no.addActionListener(noListener);  

    }
    
    public void removeDeck() {
		removeBack = deckBack.remove(0);
		deckSize--;
	}
    
    public void setSelection1(){
        selection = 1;
    }

    public void setSelection2(){
        selection = 2;
    }

    public void setSelection3(){
        selection = 3;
    }

    public void setSelection4(){
        selection = 4;
    }
    
    public void setSelection5(){
    	selection = 5;
    }
    
    public void setSelection6(){
    	selection = 6;
    }

    public void paintComponent (Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        for(int i = 0; i < playerList.size(); i ++){
            int xPos = 150;
            Player temp = playerList.get(i);
            ArrayList<Card> playerHand = temp.getHand();

            for(int j = 0; j < playerHand.size(); j++){
                Card tempCard = playerHand.get(j);
                BufferedImage image = tempCard.getImage();
                if(temp.isDealer() == true && j == 0 && dealerDone == false){
                    try{
                        image = ImageIO.read(new File("BACK.jpg"));
                    }
                    catch(Exception e){}
                }
                if(hasSplit == true && i == 1){
                    if(j == 0)
                         xPos = xPos + 200 +(15*(playerHand.size()));
                         else xPos = xPos + (15+playerHand.size());
                }
            
                if(j == 0){
                    xPos =  xPos;
                }
                else{
                    xPos =  xPos + (15*(playerHand.size()));
                }
                int yPos = temp.getYPos();
                g2.drawImage(image,xPos,yPos,null);
            }
            if(blackJack == true){
                g2.drawString("BLACKJACK!",650,500);
            }
            if(bust == true){
                g2.drawString("BUST",650,500);
            }
            if(push == true){
                g2.drawString("PUSH!",650,500);
            }
            if(win == true){
                g2.drawString("WIN!",650,500);
            }
            if(lose == true){
                g2.drawString("LOSE!",650,500);
            }
            if(push == true || win == true || lose == true|| bust == true || blackJack == true) {
            	g2.drawString("Would you like to continue? Y/N",1000,500);
            }
        }
        
        for(int i = 0; i < deckSize; i++) {
			g2.drawImage(removeBack, i + 1000, 0, null);
		}
        
    }

    public void initializePlayers(int playerCount){
        for(int i = 0; i < playerCount ; i++){
            String name = "Player "+(i+1);
            Player temp = new Player(name, false);
            playerList.add(temp);
            if(i == playerCount-1){
                playerList.add(dealer);
            }
        } 
    }

    public void clearHand(){
        for(int i = 0; i < playerList.size(); i++)
        {
            Player temp = playerList.get(i);
            temp.eraseHand();
        }
    }

    public void initialDeal(){
        for(int i = 0; i < playerList.size(); i++){
            Player temp = playerList.get(i);
            temp.addToHand(deck.deal());
            removeDeck();
            temp.addToHand(deck.deal());
            removeDeck();
            repaint();
        }

    }

    public void gameLogic(){// Condense more
        int hitCount = 0;
        add(doubleDown);
        doubleDown.setBounds(1650, 200, 200, 50);
        hasSplit = false;
        for(int count = 0; count < playerList.size(); count++){
            Player temp = playerList.get(count);
            selection = 0;
            dealerDone = false;
            
            if(temp.checkSplit() == true && temp.isDealer() == false){
                remove(doubleDown);
                add(split);
                split.setBounds(1700, 200, 100, 50);
            }

            if(temp.isDealer() == true){
                if(temp.getSum() < 17){
                    temp.addToHand(deck.deal());
                    removeDeck();
                    count--;
                    repaint();
                }   
                else{
                    dealerDone = true;
                    repaint();
                }
            }
            else{   
                if(temp.getSum() < 21){ 
                    while(selection == 0){
                        try{
                            Thread.sleep(500);
                        }
                        catch(Exception e){}
                    }
                    
                    if(selection == 1){
                    	temp.addToHand(deck.deal());
                        count--;
                        remove(doubleDown);
                        removeDeck();
                        repaint();
                    }

                    if(selection == 2){
                        //nothing
                    }
                    
                    if(selection == 3){
                        temp.addToHand(deck.deal()); 
                        removeDeck();
                        repaint();

                    }

                    if(selection == 4){
                        Player split = new Player(temp.getPlayerName(),false);
                        Card splitCard = temp.getCard(1);
                        temp.removeFromHand(1);
                        split.addToHand(splitCard);
                        temp.addToHand(deck.deal());
                        removeDeck();
                        hasSplit = true;
                        split.addToHand(deck.deal());
                        playerList.add(count+1,split);
                        repaint();
                        count--;
                    }
                    
                    if(selection == 5) {
                    	//What happens when YES button is pressed
                    }
                    
                    if(selection == 6) {
                    	System.exit(0);
                    }
                }
            }
        }
    }

    public int getDealerSum(){
        int dealerTotal = 0;
        for(int i = 0; i < playerList.size(); i++) {
            Player temp = playerList.get(i);
            if(temp.isDealer() == true) {
                dealerTotal = temp.getSum();
            } 
        }
        return dealerTotal;
    }

    public void scoring(){
        for(int i = 0; i < playerList.size() - 1; i++) {
            Player temp = playerList.get(i);
            ArrayList<Card> hand = temp.getHand();

            if(temp.getSum() == 21 && temp.countAce() == 1 && temp.checkFace() == true && hand.size() == 2) {// ace+face+8+2 counted as blackjack
                blackJack = true;
            } /*else if(temp.getSum() == 21 && temp.checkAce() == true && temp.checkFace() == true) {
            //TODO If Player and Dealer get BlackJack PUSH
            }*/ else if(temp.getSum() > 21) {
                bust = true;
            } else if(temp.getSum() == getDealerSum()) {
                push = true;
            } else if (temp.getSum() > getDealerSum() || getDealerSum() > 21) {
                win = true;
            } else {
                lose = true;
            }
            
            if(push == true || win == true || lose == true|| bust == true || blackJack == true) {
                add(yes);
                yes.setBounds(1650, 500, 100, 100);
            }
            
            if(push == true || win == true || lose == true|| bust == true || blackJack == true) {
                add(no);
                no.setBounds(1750, 500, 100, 100);
            }
        }
    }
    
    /**public void yesNo() {
    	
    	if(selection == 5) {
        	//What happens when YES button is pressed
        }
        
        if(selection == 6) {
        	System.exit(0);
        }
    	
    }**/

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1900, 1000);
        frame.setTitle("BlackJack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BlackJack game = new BlackJack();

        game.setLayout(null);
        
        frame.add(game);
        frame.setVisible(true); 
        game.initializePlayers(1);
        boolean cont = true;

        while(cont == true){
        	
        	game.initialDeal();
            game.gameLogic();
            game.scoring();
            //game.yesNo(); Might make it a new method to continue

          Scanner answer = new Scanner(System.in);
            System.out.println("\nWould you like to play again? (y/n)");
            if(answer.nextLine().equalsIgnoreCase("Y")){
                cont = true;
                game.clearHand();
                game.repaint();
            }
            else cont = false;
        }

    }
}
