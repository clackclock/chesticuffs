package Game;
import Game.Slots.*;

import java.util.ArrayList;

public interface Player {
    ArrayList<Card> getDeck();
    ArrayList<Card> getHand();
    Board playBoard();
    void getBoard();
    void makeDeck();
    void makeHand();
//    void makeBoard();
//    void attackOther(Player c, String from, int pos, String to, int opos);
    void placeCard(String posName, int cardPosNum);
    void pickUpCard();
    void discardHandCard(int c);
    void removePlacedCard(String slotName, int slotNum); //from the board
    void gamble(Player current, int handSlot, String raiseAmt);

}
