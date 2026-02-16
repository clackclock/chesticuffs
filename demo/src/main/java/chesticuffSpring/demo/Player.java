package chesticuffSpring.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Player {
    private int health = 100;
    private List<Card> deck = new ArrayList<>();
    private List<Card> hand = new ArrayList<>();
    private Board b = new Board();

    // Methods for game logic
    public void drawCard(Card card) {
        this.hand.add(card);
    }
    public boolean hasIngredients(List<Recipe.Ingredient> required) {
        for (Recipe.Ingredient req : required) {
            long count = 0; //ugh longs are weird

            if (req.id != null) {
                // Check for specific Card ID
                count = hand.stream().filter(c -> c.getId() == req.id).count();
            } else if (req.type != null) {
                // Check for any card of a certain Type
                count = hand.stream().filter(c ->
                        c.getCardTypeONE().equals(req.type) ||
                                c.getCardTypeTWO().equals(req.type)).count();
            }

            if (count < req.amt) return false; // Missing ingredients!
        }
        return true;
    }
    public void consumeIngredients(List<Recipe.Ingredient> required) {
        for (Recipe.Ingredient req : required) {
            int removed = 0;
            Iterator<Card> it = hand.iterator();
            while (it.hasNext() && removed < req.amt) {
                Card c = it.next();
                boolean match = (req.id != null && c.getId() == req.id) ||
                        (req.type != null && (c.getCardTypeONE().equals(req.type) ||
                                c.getCardTypeTWO().equals(req.type)));
                if (match) {
                    it.remove();
                    removed++;
                }
            }
        }
    }

    // Getters for Thymeleaf to display on the UI
    public int getHealth() { return health; }
    public List<Card> getHand() { return hand; }
    public Board getBoard() {return b;}
}