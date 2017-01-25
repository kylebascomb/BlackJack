
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
    private BufferedImage image;
    private int xPos;
    private int yPos;
 
    private int selection;
    private boolean hasPressed;
    JButton hit = new JButton("HIT");
    JButton stay = new JButton("STAY");
    JButton doubleDown = new JButton("DOUBLE DOWN");
    JButton split = new JButton("SPLIT");
    public BlackJack(){
        deck = new Deck();
        playerList = new ArrayList<Player>();
        dealer = new Player("Dealer",true);
        xPos = 0;
        yPos = 0;

        selection = 0;
        hasPressed = false;
        add(hit);
        add(stay);
        add(doubleDown);
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
        ActionListener hitListener = new HitButtonListener();
        hit.addActionListener(hitListener);
        ActionListener stayListener = new StayButtonListener();
        stay.addActionListener(stayListener);
        ActionListener doubleDownListener = new DoubleDownButtonListener();
        stay.addActionListener(doubleDownListener);  

    }

    public void dealCard(Player temp){
        image = temp.addToHand(deck.deal());
        xPos = temp.getXPos();
        yPos = temp.getYPos();
        repaint();

    }


    public void setSelection1(){
        selection = 1;
        hasPressed = true;
    }

    public void setSelection2(){
        selection = 2;
        hasPressed = true;
    }

    public void setSelection3(){
        selection = 3;
        hasPressed = true;
    }

    public void paintComponent (Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(image,xPos,yPos,null);
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
            dealCard(temp);
            dealCard(temp);
        }

    }

    public void gameLogic(){// Condense more
        Scanner in = new Scanner(System.in);
        for(int count = 0; count < playerList.size(); count=count){
            Player temp = playerList.get(count);
            selection = 0;
            hasPressed = false;
            if(temp.isDealer() == true){

                if(temp.getSum() < 17){
                    dealCard(temp);
                }   
            }
            else{   
                if(temp.getSum() > 21){
                    temp.printDeck();
                } else if (temp.getSum() == 21 && temp.countAce() == 1 && temp.checkFace() == true) {
                    temp.printDeck();
                }
                else {

                    if(temp.getSum() < 21 && temp.checkSplit() == true){ 
                        temp.printDeck();
                        System.out.println("Enter 1 for hit");
                        System.out.println("Enter 2 for stay");
                        System.out.println("Enter 3 for split");

                        int selection = in.nextInt();

                        if(selection == 1){
                            temp.addToHand(deck.deal());
                            count--;
                        }
                        if(selection == 2){
                            //nothing
                        }
                        if(selection == 3){
                            Player split = new Player(temp.getPlayerName(),false);
                            Card splitCard = temp.getCard(1);
                            temp.removeFromHand(1);
                            split.addToHand(splitCard);
                            temp.addToHand(deck.deal());
                            temp.printDeck();
                            split.addToHand(deck.deal());
                            playerList.add(count+1,split);
                        }
                    }

                    // else only hit or stay
                    else {
                        temp.printDeck();  
                        if(temp.getSum() < 21){ 
                            add(hit);
                            add(stay);
                            add(doubleDown);
                            while(hasPressed == false){
                                try{
                                    Thread.sleep(1000);
                                }
                                catch(Exception e){}
                            }
                            if(selection == 1){
                                dealCard(temp);

                            }

                            if(selection == 2){
                                count++;
                            }

                            if(selection == 3){
                                dealCard(temp);   
                                count++;
                            }

                        }

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

            if(temp.getSum() == 21 && temp.countAce() == 1 && temp.checkFace() == true) {
                System.out.println(temp.getPlayerName() + ": BlackJack!");
            } /*else if(temp.getSum() == 21 && temp.checkAce() == true && temp.checkFace() == true) {
            //TODO If Player and Dealer get BlackJack PUSH
            }*/ else if(temp.getSum() > 21) {
                System.out.println(temp.getPlayerName() + ": You Bust!");
            } else if(temp.getSum() == getDealerSum()) {
                System.out.println(temp.getPlayerName() + ": Push!");
            } else if (temp.getSum() > getDealerSum() || getDealerSum() > 21) {
                System.out.println(temp.getPlayerName() + ": You Win! ");
            } else {
                System.out.println(temp.getPlayerName() + ": You Lose! ");
            }

        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1600, 1000);
        frame.setTitle("BlackJack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BlackJack game = new BlackJack();

        frame.add(game);
        frame.setVisible(true); 
        game.initializePlayers(1);
        boolean cont = true;

        while(cont == true){
            game.initialDeal();
            game.gameLogic();
            game.scoring();

            Scanner answer = new Scanner(System.in);
            System.out.println("\nWould you like to play again? (y/n)");
            if(answer.nextLine().equalsIgnoreCase("Y")){
                cont = true;
                game.clearHand();
            }
            else cont = false;
        }

    }
}
