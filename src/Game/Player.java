package Game;
import Game.Slots.*;

import java.util.ArrayList;

public interface Player {
    ArrayList<Card> getDeck();
    ArrayList<Card> getHand();
    boolean hasItem();
    //Board playBoard();
    Board getBoard();
    Positions[][] getGrid();
    void makeDeck();
    void makeHand();
    void placeCard(String posName, int cardPosNum);
    void pickUpCard();
    void discardHandCard(int c);
    void removePlacedCard(String slotName, int slotNum); //from the board
    Card selectOtherPlayerCard(Player other, int row, int col);
    Card checkCard(int row, int col);
    Combos getComboBin();

}
