package Game;

import java.util.HashMap;

import Game.Slots.Board.*;
import static Game.Slots.Board.Board_Positions.*;

public class Card {
    public enum Card_Type{
        PLANT, WATER, TOOL, FLOWER, FARM, ANIMAL, METAL, ORE, ITEMS, WOOD, LUCKY, NEUTRAL;
    }
    private final String cardName;
    private final int id;

    private final Card_Type typeONE;
    private final Card_Type typeTWO;

    private final HashMap<Board_Positions, int[]> positionMap = new HashMap<>();

    public Card(int ID, String name, int[] uAtk, int[] atk, int[] cDef, int[] corn, int[] def, Card_Type prime, Card_Type secondary) {
        cardName = name;
        id = ID;

        positionMap.put(UBER, uAtk);
        positionMap.put(ATTACK, atk);
        positionMap.put(CoreDEFENCE, cDef);
        positionMap.put(CORE, corn);
        positionMap.put(DEFENCE, def);

        typeONE = prime;
        typeTWO = secondary;

    }

    //for individual number stat calls *edit for type calls
    public int[] getValue(Board_Positions boardPos){
        return positionMap.get(boardPos);
    }

    public Card_Type activeTypeOne() { return typeONE; }
    public Card_Type activeTypeTwo() {
        return typeTWO;
    }
    public int getId(){
        return id;
    }

    public String toString() {
        return id + " " + cardName + " " + typeONE + " " + typeTWO + " ";

    }
}
