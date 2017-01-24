

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
        }

        for(int i = 0; i < playerList.size(); i++){
            Player temp = playerList.get(i);
            temp.addToHand(deck.deal());
        }
    }

    public void gameLogic(){// Condense more
        Scanner in = new Scanner(System.in);
        for(int i = 0; i < playerList.size(); i++){
            Player temp = playerList.get(i);
            if(temp.isDealer() == true){
                temp.printDeck();
                if(temp.getSum() < 17){
                    temp.addToHand(deck.deal());
                    i--;
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
                    else {
                        temp.printDeck();  
                        if(temp.getSum() < 21){ 
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
        BlackJack game = new BlackJack();
        Scanner in = new Scanner(System.in);
        System.out.println("How many players are there?");
        int playerCount = in.nextInt();
        
        game.initializePlayers(playerCount);
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
