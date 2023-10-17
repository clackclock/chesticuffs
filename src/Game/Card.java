package Game;

import java.util.HashMap;

import Game.Slots.Board.*;

public class Card {
    public enum Modifier{
        atkMod(0), defMod(0), noSkill(0);
        private int mod;
        Modifier(int modNum){
            modNum = mod;
        }


    }

    private final String cardName;
    private final int id;

    private final String typeONE;
    private final String typeTWO;
    private final HashMap<Position, int[]> positionMap = new HashMap<>();

    public Card(int ID, String name, int[] uAtk, int[] atk, int[] cDef, int[] corn, int[] def, String prime, String secondary) {
        cardName = name;
        id = ID;

        positionMap.put(positions.UBER, uAtk);
        positionMap.put(positions.ATTACK, atk);
        positionMap.put(positions.CoreDEFENCE, cDef);
        positionMap.put(positions.CORE, corn);
        positionMap.put(positions.DEFENCE, def);

        typeONE = prime;
        typeTWO = secondary;
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
        return id + " " + cardName + typeONE + " " + typeTWO;

    }
}
