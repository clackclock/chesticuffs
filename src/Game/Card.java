package Game;

import java.util.Arrays;
public class Card {
    private final String cardName;
    private final int id;
    private final int[] uberAtk;
    //private final int[] uberAtkDEF;
    private final int[] attack;
    //private final int[] attackDEF;
    private final int[] coreDef;
    //private final int[] coreDefDEF;
    private final int[] core;
    //private final int[] coreDEF;
    private final int[] defence;
    //private final int[] defenceDEF;
    private final String typeONE;
    private final String typeTWO;

    public Card(int ID, String name, int[] uAtk, int[] atk, int[] cDef, int[] corn, int[] def, String typeO, String typeT) {
        cardName = name;
        id = ID;
        uberAtk = uAtk;
        //uberAtkDEF = uADef;
        attack = atk;
        //attackDEF = aDef;
        coreDef = cDef;
        //coreDefDEF = cDDef;
        core = corn;
        //coreDEF = cDef;
        defence = def;
        //defenceDEF = dAtk;
        typeONE = typeO;
        typeTWO = typeT;
    }

    //for individual number stat calls
    public int getUberAtk() {
        return uberAtk[0];
    }

    public int getUberDef() {
        return uberAtk[1];
    }

    public int getAtkAtk() {
        return attack[0];
    }

    public int getAtkDef() {
        return attack[1];
    }

    public int getCDefAtk() {
        return coreDef[0];
    }

    public int getCDefDef() {
        return coreDef[1];
    }

    public int getCoreAtk() {
        return core[0];
    }

    public int getCoreDef() {
        return core[1];
    }

    public int getDefAtk() {
        return defence[0];
    }

    public int getDefDef() {
        return defence[1];
    }

    //for board calls
    public int[] getUber() {
        return uberAtk;
    }

    public int[] getAtk() {
        return attack;
    }

    public int[] getCDef() {
        return coreDef;
    }

    public int[] getCore() {
        return core;
    }

    public int[] getDef() {
        return defence;
    }

    public String activeTypeOne() {
        return typeONE;
    }

    public String activeTypeTwo() {
        return typeTWO;
    }

    public String toString() {
        return id + " " + cardName + " " + Arrays.toString(uberAtk) + " " + Arrays.toString(attack) + " " + Arrays.toString(coreDef) + " " + Arrays.toString(core) + " " + Arrays.toString(defence) + " " + typeONE + " " + typeTWO;

    }
}
