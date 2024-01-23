package Game;

import java.util.ArrayList;

public class cardDatabase {
    public ArrayList<Card> pack = new ArrayList<>();
    public ArrayList<ComboBuild> formPack = new ArrayList<>();

    public void cardData() {
        pack.add(new Card(-1,"Blank", new int[]{0, 0}, new int[]{0, 0}, new int[]{0, 0}, new int[]{0, 0}, new int[]{0, 0}, "NEUTRAL", "NEUTRAL"));
        pack.add(new Card(0,"Basic_Seeds", new int[]{4, 2}, new int[]{2, 2}, new int[]{2, 3}, new int[]{2, 4}, new int[]{1, 4}, "PLANT", "FARM"));
        pack.add(new Card(1,"Simple_Axe", new int[]{4, 3}, new int[]{3, 3}, new int[]{3, 4}, new int[]{3, 5}, new int[]{2, 4}, "TOOL", "NEUTRAL"));
        pack.add(new Card(2,"Feather", new int[]{3, 1}, new int[]{3, 1}, new int[]{4, 2}, new int[]{3, 3}, new int[]{5, 3}, "ANIMAL", "FARM"));
        pack.add(new Card(3,"Flaccid_Flower", new int[]{2, 1}, new int[]{1, 1}, new int[]{1, 3}, new int[]{1, 3}, new int[]{1, 2}, "FLOWER", "PLANT"));
        pack.add(new Card(4,"Gourd_Seeds", new int[]{1, 4}, new int[]{1, 4}, new int[]{2, 4}, new int[]{2, 4}, new int[]{3, 4}, "PLANT", "FARM"));
        pack.add(new Card(5,"Pumpkin", new int[]{2, 5}, new int[]{2, 5}, new int[]{3, 5}, new int[]{3, 5}, new int[]{3, 5}, "PLANT", "FARM"));
        pack.add(new Card(6,"Apple", new int[]{3, 2}, new int[]{3, 2}, new int[]{2, 3}, new int[]{2, 3}, new int[]{2, 3}, "PLANT", "FARM"));
        pack.add(new Card(7,"Mutton", new int[]{4, 1}, new int[]{3, 2}, new int[]{3, 3}, new int[]{3, 3}, new int[]{2, 3}, "ANIMAL", "FARM"));
        pack.add(new Card(8,"HayBale", new int[]{3, 3}, new int[]{3, 3}, new int[]{2, 4}, new int[]{2, 4}, new int[]{2, 5}, "PLANT", "FARM"));
        pack.add(new Card(9,"Shears", new int[]{3, 1}, new int[]{2, 1}, new int[]{1, 2}, new int[]{1, 2}, new int[]{1, 2}, "ANIMAL", "FARM"));
        pack.add(new Card(10,"Cornflower", new int[]{2, 1}, new int[]{2, 1}, new int[]{1, 1}, new int[]{1, 1}, new int[]{1, 1}, "FLOWER", "FARM"));
        pack.add(new Card(11,"Sapling", new int[]{3, 1}, new int[]{2, 1}, new int[]{1, 2}, new int[]{1, 2}, new int[]{1, 2}, "PLANT", "WOOD"));
        pack.add(new Card(12,"Door", new int[]{2, 1}, new int[]{1, 2}, new int[]{1, 2}, new int[]{1, 3}, new int[]{1, 3}, "ITEM", "WOOD"));
        pack.add(new Card(14,"Jeweled_Axe", new int[]{5,1}, new int[]{5,1}, new int[]{4,2}, new int[]{4,2}, new int[]{4,2}, "TOOL", "LUCKY"));
        pack.add(new Card(15,"Simple Spade", new int[]{2,	3}, new int[]{2,3}, new int[]{3,3}, new int[]{3,4}, new int[]{3,2},	"TOOL", "NEUTRAL"));
        pack.add(new Card(16,"Fishing_Rod",new int[]{1,1},new int[]{1,1},new int[]{1,1},new int[]{1,1},new int[]{1,1},"ITEM","TOOL"));
        pack.add(new Card(19,"Iron",	new int[]{3,3}, new int[]{3,3}, new int[]{3,3}, new int[]{3,3}, new int[]{3,3},	"METAL", "NEUTRAL"));
        pack.add(new Card(20,"Iron_Ore", new int[]{1,1}, new int[]{2,2}, new int[]{3,3}, new int[]{2,2}, new int[]{1,1},"ORE","METAL"));
        pack.add(new Card(21,"Planks",	new int[]{3,2}, new int[]{3,2}, new int[]{2,3}, new int[]{2,3}, new int[]{2,3},"PLANT","WOOD"));
        pack.add(new Card(22,"Water_Bucket", new int[]{2,2},new int[]{1,2},new int[]{2,3},new int[]{2,	4}, new int[]{3,3},"ITEM","FARM"));
        pack.add(new Card(23,"Gold", new int[]{3,3}, new int[]	{2,	2}, new int[]{3,3}, new int[]{2,2}, new int[]{3,3},	"METAL", "NEUTRAL"));
        pack.add(new Card(24,"Anvil", new int[]{3,	4}, new int[]{4,3}, new int[]{3,4}, new int[]{3,4}, new int[]{3,3}, "TOOL", "METAL"));
        pack.add(new Card(25,"NameTag",new int[]{1,2},new int[]{1,2},new int[]{1,2},new int[]{1,2},new int[]{1,2},"ITEM","NEUTRAL"));
        pack.add(new Card(28,"Furnace", new int[]{2,4}, new int[]{2,3}, new int[]{3,4}, new int[]{4,4}, new int[]{3,3},"TOOL","NEUTRAL"));
        pack.add(new Card(29,"Trident", new int[]{3,3}, new int[]{3,3}, new int[]{3,3}, new int[]{3,3}, new int[]{3,3}, "WATER", "TOOL"));
        pack.add(new Card(30,"Healing_Potion",new int[]{0,1},new int[]{0,1},new int[]{1,3},new int[]{1,3},new int[]{2,3},"ITEM","NEUTRAL"));
        pack.add(new Card(31,"Wheat", new int[]{2,4}, new int[]{2,4}, new int[]{2,4}, new int[]{2,	4}, new int[]{2,3},"PLANT","FARM"));
        pack.add(new Card(32,"Coral", new int[]{3,3}, new int[]{2,3}, new int[]{3,4}, new int[]{4,4}, new int[]{4,5},"WATER","PLANT"));
        pack.add(new Card(33,"Kelp", new int[]{2,4}, new int[]{3,4}, new int[]{3,3}, new int[]{2,3}, new int[]{4,4},"WATER","PLANT"));
        pack.add(new Card(35,"Aquamarine", new int[]{2,3}, new int[]{3,3}, new int[]{3,3}, new int[]{4,4}, new int[]{4,5},"WATER","METAL"));
        pack.add(new Card(36,"Gold_Ore", new int[]{2,2}, new int[]{1,1}, new int[]{2,2}, new int[]{2,2}, new int[]{2,2},"ORE", "NEUTRAL"));
        pack.add(new Card(37,"Poppy", new int[]{2,2}, new int[]{2,2}, new int[]{2,2}, new int[]{1,1}, new int[]{2,2},"FLOWER", "NEUTRAL"));
        pack.add(new Card(38,"Dandelion",new int[]{3,2},new int[]{3,2},new int[]{2,2},new int[]{2,1},new int[]{3,3},"FLOWER", "NEUTRAL"));
        pack.add(new Card(39,"Raw_Steak",new int[]{3,4},new int[]{2,3},new int[]{3,4},new int[]{2,4},new int[]{3,3},"ANIMAL","FARM"));
        pack.add(new Card(40,"Wool",new int[]{2,2},new int[]{1,2},new int[]{3,	3},new int[]{2,4},new int[]{3,4},"FARM","ANIMAL"));
        pack.add(new Card(41,"Newton's_Apple",	new int[]{4,2},new int[]{3,1},new int[]{3,1},new int[]{4,4},new int[]{1,1},"PLANT","LUCKY"));

    }
    public void formData(){
        formPack.add(new ComboBuild(17,"Tree", new int[]{2,	4},	"PLANT"));
        formPack.add(new ComboBuild(18,"Flower_Bush", new int[]{2,2},"FLOWER"));
        formPack.add(new ComboBuild(26,"Item_Frame", new int[]{2,2},"TOOL"));
        formPack.add(new ComboBuild(27,"Stairs", new int[]{1,1},"TOOL"));
        formPack.add(new ComboBuild(34,"Ocean_Monument",new int[]{3,3},"WATER"));
        formPack.add(new ComboBuild(42,"Apple_Pie", new int[]{5,5},"NEUTRAL"));
    }

}
