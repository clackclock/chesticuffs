package Game;// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.Scanner;
public class Main {
    public static void play(Player one, Player two) {
        //Scanner input = new Scanner(System.in);
        one.makeDeck(); //making card groups for player one
        one.makeHand();

        two.makeDeck(); //making card groups for player two
        two.makeHand();

        Player current;
        current = one;
        System.out.println("Current Player one");

        //Board setup stuff

       // boolean isWinner = false;
        //while(!isWinner) {
            summonPhase(current, one, two);
            //calculation

        //}

    }

    public static void menu() {
        System.out.println("1) Place a card on the board");
        System.out.println("2) Discard from hand");
        System.out.println("3) Remove from board position");
        System.out.println("4) Add card to the pot");
    }

    public static void actionMenu(int numSelectInput, Player current) {
        Scanner input = new Scanner(System.in);
        String act = input.nextLine();

        switch(act){
            case "1" -> {
                System.out.println("Which spot on the board?");
                System.out.println("UBER, ATTACK, CoreDEFENCE, CORE, DEFENCE");
                String cardPosSelect = input.nextLine();
                System.out.println("Which card in your hand? (left to right [0-4])");
                System.out.println(numSelectInput);
                current.placeCard(cardPosSelect, numSelectInput);
            }
            case "2" ->{
                System.out.println("which card in your hand? (number left to right)");
                System.out.println(numSelectInput);
                current.discardHandCard(numSelectInput);
            }
            case "3"->{
                System.out.println("Which spot on the board?");
                System.out.println("UBER, ATTACK, CoreDEFENCE, CORE, DEFENCE");
                String cardPosSelect = input.nextLine();
                System.out.println("Which slot's card? (number left to right)");
                System.out.println(numSelectInput);
                current.removePlacedCard(cardPosSelect, numSelectInput);
            }
            case "4" ->{
                System.out.println("which card in your hand? (number left to right)");
                System.out.println(numSelectInput);
                System.out.println("how many? one) 1 two) everything in hand all) every version in deck and hand");
                String test = input.nextLine();
                current.gamble(current, numSelectInput, test);
            }
            default -> {
                System.out.println("Invalid choice");
                System.exit(1);
            }
        }

    }

    public static void summonPhase(Player current, Player one, Player two) {
        int numSelectInput;
        Scanner input = new Scanner((System.in));
        //every 4 or 5 rounds there will be a calculation, 3 is the test
        int roundCount = 1;
        while (roundCount % 3 != 0) {
            System.out.println("player two");
            two.getBoard();
            System.out.println("player one");
            one.getBoard();
            //every round pick up a card
            current.pickUpCard();
            //place cards in optimal position phase #chess
            System.out.println(current.getHand());
            menu();
            System.out.print("Which card are you acting with?: ");
            numSelectInput = input.nextInt();
            System.out.print("choose: ");
            actionMenu(numSelectInput, current);
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

    public static Player opposite(Player loser, Player one, Player two) { //for winner thing
        if (loser == one) {
            System.out.println("Player two");
            return two;

        } else if(loser == two){
            System.out.println("Player one");
            return one;
        }
        return loser;
    }

    public static void main(String[] args) {

        Player one = new Human();
        Player two = new Human();

        play(one, two);
    }
}