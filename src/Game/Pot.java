package Game;

import java.util.ArrayList;

public class Pot {
    private int numItemsInPot = 0;
    private double multiplier = 1;
    private final ArrayList<Card> pot = new ArrayList<>();

    public void get(Player current, int cardIndex) {
        pot.add(current.getHand().get(cardIndex));
        numItemsInPot++;
        multiplier = Math.abs(multiplier + 0.5);
        current.getHand().remove(cardIndex);
    }

    public void getHandAmt(Player current,int cardIndex) {
        for (int k = 0; k < current.getHand().size(); k++) {
            if (current.getHand().get(k) == current.getHand().get(cardIndex)) {
                pot.add(current.getHand().get(cardIndex));
                current.getHand().remove(cardIndex);
                numItemsInPot++;
            }
        }
    }

    public void getAll(Player current, int cardIndex) {
        for (int i = 0; i <= current.getDeck().size(); i++) {
            if (current.getDeck().get(i) == current.getHand().get(cardIndex)) {
                pot.add(current.getDeck().get(cardIndex));
                current.getDeck().remove(cardIndex);
                numItemsInPot++;
            }
        }
        for (int k = 0; k <= current.getHand().size(); k++) {
            if (current.getHand().get(k) == current.getHand().get(cardIndex)) {
                pot.add(current.getHand().get(cardIndex));
                current.getHand().remove(cardIndex);
                numItemsInPot++;
            }
        }
    }

    public long problems() {
        return Math.abs(Math.round(numItemsInPot * multiplier));
    }
}
