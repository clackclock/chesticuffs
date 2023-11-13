package Game;

public class Modifier {
    private final int am;
    private final int dm;
    public Modifier(int atkMod, int defMod){
        am = atkMod;
        dm = defMod;
    }

    public int addAtkToType(){
        return am;
    }
    public int addDef(){
        return dm;
    }
}
