import java.util.*;

public class BlackJack {

    public static void main(String[] args) {
        Deck deck = new Deck();
        ArrayList<Player> playerList= new ArrayList<Player>();
        Player dealer = new Player("Dealer",true);

        Scanner in = new Scanner(System.in);
        System.out.println("How many players are there?");
        int playerCount = in.nextInt();

        //initialize players
        for(int i = 0; i < playerCount ; i++){
            String name = "Player"+(i+1);
            Player temp = new Player(name, false);
            playerList.add(temp);
            if(i == playerCount-1){
                playerList.add(dealer);
            }
        }

        //initial deal
        for(int i = 0; i < playerList.size(); i++){
            Player temp = playerList.get(i);
            temp.addToHand(deck.deal());
            temp.addToHand(deck.deal());
        }

        //game
        for(int i = 0; i < playerList.size(); i++){
            Player temp = playerList.get(i);
            if(temp.isDealer() == true){
                //TODO DEALER CODE CHECK FOR ACE
                temp.printDeck();
                if(temp.getSum() < 17){
                    temp.addToHand(deck.deal());
                    i--;
                }   
            }
            else{
                if(temp.getSum() > 21){
                    if(temp.checkAce() == true){
                        //TODO fix for ace
                    }
                    System.out.println("BUST");
                    temp.printDeck();
                }
                else{
                    if(temp.checkSplit() == true){
                        temp.printDeck();
                        System.out.println("Enter 1 for hit");
                        System.out.println("Enter 2 for stay");
                        System.out.println("Enter 3 for split");

                        int selection = in.nextInt();

                        if(selection == 1){
                            temp.addToHand(deck.deal());
                            i--;
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
                            playerList.add(i+1,split);
                        }
                    }
                    // else only hit or stay
                    else{
                        temp.printDeck();
                        System.out.println("Enter 1 for hit");
                        System.out.println("Enter 2 for stay");
                        System.out.println("Enter 3 for double down");

                        int selection = in.nextInt();

                        if(selection == 1){
                            temp.addToHand(deck.deal());
                            i--;
                        }
                        if(selection == 2){
                            //nothing
                        }
                        if(selection == 3){
                            temp.addToHand(deck.deal());
                        }

                    }
                }
            }

        }

    }
}
