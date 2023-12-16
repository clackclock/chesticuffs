package Game;
//import static Game.Card.Card_Type.*;

import java.util.ArrayList;

public class cardDatabase {
    public ArrayList<Card> pack = new ArrayList<>();

    public void cardData() {
        pack.add(new Card(-1, "Blank", new int[]{0, 0}, new int[]{0, 0}, new int[]{0, 0}, new int[]{0, 0}, new int[]{0, 0}, "NEUTRAL", "NEUTRAL"));
        pack.add(new Card(0, "Basic_Seeds", new int[]{4, 2}, new int[]{2, 2}, new int[]{2, 3}, new int[]{2, 4}, new int[]{1, 4}, "PLANT", "FARM"));
        pack.add(new Card(1, "Simple_Axe", new int[]{4, 3}, new int[]{3, 3}, new int[]{3, 4}, new int[]{3, 5}, new int[]{2, 4}, "TOOL", "NEUTRAL"));
        pack.add(new Card(2, "Feather", new int[]{3, 1}, new int[]{3, 1}, new int[]{4, 2}, new int[]{3, 3}, new int[]{5, 3}, "ANIMAL", "FARM"));
        pack.add(new Card(3, "Flaccid_Flower", new int[]{2, 1}, new int[]{1, 1}, new int[]{1, 3}, new int[]{1, 3}, new int[]{1, 2}, "FLOWER", "PLANT"));
        pack.add(new Card(4, "Gourd_Seeds", new int[]{1, 4}, new int[]{1, 4}, new int[]{2, 4}, new int[]{2, 4}, new int[]{3, 4}, "PLANT", "FARM"));
        pack.add(new Card(5, "Pumpkin", new int[]{2, 5}, new int[]{2, 5}, new int[]{3, 5}, new int[]{3, 5}, new int[]{3, 5}, "PLANT", "FARM"));
        pack.add(new Card(6, "Apple", new int[]{3, 2}, new int[]{3, 2}, new int[]{2, 3}, new int[]{2, 3}, new int[]{2, 3}, "PLANT", "FARM"));
        pack.add(new Card(7, "Mutton", new int[]{4, 1}, new int[]{3, 2}, new int[]{3, 3}, new int[]{3, 3}, new int[]{2, 3}, "ANIMAL", "FARM"));
        pack.add(new Card(8, "HayBale", new int[]{3, 3}, new int[]{3, 3}, new int[]{2, 4}, new int[]{2, 4}, new int[]{2, 5}, "PLANT", "FARM"));
        pack.add(new Card(9, "Shears", new int[]{3, 1}, new int[]{2, 1}, new int[]{1, 2}, new int[]{1, 2}, new int[]{1, 2}, "ANIMAL", "FARM"));
        pack.add(new Card(10, "Cornflower", new int[]{2, 1}, new int[]{2, 1}, new int[]{1, 1}, new int[]{1, 1}, new int[]{1, 1}, "FLOWER", "FARM"));
        pack.add(new Card(11, "Sapling", new int[]{3, 1}, new int[]{2, 1}, new int[]{1, 2}, new int[]{1, 2}, new int[]{1, 2}, "PLANT", "WOOD"));
    }

}
