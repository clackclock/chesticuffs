package Game;

import Game.Slots.Board;
import Game.Slots.Positions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Human implements Player {
    private final Random r = new Random();
    private final cardDatabase cards = new cardDatabase();
    private final ArrayList<Card> deck = new ArrayList<>();
    private final ArrayList<Card> hand = new ArrayList<>();

    private final Board b = new Board();

    public Human() throws IOException {
        new Scanner(System.in);
    }

    public void makeDeck() {
        cards.cardData();
        int deckSize = 30;
        for (int i = 0; i < deckSize; i++) {
            int shuffle = r.nextInt( cards.pack.size());
            deck.add(cards.pack.get(shuffle));
        }
    }
    public ArrayList<Card> getDeck() { return deck; }

    public void makeHand() {
        cards.cardData();
        int handSize = 5;
        for (int i = 0; i < handSize; i++) {
            int shuffle = r.nextInt(deck.size() );
            hand.add(deck.get(shuffle));
            //deck.remove(shuffle);
        }
    }
    public ArrayList<Card> getHand() { return hand; }
    public boolean hasItem(){
        for(Card i: hand){
            if(i.activeTypeOne().equals("ITEM")){
                return true;
            }
        }
        return false;
    }

    public Board getBoard() { return b; }
    public Positions[][] getGrid(){ return b.getGrid(); }

    public void placeCard(String pos, int card) {
        b.addToSlots(pos,hand.get(card));
        hand.remove(card);
    }
    public void pickUpCard() {
        Card up;
        int shuffle = r.nextInt(deck.size());
        up = deck.get(shuffle);
        if (deck.size() == 1) {
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
    public void removePlacedCard(String pos, int slotNum) { b.removeFromSlots(pos, slotNum); }

    public Card selectOtherPlayerCard(Player other, int row, int col){
        Card selectedCard = other.getGrid()[row][col].getSlot();
        if(other.getGrid()[row][col].isEmpty() && selectedCard.isItem()) {
            System.exit(1);
        }
        return selectedCard;
    }
    public Card checkCard(int row, int col){
        Card thisCard = getGrid()[row][col].getSlot();
        if(thisCard == null){
            System.out.println("This is null");
            System.exit(1);
        }
        if(getGrid()[row][col].isEmpty() && thisCard.isItem()){
            System.exit(1);
        }
        return thisCard;
    }
    Combos bin = new Combos();
    public Combos comboBin(){
        return bin;
    }

}
