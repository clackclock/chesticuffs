package Game;

import java.util.HashMap;

import Game.Slots.Board.*;

public class Card {
    private final String cardName;
    private final int id;

    private final String typeONE;
    private final String typeTWO;

    private final int atkMod;
    private final int defMod;
    private final HashMap<Position, int[]> positionMap = new HashMap<>();

    public Card(int ID, String name, int[] uAtk, int[] atk, int[] cDef, int[] corn, int[] def, String prime, String secondary, int modA, int modD) {
        cardName = name;
        id = ID;

        positionMap.put(positions.UBER, uAtk);
        positionMap.put(positions.ATTACK, atk);
        positionMap.put(positions.CoreDEFENCE, cDef);
        positionMap.put(positions.CORE, corn);
        positionMap.put(positions.DEFENCE, def);

        typeONE = prime;
        typeTWO = secondary;

        atkMod = modA;
        defMod = modD;
    }

    //for individual number stat calls *edit for type calls
    public int[] getValue(Position boardPos){
        return positionMap.get(boardPos);
    }

    public String activeTypeOne() {
        return typeONE;
    }
    public String activeTypeTwo() {
        return typeTWO;
    }

    public String toString() {
        return id + " " + cardName + " " + typeONE + " " + typeTWO;

    }
}
