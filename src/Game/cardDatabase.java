package Game;

import java.util.ArrayList;

public class cardDatabase {
    public ArrayList<Card> pack = new ArrayList<>();

    public void cardData() {
        pack.add(new Card(-1, "Blank", new int[]{0, 0}, new int[]{0, 0}, new int[]{0, 0}, new int[]{0, 0}, new int[]{0, 0}, "none", "none"));
        pack.add(new Card(0, "Basic_Seeds", new int[]{4, 2}, new int[]{2, 2}, new int[]{2, 3}, new int[]{2, 4}, new int[]{1, 4}, "plant", "farming"));
        pack.add(new Card(1, "Simple_Axe", new int[]{4, 3}, new int[]{3, 3}, new int[]{3, 4}, new int[]{3, 5}, new int[]{2, 4}, "tool", "none"));
        pack.add(new Card(2, "Feather", new int[]{3, 1}, new int[]{3, 1}, new int[]{4, 2}, new int[]{3, 3}, new int[]{5, 3}, "animal", "farming"));
        pack.add(new Card(3, "Flaccid_Flower", new int[]{2, 1}, new int[]{1, 1}, new int[]{1, 3}, new int[]{1, 3}, new int[]{1, 2}, "flower", "plant"));
        pack.add(new Card(4, "Pumkin Seeds", new int[]{1, 4}, new int[]{1, 4}, new int[]{2, 4}, new int[]{2, 4}, new int[]{3, 4}, "plant", "farming"));
        pack.add(new Card(5, "Pumkin", new int[]{2, 5}, new int[]{2, 5}, new int[]{3, 5}, new int[]{3, 5}, new int[]{3, 5}, "plant", "farming"));
        pack.add(new Card(6, "Apple", new int[]{3, 2}, new int[]{3, 2}, new int[]{2, 3}, new int[]{2, 3}, new int[]{2, 3}, "plant", "farming"));
        pack.add(new Card(7, "Mutton", new int[]{4, 1}, new int[]{3, 2}, new int[]{3, 3}, new int[]{3, 3}, new int[]{2, 3}, "animal", "farming"));
        pack.add(new Card(8, "Haybale", new int[]{3, 3}, new int[]{3, 3}, new int[]{2, 4}, new int[]{2, 4}, new int[]{2, 5}, "plant", "farming"));
        pack.add(new Card(9, "Shears", new int[]{3, 1}, new int[]{2, 1}, new int[]{1, 2}, new int[]{1, 2}, new int[]{1, 2}, "animal", "farming"));
        pack.add(new Card(10, "Cornflower", new int[]{2, 1}, new int[]{2, 1}, new int[]{1, 1}, new int[]{1, 1}, new int[]{1, 1}, "flower", "farming"));
        pack.add(new Card(11, "Sapling", new int[]{3, 1}, new int[]{2, 1}, new int[]{1, 2}, new int[]{1, 2}, new int[]{1, 2}, "plant", "wood"));
    }

}
