package Game;// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void play(Player one, Player two) throws IOException {
        one.makeDeck(); //making card groups for player one
        one.makeHand();

        two.makeDeck(); //making card groups for player two
        two.makeHand();

        Player current;
        current = one;
        System.out.println("Current Player one");

        boolean isWinner = false;
        while(!isWinner) {
            summonPhase(current, one, two);

            //calculation
            one.getBoard().calculate(two.getBoard());

            if(one.getBoard().isBoardEmpty()){
                System.out.println("Player 2 Wins");
                isWinner = true;
            }else if (two.getBoard().isBoardEmpty()){
                System.out.println("Player 1 Wins");
                isWinner = true;
            }

            //at the end remove all temp. buff cards
            one.getBoard().removeMetalOre();
            two.getBoard().removeMetalOre();
        }

    }

    public static void menu() {
        System.out.println("1) Place a card on the board");
        System.out.println("2) Discard from hand");
        System.out.println("3) Remove from board position");
        System.out.println("4) Use an Item");
        System.out.println("5) Add to Bin");
        System.out.println("6) Get Combo");
        System.out.println("7) Evolve Card");
    }

    public static void actionMenu(Player current, Player other) throws IOException {
        Scanner input = new Scanner(System.in);
        menu();
        System.out.print("choose: ");
        String act = input.nextLine();

        switch(act){
            case "1" -> {
                System.out.println("Which spot on the board?");
                System.out.println("UBER, ATTACK, CoreDEFENCE, CORE, DEFENCE");
                String cardPosSelect = input.nextLine();
                System.out.println("Which card in your hand? (left to right [0-4])");
                int numSelectInput = input.nextInt();
                current.placeCard(cardPosSelect, numSelectInput);
            }
            case "2" ->{
                System.out.println("which card in your hand? (number left to right)");
                int numSelectInput = input.nextInt();
                current.discardHandCard(numSelectInput);
            }
            case "3"->{
                System.out.println("Which spot on the board?");
                System.out.println("UBER, ATTACK, CoreDEFENCE, CORE, DEFENCE");
                String cardPosSelect = input.nextLine();
                System.out.println("Which slot's card? (number left to right)");
                int numSelectInput = input.nextInt();
                current.removePlacedCard(cardPosSelect, numSelectInput);
            }
            case "4" ->{
                if(current.hasItem()){
                    ItemSkills useItem;
                    System.out.println("Which Item do you want to use? (0-4)");
                    int numSelectInput = input.nextInt();
                    input.nextLine(); // input int String bug happened I think
                    String cardType = current.getHand().get(numSelectInput).activeTypeOne();
                    if(cardType.equals("ITEM")){
                        Card currentCard = current.getHand().get(numSelectInput);
                        currentCard.useAsItem();
                        System.out.println("Use on your cards or your opponent? (mine/yours)");
                        String choice = input.nextLine();
                        switch(choice){
                            case "mine" -> {
                                System.out.println("Which row (0-3)? col (0-2)?");
                                int row = input.nextInt();
                                int col = input.nextInt();
                                useItem = new ItemSkills(currentCard, current.checkCard(row, col), current);
                                useItem.isUsed();
                            }
                            case "yours" -> {
                                System.out.println("Which row (0-3)? col (0-2)?");
                                int row = input.nextInt();
                                int col = input.nextInt();
                                useItem = new ItemSkills(currentCard, current.selectOtherPlayerCard(other, row, col), current);
                                useItem.isUsed();
                            }
                        }
                        current.discardHandCard(numSelectInput);
                    }else if(cardType.equals("METAL") || cardType.equals("ORE")) {
                        Card currentCard = current.getHand().get(numSelectInput);
                        currentCard.useAsItem();
                        System.out.println("Use on your cards or your opponent? (mine/yours)");
                        String choice = input.nextLine();
                        switch (choice) {
                            case "mine" -> {
                                System.out.println("Which row (0-3)? col (0-2)?");
                                int row = input.nextInt();
                                int col = input.nextInt();
                                input.next();
                                useItem = new ItemSkills(currentCard, current.checkCard(row, col), current);
                                useItem.isUsed();
                                System.out.println("Where do you want to temp. place it?");
                                System.out.println("UBER, ATTACK, CoreDEFENCE, CORE, DEFENCE");
                                String cardPosSelect = input.nextLine();
                                current.placeCard(cardPosSelect, numSelectInput);
                            }
                            case "yours" -> {
                                System.out.println("Which row (0-3)? col (0-2)?");
                                int row = input.nextInt();
                                int col = input.nextInt();
                                input.next();
                                useItem = new ItemSkills(currentCard, current.selectOtherPlayerCard(other, row, col), current);
                                useItem.isUsed();
                                System.out.println("Where do you want to temp. place it?");
                                System.out.println("UBER, ATTACK, CoreDEFENCE, CORE, DEFENCE");
                                String cardPosSelect = input.nextLine();
                                current.placeCard(cardPosSelect, numSelectInput);
                            }
                        }
                    }else{ System.out.println("Literally No"); System.exit(1);}
                }
            }
            case "5" -> {
                System.out.println("Which card in your hand? (left to right [0-4])");
                int numSelectInput = input.nextInt();
                current.getComboBin().addToBin(current.getHand().get(numSelectInput));
                current.getHand().remove(numSelectInput);
                System.out.println(current.getComboBin().checkBin());

            } //if true get which then choose to use
            case "6" -> {
                System.out.println("What Combo do you want?");

                String cName = input.nextLine();
                Combos search = new Combos();
                current.getBoard().addBuild(search.comboSearch(cName)); // meaning it has been marked to be calculated

            }
            case "7" -> {
                System.out.println("What is the ID of the card you want to evolve?");
                int id = input.nextInt();
                current.getBoard().evolve(current, id);
            }

            default -> System.out.println("Invalid choice");

        }

    }

    public static void summonPhase(Player current, Player one, Player two) throws IOException {
        Scanner input = new Scanner(System.in);
        //every 8 or 10 rounds there will be a calculation, 3 or 6 is the test
        int roundCount = 1;
        while (roundCount % 3 != 0) {
            System.out.println("player two");
            System.out.println(Arrays.deepToString(two.getGrid()));
            System.out.println("player one");
            System.out.println(Arrays.deepToString(one.getGrid()));

            //every round pick up a card
            current.pickUpCard();
            Player other = two;
            if(current == two){ other = one;}

            boolean endTurn = false;
            while(!endTurn) {
                //place cards in optimal position phase #chess
                System.out.println(current.getHand());
                actionMenu(current, other);
                System.out.println("End your turn? (y/n)");
                String answer = input.nextLine();
                if(answer.equals("y")){
                    endTurn = true;
                }
            }
            //switching players
            current = switchPlayer(current, one, two);
            roundCount++;

        }
    }

    public static Player switchPlayer(Player current, Player one, Player two) {

        if (current == one) {
            current = two;
            System.out.println("Player two");
            return current;
        }
        if (current == two) {
            current = one;
            System.out.println("Player one");
            return current;

        }
        return current;
    }

    public static void main(String[] args) throws IOException {
        Player one = new Human();
        Player two = new Human();

        play(one, two);
    }
}