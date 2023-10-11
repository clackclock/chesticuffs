package Game;

import Game.Slots.Board;
//import static Game.Slots.Board.position.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class Human implements Player {
    private final Random r = new Random();
    private final cardDatabase cards = new cardDatabase();
    private final Pot p = new Pot();
    private final ArrayList<Card> deck = new ArrayList<>();
    private final ArrayList<Card> hand = new ArrayList<>();

    //board test
    private final Board b = new Board();

    //end Board

    public Human() {
        new Scanner(System.in);
    }

    public void makeDeck() {
        cards.cardData();
        int deckSize = 30;
        for (int i = 0; i < deckSize; i++) {
            int shuffle = r.nextInt(1, cards.pack.size());
            deck.add(cards.pack.get(shuffle));
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void makeHand() {
        cards.cardData();
        int handSize = 5;
        for (int i = 0; i < handSize; i++) {
            int shuffle = r.nextInt(1, cards.pack.size());
            hand.add(deck.get(shuffle));
            //deck.remove(shuffle);
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void getBoard() {
        b.printBoard();
    }
    public Board playBoard(){
        return b;
    }

    public void placeCard(String pos, int card) {
        b.addToSlots(pos,hand.get(card));
        hand.remove(card);
    }

    public void pickUpCard() {
        Card up;
        int shuffle = r.nextInt(0, deck.size());
        up = deck.get(shuffle);
        if (deck.size() == 0) {
            System.out.println("No more cards, SUFFER");
            return;
        }

        hand.add(up);
        deck.remove(shuffle);
    }

    public void discardHandCard(int c) {
        deck.add(hand.get(c));
        hand.remove(c);
    }

    public void removePlacedCard(String pos, int slotNum) {
        b.removeFromSlots(pos, slotNum);
    }

    public void gamble(Player current, int c, String index) {
        switch(index){
            case "one" -> p.get(current, c);
            case "two" -> p.getHandAmt(current, c);
            case "all" -> p.getAll(current, c);
        }

    }
}
