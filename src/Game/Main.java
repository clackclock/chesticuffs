package Game;// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import Game.Slots.Calculation;

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
            Calculation p1 = new Calculation(one, two);
            Calculation p2 = new Calculation( two, one);

            if(p1.calculate()){
                System.out.println("Player 1 Wins");
                isWinner = p1.calculate();
            }else if(p2.calculate()){
                System.out.println("Player 2 Wins");
                isWinner = p2.calculate();
            }

        }

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
            System.out.println(Arrays.deepToString(two.getGrid()));
            System.out.println("player one");
            System.out.println(Arrays.deepToString(one.getGrid()));
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

    public static void main(String[] args) throws IOException {
        Player one = new Human();
        Player two = new Human();

        play(one, two);
    }
}