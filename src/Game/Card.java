package Game;

import java.util.HashMap;

import Game.Slots.Board.*;
import static Game.Slots.Board.Board_Positions.*;

public class Card {
    private final String cardName;
    private final int id;
    private boolean item = false;
    private final String cardTypeONE;
    private final String cardTypeTWO;

    private final HashMap<Board_Positions, int[]> positionMap = new HashMap<>();

    public Card(int ID, String name, int[] uAtk, int[] atk, int[] cDef, int[] corn, int[] def, String prime, String secondary) {
        cardName = name;
        id = ID;

        positionMap.put(UBER, uAtk);
        positionMap.put(ATTACK, atk);
        positionMap.put(CoreDEFENCE, cDef);
        positionMap.put(CORE, corn);
        positionMap.put(DEFENCE, def);

        cardTypeONE = prime;
        cardTypeTWO = secondary;
    }
    //for individual number stat calls *edit for type calls
    public int[] getValue(Board_Positions boardPos){
        return positionMap.get(boardPos);
    }

    public String activeTypeOne() { return cardTypeONE; }
    public String activeTypeTwo() { return cardTypeTWO; }
    public void useAsItem(){ item = true; } // if item is true use item mods in calculation
    public boolean isItem(){ return item; }

    public int getId(){ return id; }
    public String toString() {
        return id + " " + cardName + " " + cardTypeONE + " " + cardTypeTWO + " ";

    }
}
